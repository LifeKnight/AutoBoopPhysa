package com.lifeknight.autoboop.variables;


import net.minecraft.util.EnumChatFormatting;

import static com.lifeknight.autoboop.mod.Core.configuration;
import static net.minecraft.util.EnumChatFormatting.GREEN;
import static net.minecraft.util.EnumChatFormatting.RED;

public class LifeKnightBoolean extends LifeKnightVariable {
    private final boolean defaultValue;
    private boolean value;
    private LifeKnightVariable lifeKnightList;

    public LifeKnightBoolean(String name, String group, boolean value) {
        super(name, group);
        this.value = value;
        this.defaultValue = value;
    }

    public LifeKnightBoolean(String name, String group, boolean value, LifeKnightList<?> lifeKnightList) {
        this(name, group, value);
        this.lifeKnightList = lifeKnightList;
        lifeKnightList.setIndependent(false);
    }

    public LifeKnightBoolean(String name, String group, boolean value, LifeKnightObjectList lifeKnightObjectList) {
        this(name, group, value);
        this.lifeKnightList = lifeKnightObjectList;
        lifeKnightObjectList.setIndependent(false);
    }

    public Boolean getDefaultValue() {
        return this.defaultValue;
    }

    public boolean hasList() {
        return this.lifeKnightList != null;
    }

    public LifeKnightVariable getList() {
        return this.lifeKnightList;
    }

    public Boolean getValue() {
        return this.value;
    }

    public void toggle() {
        this.value = !this.value;
        configuration.updateConfigurationFromVariables();
        this.onSetValue();
    }

    public void setValue(boolean newValue) {
        this.value = newValue;
        if (configuration != null) {
            configuration.updateConfigurationFromVariables();
            this.onSetValue();
        }
    }

    public String getAsString() {
        if (this.value) {
            return super.getName() + ": " + GREEN + "ENABLED";
        } else {
            return super.getName() + ": " + RED + "DISABLED";
        }
    }

    public void onSetValue() {}

    @Override
    public void reset() {
        this.value = this.defaultValue;
    }

    @Override
    public String getCustomDisplayString() {
        if (this.iCustomDisplayString != null) {
            return this.iCustomDisplayString.customDisplayString();
        }
        return this.name + ": " + (this.value ? EnumChatFormatting.GREEN + "ENABLED" : EnumChatFormatting.RED + "DISABLED");
    }
}
