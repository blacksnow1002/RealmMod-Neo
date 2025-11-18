package com.blacksnow1002.realmmod.common.registers;

import com.blacksnow1002.realmmod.RealmMod;
import com.blacksnow1002.realmmod.database.PlayerDataService;
import com.blacksnow1002.realmmod.system.realm.attachment.CultivationData;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

import static com.blacksnow1002.realmmod.common.attachment.ModAttachment.CULTIVATION_ATTACHMENT;

@EventBusSubscriber(modid = RealmMod.MOD_ID)
public class ModEvent {

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        UUID uuid = player.getUUID();

        CultivationData progressAttachment = player.getData(CULTIVATION_ATTACHMENT.get());

        Optional<CultivationData> loadedData = PlayerDataService.loadPlayerProgress(uuid);

        if (loadedData.isPresent()) {
            CultivationData loadedDataObj = loadedData.get();

            // 3. 核心修正：使用 Setter，將新數據寫入到 progressAttachment 實例中
            // 絕對不能使用 progressAttachment = loadedDataObj;
            progressAttachment.setCultivationRealm(loadedDataObj.getCultivationRealm());
            progressAttachment.setLayer(loadedDataObj.getLayer());
            progressAttachment.setCultivation(loadedDataObj.getCultivation());
            progressAttachment.setSuccessRate(loadedDataObj.getSuccessRate());
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        ServerPlayer player = (ServerPlayer) event.getEntity();
        UUID uuid = player.getUUID();

        CultivationData data = player.getData(CULTIVATION_ATTACHMENT.get());
        if (data != null) {
            String realm = data.getCultivationRealm().name();
            int layer = data.getLayer();
            int cultivation = data.getCultivation();
            float successRate = data.getSuccessRate();

            PlayerDataService.savePlayerProgress(uuid, realm, layer, cultivation, successRate);

            System.out.println("✅ 玩家 [" + player.getName().getString() + "] 修為資料已儲存到 MariaDB。");
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            CultivationData oldData = event.getOriginal().getData(CULTIVATION_ATTACHMENT.get());
            CultivationData newData = event.getEntity().getData(CULTIVATION_ATTACHMENT.get());

            newData.setCultivationRealm(oldData.getCultivationRealm());
            newData.setLayer(oldData.getLayer());
            newData.setCultivation(oldData.getCultivation());
            newData.setSuccessRate(oldData.getSuccessRate());

        }
    }
}
