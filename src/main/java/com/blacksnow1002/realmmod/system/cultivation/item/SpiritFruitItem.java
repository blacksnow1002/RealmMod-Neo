package com.blacksnow1002.realmmod.system.cultivation.item;

import com.blacksnow1002.realmmod.system.realm.attachment.RealmData;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import static com.blacksnow1002.realmmod.common.attachment.ModAttachment.REALM_ATTACHMENT;

public class SpiritFruitItem extends Item {
    private final int cultivationGain;

    public SpiritFruitItem(Item.Properties properties, int cultivationGain) {
        super(properties);
        this.cultivationGain = cultivationGain;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (entity instanceof ServerPlayer player) {
            RealmData data = player.getData(REALM_ATTACHMENT);
            data.addCultivation(cultivationGain);

            stack.shrink(1);
            player.sendSystemMessage(Component.literal("§a你吞下靈果，只覺一股清靈之氣湧入丹田！修為 +§b" + cultivationGain + " §7(目前：§e" + data.getCultivation() + "§7/§e" + data.getRequiredCultivation() + "§7)§r"));
        }
        return stack;
    }
}
