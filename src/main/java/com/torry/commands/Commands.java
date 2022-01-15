package com.torry.commands;

import com.torry.commands.command.FeedCommand;
import com.torry.commands.command.WildCommand;
import com.torry.commands.tabCompleter.WildTabCompleter;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Commands extends JavaPlugin {

    private static Plugin instance;

    public static Plugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

        System.out.println("Torry's plugin is running!!!!!!!!!");

        saveDefaultConfig();

        instance = this;

        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("wild").setExecutor(new WildCommand());
        getCommand("wild").setTabCompleter(new WildTabCompleter());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
