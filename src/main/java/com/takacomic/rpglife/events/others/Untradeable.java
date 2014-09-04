package com.takacomic.rpglife.events.others;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class Untradeable implements Listener
{
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e)
    {
        if(e.getItemDrop().getItemStack().getItemMeta().hasLore() && e.getItemDrop().getItemStack().getItemMeta().getLore().contains("Untradeable"))
        {
            e.getItemDrop().remove();
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.FIZZ, 1F,0F);
            e.getPlayer().sendMessage(ChatColor.GRAY + "The untradeable item has " + ChatColor.GRAY + ChatColor.ITALIC +"vanished");
        }
    }

    @EventHandler
    public void onPickUp(PlayerPickupItemEvent e)
    {
        if(e.getItem().getItemStack().getItemMeta().hasLore() && e.getItem().getItemStack().getItemMeta().getLore().contains("Untradeable"))
        {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e)
    {
        if(e.getInventory().getHolder() != e.getWhoClicked())
        {
            if (e.getCurrentItem() != null)
            {
                if (e.getCurrentItem().getItemMeta().hasLore())
                {
                    if (e.getCurrentItem().getItemMeta().getLore().contains("Untradeable"))
                    {
                        e.setCancelled(true);
                        if (e.getWhoClicked() instanceof Player)
                        {
                            Player p = (Player) e.getWhoClicked();
                            p.sendMessage(ChatColor.RED + "You " + ChatColor.RED + ChatColor.UNDERLINE + "cannot" + ChatColor.RED + " bank this item, as it is part of your spawn kit.");
                        }
                    }
                }
            }
        }
    }
}
