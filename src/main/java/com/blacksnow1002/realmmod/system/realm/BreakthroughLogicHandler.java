package com.blacksnow1002.realmmod.system.realm;

import com.blacksnow1002.realmmod.system.realm.attachment.RealmData;
import com.blacksnow1002.realmmod.system.realm.data.BaseRealm;
import com.blacksnow1002.realmmod.system.realm.data.RealmRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.blacksnow1002.realmmod.common.attachment.ModAttachment.REALM_ATTACHMENT;

public class BreakthroughLogicHandler {

    private static final Random random = ThreadLocalRandom.current();

    public static void startBreakthrough(Player player) {
        if (!(player instanceof ServerPlayer serverPlayer)) return;

        RealmData data = player.getData(REALM_ATTACHMENT);

        String realmId = data.getRealmId();
        BaseRealm realm = RealmRegistry.getRealmById(realmId);
        int cultivation = data.getCultivation();
        int requiredCultivation = data.getRequiredCultivation();
        float successRate = data.getSuccessRate();

        if (realm == null) return;

        float cultivationAlter = realm.getCultivationSubtractRate();
        float successRateAlter = realm.getBreakthroughSuccessAddRate();

        if (cultivation <= requiredCultivation) {
            serverPlayer.sendSystemMessage(Component.literal("修為不足，無法突破境界"));
            return;
        }

        if (random.nextFloat() > successRate) {
            serverPlayer.sendSystemMessage(Component.literal("突破境界失敗，損失 " + cultivationAlter * 100 + "% 修為，增加 "+ successRateAlter * 100 + "% 突破成功率，現在成功率為" + (successRate + successRateAlter) * 100 + "%"));
            data.setCultivation((int) (cultivation * cultivationAlter));
            data.setSuccessRate(successRate + successRateAlter);
            return;
        }

        BaseRealm nextRealm = realm.getNextRealm();
        serverPlayer.sendSystemMessage(Component.literal("突破境界成功，恭喜進入 " + nextRealm.getDisplayName()));

        data.setRealmId(nextRealm.getId());
        data.setCultivation(cultivation - requiredCultivation);
        data.setRequiredCultivation(nextRealm.getRequiredCultivation());
        data.setSuccessRate(nextRealm.getBreakthroughSuccessRate());


    }
}
