package com.b1n_ry.gravebound.config;

import com.b1n_ry.gravebound.Gravebound;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = Gravebound.MOD_ID)
public class GraveboundConfig implements ConfigData {
    public static GraveboundConfig getConfig() {
        return AutoConfig.getConfigHolder(GraveboundConfig.class).getConfig();
    }

    public boolean isTreasureOnly = true;
    public boolean isTradeable = true;
    public boolean isDiscoverable = false;
    public boolean removeLevelsWhenUsed = false;
    public int maxLevel = 1;
}
