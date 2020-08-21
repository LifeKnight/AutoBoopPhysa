package com.lifeknight.autoboopphysa.variables;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import net.minecraft.util.EnumChatFormatting;
import com.lifeknight.autoboopphysa.utilities.Chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.lifeknight.autoboopphysa.mod.Core.configuration;

public abstract class LifeKnightObjectList extends LifeKnightVariable {
    private final List<LifeKnightObject> defaultValues;
    private List<LifeKnightObject> values = new ArrayList<>();
    private boolean isIndependent = true;

    public LifeKnightObjectList(String name, String group) {
        super(name, group);
        this.defaultValues = new ArrayList<>();
    }

    public LifeKnightObjectList(String name, String group, List<LifeKnightObject> value) {
        super(name, group);
        this.defaultValues = value;
    }

    @Override
    public List<LifeKnightObject> getValue() {
        return this.values;
    }

    public void setValues(List<LifeKnightObject> values) {
        this.values = values;
        configuration.updateConfigurationFromVariables();
        this.onSetValue();
    }

    public void addElement(LifeKnightObject element) throws IOException {
        if (!this.values.contains(element)) {
            this.values.add(element);
            configuration.updateConfigurationFromVariables();
            this.onAddElement();
        } else {
            throw new IOException(this.name + " already contains " + element + "!");
        }
    }

    public void removeElement(LifeKnightObject element) throws IOException {
        if (element == null) throw new IOException("Cannot remove null element!");
        if (this.values.contains(element)) {
            this.values.remove(element);
            configuration.updateConfigurationFromVariables();
            this.onRemoveElement();
        } else {
            throw new IOException(this.name + " does not contain " + element + "!");
        }
    }

    public void removeByDisplayString(String displayString) {
        for (LifeKnightObject element : this.values) {
            if (element.getCustomDisplayString().equals(displayString)) {
                try {
                    this.removeElement(element);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                break;
            }
        }
    }

    public void clear() {
        this.values.clear();
        configuration.updateConfigurationFromVariables();
        this.onClear();
    }

    public void setValueFromJsonArray(JsonArray jsonElements) {
        for (JsonElement element : jsonElements) {
            try {
                LifeKnightObject procuredElement = fromString(element.toString());
                this.values.add(procuredElement);
            } catch (Exception exception) {
                Chat.queueChatMessageForConnection(EnumChatFormatting.RED + "An error occurred when trying to parse the value of " +
                        EnumChatFormatting.YELLOW + "\"" + element + ".\"" + EnumChatFormatting.RED + " It will not be added to " + name + ".");
            }

            if (this.values.size() == 0) {
                this.values = this.defaultValues;
            }
        }
    }

    public LifeKnightObject fromString(String string) {
        LifeKnightObject lifeKnightObject = getDefault();
        lifeKnightObject.setValueFromJsonObject(new JsonParser().parse(string).getAsJsonObject());
        return lifeKnightObject;
    }

    public JsonArray toJsonArray() {
        JsonArray jsonArray = new JsonArray();

        for (LifeKnightObject element : values) {
            jsonArray.add(element.getAsJsonObject());
        }

        return jsonArray;
    }

    @Override
    public String getCustomDisplayString() {
        if (this.iCustomDisplayString != null) {
            return this.iCustomDisplayString.customDisplayString();
        }
        return "Edit " + name;
    }

    @Override
    public void reset() {
        this.values = this.defaultValues;
    }

    public void onAddElement() {
    }

    public void onRemoveElement() {
    }

    public void onClear() {
    }

    public void onSetValue() {
    }

    public void setIndependent(boolean isIndependent) {
        this.isIndependent = isIndependent;
    }

    public boolean isIndependent() {
        return isIndependent;
    }

    public abstract LifeKnightObject getDefault();
}
