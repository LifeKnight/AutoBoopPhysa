package com.lifeknight.autoboop.variables;

import com.lifeknight.autoboop.utilities.Text;

import java.util.ArrayList;
import java.util.List;

public abstract class LifeKnightVariable {
    protected static final List<LifeKnightVariable> variables = new ArrayList<>();
    protected final String name;
    protected final String group;
    private boolean storeValue = true;
    private boolean showInLifeKnightGui = true;
    protected ICustomDisplayString iCustomDisplayString = null;

    public LifeKnightVariable(String name, String group) {
        this.name = name;
        this.group = group;
        variables.add(this);
    }

    public static List<LifeKnightVariable> getVariables() {
        return variables;
    }

    public String getName() {
        return this.name;
    }

    public String getNameForConfiguration() {
        if (this.name.contains(" ")) {
            String[] words = this.name.split(" ");
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < words.length; i++) {
                result.append(Text.formatCapitalization(words[i], i != 0));
            }
            return result.toString();
        }
        return Text.formatCapitalization(this.name, false);
    }
    
    public String getGroup() {
        return this.group;
    }

    public String getGroupForConfiguration() {
        if (this.group.contains(" ")) {
            String[] words = this.group.split(" ");
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < words.length; i++) {
                result.append(Text.formatCapitalization(words[i], i != 0));
            }
            return result.toString();
        }
        return Text.formatCapitalization(this.group, false);
    }

    public abstract Object getValue();

    public abstract void reset();

    public boolean isStoreValue() {
        return this.storeValue;
    }

    public void setStoreValue(boolean storeValue) {
        this.storeValue = storeValue;
    }

    public boolean showInLifeKnightGui() {
        return this.showInLifeKnightGui;
    }

    public void setShowInLifeKnightGui(boolean showInLifeKnightGui) {
        this.showInLifeKnightGui = showInLifeKnightGui;
    }

    public String getCustomDisplayString() {
        if (this.iCustomDisplayString != null) {
            return this.iCustomDisplayString.customDisplayString();
        }
        return this.name + ": " + this.getValue();
    }

    public void setiCustomDisplayString(ICustomDisplayString iCustomDisplayString) {
        this.iCustomDisplayString = iCustomDisplayString;
    }

    public interface ICustomDisplayString {
        String customDisplayString(Object... objects);
    }
}
