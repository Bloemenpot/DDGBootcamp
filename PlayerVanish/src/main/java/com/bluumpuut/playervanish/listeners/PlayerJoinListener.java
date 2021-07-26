package com.bluumpuut.playervanish.listeners;

import com.bluumpuut.playervanish.Playervanish;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinListener implements Listener {

    private final Playervanish plugin;

    public PlayerJoinListener(Playervanish playervanish) {
        this.plugin = playervanish;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        event.setJoinMessage(plugin.getMessagePrefix() + "Fantastisch! " + player.getName() + " is nu online!");
        //player.
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

    }
}
