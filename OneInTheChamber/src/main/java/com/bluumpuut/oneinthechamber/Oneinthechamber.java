package com.bluumpuut.oneinthechamber;

import com.bluumpuut.oneinthechamber.listeners.ArrowShotListener;
import com.bluumpuut.oneinthechamber.listeners.EntityDamageListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Oneinthechamber extends JavaPlugin {

    @Override
    public void onEnable() {
        //Arrow Shot Event
        Bukkit.getPluginManager().registerEvents(new ArrowShotListener(this), this);

        //Entity damage by entity Event
        Bukkit.getPluginManager().registerEvents(new EntityDamageListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
