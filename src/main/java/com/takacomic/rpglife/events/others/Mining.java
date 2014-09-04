package com.takacomic.rpglife.events.others;

import com.takacomic.rpglife.RPGLife;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.Random;

public class Mining implements Listener
{
    public static RPGLife plugin;

    public Mining(RPGLife plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e)
    {
        if(e.getAction() == Action.LEFT_CLICK_BLOCK && e.getPlayer().getItemInHand().getType() == Material.GOLD_PICKAXE)
        {
            if(e.getClickedBlock().getType() == Material.COAL_ORE)
            {
                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 2), true);
            }
            else if(e.getClickedBlock().getType() == Material.EMERALD_ORE)
            {
                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 2), true);
            }
            else if(e.getClickedBlock().getType() == Material.IRON_ORE)
            {
                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 3), true);
            }
            else if(e.getClickedBlock().getType() == Material.DIAMOND_ORE)
            {
                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 160, 3), true);
            }
            else if(e.getClickedBlock().getType() == Material.GOLD_ORE)
            {
                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 200, 4), true);
            }
            else if(e.getClickedBlock().getType() == Material.LAPIS_ORE)
            {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBreak(final BlockBreakEvent e)
    {
        Player p = e.getPlayer();
        Random random = new Random();
        int amt = random.nextInt(7) + 6;
        int treasure = random.nextInt(75) + 1;

        if(p.getItemInHand().getType() != Material.GOLD_PICKAXE)
        {
            return;
        }
        else
        {
            if(e.getBlock().getType() == Material.COAL_ORE)
            {
                ItemStack is = new ItemStack(Material.EMERALD, amt);
                ItemMeta im = is.getItemMeta();

                im.setDisplayName(ChatColor.WHITE + "Gem");
                im.setLore(Arrays.asList(ChatColor.GRAY + "The currency of Someplace"));
                is.setItemMeta(im);
                p.getWorld().dropItemNaturally(e.getBlock().getLocation(), is);
                p.sendMessage("" + ChatColor.YELLOW + ChatColor.BOLD + "              Found " + amt + " GEM(s)");
                e.getBlock().setType(Material.STONE);
                p.getItemInHand().setDurability((short) 0);
                p.updateInventory();

                new BukkitRunnable()
                {
                    public void run()
                    {
                        e.getBlock().setType(Material.COAL_ORE);
                    }
                }.runTaskLater(plugin, 800L);

                if(treasure == 1)
                {
                    ItemStack orb = new ItemStack(Material.MAGMA_CREAM);
                    ItemMeta orbmeta = orb.getItemMeta();

                    orbmeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Orb of Alteration");
                    orbmeta.setLore(Arrays.asList(ChatColor.GRAY + "Randomizes stats of selected equipment"));
                    orb.setItemMeta(orbmeta);
                    p.sendMessage("" + ChatColor.YELLOW + ChatColor.BOLD + "               Found 1x " + ChatColor.LIGHT_PURPLE + "Orb of Alteration " + ChatColor.YELLOW + ChatColor.BOLD + "embedded in the ore");
                    p.getWorld().dropItemNaturally(e.getBlock().getLocation(), orb);
                }
                else if(treasure == 2)
                {
                    ItemStack scroll = new ItemStack(Material.EMPTY_MAP);
                    ItemMeta scrollmeta = scroll.getItemMeta();

                    scrollmeta.setDisplayName("" + ChatColor.WHITE + ChatColor.BOLD + "Scroll: " + ChatColor.WHITE + "Enchant Leather Armor");
                    scrollmeta.setLore(Arrays.asList(
                                ChatColor.RED + "+5% HP",
                                ChatColor.RED + "+5% HP REGEN",
                                ChatColor.GRAY + "   - OR -",
                                ChatColor.RED + "+5% VIT",
                                "" + ChatColor.GRAY + ChatColor.ITALIC + "Armor will VANISH if enchant above +3 FAILS."
                            ));
                    scroll.setItemMeta(scrollmeta);
                    p.sendMessage("" + ChatColor.YELLOW + ChatColor.BOLD + "               Found 1x " + ChatColor.WHITE + ChatColor.BOLD + "Scroll: " + ChatColor.WHITE + "Enchant Leather Armor" + ChatColor.YELLOW + ChatColor.BOLD + "embedded in the ore");
                    p.getWorld().dropItemNaturally(e.getBlock().getLocation(), scroll);
                }
                else if(treasure == 3)
                {
                    ItemStack scroll = new ItemStack(Material.EMPTY_MAP);
                    ItemMeta scrollmeta = scroll.getItemMeta();

                    scrollmeta.setDisplayName("" + ChatColor.WHITE + ChatColor.BOLD + "Scroll: " + ChatColor.WHITE + "Enchant Wooden Weapon");
                    scrollmeta.setLore(Arrays.asList(
                            ChatColor.RED + "+5% DMG",
                            "" + ChatColor.GRAY + ChatColor.ITALIC + "Weapom will VANISH if enchant above +3 FAILS."
                    ));
                    scroll.setItemMeta(scrollmeta);
                    p.sendMessage("" + ChatColor.YELLOW + ChatColor.BOLD + "               Found 1x " + ChatColor.WHITE + ChatColor.BOLD + "Scroll: " + ChatColor.WHITE + "Enchant Wooden Weapon" + ChatColor.YELLOW + ChatColor.BOLD + "embedded in the ore");
                    p.getWorld().dropItemNaturally(e.getBlock().getLocation(), scroll);
                }

                e.setCancelled(true);
            }
            else if(e.getBlock().getType() == Material.EMERALD_ORE)
            {
                ItemStack is = new ItemStack(Material.EMERALD, amt);
                ItemMeta im = is.getItemMeta();

                im.setDisplayName(ChatColor.WHITE + "Gem");
                im.setLore(Arrays.asList(ChatColor.GRAY + "The currency of Someplace"));
                is.setItemMeta(im);
                p.getWorld().dropItemNaturally(e.getBlock().getLocation(), is);
                p.sendMessage("" + ChatColor.YELLOW + ChatColor.BOLD + "              Found " + amt + " GEM(s)");
                e.getBlock().setType(Material.STONE);
                p.getItemInHand().setDurability((short) 0);
                p.updateInventory();

                new BukkitRunnable()
                {
                    public void run()
                    {
                        e.getBlock().setType(Material.EMERALD_ORE);
                    }
                }.runTaskLater(plugin, 1000L);

                if(treasure == 1)
                {
                    ItemStack orb = new ItemStack(Material.MAGMA_CREAM);
                    ItemMeta orbmeta = orb.getItemMeta();

                    orbmeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Orb of Alteration");
                    orbmeta.setLore(Arrays.asList(ChatColor.GRAY + "Randomizes stats of selected equipment"));
                    orb.setItemMeta(orbmeta);
                    p.sendMessage("" + ChatColor.YELLOW + ChatColor.BOLD + "               Found 1x " + ChatColor.LIGHT_PURPLE + "Orb of Alteration " + ChatColor.YELLOW + ChatColor.BOLD + "embedded in the ore");
                    p.getWorld().dropItemNaturally(e.getBlock().getLocation(), orb);
                }
                else if(treasure == 2)
                {
                    ItemStack scroll = new ItemStack(Material.EMPTY_MAP);
                    ItemMeta scrollmeta = scroll.getItemMeta();

                    scrollmeta.setDisplayName("" + ChatColor.WHITE + ChatColor.BOLD + "Scroll: " + ChatColor.WHITE + "Enchant Chainmail Armor");
                    scrollmeta.setLore(Arrays.asList(
                            ChatColor.RED + "+5% HP",
                            ChatColor.RED + "+5% HP REGEN",
                            ChatColor.GRAY + "   - OR -",
                            ChatColor.RED + "+5% VIT",
                            "" + ChatColor.GRAY + ChatColor.ITALIC + "Armor will VANISH if enchant above +3 FAILS."
                    ));
                    scroll.setItemMeta(scrollmeta);
                    p.sendMessage("" + ChatColor.YELLOW + ChatColor.BOLD + "               Found 1x " + ChatColor.WHITE + ChatColor.BOLD + "Scroll: " + ChatColor.WHITE + "Enchant Chainmail Armor" + ChatColor.YELLOW + ChatColor.BOLD + "embedded in the ore");
                    p.getWorld().dropItemNaturally(e.getBlock().getLocation(), scroll);
                }
                else if(treasure == 3)
                {
                    ItemStack scroll = new ItemStack(Material.EMPTY_MAP);
                    ItemMeta scrollmeta = scroll.getItemMeta();

                    scrollmeta.setDisplayName("" + ChatColor.WHITE + ChatColor.BOLD + "Scroll: " + ChatColor.WHITE + "Enchant Stone Weapon");
                    scrollmeta.setLore(Arrays.asList(
                            ChatColor.RED + "+5% DMG",
                            "" + ChatColor.GRAY + ChatColor.ITALIC + "Weapom will VANISH if enchant above +3 FAILS."
                    ));
                    scroll.setItemMeta(scrollmeta);
                    p.sendMessage("" + ChatColor.YELLOW + ChatColor.BOLD + "               Found 1x " + ChatColor.WHITE + ChatColor.BOLD + "Scroll: " + ChatColor.WHITE + "Enchant Stone Weapon" + ChatColor.YELLOW + ChatColor.BOLD + "embedded in the ore");
                    p.getWorld().dropItemNaturally(e.getBlock().getLocation(), scroll);
                }

                e.setCancelled(true);
            }
            else if(e.getBlock().getType() == Material.IRON_ORE)
            {
                ItemStack is = new ItemStack(Material.EMERALD, amt);
                ItemMeta im = is.getItemMeta();

                im.setDisplayName(ChatColor.WHITE + "Gem");
                im.setLore(Arrays.asList(ChatColor.GRAY + "The currency of Someplace"));
                is.setItemMeta(im);
                p.getWorld().dropItemNaturally(e.getBlock().getLocation(), is);
                p.sendMessage("" + ChatColor.YELLOW + ChatColor.BOLD + "              Found " + amt + " GEM(s)");
                e.getBlock().setType(Material.STONE);
                p.getItemInHand().setDurability((short) 0);
                p.updateInventory();

                new BukkitRunnable()
                {
                    public void run()
                    {
                        e.getBlock().setType(Material.IRON_ORE);
                    }
                }.runTaskLater(plugin, 1200L);

                if(treasure == 1)
                {
                    ItemStack orb = new ItemStack(Material.MAGMA_CREAM);
                    ItemMeta orbmeta = orb.getItemMeta();

                    orbmeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Orb of Alteration");
                    orbmeta.setLore(Arrays.asList(ChatColor.GRAY + "Randomizes stats of selected equipment"));
                    orb.setItemMeta(orbmeta);
                    p.sendMessage("" + ChatColor.YELLOW + ChatColor.BOLD + "               Found 1x " + ChatColor.LIGHT_PURPLE + "Orb of Alteration " + ChatColor.YELLOW + ChatColor.BOLD + "embedded in the ore");
                    p.getWorld().dropItemNaturally(e.getBlock().getLocation(), orb);
                }
                else if(treasure == 2)
                {
                    ItemStack scroll = new ItemStack(Material.EMPTY_MAP);
                    ItemMeta scrollmeta = scroll.getItemMeta();

                    scrollmeta.setDisplayName("" + ChatColor.WHITE + ChatColor.BOLD + "Scroll: " + ChatColor.WHITE + "Enchant Iron Armor");
                    scrollmeta.setLore(Arrays.asList(
                            ChatColor.RED + "+5% HP",
                            ChatColor.RED + "+5% HP REGEN",
                            ChatColor.GRAY + "   - OR -",
                            ChatColor.RED + "+5% VIT",
                            "" + ChatColor.GRAY + ChatColor.ITALIC + "Armor will VANISH if enchant above +3 FAILS."
                    ));
                    scroll.setItemMeta(scrollmeta);
                    p.sendMessage("" + ChatColor.YELLOW + ChatColor.BOLD + "               Found 1x " + ChatColor.WHITE + ChatColor.BOLD + "Scroll: " + ChatColor.WHITE + "Enchant Iron Armor" + ChatColor.YELLOW + ChatColor.BOLD + "embedded in the ore");
                    p.getWorld().dropItemNaturally(e.getBlock().getLocation(), scroll);
                }
                else if(treasure == 3)
                {
                    ItemStack scroll = new ItemStack(Material.EMPTY_MAP);
                    ItemMeta scrollmeta = scroll.getItemMeta();

                    scrollmeta.setDisplayName("" + ChatColor.WHITE + ChatColor.BOLD + "Scroll: " + ChatColor.WHITE + "Enchant Iron Weapon");
                    scrollmeta.setLore(Arrays.asList(
                            ChatColor.RED + "+5% DMG",
                            "" + ChatColor.GRAY + ChatColor.ITALIC + "Weapom will VANISH if enchant above +3 FAILS."
                    ));
                    scroll.setItemMeta(scrollmeta);
                    p.sendMessage("" + ChatColor.YELLOW + ChatColor.BOLD + "               Found 1x " + ChatColor.WHITE + ChatColor.BOLD + "Scroll: " + ChatColor.WHITE + "Enchant Iron Weapon" + ChatColor.YELLOW + ChatColor.BOLD + "embedded in the ore");
                    p.getWorld().dropItemNaturally(e.getBlock().getLocation(), scroll);
                }

                e.setCancelled(true);
            }
            else if(e.getBlock().getType() == Material.DIAMOND_ORE)
            {
                ItemStack is = new ItemStack(Material.EMERALD, amt);
                ItemMeta im = is.getItemMeta();

                im.setDisplayName(ChatColor.WHITE + "Gem");
                im.setLore(Arrays.asList(ChatColor.GRAY + "The currency of Someplace"));
                is.setItemMeta(im);
                p.getWorld().dropItemNaturally(e.getBlock().getLocation(), is);
                p.sendMessage("" + ChatColor.YELLOW + ChatColor.BOLD + "              Found " + amt + " GEM(s)");
                e.getBlock().setType(Material.STONE);
                p.getItemInHand().setDurability((short) 0);
                p.updateInventory();

                new BukkitRunnable()
                {
                    public void run()
                    {
                        e.getBlock().setType(Material.DIAMOND_ORE);
                    }
                }.runTaskLater(plugin, 1600L);

                if(treasure == 1)
                {
                    ItemStack orb = new ItemStack(Material.MAGMA_CREAM);
                    ItemMeta orbmeta = orb.getItemMeta();

                    orbmeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Orb of Alteration");
                    orbmeta.setLore(Arrays.asList(ChatColor.GRAY + "Randomizes stats of selected equipment"));
                    orb.setItemMeta(orbmeta);
                    p.sendMessage("" + ChatColor.YELLOW + ChatColor.BOLD + "               Found 1x " + ChatColor.LIGHT_PURPLE + "Orb of Alteration " + ChatColor.YELLOW + ChatColor.BOLD + "embedded in the ore");
                    p.getWorld().dropItemNaturally(e.getBlock().getLocation(), orb);
                }
                else if(treasure == 2)
                {
                    ItemStack scroll = new ItemStack(Material.EMPTY_MAP);
                    ItemMeta scrollmeta = scroll.getItemMeta();

                    scrollmeta.setDisplayName("" + ChatColor.WHITE + ChatColor.BOLD + "Scroll: " + ChatColor.WHITE + "Enchant Diamond Armor");
                    scrollmeta.setLore(Arrays.asList(
                            ChatColor.RED + "+5% HP",
                            ChatColor.RED + "+5% HP REGEN",
                            ChatColor.GRAY + "   - OR -",
                            ChatColor.RED + "+5% VIT",
                            "" + ChatColor.GRAY + ChatColor.ITALIC + "Armor will VANISH if enchant above +3 FAILS."
                    ));
                    scroll.setItemMeta(scrollmeta);
                    p.sendMessage("" + ChatColor.YELLOW + ChatColor.BOLD + "               Found 1x " + ChatColor.WHITE + ChatColor.BOLD + "Scroll: " + ChatColor.WHITE + "Enchant Diamond Armor" + ChatColor.YELLOW + ChatColor.BOLD + "embedded in the ore");
                    p.getWorld().dropItemNaturally(e.getBlock().getLocation(), scroll);
                }
                else if(treasure == 3)
                {
                    ItemStack scroll = new ItemStack(Material.EMPTY_MAP);
                    ItemMeta scrollmeta = scroll.getItemMeta();

                    scrollmeta.setDisplayName("" + ChatColor.WHITE + ChatColor.BOLD + "Scroll: " + ChatColor.WHITE + "Enchant Diamond Weapon");
                    scrollmeta.setLore(Arrays.asList(
                            ChatColor.RED + "+5% DMG",
                            "" + ChatColor.GRAY + ChatColor.ITALIC + "Weapom will VANISH if enchant above +3 FAILS."
                    ));
                    scroll.setItemMeta(scrollmeta);
                    p.sendMessage("" + ChatColor.YELLOW + ChatColor.BOLD + "               Found 1x " + ChatColor.WHITE + ChatColor.BOLD + "Scroll: " + ChatColor.WHITE + "Enchant Diamond Weapon" + ChatColor.YELLOW + ChatColor.BOLD + "embedded in the ore");
                    p.getWorld().dropItemNaturally(e.getBlock().getLocation(), scroll);
                }

                e.setCancelled(true);
            }
            else if(e.getBlock().getType() == Material.GOLD_ORE)
            {
                ItemStack is = new ItemStack(Material.EMERALD, amt);
                ItemMeta im = is.getItemMeta();

                im.setDisplayName(ChatColor.WHITE + "Gem");
                im.setLore(Arrays.asList(ChatColor.GRAY + "The currency of Someplace"));
                is.setItemMeta(im);
                p.getWorld().dropItemNaturally(e.getBlock().getLocation(), is);
                p.sendMessage("" + ChatColor.YELLOW + ChatColor.BOLD + "              Found " + amt + " GEM(s)");
                e.getBlock().setType(Material.STONE);
                p.getItemInHand().setDurability((short) 0);
                p.updateInventory();

                new BukkitRunnable()
                {
                    public void run()
                    {
                        e.getBlock().setType(Material.GOLD_ORE);
                    }
                }.runTaskLater(plugin, 2000L);

                if(treasure == 1)
                {
                    ItemStack orb = new ItemStack(Material.MAGMA_CREAM);
                    ItemMeta orbmeta = orb.getItemMeta();

                    orbmeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Orb of Alteration");
                    orbmeta.setLore(Arrays.asList(ChatColor.GRAY + "Randomizes stats of selected equipment"));
                    orb.setItemMeta(orbmeta);
                    p.sendMessage("" + ChatColor.YELLOW + ChatColor.BOLD + "               Found 1x " + ChatColor.LIGHT_PURPLE + "Orb of Alteration " + ChatColor.YELLOW + ChatColor.BOLD + "embedded in the ore");
                    p.getWorld().dropItemNaturally(e.getBlock().getLocation(), orb);
                }
                else if(treasure == 2)
                {
                    ItemStack scroll = new ItemStack(Material.EMPTY_MAP);
                    ItemMeta scrollmeta = scroll.getItemMeta();

                    scrollmeta.setDisplayName("" + ChatColor.WHITE + ChatColor.BOLD + "Scroll: " + ChatColor.WHITE + "Enchant Gold Armor");
                    scrollmeta.setLore(Arrays.asList(
                            ChatColor.RED + "+5% HP",
                            ChatColor.RED + "+5% HP REGEN",
                            ChatColor.GRAY + "   - OR -",
                            ChatColor.RED + "+5% VIT",
                            "" + ChatColor.GRAY + ChatColor.ITALIC + "Armor will VANISH if enchant above +3 FAILS."
                    ));
                    scroll.setItemMeta(scrollmeta);
                    p.sendMessage("" + ChatColor.YELLOW + ChatColor.BOLD + "               Found 1x " + ChatColor.WHITE + ChatColor.BOLD + "Scroll: " + ChatColor.WHITE + "Enchant Gold Armor" + ChatColor.YELLOW + ChatColor.BOLD + "embedded in the ore");
                    p.getWorld().dropItemNaturally(e.getBlock().getLocation(), scroll);
                }
                else if(treasure == 3)
                {
                    ItemStack scroll = new ItemStack(Material.EMPTY_MAP);
                    ItemMeta scrollmeta = scroll.getItemMeta();

                    scrollmeta.setDisplayName("" + ChatColor.WHITE + ChatColor.BOLD + "Scroll: " + ChatColor.WHITE + "Enchant GOLD Weapon");
                    scrollmeta.setLore(Arrays.asList(
                            ChatColor.RED + "+5% DMG",
                            "" + ChatColor.GRAY + ChatColor.ITALIC + "Weapom will VANISH if enchant above +3 FAILS."
                    ));
                    scroll.setItemMeta(scrollmeta);
                    p.sendMessage("" + ChatColor.YELLOW + ChatColor.BOLD + "               Found 1x " + ChatColor.WHITE + ChatColor.BOLD + "Scroll: " + ChatColor.WHITE + "Enchant GOLD Weapon" + ChatColor.YELLOW + ChatColor.BOLD + "embedded in the ore");
                    p.getWorld().dropItemNaturally(e.getBlock().getLocation(), scroll);
                }

                e.setCancelled(true);
            }
        }
    }
}
