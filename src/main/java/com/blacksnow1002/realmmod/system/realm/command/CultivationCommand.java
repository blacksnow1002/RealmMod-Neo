package com.blacksnow1002.realmmod.system.realm.command;

import com.blacksnow1002.realmmod.RealmMod;
import com.blacksnow1002.realmmod.system.realm.BreakthroughLogicHandler;
import com.blacksnow1002.realmmod.system.realm.attachment.CultivationData;

import com.blacksnow1002.realmmod.system.realm.data.BaseRealm;
import com.blacksnow1002.realmmod.system.realm.data.RealmRegistry;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import static com.blacksnow1002.realmmod.common.attachment.ModAttachment.CULTIVATION_ATTACHMENT;

@EventBusSubscriber(modid = RealmMod.MOD_ID)
public class CultivationCommand {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        dispatcher.register(
                Commands.literal("realm")
                        .requires(source -> source.hasPermission(2))
                        .then(Commands.literal("get")
                                .executes(CultivationCommand::getRealm)
                        )
                        .then(Commands.literal("set")
                                .then(Commands.argument("realmId", StringArgumentType.string())
                                        .executes(CultivationCommand::setRealm)
                                )
                        )
                        .then(Commands.literal("breakthrough")
                                .requires(source -> source.hasPermission(0))
                                .executes(CultivationCommand::breakthrough)
                        )
        );

        dispatcher.register(
                Commands.literal("cultivation")
                        .requires(source -> source.hasPermission(2))
                        .then(Commands.literal("add")
                                .then(Commands.argument("amount", IntegerArgumentType.integer(0))
                                        .executes(CultivationCommand::addCultivation)
                                )
                        )
        );
    }


    private static int getRealm(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer player = ctx.getSource().getPlayer();
        if (player == null) return 0;
        CultivationData data = player.getData(CULTIVATION_ATTACHMENT.get());
        BaseRealm realm = RealmRegistry.getRealmById(data.getRealmId());

        player.sendSystemMessage(Component.literal("境界：" + realm.getDisplayName() +
                "\n修為：" + data.getCultivation() + "/" + data.getRequiredCultivation() +
                "\n突破成功率：" + data.getSuccessRate() * 100 + "%" ));

        return 1;
    }

    private static int setRealm(CommandContext<CommandSourceStack> ctx) {
        ServerPlayer player = ctx.getSource().getPlayer();
        if (player == null) return 0;
        CultivationData data = player.getData(CULTIVATION_ATTACHMENT.get());

        String realmId = StringArgumentType.getString(ctx, "realmId");

        data.setRealmId(realmId);

        player.sendSystemMessage(Component.literal("境界已設置為：" + RealmRegistry.getRealmById(realmId).getDisplayName()));
        return 1;
    }

    private static int breakthrough(CommandContext<CommandSourceStack> ctx) {
        ServerPlayer player = ctx.getSource().getPlayer();
        if (player == null) return 0;
        BreakthroughLogicHandler.startBreakthrough(player);

        return 1;
    }

    private static int addCultivation(CommandContext<CommandSourceStack> ctx) {
        ServerPlayer player = ctx.getSource().getPlayer();
        if (player == null) return 0;

        CultivationData data = player.getData(CULTIVATION_ATTACHMENT.get());
        data.addCultivation(IntegerArgumentType.getInteger(ctx, "amount"));

        return 1;
    }
}
