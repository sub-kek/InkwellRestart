package io.inkwellmc.restart.command;

import io.inkwellmc.restart.RestartPlugin;
import io.inkwellmc.restart.util.Formatter;
import io.inkwellmc.restart.util.SubCommand;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RestartCommand implements CommandExecutor, TabCompleter {
    private final RestartPlugin plugin = RestartPlugin.getInstance();
    private final ArrayList<SubCommand> subCommands = new ArrayList<>();

    public RestartCommand() {
        subCommands.add(new SubCommand("inkwellrestart.command.restart", "make"));
        subCommands.add(new SubCommand("inkwellrestart.command.reload", "reload"));
    }

    @Override
    @SuppressWarnings("all")
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (args[0]) {
            case "make" -> {
                if (!sender.hasPermission("inkwellrestart.command.make")) {
                    Component text = plugin.miniMessage.deserialize("<gradient:red:dark_red>Нет прав сучка ;)");
                    sender.sendMessage(text);
                    return true;
                }

                for (Player serverPlayer : plugin.getServer().getOnlinePlayers()) {
                    serverPlayer.sendMessage(
                            plugin.miniMessage.deserialize(
                                    Formatter.format(
                                            plugin.getConfig().getString("restart-msg"),
                                            String.valueOf(plugin.getConfig().getInt("restart-delay")/20)
                                    )
                            )
                    );
                }
                plugin.getServer().getScheduler().runTaskLater(
                        plugin,
                        () -> {
                            plugin.getServer().dispatchCommand(
                                    plugin.getServer().getConsoleSender(),
                                    plugin.getConfig().getString("restart-cmd")
                            );
                        },
                        plugin.getConfig().getInt("restart-delay")
                );
                return true;
            }
            case "reload" -> {
                if (!sender.hasPermission("inkwellrestart.command.reload")) {
                    Component text = plugin.miniMessage.deserialize("<gradient:red:dark_red>Нет прав сучка ;)");
                    sender.sendMessage(text);
                    return true;
                }
                plugin.reloadConfig();
                Component text = plugin.miniMessage.deserialize("<green>Конфигурация перезагружена");
                sender.sendMessage(text);
                return true;
            }
        }
        return true;
    }

    @Override
    @SuppressWarnings("all")
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> arguments = new ArrayList<>();

        for (SubCommand subCommand : subCommands) {
            if (sender.hasPermission(subCommand.permission())) arguments.add(subCommand.label());
        }

        return arguments;
    }
}