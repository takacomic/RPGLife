package com.takacomic.rpglife.events.others;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerBucketEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class AntiBuild implements Listener
{
    @EventHandler
    public void onBreak(BlockBreakEvent e)
    {
        if(!e.getPlayer().isOp())
        {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e)
    {
        if(!e.getPlayer().isOp())
        {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteractable(PlayerInteractEvent e)
    {
        if(!e.getPlayer().isOp())
        {
            if(e.getAction() == Action.RIGHT_CLICK_BLOCK)
            {
                if(e.getClickedBlock().getType() == Material.ANVIL || e.getClickedBlock().getType() == Material.WORKBENCH || e.getClickedBlock().getType() == Material.FURNACE || e.getClickedBlock().getType() == Material.DROPPER || e.getClickedBlock().getType() == Material.DISPENSER || e.getClickedBlock().getType() == Material.CHEST || e.getClickedBlock().getType() == Material.TRAPPED_CHEST || e.getClickedBlock().getType() == Material.WOOD_BUTTON || e.getClickedBlock().getType() == Material.STONE_BUTTON || e.getClickedBlock().getType() == Material.TRAP_DOOR || e.getClickedBlock().getType() == Material.BREWING_STAND)
                {
                    e.setCancelled(true);

                    if(e.getClickedBlock().getType() == Material.CHEST || e.getClickedBlock().getType() == Material.TRAPPED_CHEST)
                    {
                        e.getPlayer().sendMessage(ChatColor.GRAY + "The chest is locked.");
                    }
                }
            }
            else if(e.getAction() == Action.LEFT_CLICK_BLOCK)
            {
                Block b = e.getClickedBlock();
                BlockFace bf = e.getBlockFace();

                if(b.getRelative(bf).getType() == Material.FIRE)
                {
                    e.setCancelled(true);
                    //e.getPlayer().sendBlockChange(b.getRelative(bf).getLocation(), Material.FIRE, (byte)0);
                }
            }
        }
    }

    @EventHandler
    public void onItemFrameClick(PlayerInteractEntityEvent e)
    {
        if(e.getRightClicked() instanceof ItemFrame && !e.getPlayer().isOp())
        {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemHit(EntityDamageByEntityEvent e)
    {
        if(e.getDamager() instanceof Player && e.getEntity() instanceof ItemFrame || e.getEntity() instanceof Minecart)
        {
            Player p = (Player)e.getDamager();
            if(!p.isOp())
            {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBucket(PlayerBucketEvent e)
    {
        if(!e.getPlayer().isOp())
        {
            e.setCancelled(true);
        }
    }
}
