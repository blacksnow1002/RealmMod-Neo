package com.blacksnow1002.realmmod.system.cultivation.network;

import com.blacksnow1002.realmmod.client.cultivation.network.StartMeditationPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class StartMeditationHandler {
    public static void register(PayloadRegistrar registrar) {
        registrar.playToServer(
                StartMeditationPacket.TYPE,
                StartMeditationPacket.STREAM_CODEC,
                StartMeditationHandler::handleStartMeditation
        );
    }

    private static void handleStartMeditation(StartMeditationPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            player.sendSystemMessage(Component.literal("hello world"));

            String name = packet.name();
            Integer number = packet.number();
        });
    }
}
