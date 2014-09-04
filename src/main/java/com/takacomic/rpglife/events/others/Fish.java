package com.takacomic.rpglife.events.others;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class Fish implements Listener
{
    @EventHandler
    public void onSpeedFish(PlayerInteractEvent e)
    {
        Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_AIR) ||
                (e.getAction() == Action.RIGHT_CLICK_BLOCK))
        {
            if ((p.getItemInHand().getType() == Material.COOKED_FISH) &&
                    (p.getItemInHand().getItemMeta().hasDisplayName()) &&
                    (p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Cooked Sardine of Quickness")))
            {
                if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) &&
                        (e.getClickedBlock().getType() == Material.FURNACE)) {
                    return;
                }
                if (p.hasPotionEffect(PotionEffectType.SPEED))
                {
                    e.setCancelled(true);
                    return;
                }
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
                        1200, 0));
                p.playSound(p.getLocation(), Sound.EAT, 1.0F, 1.0F);
                if (p.getItemInHand().getAmount() > 1)
                {
                    p.getItemInHand().setAmount(
                            p.getItemInHand().getAmount() - 1);
                    return;
                }
                e.setCancelled(true);
                p.setItemInHand(null);


                return;
            }
            if ((p.getItemInHand().getType() == Material.COOKED_FISH) &&
                    (p.getItemInHand().getItemMeta().hasDisplayName()) &&
                    (p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Cooked Trout of Beast Swiftness")))
            {
                if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) &&
                        (e.getClickedBlock().getType() == Material.FURNACE)) {
                    return;
                }
                if (p.hasPotionEffect(PotionEffectType.SPEED))
                {
                    e.setCancelled(true);
                    return;
                }
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
                        300, 1));
                p.playSound(p.getLocation(), Sound.EAT, 1.0F, 1.0F);
                if (p.getItemInHand().getAmount() > 1)
                {
                    p.getItemInHand().setAmount(
                            p.getItemInHand().getAmount() - 1);
                    return;
                }
                e.setCancelled(true);
                p.setItemInHand(null);


                return;
            }
            if ((p.getItemInHand().getType() == Material.COOKED_FISH) &&
                    (p.getItemInHand().getItemMeta().hasDisplayName()) &&
                    (p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Cooked Salmon of Lasting Agility")))
            {
                if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) &&
                        (e.getClickedBlock().getType() == Material.FURNACE)) {
                    return;
                }
                if (p.hasPotionEffect(PotionEffectType.SPEED))
                {
                    e.setCancelled(true);
                    return;
                }
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
                        600, 1));
                p.playSound(p.getLocation(), Sound.EAT, 1.0F, 1.0F);
                if (p.getItemInHand().getAmount() > 1)
                {
                    p.getItemInHand().setAmount(
                            p.getItemInHand().getAmount() - 1);
                    return;
                }
                e.setCancelled(true);
                p.setItemInHand(null);


                return;
            }
            if ((p.getItemInHand().getType() == Material.COOKED_FISH) &&
                    (p.getItemInHand().getItemMeta().hasDisplayName()) &&
                    (p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.LIGHT_PURPLE + "Cooked Lobster of Bursting Agility")))
            {
                if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) &&
                        (e.getClickedBlock().getType() == Material.FURNACE)) {
                    return;
                }
                if (p.hasPotionEffect(PotionEffectType.SPEED))
                {
                    e.setCancelled(true);
                    return;
                }
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
                        300, 2));
                p.playSound(p.getLocation(), Sound.EAT, 1.0F, 1.0F);
                if (p.getItemInHand().getAmount() > 1)
                {
                    p.getItemInHand().setAmount(
                            p.getItemInHand().getAmount() - 1);
                    return;
                }
                e.setCancelled(true);
                p.setItemInHand(null);


                return;
            }
            if ((p.getItemInHand().getType() == Material.COOKED_FISH) &&
                    (p.getItemInHand().getItemMeta().hasDisplayName()) &&
                    (p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Cooked Swordfish of Godlike Speed")))
            {
                if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) &&
                        (e.getClickedBlock().getType() == Material.FURNACE)) {
                    return;
                }
                if (p.hasPotionEffect(PotionEffectType.SPEED))
                {
                    e.setCancelled(true);
                    return;
                }
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
                        600, 2));
                p.playSound(p.getLocation(), Sound.EAT, 1.0F, 1.0F);
                if (p.getItemInHand().getAmount() > 1)
                {
                    p.getItemInHand().setAmount(
                            p.getItemInHand().getAmount() - 1);
                    return;
                }
                e.setCancelled(true);
                p.setItemInHand(null);


                return;
            }
            if ((p.getItemInHand().getType() == Material.COOKED_FISH) &&
                    (p.getItemInHand().getItemMeta().hasDisplayName()) &&
                    (p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Cooked Breem of Amazing Stamina")))
            {
                if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) &&
                        (e.getClickedBlock().getType() == Material.FURNACE)) {
                    return;
                }
                if (p.hasPotionEffect(PotionEffectType.SPEED))
                {
                    e.setCancelled(true);
                    return;
                }
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
                        900, 3));
                p.playSound(p.getLocation(), Sound.EAT, 1.0F, 1.0F);
                if (p.getItemInHand().getAmount() > 1)
                {
                    p.getItemInHand().setAmount(
                            p.getItemInHand().getAmount() - 1);
                    return;
                }
                e.setCancelled(true);
                p.setItemInHand(null);


                return;
            }
            if ((p.getItemInHand().getType() == Material.COOKED_FISH) &&
                    (p.getItemInHand().getItemMeta().hasDisplayName()) &&
                    (p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_GREEN + "Cooked Tuna of Insane Stamina")))
            {
                if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) &&
                        (e.getClickedBlock().getType() == Material.FURNACE)) {
                    return;
                }
                if (p.hasPotionEffect(PotionEffectType.SPEED))
                {
                    e.setCancelled(true);
                    return;
                }
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
                        1200, 3));
                p.playSound(p.getLocation(), Sound.EAT, 1.0F, 1.0F);
                if (p.getItemInHand().getAmount() > 1)
                {
                    p.getItemInHand().setAmount(
                            p.getItemInHand().getAmount() - 1);
                    return;
                }
                e.setCancelled(true);
                p.setItemInHand(null);


                return;
            }
            if ((p.getItemInHand().getType() == Material.RAW_FISH) &&
                    (p.getItemInHand().getItemMeta().hasDisplayName()) &&
                    (p.getItemInHand().getItemMeta().getDisplayName().contains("Raw")))
            {
                if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) &&
                        (e.getClickedBlock().getType() == Material.FURNACE)) {
                    return;
                }
                p.sendMessage(ChatColor.YELLOW + "To cook, " +
                        ChatColor.UNDERLINE + "RIGHT CLICK" +
                        ChatColor.YELLOW + " any heat source.");
                p.sendMessage(ChatColor.GRAY + "Ex. Fire, Lava, Furnace");
            }
        }
    }

    @EventHandler
    public void onFishCook(PlayerInteractEvent e)
    {
        Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) &&
                (e.getClickedBlock().getType() == Material.FURNACE))
        {
            if ((p.getItemInHand().getType() == Material.RAW_FISH) &&
                    (p.getItemInHand().getItemMeta().hasDisplayName()) &&
                    (p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Raw Sardine of Quickness")))
            {
                e.setCancelled(true);
                p.playSound(p.getLocation(), Sound.FIZZ, 1.0F, 0.0F);
                ItemStack F = new ItemStack(Material.COOKED_FISH, p
                        .getItemInHand().getAmount());
                ItemMeta fishmeta = F.getItemMeta();
                fishmeta.setDisplayName(ChatColor.WHITE +
                        "Cooked Sardine of Quickness");
                List<String> lore = new ArrayList();
                lore.add(ChatColor.RED + "SPEED (I) BUFF" + ChatColor.GRAY +
                        " (60s)");
                lore.add(ChatColor.RED + "-30% HUNGER" + ChatColor.GRAY +
                        " (instant)");
                lore.add(ChatColor.GRAY.toString() + ChatColor.ITALIC +
                        "A Fish from a can.");
                fishmeta.setLore(lore);
                F.setItemMeta(fishmeta);
                p.setItemInHand(F);
                return;
            }
            if ((p.getItemInHand().getType() == Material.RAW_FISH) &&
                    (p.getItemInHand().getItemMeta().hasDisplayName()) &&
                    (p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Raw Trout of Beast Swiftness")))
            {
                e.setCancelled(true);
                p.playSound(p.getLocation(), Sound.FIZZ, 1.0F, 0.0F);
                ItemStack F = new ItemStack(Material.COOKED_FISH, p
                        .getItemInHand().getAmount());
                ItemMeta fishmeta = F.getItemMeta();
                fishmeta.setDisplayName(ChatColor.GREEN +
                        "Cooked Trout of Beast Swiftness");
                List<String> lore = new ArrayList();
                lore.add(ChatColor.RED + "SPEED (II) BUFF" + ChatColor.GRAY +
                        " (15s)");
                lore.add(ChatColor.RED + "-30% HUNGER" + ChatColor.GRAY +
                        " (instant)");
                lore.add(ChatColor.GRAY.toString() + ChatColor.ITALIC +
                        "A beautiful rainbow trout.");
                fishmeta.setLore(lore);
                F.setItemMeta(fishmeta);
                p.setItemInHand(F);
                return;
            }
            if ((p.getItemInHand().getType() == Material.RAW_FISH) &&
                    (p.getItemInHand().getItemMeta().hasDisplayName()) &&
                    (p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Raw Salmon of Lasting Agility")))
            {
                e.setCancelled(true);
                p.playSound(p.getLocation(), Sound.FIZZ, 1.0F, 0.0F);
                ItemStack F = new ItemStack(Material.COOKED_FISH, p
                        .getItemInHand().getAmount());
                ItemMeta fishmeta = F.getItemMeta();
                fishmeta.setDisplayName(ChatColor.AQUA +
                        "Cooked Salmon of Lasting Agility");
                List<String> lore = new ArrayList();
                lore.add(ChatColor.RED + "SPEED (II) BUFF" + ChatColor.GRAY +
                        " (30s)");
                lore.add(ChatColor.RED + "-30% HUNGER" + ChatColor.GRAY +
                        " (instant)");
                lore.add(ChatColor.GRAY.toString() + ChatColor.ITALIC +
                        "A beautiful jumping fish.");
                fishmeta.setLore(lore);
                F.setItemMeta(fishmeta);
                p.setItemInHand(F);
                return;
            }
            if ((p.getItemInHand().getType() == Material.RAW_FISH) &&
                    (p.getItemInHand().getItemMeta().hasDisplayName()) &&
                    (p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.LIGHT_PURPLE + "Raw Lobster of Bursting Agility")))
            {
                e.setCancelled(true);
                p.playSound(p.getLocation(), Sound.FIZZ, 1.0F, 0.0F);
                ItemStack F = new ItemStack(Material.COOKED_FISH, p
                        .getItemInHand().getAmount());
                ItemMeta fishmeta = F.getItemMeta();
                fishmeta.setDisplayName(ChatColor.LIGHT_PURPLE +
                        "Cooked Lobster of Bursting Agility");
                List<String> lore = new ArrayList();
                lore.add(ChatColor.RED + "SPEED (III) BUFF" + ChatColor.GRAY +
                        " (15s)");
                lore.add(ChatColor.RED + "-40% HUNGER" + ChatColor.GRAY +
                        " (instant)");
                lore.add(ChatColor.GRAY.toString() + ChatColor.ITALIC +
                        "A large red crustacean.");
                fishmeta.setLore(lore);
                F.setItemMeta(fishmeta);
                p.setItemInHand(F);
                return;
            }
            if ((p.getItemInHand().getType() == Material.RAW_FISH) &&
                    (p.getItemInHand().getItemMeta().hasDisplayName()) &&
                    (p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Raw Swordfish of Godlike Speed")))
            {
                e.setCancelled(true);
                p.playSound(p.getLocation(), Sound.FIZZ, 1.0F, 0.0F);
                ItemStack F = new ItemStack(Material.COOKED_FISH, p
                        .getItemInHand().getAmount());
                ItemMeta fishmeta = F.getItemMeta();
                fishmeta.setDisplayName(ChatColor.YELLOW +
                        "Cooked Swordfish of Godlike Speed");
                List<String> lore = new ArrayList();
                lore.add(ChatColor.RED + "SPEED (III) BUFF" + ChatColor.GRAY +
                        " (30s)");
                lore.add(ChatColor.RED + "-50% HUNGER" + ChatColor.GRAY +
                        " (instant)");
                lore.add(ChatColor.GRAY.toString() + ChatColor.ITALIC +
                        "An elongated fish with a long bill.");
                fishmeta.setLore(lore);
                F.setItemMeta(fishmeta);
                p.setItemInHand(F);
                return;
            }
            if ((p.getItemInHand().getType() == Material.RAW_FISH) &&
                    (p.getItemInHand().getItemMeta().hasDisplayName()) &&
                    (p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Raw Breem of Amazing Stamina")))
            {
                e.setCancelled(true);
                p.playSound(p.getLocation(), Sound.FIZZ, 1.0F, 0.0F);
                ItemStack F = new ItemStack(Material.COOKED_FISH, p
                        .getItemInHand().getAmount());
                ItemMeta fishmeta = F.getItemMeta();
                fishmeta.setDisplayName(ChatColor.DARK_PURPLE +
                        "Cooked Breem of Amazing Stamina");
                List<String> lore = new ArrayList();
                lore.add(ChatColor.RED + "SPEED (IV) BUFF" + ChatColor.GRAY +
                        " (45s)");
                lore.add(ChatColor.RED + "-50% HUNGER" + ChatColor.GRAY +
                        " (instant)");
                lore.add(ChatColor.GRAY.toString() + ChatColor.ITALIC +
                        "The Banana of the Ocean.");
                fishmeta.setLore(lore);
                F.setItemMeta(fishmeta);
                p.setItemInHand(F);
                return;
            }
            if ((p.getItemInHand().getType() == Material.RAW_FISH) &&
                    (p.getItemInHand().getItemMeta().hasDisplayName()) &&
                    (p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_GREEN + "Raw Tuna of Insane Stamina")))
            {
                e.setCancelled(true);
                p.playSound(p.getLocation(), Sound.FIZZ, 1.0F, 0.0F);
                ItemStack F = new ItemStack(Material.COOKED_FISH, p
                        .getItemInHand().getAmount());
                ItemMeta fishmeta = F.getItemMeta();
                fishmeta.setDisplayName(ChatColor.DARK_GREEN +
                        "Cooked Tuna of Insane Stamina");
                List<String> lore = new ArrayList();
                lore.add(ChatColor.RED + "SPEED (IV) BUFF" + ChatColor.GRAY +
                        " (60s)");
                lore.add(ChatColor.RED + "-50% HUNGER" + ChatColor.GRAY +
                        " (instant)");
                lore.add(ChatColor.GRAY.toString() + ChatColor.ITALIC +
                        "A Mighty fast machine.");
                fishmeta.setLore(lore);
                F.setItemMeta(fishmeta);
                p.setItemInHand(F);
            }
        }
    }
}
