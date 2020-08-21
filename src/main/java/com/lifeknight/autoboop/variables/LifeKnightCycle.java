package com.lifeknight.autoboop.variables;

import net.minecraft.util.EnumChatFormatting;

import java.util.List;

import static com.lifeknight.autoboop.mod.Core.configuration;

public class LifeKnightCycle extends LifeKnightVariable {
    private final List<String> defaultValues;
    private final int defaultCurrentValue;
    private List<String> values;
    private int currentValue;

    public LifeKnightCycle(String name, String group, List<String> values, int currentValue) {
        super(name, group);
        this.defaultValues = values;
        this.defaultCurrentValue = currentValue;
        this.values = values;
        this.currentValue = currentValue;
    }

    public LifeKnightCycle(String name, String group, List<String> values) {
        this(name, group, values, 0);
    }

    public String getCurrentValueString() {
        return this.currentValue >= 0 && this.currentValue < this.values.size() ? this.values.get(this.currentValue) : "";
    }

    public Integer getValue() {
        return this.currentValue;
    }

    public String next() {
        this.currentValue = this.currentValue == this.values.size() - 1 ? 0 : this.currentValue + 1;
        if (configuration != null) {
            configuration.updateConfigurationFromVariables();
            this.onValueChange();
        }
        return getCurrentValueString();
    }

    public String previous() {
        this.currentValue = this.currentValue == 0 ? this.values.size() - 1 : this.currentValue - 1;
        if (configuration != null) {
            configuration.updateConfigurationFromVariables();
            this.onValueChange();
        }
        return getCurrentValueString();
    }

    @SuppressWarnings("UnusedReturnValue")
    public String setCurrentValue(int newValue) {
        if (!(newValue > values.size() - 1)) {
            currentValue = newValue;
        }
        if (configuration != null) {
            configuration.updateConfigurationFromVariables();
            this.onSetCurrentValue();
        }
        return this.getCurrentValueString();
    }

    public void clearValues() {
        this.currentValue = -1;
        this.values.clear();
        this.onClearValues();
    }

    public void onValueChange() {}

    public void onSetCurrentValue() {}

    public void onClearValues() {}

    @Override
    public void reset() {
        this.values = this.defaultValues;
        this.currentValue = this.defaultCurrentValue;
    }

    @Override
    public String getCustomDisplayString() {
        if (this.iCustomDisplayString != null) {
            return this.iCustomDisplayString.customDisplayString();
        }
        return this.name + ": " + EnumChatFormatting.YELLOW + this.getCurrentValueString();
    }
}
