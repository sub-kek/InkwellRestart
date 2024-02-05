package io.inkwellmc.restart.command;

import io.inkwellmc.restart.command.subcommand.MakeSubCommand;
import io.inkwellmc.restart.command.subcommand.ReloadSubCommand;
import io.inkwellmc.restart.util.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class RestartCommand implements CommandExecutor {
    public final ArrayList<SubCommand> subCommands = new ArrayList<>();

    public RestartCommand() {
        subCommands.add(new MakeSubCommand());
        subCommands.add(new ReloadSubCommand());
    }

    @Override
    @SuppressWarnings("all")
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        for (SubCommand subCommand : subCommands) {
            if (args[0].equalsIgnoreCase(subCommand.getName())) {
                subCommand.perform(sender, args);
                return true;
            }
        }
        return false;
    }
}