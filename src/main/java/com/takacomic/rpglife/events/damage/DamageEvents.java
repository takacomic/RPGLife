package com.takacomic.rpglife.events.damage;

import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.takacomic.rpglife.RPGLife;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Random;

public class DamageEvents implements Listener
{
    public static RPGLife plugin;

    public DamageEvents(RPGLife plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onNoDamager(EntityDamageByEntityEvent e)
    {
        if(e.getDamager() instanceof LivingEntity && e.getEntity() instanceof LivingEntity)
        {
            LivingEntity le = (LivingEntity)e.getDamager();
            if(plugin.wg != null && plugin.wg.getRegionManager(le.getWorld()) != null && plugin.wg.getRegionManager(le.getWorld()).getApplicableRegions(le.getLocation()) != null)
            {
                ApplicableRegionSet set = plugin.wg.getRegionManager(le.getWorld()).getApplicableRegions(le.getLocation());
                if(!set.allows(DefaultFlag.PVP))
                {
                    e.setDamage(0D);
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onNoDamage(EntityDamageEvent e)
    {
        if(e.getEntity() instanceof LivingEntity)
        {
            LivingEntity le = (LivingEntity)e.getEntity();
            if(plugin.wg != null && plugin.wg.getRegionManager(le.getWorld()) != null && plugin.wg.getRegionManager(le.getWorld()).getApplicableRegions(le.getLocation()) != null)
            {
                ApplicableRegionSet set = plugin.wg.getRegionManager(le.getWorld()).getApplicableRegions(le.getLocation());
                if(!set.allows(DefaultFlag.PVP))
                {
                    e.setDamage(0D);
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onAntiAuto(EntityDamageByEntityEvent e)
    {
        if(e.getDamager() instanceof Player && e.getEntity() instanceof LivingEntity)
        {
            final LivingEntity le = (LivingEntity)e.getEntity();

            if(plugin.nodamage.contains(le))
            {
                e.setDamage(0D);
            }

            e.setCancelled(true);
            plugin.nodamage.add(le);

            new BukkitRunnable()
            {
                @Override
                public void run()
                {
                    plugin.nodamage.remove(le);
                }
            }.runTaskLater(plugin, 4L);
        }
    }

    @EventHandler
    public void onNoEnergy(EntityDamageByEntityEvent e)
    {
        if(e.getEntity() instanceof LivingEntity && e.getDamager() instanceof Player)
        {
            Player p = (Player)e.getDamager();

            if(p.getExp() <= 0F)
            {
                e.setCancelled(true);
                e.setDamage(0D);
            }
        }
    }

    @EventHandler
    public void onDamageTick(EntityDamageByEntityEvent e)
    {
        if(e.getEntity() instanceof LivingEntity && e.getDamager() instanceof LivingEntity && !(e.getDamager() instanceof Player))
        {
            final LivingEntity le = (LivingEntity)e.getEntity();
            final LivingEntity le2 = (LivingEntity)e.getDamager();

            if(e.getDamage() <= 0D)
            {
                return;
            }
            if(le.getNoDamageTicks() < le.getMaximumNoDamageTicks() / 2)
            {
                Random random = new Random();
                final int tick = random.nextInt(2) + 1;

                /*new BukkitRunnable()
                {
                    @Override
                    public void run()
                    {
                        if(tick == 1)
                        {
                            le.setVelocity(new Vector(le2.getLocation().getX() * 0.25D, 0.175D, le2.getLocation().getZ() * 0.25D));
                        }
                        else
                        {
                            le.setVelocity(new Vector(le2.getLocation().getX() * 0.2D, 0D, le2.getLocation().getZ() * 0.2D));
                        }
                    }
                }.runTaskLater(plugin, 1L);*/
            }
        }
    }

    @EventHandler
    public void onDamagePercent(EntityDamageEvent e)
    {
        if(e.getEntity() instanceof Player)
        {
            if(e.getDamage() <= 0D)
            {
                return;
            }
            else
            {
                Player p = (Player)e.getEntity();

                if(e.getCause().equals(EntityDamageEvent.DamageCause.POISON) || e.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK) || e.getCause().equals(EntityDamageEvent.DamageCause.FIRE) || e.getCause().equals(EntityDamageEvent.DamageCause.LAVA))
                {
                    if((p.getMaxHealth() / 80D) < 1D)
                    {
                        e.setDamage(1D);
                    }
                    else
                    {
                        e.setDamage((e.getDamage() * p.getMaxHealth()) / 80D);
                    }
                }
                else if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL))
                {
                    if(((e.getDamage() * p.getMaxHealth()) / 80D) >= p.getHealth())
                    {
                        e.setDamage(p.getHealth() - 1D);
                    }
                    else if(((e.getDamage() * p.getMaxHealth()) / 80D) < 1D)
                    {
                        e.setDamage(1D);
                    }
                    else
                    {
                        e.setDamage(e.getDamage() * p.getMaxHealth() / 80D);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onArrowKnock(EntityDamageByEntityEvent e)
    {
        if(e.getEntity() instanceof LivingEntity && e.getDamager() instanceof Arrow)
        {
            final LivingEntity le = (LivingEntity)e.getEntity();
            final Arrow a = (Arrow)e.getDamager();

            if(e.getDamage() <= 0D)
            {
                return;
            }
            else
            {
                if(le.getNoDamageTicks() < le.getMaximumNoDamageTicks() / 2)
                {
                    Random random = new Random();
                    final int tick = random.nextInt(2) + 1;

                    new BukkitRunnable()
                    {
                        @Override
                        public void run()
                        {
                            if(tick == 1)
                            {
                                le.setVelocity(new Vector(a.getLocation().getX() * 0.25D, 0.175D, a.getLocation().getZ() * 0.25D));
                            }
                            else
                            {
                                le.setVelocity(new Vector(a.getLocation().getX() * 0.2D, 0D, a.getLocation().getZ() * 0.2D));
                            }
                        }
                    }.runTaskLater(plugin, 1L);
                }
                a.remove();
            }
        }
    }

    @EventHandler
    public void onEnityDamageKnock(EntityDamageByEntityEvent e)
    {
        if(e.getEntity() instanceof LivingEntity && e.getDamager() instanceof Player)
        {
            final LivingEntity le = (LivingEntity)e.getEntity();
            final Player p = (Player)e.getDamager();

            if(e.getDamage() <= 0D)
            {
                return;
            }
                p.setNoDamageTicks(0);
                Random random = new Random();
                final int tick = random.nextInt(2) + 1;

                /*new BukkitRunnable()
                {
                    @Override
                    public void run()
                    {
                        if(tick == 1)
                        {
                            le.setVelocity(new Vector(p.getLocation().getX() * 0.25D, 0.175D, p.getLocation().getZ() * 0.25D));
                        }
                        else
                        {
                            le.setVelocity(new Vector(p.getLocation().getX() * 0.2D, 0D, p.getLocation().getZ() * 0.2D));
                        }
                    }
                }.runTaskLater(plugin, 1L);*/
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent e)
    {
        if(e.getDamager() instanceof Player && e.getEntity() instanceof LivingEntity)
        {
            Player p = (Player)e.getDamager();
            if(e.getDamage() <= 0D)
            {
                return;
            }
            else
            {
                if(p.getItemInHand().getType() != Material.AIR && p.getItemInHand().getItemMeta().hasLore())
                {
                    int minDMG = getMinValueFromLore(p.getItemInHand(), "DMG");
                    int maxDMG = getMaxValueFromLore(p.getItemInHand(), "DMG");
                    Random random = new Random();
                    double trueDMG = random.nextInt(maxDMG + 1) + minDMG;

                    e.setDamage(trueDMG);
                }
            }
        }
    }

    @EventHandler
    public void onWeaponStatus(EntityDamageByEntityEvent e)
    {
        if(e.getDamager() instanceof Player && e.getEntity() instanceof LivingEntity)
        {
            double dmg = e.getDamage();
            Player p = (Player)e.getDamager();
            LivingEntity li = (LivingEntity)e.getEntity();

            if(e.getDamage() <= 0D)
            {
                return;

            }
            else
            {
                if(p.getItemInHand() != null && p.getItemInHand().getType() != Material.AIR && p.getItemInHand().getItemMeta().hasLore())
                {
                    List<String> lore = p.getItemInHand().getItemMeta().getLore();

                    for(String s : lore)
                    {
                        if(s.contains("ICE DMG"))
                        {
                            li.getWorld().playEffect(li.getEyeLocation(), Effect.POTION_BREAK, 8194);
                            li.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 0));
                            double eldmg = getValueFromLore(p.getItemInHand(), "ICE DMG");
                            dmg += eldmg;
                        }
                        else if(s.contains("POISON DMG"))
                        {
                            li.getWorld().playEffect(li.getEyeLocation(), Effect.POTION_BREAK, 8196);
                            li.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 10, 1));
                            double eldmg = getValueFromLore(p.getItemInHand(), "POISON DMG");
                            dmg += eldmg;
                        }
                        else if(s.contains("FIRE DMG"))
                        {
                            li.getWorld().playEffect(li.getEyeLocation(), Effect.POTION_BREAK, 8194);
                            li.setFireTicks(40);
                            double eldmg = getValueFromLore(p.getItemInHand(), "FIRE DMG");
                            dmg += eldmg;
                        }
                        else if(s.contains("PURE DMG"))
                        {
                            li.getWorld().playEffect(li.getEyeLocation(), Effect.POTION_BREAK, 8194);
                            double eldmg = getValueFromLore(p.getItemInHand(), "PURE DMG");
                            dmg += eldmg;
                        }
                        else if(s.contains("CRITICAL HIT"))
                        {
                            li.getWorld().playEffect(li.getEyeLocation(), Effect.EXTINGUISH, 0);
                            int crit = getValueFromLore(p.getItemInHand(), "CRITICAL HIT");
                            Random random = new Random();
                            int drop = random.nextInt(25) + 1;

                            if(drop <= crit)
                            {
                                dmg *= 2D;
                            }
                        }
                        else if(s.contains("LIFE STEAL"))
                        {
                            li.getWorld().playEffect(li.getEyeLocation(), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
                            double base = getValueFromLore(p.getItemInHand(), "LIFE STEAL");
                            double pcnt = base / 100D;
                            int life = 0;

                            if((int)(pcnt * dmg) > 0)
                            {
                                life = (int)(pcnt * dmg);
                            }
                            else
                            {
                                life = 1;
                            }

                            if(p.getHealth() < p.getMaxHealth() - life)
                            {
                                p.setHealth(p.getHealth() + life);
                            }
                            else if(p.getHealth() >= p.getHealth() - life)
                            {
                                p.setHealth(p.getMaxHealth());
                            }

                            p.sendMessage(ChatColor.GREEN + "             +" + life + ChatColor.GREEN + ChatColor.BOLD + " HP " + ChatColor.GRAY + "[" + (int)p.getHealth() + "/" + (int)p.getMaxHealth() + "HP]");
                        }
                    }
                }
            }
            e.setDamage(dmg);
        }
    }

    @EventHandler
    public void onDeathMessageElem(EntityDamageEvent e)
    {
        if(e.getEntity() instanceof Player)
        {
            Player p = (Player)e.getEntity();

            if(e.getDamage() >= p.getHealth() && (p.getNoDamageTicks() <= p.getMaximumNoDamageTicks() /2) && p.getHealth() > 0D)
            {
                if(e.getCause().equals(EntityDamageEvent.DamageCause.LAVA) || e.getCause().equals(EntityDamageEvent.DamageCause.FIRE) || e.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK))
                {
                    Bukkit.getServer().broadcastMessage(p.getDisplayName() + " burned to death.");
                }
            }
        }
    }

    @EventHandler
    public void onDeathMessage(EntityDamageByEntityEvent e)
    {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof LivingEntity)
        {
            Player p = (Player)e.getEntity();
            if ((e.getDamage() >= p.getHealth()) && (p.getNoDamageTicks() <= p.getMaximumNoDamageTicks() / 2) && (p.getHealth() > 0.0D))
            {
                if ((e.getDamager() instanceof Player))
                {
                    Player d = (Player)e.getDamager();
                    if ((d.getItemInHand() != null) && (d.getItemInHand().getType() != Material.AIR))
                    {
                        Bukkit.getServer().broadcastMessage( p.getDisplayName() + ChatColor.WHITE + " was killed by " + ChatColor.RESET + d.getDisplayName() + ChatColor.WHITE + " with a(n) " + d.getItemInHand().getItemMeta() .getDisplayName());
                    }
                    else
                    {
                        Bukkit.getServer().broadcastMessage( p.getDisplayName() + ChatColor.WHITE + " was killed by " + ChatColor.RESET + d.getDisplayName() + ChatColor.WHITE + " with a(n) Air");
                    }
                }
                else if ((e.getDamager() instanceof LivingEntity))
                {
                    LivingEntity l = (LivingEntity)e.getDamager();
                    if (l.getCustomName() != null)
                    {
                        Bukkit.getServer().broadcastMessage( p.getDisplayName() + ChatColor.WHITE + " was killed by a(n) " + ChatColor.RESET + l.getCustomName());
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDamageSound(EntityDamageByEntityEvent e)
    {
        if(e.getDamager() instanceof Player && e.getEntity() instanceof LivingEntity)
        {
            Player p = (Player)e.getDamager();

            if(e.getDamage() <= 0D)
            {
                return;
            }
            else
            {
                p.playSound(p.getLocation(), Sound.HURT_FLESH, 1F, 1F);
            }
        }
    }

    @EventHandler
    public void onArmorDura(EntityDamageEvent e)
    {
        if ((e.getEntity() instanceof Player))
        {
            Player p = (Player)e.getEntity();
            final PlayerInventory i = p.getInventory();
            if (i.getHelmet() != null) {
                i.getHelmet().setDurability((short)0);
            }
            if (i.getChestplate() != null) {
                i.getChestplate().setDurability((short)0);
            }
            if (i.getLeggings() != null) {
                i.getLeggings().setDurability((short)0);
            }
            if (i.getBoots() != null) {
                i.getBoots().setDurability((short)0);
            }
            new BukkitRunnable()
            {
                public void run()
                {
                    if (i.getHelmet() != null) {
                        i.getHelmet().setDurability((short)0);
                    }
                    if (i.getChestplate() != null) {
                        i.getChestplate().setDurability((short)0);
                    }
                    if (i.getLeggings() != null) {
                        i.getLeggings().setDurability((short)0);
                    }
                    if (i.getBoots() != null) {
                        i.getBoots().setDurability((short)0);
                    }
                }
            }.runTaskLater(plugin, 1L);
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e)
    {
        if (((e.getDamager() instanceof Player)) &&
                ((e.getEntity() instanceof LivingEntity)))
        {
            Player p = (Player)e.getDamager();
            if ((p.getItemInHand().getType() == Material.WOOD_SWORD) ||
                    (p.getItemInHand().getType() == Material.WOOD_AXE) ||
                    (p.getItemInHand().getType() == Material.STONE_SWORD) ||
                    (p.getItemInHand().getType() == Material.STONE_AXE) ||
                    (p.getItemInHand().getType() == Material.IRON_SWORD) ||
                    (p.getItemInHand().getType() == Material.IRON_AXE) ||
                    (p.getItemInHand().getType() == Material.DIAMOND_SWORD) ||
                    (p.getItemInHand().getType() == Material.DIAMOND_AXE) ||
                    (p.getItemInHand().getType() == Material.GOLD_SWORD) ||
                    (p.getItemInHand().getType() == Material.GOLD_AXE) ||
                    (p.getItemInHand().getType() == Material.GOLD_PICKAXE))
            {
                p.getInventory().getItemInHand().setDurability((short)0);
                p.updateInventory();
            }
        }
    }

    @EventHandler
    public void onVitSword(EntityDamageByEntityEvent e)
    {
        if(e.getDamager() instanceof Player && e.getEntity() instanceof LivingEntity)
        {
            if(e.getDamage() <= 0D)
            {
                return;
            }
            else
            {
                Player p = (Player)e.getDamager();
                PlayerInventory i = p.getInventory();

                if(p.getItemInHand() != null && p.getItemInHand().getType().name().contains("sword") && p.getItemInHand().getItemMeta().hasLore())
                {
                    double dmg = e.getDamage();
                    double vital = 0D;

                    if(i.getHelmet() != null && i.getHelmet().getItemMeta().hasLore())
                    {
                        int vit = getValueFromLore(i.getHelmet(), "VIT");
                        vital += vit;
                    }
                    if(i.getChestplate() != null && i.getChestplate().getItemMeta().hasLore())
                    {
                        int vit = getValueFromLore(i.getChestplate(), "VIT");
                        vital += vit;
                    }
                    if(i.getLeggings() != null && i.getLeggings().getItemMeta().hasLore())
                    {
                        int vit = getValueFromLore(i.getLeggings(), "VIT");
                        vital += vit;
                    }
                    if(i.getBoots() != null && i.getBoots().getItemMeta().hasLore())
                    {
                        int vit = getValueFromLore(i.getBoots(), "VIT");
                        vital += vit;
                    }
                    if(vital > 0D)
                    {
                        double divide = vital / 7500D;
                        double preset = dmg * divide;
                        int cleanedDMG = (int)(dmg + preset);
                        e.setDamage(cleanedDMG);
                    }
                    else
                    {
                        e.setDamage(dmg);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDpsDamage(EntityDamageByEntityEvent e)
    {
        if(e.getDamager() instanceof Player && e.getEntity() instanceof LivingEntity)
        {
            if(e.getDamage() <= 0D)
            {
                return;
            }
            else
            {
                Player p = (Player)e.getDamager();
                PlayerInventory i = p.getInventory();

                if(p.getItemInHand() != null && p.getItemInHand().getType().name().contains("sword") && p.getItemInHand().getItemMeta().hasLore())
                {
                    double dmg = e.getDamage();
                    double dps = 0D;

                    if(i.getHelmet() != null && i.getHelmet().getItemMeta().hasLore())
                    {
                        int vit = getValueFromLore(i.getHelmet(), "DPS");
                        dps += vit;
                    }
                    if(i.getChestplate() != null && i.getChestplate().getItemMeta().hasLore())
                    {
                        int vit = getValueFromLore(i.getChestplate(), "DPS");
                        dps += vit;
                    }
                    if(i.getLeggings() != null && i.getLeggings().getItemMeta().hasLore())
                    {
                        int vit = getValueFromLore(i.getLeggings(), "DPS");
                        dps += vit;
                    }
                    if(i.getBoots() != null && i.getBoots().getItemMeta().hasLore())
                    {
                        int vit = getValueFromLore(i.getBoots(), "DPS");
                        dps += vit;
                    }
                    if(dps > 0D)
                    {
                        double divide = dps / 7500D;
                        double preset = dmg * divide;
                        int cleanedDMG = (int)(dmg + preset);
                        e.setDamage(cleanedDMG);
                    }
                    else
                    {
                        e.setDamage(dmg);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onTag(EntityDamageEvent e)
    {
        if ((e.getEntity() instanceof Player))
        {
            if (e.getDamage() <= 0.0D) {
                return;
            }
            final Player p = (Player)e.getEntity();
            plugin.tagged.add(p.getName());
            new BukkitRunnable()
            {
                public void run()
                {
                    plugin.tagged.remove(p.getName());
                }
            }.runTaskLater(plugin, 200L);
        }
    }

    @EventHandler
    public void onHitTag(EntityDamageByEntityEvent e)
    {
        if (((e.getDamager() instanceof Player)) &&
                ((e.getEntity() instanceof LivingEntity)))
        {
            if (e.getDamage() <= 0.0D) {
                return;
            }
            final Player p = (Player)e.getDamager();
            plugin.tagged.add(p.getName());
            new BukkitRunnable()
            {
                public void run()
                {
                    plugin.tagged.remove(p.getName());
                }
            }.runTaskLater(plugin, 200L);
        }
    }
    ///
    public static int getMinValueFromLore(ItemStack is, String value)
    {
        int minValue = 1;
        ItemMeta im = is.getItemMeta();

        try
        {
            List<String> lore = im.getLore();
            if(lore != null)
            {
                for(String s : lore)
                {
                    if(s.contains(value))
                    {
                        String vals = s.split(": ")[1];
                        vals = ChatColor.stripColor(vals);
                        vals = vals.split(" - ")[0];

                        minValue = Integer.parseInt(vals);
                    }
                }
            }
        }
        catch(Exception e)
        {

        }

        return minValue;
    }

    public static int getMaxValueFromLore(ItemStack is, String value)
    {
        int maxValue = 1;
        ItemMeta im = is.getItemMeta();

        try
        {
            List<String> lore = im.getLore();
            if(lore != null)
            {
                for(String s : lore)
                {
                    if(s.contains(value))
                    {
                        String vals = s.split(": ")[1];
                        vals = ChatColor.stripColor(vals);
                        vals = vals.split(" - ")[1];

                        maxValue = Integer.parseInt(vals);
                    }
                }
            }
        }
        catch(Exception e)
        {

        }

        return maxValue;
    }

    public static int getValueFromLore(ItemStack is, String value)
    {
        int values = 0;
        ItemMeta im = is.getItemMeta();

        try
        {
            List<String> lore = im.getLore();
            if(lore != null)
            {
                for(String s : lore)
                {
                    if(s.contains("%"))
                    {
                        if (s.contains(value))
                        {
                            String vals = s.split(": ")[1];
                            vals = ChatColor.stripColor(vals);
                            vals = vals.replace("%", "");

                            values = Integer.parseInt(vals);
                        }
                    }
                    else if(s.contains("+"))
                    {
                        if (s.contains(value))
                        {
                            String vals = s.split(": +")[1];
                            vals = ChatColor.stripColor(vals);

                            values = Integer.parseInt(vals);
                        }
                    }
                }
            }
        }
        catch(Exception e)
        {

        }

        return values;
    }

    public static void checks(Player p)
    {
        PlayerInventory i = p.getInventory();
        double hp = 50D;
        double vital = 0D;

        if(i.getHelmet() != null && i.getHelmet().getItemMeta().hasLore())
        {
            hp += getValueFromLore(i.getHelmet(), "HP");
            vital += getValueFromLore(i.getHelmet(), "VIT");
        }
        if(i.getChestplate() != null && i.getChestplate().getItemMeta().hasLore())
        {
            hp += getValueFromLore(i.getChestplate(), "HP");
            vital += getValueFromLore(i.getChestplate(), "VIT");
        }
        if(i.getLeggings() != null && i.getLeggings().getItemMeta().hasLore())
        {
            hp += getValueFromLore(i.getLeggings(), "HP");
            vital += getValueFromLore(i.getLeggings(), "VIT");
        }
        if(i.getBoots() != null && i.getBoots().getItemMeta().hasLore())
        {
            hp += getValueFromLore(i.getBoots(), "HP");
            vital += getValueFromLore(i.getBoots(), "VIT");
        }

        if(vital > 0D)
        {
            double divide = vital / 2000D;
            double preset = hp * divide;
            int cleanedhp = (int)(preset + hp);

            if(p.getHealth() > cleanedhp)
            {
                p.setHealth(cleanedhp);
            }

            p.setMaxHealth(cleanedhp);
        }
        else
        {
            p.setMaxHealth(hp);
        }

        p.setHealthScale(20D);
        p.setHealthScaled(true);
    }
}
