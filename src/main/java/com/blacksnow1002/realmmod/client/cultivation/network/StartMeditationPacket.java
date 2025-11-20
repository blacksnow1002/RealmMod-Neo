package com.blacksnow1002.realmmod.client.cultivation.network;

import com.blacksnow1002.realmmod.RealmMod;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record StartMeditationPacket(String name, Integer number) implements CustomPacketPayload {

    public  static final CustomPacketPayload.Type<StartMeditationPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(RealmMod.MOD_ID, "start_meditation_packet"));

    public static final StreamCodec<RegistryFriendlyByteBuf, StartMeditationPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            StartMeditationPacket::name,
            ByteBufCodecs.VAR_INT,
            StartMeditationPacket::number,
            StartMeditationPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
