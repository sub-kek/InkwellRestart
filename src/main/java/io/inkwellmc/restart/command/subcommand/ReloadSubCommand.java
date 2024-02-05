package io.inkwellmc.restart.command.subcommand;

import io.inkwellmc.restart.RestartPlugin;
import io.inkwellmc.restart.util.SubCommand;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;

public class ReloadSubCommand implements SubCommand {
    private final RestartPlugin plugin = RestartPlugin.getInstance();

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "reloades config";
    }

    @Override
    public String getSyntax() {
        return "/inkwellrestart reload";
    }

    @Override
    public boolean hasPermissions(CommandSender sender) {
        return sender.hasPermission("inkwellrestart.command.reload");
    }

    @Override
    public boolean canExecute(CommandSender sender) {
        return true;
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (!hasPermissions(sender)) {
            Component text = plugin.miniMessage.deserialize("<gradient:red:dark_red>No permission to use that command!");
            sender.sendMessage(text);
            return;
        }
        plugin.reloadConfig();
        Component text = plugin.miniMessage.deserialize("<green>Конфигурация перезагружена");
        sender.sendMessage(text);
    }
}
