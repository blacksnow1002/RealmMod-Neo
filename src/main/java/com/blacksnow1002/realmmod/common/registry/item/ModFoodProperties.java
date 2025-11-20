package com.blacksnow1002.realmmod.common.registry.item;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class ModFoodProperties {
    private static final Holder<SoundEvent> PANDA_EAT_HOLDER =
            BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.PANDA_EAT);

    public static final FoodProperties SPIRIT_FRUIT = new FoodProperties.Builder().nutrition(0).saturationModifier(0).build();

    public static final Consumable SPIRIT_FRUIT_EFFECT = Consumables.defaultFood()
            .consumeSeconds(0.8f)
            .animation(ItemUseAnimation.EAT)
            .sound(PANDA_EAT_HOLDER)
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 400), 0.35f)).build();

}