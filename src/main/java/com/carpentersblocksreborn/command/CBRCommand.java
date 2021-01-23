package com.carpentersblocksreborn.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.TranslationTextComponent;

public class CBRCommand {
    public static final SimpleCommandExceptionType NOT_FOUND = new SimpleCommandExceptionType(new TranslationTextComponent("commands.cbr.not_found"));

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> builder = Commands.literal("carpentersblocksreborn")
                .then(InfoCommand.register());

        LiteralCommandNode<CommandSource> node = dispatcher.register(builder);
        dispatcher.register(Commands.literal("cbr").redirect(node));

    }
}
