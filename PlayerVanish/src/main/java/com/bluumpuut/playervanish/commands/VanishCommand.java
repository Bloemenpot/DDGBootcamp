package com.bluumpuut.playervanish.commands;

import com.bluumpuut.playervanish.Playervanish;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    private final Playervanish plugin;

    public VanishCommand(Playervanish playervanish) {
        this.plugin = playervanish;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Kijkt of de sender een player is of niet.
        if (!(sender instanceof Player)) {
            sender.sendMessage(plugin.getMessagePrefix() + ChatColor.RED + "Je moet een speler zijn om dit commando uit te kunnen voeren!");
            return false;
        }

        Player player = (Player) sender;

        player.sendMessage(plugin.getMessagePrefix() + player.getName() + " staat nu in vanish!");
        player.hidePlayer(plugin, player);
        return true;
    }
}
