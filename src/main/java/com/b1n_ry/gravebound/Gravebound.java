package com.b1n_ry.gravebound;

import com.b1n_ry.gravebound.config.GraveboundConfig;
import com.b1n_ry.gravebound.enchantment.GraveboundEnchantment;
import com.b1n_ry.yigd.events.DropRuleEvent;
import com.b1n_ry.yigd.util.DropRule;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.Map;

public class Gravebound implements ModInitializer {
    public static final String MOD_ID = "gravebound";

    public static final GraveboundEnchantment GRAVEBOUND_ENCHANTMENT = new GraveboundEnchantment(Enchantment.Rarity.UNCOMMON);

    public static final TagKey<Item> ENCHANTMENT_BLACKLIST = TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, "enchantment_blacklist"));

    @Override
    public void onInitialize() {
        AutoConfig.register(GraveboundConfig.class, GsonConfigSerializer::new);

        Registry.register(BuiltInRegistries.ENCHANTMENT, new ResourceLocation(MOD_ID, "gravebound"), GRAVEBOUND_ENCHANTMENT);

        DropRuleEvent.EVENT.register((itemStack, i, deathContext, b) -> {
            Map<Enchantment, Integer> enchantmentMap = EnchantmentHelper.getEnchantments(itemStack);
            Integer level = enchantmentMap.get(GRAVEBOUND_ENCHANTMENT);
            if (level != null) {
                if (GraveboundConfig.getConfig().removeLevelsWhenUsed) {
                    if (level <= 1) {
                        enchantmentMap.remove(GRAVEBOUND_ENCHANTMENT);
                    } else {
                        enchantmentMap.replace(GRAVEBOUND_ENCHANTMENT, level - 1);
                    }
                    EnchantmentHelper.setEnchantments(enchantmentMap, itemStack);
                }
                return DropRule.PUT_IN_GRAVE;
            }
            return DropRule.DROP;
        });
    }
}
