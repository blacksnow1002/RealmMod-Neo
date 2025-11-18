package com.blacksnow1002.realmmod.system.realm;

import com.blacksnow1002.realmmod.system.realm.attachment.CultivationData;
import net.minecraft.world.entity.player.Player;

import static com.blacksnow1002.realmmod.common.attachment.ModAttachment.CULTIVATION_ATTACHMENT;

public class CultivationLogicHandler {
    Player player;

    CultivationData cultivationData = player.getData(CULTIVATION_ATTACHMENT.get());

    public void test() {
        cultivationData.addCultivation(10);
    }
}
