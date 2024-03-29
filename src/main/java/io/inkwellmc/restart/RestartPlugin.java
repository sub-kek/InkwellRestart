package io.inkwellmc.restart;

import io.inkwellmc.restart.command.RestartCommand;
import io.inkwellmc.restart.command.tabcompleter.RestartCommandTabCompleter;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.java.JavaPlugin;

public class RestartPlugin extends JavaPlugin {
    private static RestartPlugin instance;
    public static RestartPlugin getInstance() {return instance;}

    public MiniMessage miniMessage = MiniMessage.miniMessage();

    @SuppressWarnings("all")
    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        RestartCommand restartCommandExecutor = new RestartCommand();
        getCommand("inkwellrestart").setExecutor(restartCommandExecutor);
        getCommand("inkwellrestart").setTabCompleter(new RestartCommandTabCompleter(restartCommandExecutor));
    }
}
