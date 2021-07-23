package com.bluumpuut.oneinthechamber.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

public class ArrowShotListener implements Listener {

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Arrow) {
            Projectile arrow = event.getEntity();
            arrow.remove();
            if (event.getEntity().getShooter() instanceof Player) {
                Player whoHit = (Player) event.getEntity().getShooter();
                if (event.getHitEntity() instanceof Player) {
                    Player whoWasHit = (Player) event.getHitEntity();

                    //Geeft beide spelers een title met of ze gehit zijn of een kill hebben gemaakt.
                    whoWasHit.sendTitle(ChatColor.RED + "You died", " ", 1, 15, 1);
                    whoHit.sendTitle(ChatColor.GREEN + "+1 Kill", ChatColor.WHITE + "+1 Arrow", 1, 15, 1);

                    //Geeft de persoon die gehit is zijn arrow terug.
                    whoHit.getInventory().setItem(1, new ItemStack(Material.ARROW));
                    whoWasHit.getInventory().setItem(1, new ItemStack(Material.ARROW));

                    //Teleport de speler die gehit is naar een random gekozen plek
                    int random = (int) (Math.random() * 4 + 1);
                    whoWasHit.sendMessage("[DEBUG] SpawnLoc = " + ChatColor.AQUA + random);
                    if (random == 1) {
                        whoWasHit.teleport(new Location(Bukkit.getWorld("flatworld"), 2.5, 4, 2.5));
                    }
                    if (random == 2) {
                        whoWasHit.teleport(new Location(Bukkit.getWorld("flatworld"), -2.5, 4, 2.5));
                    }
                    if (random == 3) {
                        whoWasHit.teleport(new Location(Bukkit.getWorld("flatworld"), 2.5, 4, -2.5));
                    }
                    if (random == 4) {
                        whoWasHit.teleport(new Location(Bukkit.getWorld("flatworld"), -2.5, 4, -2.5));
                    }
                    return;
                }
                whoHit.getInventory().setItem(1, new ItemStack(Material.ARROW));
            }
        }
    }
}