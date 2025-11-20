package com.blacksnow1002.realmmod.common.attachment;

import com.blacksnow1002.realmmod.RealmMod;
import com.blacksnow1002.realmmod.system.realm.attachment.RealmData;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachment {

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, RealmMod.MOD_ID);

    public static final Supplier<AttachmentType<RealmData>> REALM_ATTACHMENT =
            ATTACHMENT_TYPES.register("cultivation_data",
                    () -> AttachmentType.builder(() -> new RealmData())
                            .build());
}
