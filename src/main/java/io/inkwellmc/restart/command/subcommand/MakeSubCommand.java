package io.inkwellmc.restart.command.subcommand;

import io.inkwellmc.restart.RestartPlugin;
import io.inkwellmc.restart.util.Formatter;
import io.inkwellmc.restart.util.SubCommand;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class MakeSubCommand implements SubCommand {
    private final RestartPlugin plugin = RestartPlugin.getInstance();

    @Override
    public String getName() {
        return "make";
    }

    @Override
    public String getDescription() {
        return "makes server restart";
    }

    @Override
    public String getSyntax() {
        return "/inkwellrestart make";
    }

    @Override
    public boolean hasPermissions(CommandSender sender) {
        return sender.hasPermission("inkwellrestart.command.make");
    }

    @Override
    public boolean canExecute(CommandSender sender) {
        return true;
    }

    @Override
    @SuppressWarnings("all")
    public void perform(CommandSender sender, String[] args) {
        if (!hasPermissions(sender)) {
            Component text = plugin.miniMessage.deserialize("<gradient:red:dark_red>No permission to use that command!");
            sender.sendMessage(text);
            return;
        }

        for (Player serverPlayer : plugin.getServer().getOnlinePlayers()) {
            // Show chat message
            String chatStr = plugin.getConfig().getString("restart-msg.chat");
            if (!chatStr.isEmpty())
                serverPlayer.sendMessage(
                        plugin.miniMessage.deserialize(
                                Formatter.format(
                                        chatStr,
                                        String.valueOf(plugin.getConfig().getInt("restart-delay")/20)
                                )
                        )
                );

            // Show action bar message
            String actionBarStr = plugin.getConfig().getString("restart-msg.action-bar");
            if (!actionBarStr.isEmpty())
                serverPlayer.sendActionBar(
                        plugin.miniMessage.deserialize(
                                Formatter.format(
                                        actionBarStr,
                                        String.valueOf(plugin.getConfig().getInt("restart-delay")/20)
                                )
                ));

            // Show title message
            String titleStr = plugin.getConfig().getString("restart-msg.title");
            if (!titleStr.isEmpty())
                serverPlayer.showTitle(
                        Title.title(
                                plugin.miniMessage.deserialize(
                                        Formatter.format(
                                                titleStr,
                                                String.valueOf(plugin.getConfig().getInt("restart-delay")/20)
                                        )
                                ),
                                plugin.miniMessage.deserialize(
                                        Formatter.format(
                                                plugin.getConfig().getString("restart-msg.sub-title"),
                                                String.valueOf(plugin.getConfig().getInt("restart-delay")/20)
                                )
                        )
                ));
        }
        plugin.getServer().getGlobalRegionScheduler().runDelayed(
                plugin,
                (task) -> plugin.getServer().dispatchCommand(
                        plugin.getServer().getConsoleSender(),
                        Objects.requireNonNull(plugin.getConfig().getString("restart-cmd"))
                ),
                plugin.getConfig().getInt("restart-delay")
        );
    }
}
