package net.dzikoysk.wildskript.events;

import net.dzikoysk.wildskript.events.skript.EvtJump;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Objects;

public class PlayerMove implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Block block, control;
        if (Objects.requireNonNull(event.getTo()).getY() > event.getFrom().getY()) {
            block = event.getPlayer().getWorld().getBlockAt(new Location(event.getPlayer().getWorld(), event.getTo().getX(), event.getTo().getY() + 2, event.getTo().getZ()));
            control = event.getPlayer().getWorld().getBlockAt(new Location(event.getPlayer().getWorld(), event.getTo().getX(), event.getTo().getY() - 2, event.getTo().getZ()));
            if (!(block.getType() != Material.AIR || control.getType() == Material.AIR)) {
                EvtJump custom = new EvtJump(event.getPlayer());
                Bukkit.getServer().getPluginManager().callEvent(custom);
                if (custom.isCancelled()) {
                    event.setCancelled(true);
                }
            }
        }
    }

}
