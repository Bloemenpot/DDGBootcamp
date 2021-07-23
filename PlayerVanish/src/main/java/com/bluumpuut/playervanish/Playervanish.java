package com.bluumpuut.playervanish;

import com.bluumpuut.playervanish.commands.VanishCommand;
import com.bluumpuut.playervanish.listeners.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.LinkedHashSet;

public final class Playervanish extends JavaPlugin {

    private final String messagePrefix = "[DDG] ";

    private final Collection<Player> vanishedPlayers = new LinkedHashSet<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("vanish").setExecutor(new VanishCommand(this));
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);
    }

    public Collection<Player> getVanishedPlayers() {
        return vanishedPlayers;
    }

    public void setPlayerVanished(Player player, boolean vanished) {
        boolean changed = false;

        if (vanished) {
            changed = vanishedPlayers.add(player);
        } else {
            changed = vanishedPlayers.remove(player);
        }
    }


    public String getMessagePrefix() {
        return messagePrefix;
    }
}