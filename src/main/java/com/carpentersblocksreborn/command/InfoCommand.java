package com.carpentersblocksreborn.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

public class InfoCommand {
    public static LiteralArgumentBuilder<CommandSource> register() {
        return Commands.literal("cbr info").requires(cs -> cs.hasPermissionLevel(2)).executes(InfoCommand::run);
    }

    private static int run(CommandContext<CommandSource> ctx) {
        throw new CommandException(new StringTextComponent("This command is not supported!"));

    }
}