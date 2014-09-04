package com.takacomic.rpglife.events.worldguard;

import com.mewin.WGRegionEvents.events.RegionEnterEvent;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.takacomic.rpglife.RPGLife;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;



public class WorldGuardEvents implements Listener
{
    public static RPGLife plugin;

    public WorldGuardEvents(RPGLife plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onRegionEnter(RegionEnterEvent e)
    {
        if(e.getRegion().getFlag(DefaultFlag.PVP).equals(StateFlag.State.DENY) && (RPGLife.combat.contains(e.getPlayer().getName()) || RPGLife.chaotic.contains(e.getPlayer().getName())))
        {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "You " + ChatColor.UNDERLINE + "cannot" + ChatColor.RED + " leave a chaotic zone while in combat.");
        }
    }

    @EventHandler
    public void onNoDamage(EntityDamageByEntityEvent e)
    {
        if(e.getEntity() instanceof LivingEntity)
        {
            LivingEntity le = (LivingEntity) e.getEntity();
            if(plugin.wg != null && plugin.wg.getRegionManager(le.getWorld()) != null && plugin.wg.getRegionManager(Bukkit.getServer().getWorld("world")).getApplicableRegions(le.getLocation()) != null)
            {
                ApplicableRegionSet set = plugin.wg.getRegionManager(le.getWorld()).getApplicableRegions(le.getLocation());

                if (!set.allows(DefaultFlag.PVP))
                {
                    e.setDamage(0D);
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onNoDamager(EntityDamageByEntityEvent e)
    {
        if(e.getDamager() instanceof LivingEntity)
        {
            LivingEntity le = (LivingEntity) e.getDamager();
            World world = le.getWorld();
            ApplicableRegionSet set = plugin.wg.getRegionManager(world).getApplicableRegions(le.getLocation());

            if(!set.allows(DefaultFlag.PVP))
            {
                e.setDamage(0D);
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onCombatEnter(PlayerMoveEvent e)
    {
        Player p = e.getPlayer();

        if(plugin.combat.contains(p.getName()) || plugin.chaotic.contains(p.getName()))
        {
            int fromX = (int)e.getFrom().getX();
            int fromY = (int)e.getFrom().getY();
            int fromZ = (int)e.getFrom().getZ();
            int toX = (int)e.getTo().getX();
            int toY = (int)e.getTo().getY();
            int toZ = (int)e.getTo().getZ();

            if(fromX != toX || fromY != toY || fromZ != toZ)
            {
                if(plugin.wg != null && plugin.wg.getRegionManager(p.getWorld()) != null && plugin.wg.getRegionManager(p.getWorld()).getApplicableRegions(p.getLocation()) != null)
                {
                    ApplicableRegionSet set = plugin.wg.getRegionManager(e.getTo().getWorld()).getApplicableRegions(e.getTo());

                    if(!set.allows(DefaultFlag.PVP))
                    {
                        org.bukkit.util.Vector unitVector = p.getLocation().toVector().subtract(e.getTo().toVector()).normalize();
                        p.setVelocity(unitVector.multiply(1.5D).setY(0));
                    }
                }
            }
        }
    }

    @EventHandler
    public void onEnterMessage(RegionEnterEvent e)
    {
        Player p = e.getPlayer();

        if(e.getRegion().getFlag(DefaultFlag.PVP) == StateFlag.State.DENY)
        {
            p.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + "              *** SAFE ZONE (PVP-ON) ***");
        }
        else
        {
            p.sendMessage("" + ChatColor.RED + ChatColor.BOLD + "              *** CHAOTIC ZONE (PVP-ON) ***");
        }
    }
}
