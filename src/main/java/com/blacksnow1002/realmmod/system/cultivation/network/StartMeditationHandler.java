package com.blacksnow1002.realmmod.system.cultivation.network;

import com.blacksnow1002.realmmod.client.cultivation.network.StartMeditationPacket;
import com.blacksnow1002.realmmod.system.cultivation.MeditationLogicHandler;
import com.blacksnow1002.realmmod.system.player.attachment.PlayerStateData;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import static com.blacksnow1002.realmmod.common.attachment.ModAttachment.PLAYER_STATE_ATTACHMENT;

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

            PlayerStateData data = player.getData(PLAYER_STATE_ATTACHMENT);

            Boolean meditationState = data.getMeditationState();

            if (meditationState == true) {
                MeditationLogicHandler.removeMeditationPlayer(player);
                player.sendSystemMessage(Component.literal("玩家取消打坐"));
            } else {
                MeditationLogicHandler.addMeditationPlayer(player);
                player.sendSystemMessage(Component.literal("玩家開始打坐"));
            }
            data.setMeditationState(!meditationState);
        });
    }
}
