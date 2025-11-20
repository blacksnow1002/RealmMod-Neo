package com.blacksnow1002.realmmod.common.registry.item;


import com.blacksnow1002.realmmod.RealmMod;
import com.blacksnow1002.realmmod.system.cultivation.item.SpiritFruitItem;
import com.blacksnow1002.realmmod.system.cultivation.item.SpiritStoneItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RealmMod.MOD_ID);

    public static final DeferredItem<Item> SPIRIT_STONE_LOW = ITEMS.registerItem("spirit_stone_low",
            properties -> new SpiritFruitItem(properties, 10));
    public static final DeferredItem<Item> SPIRIT_STONE_MIDDLE = ITEMS.registerItem("spirit_stone_middle",
            properties -> new SpiritStoneItem(properties, 100));
    public static final DeferredItem<Item> SPIRIT_STONE_HIGH = ITEMS.registerItem("spirit_stone_high",
            properties -> new SpiritStoneItem(properties, 1000));

    public static final DeferredItem<Item> SPIRIT_FRUIT = ITEMS.registerItem("spirit_fruit",
            properties ->  new SpiritFruitItem(properties.food(ModFoodProperties.SPIRIT_FRUIT, ModFoodProperties.SPIRIT_FRUIT_EFFECT), 100));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}