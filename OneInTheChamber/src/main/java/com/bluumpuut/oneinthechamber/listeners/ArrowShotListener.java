package com.bluumpuut.oneinthechamber.listeners;

import com.bluumpuut.oneinthechamber.Oneinthechamber;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.TimeUnit;

public class ArrowShotListener implements Listener {

    private final Oneinthechamber plugin;

    public ArrowShotListener(Oneinthechamber oneInTheChamber){
        this.plugin = oneInTheChamber;
    }

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

                    //SoundEffect voor beide spelers.
                    whoHit.playSound(whoHit.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1.0f, 1.0f);
                    whoWasHit.playSound(whoWasHit.getLocation(), Sound.ENTITY_BAT_DEATH, 1.0f, 1.0f);

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
                nonHitTimer(whoHit);
            }
        }
    }

    public void nonHitTimer(Player p){

        long duration = TimeUnit.SECONDS.toNanos(10);
        long start = System.nanoTime();
        new BukkitRunnable()  {
            public void run() {
                long diff = System.nanoTime()  - start;
                if (diff > duration) {
                    p.setExp(0F);
                    p.getInventory().setItem(1, new ItemStack(Material.ARROW));
                    this.cancel();
                }
                if (!((0F + ((float) diff / (float) duration)) >= 0.99F)) {
                    p.setExp(0F + ((float) diff / (float) duration));
            }
        }
    }.runTaskTimer(plugin, 0, 1);
    }
}
