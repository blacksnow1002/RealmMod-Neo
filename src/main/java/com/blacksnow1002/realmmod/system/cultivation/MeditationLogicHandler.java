package com.blacksnow1002.realmmod.system.cultivation;

import com.blacksnow1002.realmmod.RealmMod;
import com.blacksnow1002.realmmod.system.cultivation.item.SpiritStoneItem;
import com.blacksnow1002.realmmod.system.realm.attachment.RealmData;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.*;

import static com.blacksnow1002.realmmod.common.attachment.ModAttachment.REALM_ATTACHMENT;

@EventBusSubscriber
public class MeditationLogicHandler {
    private static final Map<UUID, Integer> meditationPlayers = new HashMap<>();
    private static final int CULTIVATION_GAIN = 1;
    private static final int GAIN_INTERVAL = 20;

    private static final AttributeModifier NO_MOVEMENT_MODIFIER = new AttributeModifier(
            ResourceLocation.fromNamespaceAndPath(RealmMod.MOD_ID, "meditation_no_move"),
            -1.0,
            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
    );

    public static void addMeditationPlayer(Player player) {
        meditationPlayers.put(player.getUUID(), 0);

        AttributeInstance speedAttribute = player.getAttribute(Attributes.MOVEMENT_SPEED);
        if (speedAttribute != null && !speedAttribute.hasModifier(NO_MOVEMENT_MODIFIER.id())) {
            speedAttribute.addPermanentModifier(NO_MOVEMENT_MODIFIER);
        }

        AttributeInstance jumpStrength = player.getAttribute(Attributes.JUMP_STRENGTH);
        if (jumpStrength != null && !jumpStrength.hasModifier(NO_MOVEMENT_MODIFIER.id())) {
            jumpStrength.addPermanentModifier(NO_MOVEMENT_MODIFIER);
        }
    }

    public static void removeMeditationPlayer(Player player) {
        meditationPlayers.remove(player.getUUID());

        AttributeInstance speedAttribute = player.getAttribute(Attributes.MOVEMENT_SPEED);
        if (speedAttribute != null ) { speedAttribute.removeModifier(NO_MOVEMENT_MODIFIER.id()); }

        AttributeInstance jumpStrength = player.getAttribute(Attributes.JUMP_STRENGTH);
        if (jumpStrength != null ) { jumpStrength.removeModifier(NO_MOVEMENT_MODIFIER.id()); }
    }

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {
        if (event.getServer().isPaused() || meditationPlayers.isEmpty()) return;

        Iterator<Map.Entry<UUID, Integer>> iterator = meditationPlayers.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<UUID, Integer> entry = iterator.next();
            UUID playerUUID = entry.getKey();
            Integer currentTick = entry.getValue();

            ServerPlayer player = event.getServer().getPlayerList().getPlayer(playerUUID);

            if (player == null || player.isRemoved()) {
                iterator.remove();
                continue;
            }

            currentTick ++;
            if (currentTick >= GAIN_INTERVAL) {
                grantMeditationCultivation(player);
                currentTick -= GAIN_INTERVAL;
            }

            entry.setValue(currentTick);
        }
    }


    private static void grantMeditationCultivation(ServerPlayer player) {
        RealmData data = player.getData(REALM_ATTACHMENT);

        ItemStack itemStack = player.getMainHandItem();
        Item item =  itemStack.getItem();

        int cultivationGain = CULTIVATION_GAIN;
        if (item instanceof SpiritStoneItem spiritStoneItem) {
            cultivationGain = spiritStoneItem.getCultivationValue();
            itemStack.shrink(1);
        }

        data.addCultivation(cultivationGain);
        player.sendSystemMessage(Component.literal("打坐獲得修為 " + cultivationGain));
    }
}
