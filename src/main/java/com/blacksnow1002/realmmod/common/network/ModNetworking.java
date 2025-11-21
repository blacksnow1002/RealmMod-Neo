package com.blacksnow1002.realmmod.common.network;

import com.blacksnow1002.realmmod.RealmMod;
import com.blacksnow1002.realmmod.system.cultivation.network.StartMeditationHandler;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = RealmMod.MOD_ID)
public class ModNetworking {

    @SubscribeEvent
    public static void onRegisterPayloadHandler(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");
        StartMeditationHandler.register(registrar);
    }

}
