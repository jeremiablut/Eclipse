package com.eclipse.client.config;

import com.eclipse.client.ui.EclipseTextArea;

public class ConfigValue {
    public Object custom;
    public final Object normal;
    public ConfigValue(Object custom, final Object normal) {
        this.custom = custom;
        this.normal = normal;
    }

    public void reset(EclipseTextArea e) {
        custom = normal;
        e.setValue(normal.toString());
        ConfigManager.save();
    }
}
