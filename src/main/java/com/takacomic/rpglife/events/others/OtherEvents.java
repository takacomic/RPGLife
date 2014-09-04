package com.takacomic.rpglife.events.others;

import ca.wacos.nametagedit.NametagAPI;
import com.takacomic.rpglife.RPGLife;
import com.takacomic.rpglife.events.damage.DamageEvents;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OtherEvents implements Listener
{
    public static RPGLife plugin;

    public OtherEvents(RPGLife plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();
        p.setHealthScale(20D);
        p.setHealthScaled(true);

        if (p.hasPlayedBefore())
        {
            return;
        } else
        {
            plugin.startKit(p);
        }
    }

    @EventHandler
    public void onEnergyUse(PlayerInteractEvent e)
    {
        if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK)
        {
            Player p = e.getPlayer();

            if (p.getExp() > -0.5D)
            {
                Random random = new Random();
                int amt;

                if (p.getItemInHand().getType().name().contains("axe"))
                {
                    amt = random.nextInt(3) + 15;
                } else if (p.getItemInHand().getType().name().contains("sword"))
                {
                    amt = random.nextInt(3) + 12;
                } else
                {
                    amt = random.nextInt(3) + 10;
                }

                p.setExp((float) (p.getExp() - amt * 0.01D));
                p.setLevel(p.getLevel() - amt);
            } else if (p.getExp() < 0F)
            {
                p.setExp(-0.5F);
                p.setLevel(-50);
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 25, 5), true);
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e)
    {
        Player p = e.getEntity();

        p.playSound(p.getLocation(), Sound.WITHER_SPAWN, 1F, 1F);
        e.setDroppedExp(0);
        e.setDeathMessage(null);

        if (RPGLife.tagged.contains(p.getName()))
        {
            for (int i = 0; i < RPGLife.tagged.size(); i++)
            {
                RPGLife.tagged.remove(p.getName());
            }
        }
        if (RPGLife.combat.contains(p.getName()))
        {
            for (int i = 0; i < RPGLife.combat.size(); i++)
            {
                RPGLife.combat.remove(p.getName());
            }
        }
        if (RPGLife.chaotic.contains(p.getName()))
        {
            for (int i = 0; i < RPGLife.chaotic.size(); i++)
            {
                RPGLife.chaotic.remove(p.getName());
            }
        }
        if (RPGLife.neutral.contains(p.getName()))
        {
            for (int i = 0; i < RPGLife.neutral.size(); i++)
            {
                RPGLife.neutral.remove(p.getName());
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e)
    {
        final Player p = (Player) e.getWhoClicked();

        new BukkitRunnable()
        {
            public void run()
            {
                checks(p);
            }
        }.runTaskLater(plugin, 1L);
    }

    @EventHandler
    public void onHealthRegen(EntityRegainHealthEvent e)
    {
        e.setCancelled(true);
    }

    @EventHandler
    public void onMapOpen(PlayerInteractEvent e)
    {
        Player p = e.getPlayer();

        if ((e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) && p.getItemInHand().getType() == Material.EMPTY_MAP)
        {
            e.setCancelled(true);
            p.updateInventory();
        }
    }

    @EventHandler
    public void onGemCollect(PlayerPickupItemEvent e)
    {
        Player p = e.getPlayer();

        if (e.getItem().getItemStack().getType() == Material.EMERALD && e.getItem().getItemStack().getItemMeta().hasLore())
        {
            p.sendMessage(ChatColor.GREEN + "             +" + e.getItem().getItemStack().getAmount() + ChatColor.BOLD + "G");
            p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 1F, 1F);
        }
    }

    @EventHandler
    public void onNeutral(EntityDamageByEntityEvent e)
    {
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Player)
        {
            final Player p = (Player) e.getDamager();

            if (e.getDamage() <= 0D)
            {
                return;
            } else
            {
                if (plugin.neutral.contains(p.getName()) && !plugin.chaotic.contains(p.getName()))
                {
                    plugin.neutral.add(p.getName());

                    new BukkitRunnable()
                    {
                        @Override
                        public void run()
                        {
                            plugin.neutral.remove(p.getName());

                            if (!plugin.neutral.contains(p.getName()) && !plugin.chaotic.contains(p.getName()) && p.isOnline())
                            {
                                p.sendMessage("            §a* YOU ARE NOW §lLAWFUL §aALIGNMENT *");
                                p.sendMessage("§7While lawful, you will not lose any equiped armor on death.");
                                p.sendMessage("§7Any players who kill you while you're lawfully aligned will");
                                p.sendMessage("§7become chaotic.");
                                p.sendMessage("            §a* YOU ARE NOW §lLAWFUL §aALIGNMENT *");
                                NametagAPI.updateNametagHard(p.getName(), null, null);
                            }
                        }
                    }.runTaskLater(plugin, 1200L);
                } else if (!plugin.neutral.contains(p.getName()) && !plugin.chaotic.contains(p.getName()))
                {
                    p.sendMessage("            §e* YOU ARE NOW §e§lNEUTRAL §eALIGNMENT *");
                    p.sendMessage("§7While neutral, players who kill you will not become chaotic. You");
                    p.sendMessage("§7have a 50% chance of dropping your weapon, and a 25%");
                    p.sendMessage("§7chance of dropping each piece of equiped armor on death.");
                    p.sendMessage("§7Neutral alignment will expire 1 minute after last hit on player.");
                    p.sendMessage("            §e* YOU ARE NOW §e§lNEUTRAL §eALIGNMENT *");
                    plugin.neutral.add(p.getName());
                    NametagAPI.updateNametagHard(p.getName(), ChatColor.YELLOW.toString(), null);
                    new BukkitRunnable()
                    {
                        public void run()
                        {
                            plugin.neutral.remove(p.getName());
                            if ((!plugin.neutral.contains(p.getName())) && (!plugin.chaotic.contains(p.getName())) && (p.isOnline()))
                            {
                                p.sendMessage("            §a* YOU ARE NOW §lLAWFUL §aALIGNMENT *");
                                p.sendMessage("§7While lawful, you will not lose any equiped armor on death.");
                                p.sendMessage("§7Any players who kill you while you're lawfully aligned will");
                                p.sendMessage("§7become chaotic.");
                                p.sendMessage("            §a* YOU ARE NOW §lLAWFUL §aALIGNMENT *");
                                NametagAPI.updateNametagHard(p.getName(), null, null);
                            }
                        }
                    }.runTaskLater(this.plugin, 1200L);
                }
            }
        }
    }

    @EventHandler
    public void onChaotic(EntityDamageByEntityEvent e)
    {
        if ((e.getEntity() instanceof Player))
        {
            Player p = (Player) e.getEntity();
            if ((e.getDamager() instanceof Player))
            {
                final Player d = (Player) e.getDamager();
                if ((e.getDamage() >= p.getHealth()) && (p.getNoDamageTicks() <= p.getMaximumNoDamageTicks() / 2) && (!plugin.neutral.contains(p.getName())) && (!plugin.chaotic.contains(p.getName())))
                {
                    if (plugin.chaotic.contains(d.getName()))
                    {
                        plugin.chaotic.add(d.getName());
                        d.sendMessage("§cLAWFUL player slain, §l+300s §cadded to Chaotic timer");
                        new BukkitRunnable()
                        {
                            public void run()
                            {
                                plugin.chaotic.remove(d.getName());
                                plugin.neutral.add(d.getName());
                                if ((!plugin.chaotic.contains(d.getName())) && (d.isOnline()))
                                {
                                    d.sendMessage("            §e* YOU ARE NOW §e§lNEUTRAL §eALIGNMENT *");
                                    d.sendMessage("§7While neutral, players who kill you will not become chaotic. You");
                                    d.sendMessage("§7have a 50% chance of dropping your weapon, and a 25%");
                                    d.sendMessage("§7chance of dropping each piece of equiped armor on death.");
                                    d.sendMessage("§7Neutral alignment will expire 1 minute after last hit on player.");
                                    d.sendMessage("            §e* YOU ARE NOW §e§lNEUTRAL §eALIGNMENT *");
                                    NametagAPI.updateNametagHard(d.getName(), ChatColor.YELLOW.toString(), null);
                                }
                            }
                        }.runTaskLater(plugin, 6000L);
                        new BukkitRunnable()
                        {
                            public void run()
                            {
                                plugin.neutral.remove(d.getName());
                                if ((!plugin.chaotic.contains(d.getName())) && (!plugin.neutral.contains(d.getName())) && (d.isOnline()))
                                {
                                    d.sendMessage("            §a* YOU ARE NOW §lLAWFUL §aALIGNMENT *");
                                    d.sendMessage("§7While lawful, you will not lose any equiped armor on death.");
                                    d.sendMessage("§7Any players who kill you while you're lawfully aligned will");
                                    d.sendMessage("§7become chaotic.");
                                    d.sendMessage("            §a* YOU ARE NOW §lLAWFUL §aALIGNMENT *");
                                    NametagAPI.updateNametagHard(d.getName(), null, null);
                                }
                            }
                        }.runTaskLater(plugin, 8000L);
                    } else if (!plugin.chaotic.contains(d.getName()))
                    {
                        NametagAPI.updateNametagHard(d.getName(), ChatColor.RED.toString(), null);
                        d.sendMessage("            §c* YOU ARE NOW §lCHAOTIC §cALIGNMENT *");
                        d.sendMessage("§7If you are killed while chaotic, you will lose enerything");
                        d.sendMessage("§7in your inventory. Chaotic alignment will expire 5 minutes after");
                        d.sendMessage("§7your last player kill.");
                        d.sendMessage("            §c* YOU ARE NOW §lCHAOTIC §cALIGNMENT *");
                        d.sendMessage("§cLAWFUL player slain, §l+300s §cadded to Chaotic timer");
                        plugin.chaotic.add(d.getName());

                        new BukkitRunnable()
                        {
                            public void run()
                            {
                                plugin.chaotic.remove(d.getName());
                                plugin.neutral.add(d.getName());
                                if ((!plugin.chaotic.contains(d.getName())) && (d.isOnline()))
                                {
                                    d.sendMessage("            §e* YOU ARE NOW §e§lNEUTRAL §eALIGNMENT *");
                                    d.sendMessage("§7While neutral, players who kill you will not become chaotic. You");
                                    d.sendMessage("§7have a 50% chance of dropping your weapon, and a 25%");
                                    d.sendMessage("§7chance of dropping each piece of equiped armor on death.");
                                    d.sendMessage("§7Neutral alignment will expire 1 minute after last hit on player.");
                                    d.sendMessage("            §e* YOU ARE NOW §e§lNEUTRAL §eALIGNMENT *");
                                    NametagAPI.updateNametagHard(d.getName(), ChatColor.YELLOW.toString(), null);
                                }
                            }
                        }.runTaskLater(plugin, 6000L);

                        new BukkitRunnable()
                        {
                            public void run()
                            {
                                plugin.neutral.remove(d.getName());
                                if ((!plugin.chaotic.contains(d.getName())) &&
                                        (!plugin.neutral.contains(d.getName())) && (d.isOnline()))
                                {
                                    d.sendMessage("            §a* YOU ARE NOW §lLAWFUL §aALIGNMENT *");
                                    d.sendMessage("§7While lawful, you will not lose any equiped armor on death.");
                                    d.sendMessage("§7Any players who kill you while you're lawfully aligned will");
                                    d.sendMessage("§7become chaotic.");
                                    d.sendMessage("            §a* YOU ARE NOW §lLAWFUL §aALIGNMENT *");
                                    NametagAPI.updateNametagHard(d.getName(), null, null);
                                }
                            }
                        }.runTaskLater(plugin, 8000L);
                    }
                }
            }

        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e)
    {
        Player p = e.getEntity();
        List<ItemStack> ni = new ArrayList();
        Random random = new Random();

        int wepdrop = random.nextInt(2) + 1;
        int armor = random.nextInt(4) + 1;
        int piece = random.nextInt(4) + 1;
        int min = random.nextInt(3) + 1;
        int max = random.nextInt(min) + 1 + min;
        int wep = random.nextInt(2) + 1;

        if (!plugin.neutral.contains(p.getName()) && !plugin.chaotic.contains(p.getName()))
        {
            if (p.getInventory().getBoots() != null)
            {
                ni.add(p.getInventory().getBoots());
            }
            if (p.getInventory().getLeggings() != null)
            {
                ni.add(p.getInventory().getLeggings());
            }
            if (p.getInventory().getChestplate() != null)
            {
                ni.add(p.getInventory().getChestplate());
            }
            if (p.getInventory().getHelmet() != null)
            {
                ni.add(p.getInventory().getHelmet());
            }
            if (p.getInventory().getItem(0) != null)
            {
                ni.add(p.getInventory().getItem(0));
            } else
            {
                if (wep == 1)
                {
                    ItemStack S = new ItemStack(Material.WOOD_SWORD);
                    ItemMeta smeta = S.getItemMeta();
                    smeta.setDisplayName(ChatColor.WHITE + "Training Sword");
                    List<String> slore = new ArrayList();
                    slore.add(ChatColor.RED + "DMG: " + min + " - " + max);
                    slore.add(ChatColor.GRAY + "Untradeable");
                    smeta.setLore(slore);
                    S.setItemMeta(smeta);
                    ni.add(S);
                }
                if (wep == 2)
                {
                    ItemStack S = new ItemStack(Material.WOOD_AXE);
                    ItemMeta smeta = S.getItemMeta();
                    smeta.setDisplayName(ChatColor.WHITE + "Training Hatchet");
                    List<String> slore = new ArrayList();
                    slore.add(ChatColor.RED + "DMG: " + min + " - " + max);
                    slore.add(ChatColor.GRAY + "Untradeable");
                    smeta.setLore(slore);
                    S.setItemMeta(smeta);
                    ni.add(S);
                }
            }
            if (p.getInventory().contains(Material.GOLD_PICKAXE)) {
                for (int i = 1; i < p.getInventory().getSize(); i++) {
                    if ((p.getInventory().getItem(i) != null) &&
                            (p.getInventory().getItem(i).getType() == Material.GOLD_PICKAXE)) {
                        ni.add(p.getInventory().getItem(i));
                    }
                }
            }
        }
        else if(plugin.neutral.contains(p.getName()) && !plugin.chaotic.contains(p.getName()))
        {
            if(armor == 1)
            {
                if (piece != 1 && p.getInventory().getBoots() != null)
                {
                    ni.add(p.getInventory().getBoots());
                }
                if (piece != 2 && p.getInventory().getLeggings() != null)
                {
                    ni.add(p.getInventory().getLeggings());
                }
                if (piece != 3 && p.getInventory().getChestplate() != null)
                {
                    ni.add(p.getInventory().getChestplate());
                }
                if (piece != 4 && p.getInventory().getHelmet() != null)
                {
                    ni.add(p.getInventory().getHelmet());
                }
            }
            else
            {
                if (p.getInventory().getBoots() != null)
                {
                    ni.add(p.getInventory().getBoots());
                }
                if (p.getInventory().getLeggings() != null)
                {
                    ni.add(p.getInventory().getLeggings());
                }
                if (p.getInventory().getChestplate() != null)
                {
                    ni.add(p.getInventory().getChestplate());
                }
                if (p.getInventory().getHelmet() != null)
                {
                    ni.add(p.getInventory().getHelmet());
                }
            }
            if(wepdrop == 1)
            {
                if(p.getInventory().getItem(0) != null)
                {
                    ni.add(p.getInventory().getItem(0));
                }
                else
                {
                    if (wep == 1)
                    {
                        ItemStack S = new ItemStack(Material.WOOD_SWORD);
                        ItemMeta smeta = S.getItemMeta();
                        smeta.setDisplayName(ChatColor.WHITE + "Training Sword");
                        List<String> slore = new ArrayList();
                        slore.add(ChatColor.RED + "DMG: " + min + " - " + max);
                        slore.add(ChatColor.GRAY + "Untradeable");
                        smeta.setLore(slore);
                        S.setItemMeta(smeta);
                        ni.add(S);
                    }
                    if (wep == 2)
                    {
                        ItemStack S = new ItemStack(Material.WOOD_AXE);
                        ItemMeta smeta = S.getItemMeta();
                        smeta.setDisplayName(ChatColor.WHITE + "Training Hatchet");
                        List<String> slore = new ArrayList();
                        slore.add(ChatColor.RED + "DMG: " + min + " - " + max);
                        slore.add(ChatColor.GRAY + "Untradeable");
                        smeta.setLore(slore);
                        S.setItemMeta(smeta);
                        ni.add(S);
                    }
                }
            }
            else
            {
                if (wep == 1)
                {
                    ItemStack S = new ItemStack(Material.WOOD_SWORD);
                    ItemMeta smeta = S.getItemMeta();
                    smeta.setDisplayName(ChatColor.WHITE + "Training Sword");
                    List<String> slore = new ArrayList();
                    slore.add(ChatColor.RED + "DMG: " + min + " - " + max);
                    slore.add(ChatColor.GRAY + "Untradeable");
                    smeta.setLore(slore);
                    S.setItemMeta(smeta);
                    ni.add(S);
                }
                if (wep == 2)
                {
                    ItemStack S = new ItemStack(Material.WOOD_AXE);
                    ItemMeta smeta = S.getItemMeta();
                    smeta.setDisplayName(ChatColor.WHITE + "Training Hatchet");
                    List<String> slore = new ArrayList();
                    slore.add(ChatColor.RED + "DMG: " + min + " - " + max);
                    slore.add(ChatColor.GRAY + "Untradeable");
                    smeta.setLore(slore);
                    S.setItemMeta(smeta);
                    ni.add(S);
                }
            }
            if (p.getInventory().contains(Material.GOLD_PICKAXE)) {
                for (int i = 1; i < p.getInventory().getSize(); i++) {
                    if ((p.getInventory().getItem(i) != null) &&
                            (p.getInventory().getItem(i).getType() == Material.GOLD_PICKAXE)) {
                       ni.add(p.getInventory().getItem(i));
                    }
                }
            }
        }
        else if(plugin.chaotic.contains(p.getName()))
        {
            if (wep == 1)
            {
                ItemStack S = new ItemStack(Material.WOOD_SWORD);
                ItemMeta smeta = S.getItemMeta();
                smeta.setDisplayName(ChatColor.WHITE + "Training Sword");
                List<String> slore = new ArrayList();
                slore.add(ChatColor.RED + "DMG: " + min + " - " + max);
                slore.add(ChatColor.GRAY + "Untradeable");
                smeta.setLore(slore);
                S.setItemMeta(smeta);
                ni.add(S);
            }
            if (wep == 2)
            {
                ItemStack S = new ItemStack(Material.WOOD_AXE);
                ItemMeta smeta = S.getItemMeta();
                smeta.setDisplayName(ChatColor.WHITE + "Training Hatchet");
                List<String> slore = new ArrayList();
                slore.add(ChatColor.RED + "DMG: " + min + " - " + max);
                slore.add(ChatColor.GRAY + "Untradeable");
                smeta.setLore(slore);
                S.setItemMeta(smeta);
                ni.add(S);
            }
        }
        ItemStack[] ns = (ItemStack[]) ni.toArray(new ItemStack[ni.size()]);
        plugin.inv.put(p.getName(), ns);
        e.getDrops().removeAll(ni);
    }

    @EventHandler
    public void  onRespawn(PlayerRespawnEvent e)
    {
        ItemStack[] ni = (ItemStack[])plugin.inv.get(e.getPlayer().getName());

        if(plugin.inv.containsKey(e.getPlayer().getName()))
        {
            e.getPlayer().getInventory().addItem(ni);
            e.getPlayer().setMaxHealth(50D);
            e.getPlayer().setHealth(50D);

        }
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
}