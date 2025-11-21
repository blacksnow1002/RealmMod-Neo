package com.blacksnow1002.realmmod.client.cultivation.input;

import com.blacksnow1002.realmmod.RealmMod;
import com.blacksnow1002.realmmod.client.ModKeyBindings;
import com.blacksnow1002.realmmod.client.cultivation.network.StartMeditationPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

@EventBusSubscriber(modid = RealmMod.MOD_ID, value = Dist.CLIENT)
public class MeditationInputHandler {
    private static int meditateHoldTicks = 0;

    private static final int HOLD_TIME = 20;

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        boolean holdingKey = ModKeyBindings.MEDITATION_KEY.isDown();

        if (holdingKey) {
            meditateHoldTicks++;
            if (meditateHoldTicks == HOLD_TIME) {
                ClientPacketListener connection = mc.getConnection();
                if (connection == null) return;

                StartMeditationPacket packet = new StartMeditationPacket(true);
                connection.send(packet);
            }
        } else {
            meditateHoldTicks = 0;
        }
    }
}
