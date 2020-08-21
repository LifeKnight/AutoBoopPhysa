package com.lifeknight.autoboop.variables;

import static com.lifeknight.autoboop.mod.Core.configuration;

public abstract class LifeKnightNumber extends LifeKnightVariable {
    protected final Number defaultValue;
    protected final Number defaultMinimumValue;
    protected final Number defaultMaximumValue;
    protected Number value;
    protected Number minimumValue;
    protected Number maximumValue;

    LifeKnightNumber(String name, String group, Number value) {
        super(name, group);
        this.defaultValue = value;
        this.value = value;
        this.defaultMinimumValue = Long.MIN_VALUE;
        this.defaultMaximumValue = Long.MAX_VALUE;
        this.minimumValue = Long.MIN_VALUE;
        this.maximumValue = Long.MAX_VALUE;
    }

    LifeKnightNumber(String name, String group, Number value, Number minimumValue, Number maximumValue) {
        super(name, group);
        this.defaultValue = value;
        this.value = value;
        this.defaultMinimumValue = minimumValue;
        this.defaultMaximumValue = maximumValue;
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
    }

    @Override
    public Number getValue() {
        return this.value;
    }

    public double getAsDouble() {
        return this.value.doubleValue();
    }

    public void setValue(Number newValue) {
        this.value = newValue;
        if (configuration != null) {
            configuration.updateConfigurationFromVariables();
            this.onSetValue();
        }
    }

    public Number getMinimumValue() {
        return this.minimumValue;
    }

    public double getMinimumAsDouble() {
        return this.minimumValue.doubleValue();
    }

    public Number getMaximumValue() {
        return this.maximumValue;
    }

    public double getMaximumAsDouble() {
        return this.maximumValue.doubleValue();
    }

    public void setMinimumValue(Number minimumValue) {
        this.minimumValue = minimumValue;
        this.onSetMinimumValue();
    }

    public void setMaximumValue(Number maximumValue) {
        this.maximumValue = maximumValue;
        this.onSetMaximumValue();
    }

    public void onSetValue() {
    }

    public void onSetMinimumValue() {
    }

    public void onSetMaximumValue() {
    }

    @Override
    public void reset() {
        this.value = this.defaultValue;
        this.maximumValue = this.defaultMaximumValue;
        this.minimumValue = this.defaultMinimumValue;
    }

    @Override
    public String getCustomDisplayString() {
        if (this.iCustomDisplayString != null) {
            return this.iCustomDisplayString.customDisplayString(this.getValue());
        }
        return this.name + ": " + this.value;
    }

    public String getCustomDisplayString(Number value) {
        if (this.iCustomDisplayString != null) {
            return this.iCustomDisplayString.customDisplayString(value);
        }
        return this.name + ": " + value;
    }

    public static class LifeKnightInteger extends LifeKnightNumber {
        public LifeKnightInteger(String name, String group, Integer value) {
            super(name, group, value);
        }

        public LifeKnightInteger(String name, String group, Integer value, Integer minimumValue, Integer maximumValue) {
            super(name, group, value, minimumValue, maximumValue);
        }
        

        @Override
        public Integer getValue() {
            return (Integer) super.getValue();
        }

        @Override
        public void setValue(Number newValue) {
            super.setValue(newValue.intValue());
        }


        public String getCustomDisplayString(Number value) {
            value = value.intValue();
            if (this.iCustomDisplayString != null) {
                return this.iCustomDisplayString.customDisplayString(value);
            }
            return this.name + ": " + value;
        }
    }

    public static class LifeKnightDouble extends LifeKnightNumber {
        public LifeKnightDouble(String name, String group, Double value) {
            super(name, group, value);
        }

        public LifeKnightDouble(String name, String group, Double value, Double minimumValue, Double maximumValue) {
            super(name, group, value, minimumValue, maximumValue);
            this.minimumValue = minimumValue;
            this.maximumValue = maximumValue;
        }

        @Override
        public Double getValue() {
            return (Double) super.getValue();
        }

        @Override
        public void setValue(Number newValue) {
            super.setValue(newValue.doubleValue());
        }

        public String getCustomDisplayString(Number value) {
            value = value.doubleValue();
            if (this.iCustomDisplayString != null) {
                return this.iCustomDisplayString.customDisplayString(value);
            }
            return this.name + ": " + value;
        }
    }

    public static class LifeKnightLong extends LifeKnightNumber {
        public LifeKnightLong(String name, String group, Long value) {
            super(name, group, value);
        }

        public LifeKnightLong(String name, String group, Long value, Long minimumValue, Long maximumValue) {
            super(name, group, value, minimumValue, maximumValue);
            this.minimumValue = minimumValue;
            this.maximumValue = maximumValue;
        }

        @Override
        public Long getValue() {
            return (Long) super.getValue();
        }

        @Override
        public void setValue(Number newValue) {
            super.setValue(newValue.longValue());
        }

        public String getCustomDisplayString(Number value) {
            value = value.longValue();
            if (this.iCustomDisplayString != null) {
                return this.iCustomDisplayString.customDisplayString(value);
            }
            return this.name + ": " + value;
        }
    }

    public static class LifeKnightFloat extends LifeKnightNumber {
        public LifeKnightFloat(String name, String group, Float value) {
            super(name, group, value);
        }

        public LifeKnightFloat(String name, String group, Float value, Float minimumValue, Float maximumValue) {
            super(name, group, value, minimumValue, maximumValue);
            this.minimumValue = minimumValue;
            this.maximumValue = maximumValue;
        }

        @Override
        public Float getValue() {
            return (Float) super.getValue();
        }

        @Override
        public void setValue(Number newValue) {
            super.setValue(newValue.floatValue());
        }

        public String getCustomDisplayString(Number value) {
            value = value.floatValue();
            if (this.iCustomDisplayString != null) {
                return this.iCustomDisplayString.customDisplayString(value);
            }
            return this.name + ": " + value;
        }
    }
}
