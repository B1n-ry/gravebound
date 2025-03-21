package com.b1n_ry.gravebound.enchantment;

import com.b1n_ry.gravebound.Gravebound;
import com.b1n_ry.gravebound.config.GraveboundConfig;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class GraveboundEnchantment extends Enchantment {
    public GraveboundEnchantment(Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, EnchantmentCategory.BREAKABLE, slotTypes);
    }

    @Override
    public boolean isTreasureOnly() {
        return GraveboundConfig.getConfig().isTreasureOnly;
    }
    @Override
    public boolean isTradeable() {
        return GraveboundConfig.getConfig().isTradeable;
    }
    @Override
    public boolean isDiscoverable() {
        return GraveboundConfig.getConfig().isDiscoverable;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return !stack.is(Gravebound.ENCHANTMENT_BLACKLIST);
    }

    @Override
    public int getMaxLevel() {
        return GraveboundConfig.getConfig().maxLevel;
    }
}
