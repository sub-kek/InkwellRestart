package io.inkwellmc.restart.util;

import org.bukkit.command.CommandSender;

public interface SubCommand {
    String getName();
    String getDescription();
    String getSyntax();
    boolean hasPermissions(CommandSender sender);
    boolean canExecute(CommandSender sender);
    void perform(CommandSender sender, String[] args);
}