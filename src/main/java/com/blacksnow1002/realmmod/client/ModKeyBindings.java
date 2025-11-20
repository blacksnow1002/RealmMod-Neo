package com.blacksnow1002.realmmod.client;

import com.blacksnow1002.realmmod.RealmMod;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = RealmMod.MOD_ID, value = Dist.CLIENT)
public class ModKeyBindings {
    public static final KeyMapping MEDITATION_KEY =
            new KeyMapping("key.realmmod.meditate", GLFW.GLFW_KEY_G, KeyMapping.Category.MISC);

    @SubscribeEvent
    public static void registerKeyBindings(RegisterKeyMappingsEvent event) {
        event.register(ModKeyBindings.MEDITATION_KEY);
    }
}
