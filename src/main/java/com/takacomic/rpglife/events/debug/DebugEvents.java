package com.takacomic.rpglife.events.debug;

import com.takacomic.rpglife.RPGLife;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DebugEvents implements Listener
{
    public static RPGLife plugin;

    public DebugEvents(RPGLife plugin)
    {
        this.plugin = plugin;
    }
    @EventHandler
    public void onDebugDamage(EntityDamageByEntityEvent e)
    {
        if(e.getDamager()instanceof  Player)
        {
            Player attacker = (Player)e.getDamager();
            int dmg = (int)e.getDamage();
            int health = 0;

            if(e.getEntity() instanceof  Player)
            {
                Player attacked = (Player) e.getEntity();

                if(e.getDamage() <= 0D)
                {
                    return;
                }
                else
                {
                    attacker.sendMessage("              " + ChatColor.RED + dmg + ChatColor.BOLD + " DMG " + ChatColor.RED + "-> " + attacked.getName());
                }
            }
            else if(e.getEntity() instanceof LivingEntity)
            {
                LivingEntity attacked = (LivingEntity) e.getEntity();

                if(attacked.getHealth() - dmg > 0D)
                {
                    health = (int)(attacked.getHealth() - dmg);
                }

                attacked.setCustomNameVisible(true);
                if(!attacked.isDead())
                {
                    //attacked.setCustomName(plugin.getNameHealth(attacked.getHealth(), attacked.getMaxHealth(), attacked.getMetadata("Name").get(0).value().toString()));
                }
                attacker.sendMessage("              " + ChatColor.RED + dmg + ChatColor.BOLD + " DMG " + ChatColor.GOLD + "-> " + ChatColor.RED + attacked.getCustomName() + " [" + health + "]");
            }
            else
            {
                return;
            }
        }
    }
    @EventHandler
    public void onDebugDamage2(EntityDamageByEntityEvent e)
    {
        if(e.getEntity() instanceof Player)
        {
            Player attacked = (Player) e.getEntity();
            int dmg = (int) e.getDamage();
            int health = 0;

            if (e.getDamage() <= 0D)
            {
                return;
            }
            else
            {
                if (attacked.getNoDamageTicks() <= (attacked.getMaximumNoDamageTicks() / 2) && attacked.getHealth() > 0D)
                {
                    if (attacked.getHealth() - dmg > 0D)
                    {
                        health = (int) (attacked.getHealth() - dmg);
                    }

                    attacked.sendMessage("             -" + ChatColor.RED + dmg + ChatColor.BOLD + " HP " + ChatColor.GOLD + "-> " + ChatColor.GREEN + " [" + health + "HP]");
                }
            }
        }
    }
}
