package com.bluumpuut.oneinthechamber.listeners;


import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageListener implements Listener {

    @EventHandler
    public void entityDamage(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Arrow && event.getEntity() instanceof Player) {
            event.setCancelled(true);
        }
    }

}
