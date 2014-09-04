package com.takacomic.rpglife;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sun.corba.se.spi.orb.ORBVersion;
import com.takacomic.rpglife.events.damage.DamageEvents;
import com.takacomic.rpglife.events.debug.DebugEvents;
import com.takacomic.rpglife.events.others.*;
import com.takacomic.rpglife.events.worldguard.WorldGuardEvents;
import me.confuser.barapi.BarAPI;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class RPGLife extends JavaPlugin
{
    public final HashMap<String, ItemStack[]> inv = new HashMap();
    public static List<String> tagged = new ArrayList();
    public static List<String> neutral = new ArrayList();
    public static List<String> chaotic = new ArrayList();
    public static List<String> combat = new ArrayList();
    public static List<String> bank = new ArrayList();
    public static List<LivingEntity> nodamage = new ArrayList();

    public WorldGuardPlugin wg = getWorldGuard();
    public Economy econ = setupEconmy();

    int time = 0;

    @Override
    public void onEnable()
    {
        new BukkitRunnable()
        {
            public void run()
            {
                reboot();
            }
        }.runTaskLater(this, 864000);

        Bukkit.getServer().getPluginManager().registerEvents(new DamageEvents(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new DebugEvents(this), this);
        //Bukkit.getServer().getPluginManager().registerEvents(new AntiBuild(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Enchanting(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Fish(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Mining(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new NPC(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Orb(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new OtherEvents(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Untradeable(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new WorldGuardEvents(this), this);

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable()
        {
            @Override
            public void run()
            {
                for(Player p : Bukkit.getServer().getOnlinePlayers())
                {
                    barHealth(p);
                    if(p.getExp() < 1F)
                    {
                        p.setExp((float)(p.getExp() + 0.02D));
                    }
                    if(p.getLevel() + 2 > 100)
                    {
                        p.setLevel(100);
                    }
                    else
                    {
                        p.setLevel(p.getLevel() + 2);
                    }
                }
            }
        }, 1L, 1L);
    }

    private WorldGuardPlugin getWorldGuard() {
        Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");

        if (plugin == null || !(plugin instanceof WorldGuardPlugin))
        {
            return null;
        }

        return (WorldGuardPlugin) plugin;
    }

    private Economy setupEconmy()
    {
        if(getServer().getPluginManager().getPlugin("Vault") == null)
        {
            return null;
        }
        else
        {
            RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
            if(rsp == null)
            {
                return null;
            }
            else
            {
                return this.econ = rsp.getProvider();
            }
        }
    }

    private void reboot()
    {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable()
        {
            @Override
            public void run()
            {
                if(time == 0)
                {
                    Bukkit.broadcastMessage(ChatColor.RED + "Server is" + ChatColor.BOLD + " REBOOTING" + ChatColor.RESET + ChatColor.RED + " in" + ChatColor.GOLD + " 10 minutes");
                }
                else if(time == 300)
                {
                    Bukkit.broadcastMessage(ChatColor.RED + "Server is" + ChatColor.BOLD + " REBOOTING" + ChatColor.RESET + ChatColor.RED + " in" + ChatColor.GOLD + " 5 minutes");
                }
                else if(time == 540)
                {
                    Bukkit.broadcastMessage(ChatColor.RED + "Server is" + ChatColor.BOLD + " REBOOTING" + ChatColor.RESET + ChatColor.RED + " in" + ChatColor.GOLD + " 60 seconds");
                }
                else if(time == 570)
                {
                    Bukkit.broadcastMessage(ChatColor.RED + "Server is" + ChatColor.BOLD + " REBOOTING" + ChatColor.RESET + ChatColor.RED + " in" + ChatColor.GOLD + " 30 seconds");
                }
                else if(time == 590)
                {
                    Bukkit.broadcastMessage(ChatColor.RED + "Server is" + ChatColor.BOLD + " REBOOTING" + ChatColor.RESET + ChatColor.RED + " in" + ChatColor.GOLD + " 10 seconds");
                }
                else if(time == 591)
                {
                    Bukkit.broadcastMessage(ChatColor.RED + "Server is" + ChatColor.BOLD + " REBOOTING" + ChatColor.RESET + ChatColor.RED + " in" + ChatColor.GOLD + " 9 seconds");
                }
                else if(time == 592)
                {
                    Bukkit.broadcastMessage(ChatColor.RED + "Server is" + ChatColor.BOLD + " REBOOTING" + ChatColor.RED + " in" + ChatColor.GOLD + " 8 seconds");
                }
                else if(time == 593)
                {
                    Bukkit.broadcastMessage(ChatColor.RED + "Server is" + ChatColor.BOLD + " REBOOTING" + ChatColor.RED + " in" + ChatColor.GOLD + " 7 seconds");
                }
                else if(time == 594)
                {
                    Bukkit.broadcastMessage(ChatColor.RED + "Server is" + ChatColor.BOLD + " REBOOTING" + ChatColor.RED + " in" + ChatColor.GOLD + " 6 seconds");
                }
                else if(time == 595)
                {
                    Bukkit.broadcastMessage(ChatColor.RED + "Server is" + ChatColor.BOLD + " REBOOTING" + ChatColor.RED + " in" + ChatColor.GOLD + " 5 seconds");
                }
                else if(time == 596)
                {
                    Bukkit.broadcastMessage(ChatColor.RED + "Server is" + ChatColor.BOLD + " REBOOTING" + ChatColor.RED + " in" + ChatColor.GOLD + " 4 seconds");
                }
                else if(time == 597)
                {
                    Bukkit.broadcastMessage(ChatColor.RED + "Server is" + ChatColor.BOLD + " REBOOTING" + ChatColor.RED + " in" + ChatColor.GOLD + " 3 seconds");
                }
                else if(time == 598)
                {
                    Bukkit.broadcastMessage(ChatColor.RED + "Server is" + ChatColor.BOLD + " REBOOTING" + ChatColor.RED + " in" + ChatColor.GOLD + " 2 seconds");
                }
                else if(time == 599)
                {
                    Bukkit.broadcastMessage(ChatColor.RED + "Server is" + ChatColor.BOLD + " REBOOTING" + ChatColor.RED + " in" + ChatColor.GOLD + " 1 seconds");
                }
                else if(time == 600)
                {
                    Bukkit.getServer().shutdown();
                }

                time++;
            }
        }, 0, 20);
    }

    public void barHealth(Player player)
    {
        BarAPI.setMessage(player, "§d§lHP §d" + (int)player.getHealth() + " §d§l/§d " + (int)player.getMaxHealth());
        BarAPI.setHealth(player, (float) ((float) player.getHealth() / player.getMaxHealth() * 100.0D));
    }

    public String getNameHealth(double Health, double MaxHealth, String Name)
    {
        Name = Name.replaceAll("§0", "");
        Name = Name.replaceAll("§1", "");
        Name = Name.replaceAll("§2", "");
        Name = Name.replaceAll("§3", "");
        Name = Name.replaceAll("§4", "");
        Name = Name.replaceAll("§5", "");
        Name = Name.replaceAll("§6", "");
        Name = Name.replaceAll("§7", "");
        Name = Name.replaceAll("§8", "");
        Name = Name.replaceAll("§9", "");
        Name = Name.replaceAll("§a", "");
        Name = Name.replaceAll("§b", "");
        Name = Name.replaceAll("§c", "");
        Name = Name.replaceAll("§d", "");
        Name = Name.replaceAll("§e", "");
        Name = Name.replaceAll("§f", "");
        Name = Name.replaceAll("§l", "");
        Name = Name.replaceAll("§k", "");
        Name = Name.replaceAll("§o", "");
        Name = Name.replaceAll("§m", "");
        Name = Name.replaceAll("§n", "");

        double percent = Health / MaxHealth;
        double length = Name.length();
        double scolor = length * percent;
        int color = (int)Math.ceil(scolor);
        String n = Name;

        if (color < length / 3.0D)
        {
            String g = n.substring(color);
            String h = n.substring(0, color);
            n = "§c" + h + "§7" + g;
        }
        else if (color < length / 1.5D)
        {
            String g = n.substring(color);
            String h = n.substring(0, color);
            n = "§e" + h + "§7" + g;
        }
        else
        {
            String g = n.substring(color);
            String h = n.substring(0, color);
            n = "§a" + h + "§7" + g;
        }
        return n;
    }

    public void startKit(Player p)
    {
        PlayerInventory i = p.getInventory();
        Random random = new Random();

        int min = random.nextInt(3) + 1;
        int max = random.nextInt(min) + 1 + min;
        int wep = random.nextInt(2) + 1;

        p.setMaxHealth(50D);
        p.setHealth(50D);
        p.setHealthScale(20D);
        p.setHealthScaled(true);

        if(wep == 1)
        {
            ItemStack is = new ItemStack(Material.WOOD_SWORD);
            ItemMeta im = is.getItemMeta();
            List<String> lore = new ArrayList();

            im.setDisplayName(ChatColor.WHITE + "Training Sword");
            lore.add(ChatColor.RED + "DMG: " + min + " - " + max);
            lore.add(ChatColor.GRAY + "Untradeable");
            im.setLore(lore);
            is.setItemMeta(im);

            i.addItem(is);
        }
        else
        {
            ItemStack is = new ItemStack(Material.WOOD_AXE);
            ItemMeta im = is.getItemMeta();
            List<String> lore = new ArrayList();

            im.setDisplayName(ChatColor.WHITE + "Training Hatchet");
            lore.add(ChatColor.RED + "DMG: " + min + " - " + max);
            lore.add(ChatColor.GRAY + "Untradeable");
            im.setLore(lore);
            is.setItemMeta(im);

            i.addItem(is);
        }
    }
}
