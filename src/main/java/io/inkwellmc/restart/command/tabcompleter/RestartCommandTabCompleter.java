package io.inkwellmc.restart.command.tabcompleter;

import io.inkwellmc.restart.command.RestartCommand;
import io.inkwellmc.restart.util.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class RestartCommandTabCompleter implements TabCompleter {
    private final RestartCommand restartCommand;

    public RestartCommandTabCompleter(RestartCommand restartCommand) {
        this.restartCommand = restartCommand;
    }

    @Override
    @SuppressWarnings("all")
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> arguments = new ArrayList<>();

        for (SubCommand subCommand : restartCommand.subCommands) {
            if (subCommand.hasPermissions(sender)) arguments.add(subCommand.getName());
        }

        return arguments;
    }
}
