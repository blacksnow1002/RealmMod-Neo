package com.blacksnow1002.realmmod.common.registry;

import com.blacksnow1002.realmmod.RealmMod;
import com.blacksnow1002.realmmod.database.PlayerDataService;
import com.blacksnow1002.realmmod.system.realm.attachment.RealmData;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.Optional;
import java.util.UUID;

import static com.blacksnow1002.realmmod.common.attachment.ModAttachment.REALM_ATTACHMENT;

@EventBusSubscriber(modid = RealmMod.MOD_ID)
public class ModEvent {

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        UUID uuid = player.getUUID();

        RealmData progressAttachment = player.getData(REALM_ATTACHMENT.get());

        Optional<RealmData> loadedData = PlayerDataService.loadPlayerProgress(uuid);

        if (loadedData.isPresent()) {
            RealmData loadedDataObj = loadedData.get();

            // 3. 核心修正：使用 Setter，將新數據寫入到 progressAttachment 實例中
            // 絕對不能使用 progressAttachment = loadedDataObj;
            progressAttachment.setRealmId(loadedDataObj.getRealmId());
            progressAttachment.setCultivation(loadedDataObj.getCultivation());
            progressAttachment.setRequiredCultivation(loadedDataObj.getRequiredCultivation());
            progressAttachment.setSuccessRate(loadedDataObj.getSuccessRate());
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        ServerPlayer player = (ServerPlayer) event.getEntity();
        UUID uuid = player.getUUID();

        RealmData data = player.getData(REALM_ATTACHMENT.get());
        if (data != null) {
            String realmId = data.getRealmId();
            int cultivation = data.getCultivation();
            int requiredCultivation = data.getRequiredCultivation();
            float successRate = data.getSuccessRate();

            PlayerDataService.savePlayerProgress(uuid, realmId, cultivation, requiredCultivation, successRate);

            System.out.println("✅ 玩家 [" + player.getName().getString() + "] 修為資料已儲存到 MariaDB。");
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            RealmData oldData = event.getOriginal().getData(REALM_ATTACHMENT.get());
            RealmData newData = event.getEntity().getData(REALM_ATTACHMENT.get());

            newData.setRealmId(oldData.getRealmId());
            newData.setCultivation(oldData.getCultivation());
            newData.setRequiredCultivation(oldData.getRequiredCultivation());
            newData.setSuccessRate(oldData.getSuccessRate());

        }
    }
}
