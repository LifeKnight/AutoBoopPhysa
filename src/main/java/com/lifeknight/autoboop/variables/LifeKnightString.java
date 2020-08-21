package com.lifeknight.autoboop.variables;

import net.minecraft.util.EnumChatFormatting;

import static com.lifeknight.autoboop.mod.Core.configuration;

public class LifeKnightString extends LifeKnightVariable {
    private final String defaultValue;
    private String value;

    public LifeKnightString(String name, String group, String value) {
        super(name, group);
        this.defaultValue = value;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        if (configuration != null) {
            configuration.updateConfigurationFromVariables();
            this.onSetValue();
        }
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public void clear() {
        this.value = "";
        this.onClear();
    }

    public void onSetValue() {}

    public void onClear() {}

    @Override
    public void reset() {
        this.value = this.defaultValue;
    }

    @Override
    public String getCustomDisplayString() {
        if (this.iCustomDisplayString != null) {
            return this.iCustomDisplayString.customDisplayString();
        }
        return this.name + ": " + EnumChatFormatting.YELLOW + this.value;
    }
}
