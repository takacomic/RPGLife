package com.takacomic.rpglife.events.others;

import com.takacomic.rpglife.RPGLife;
import com.takacomic.rpglife.handlers.ParticleEffects;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Orb implements Listener
{
    public static RPGLife plugin;

    public Orb(RPGLife plugin)
    {
        this.plugin = plugin;
    }

    Material[] matsArmor =
    {
            Material.LEATHER_HELMET,
            Material.LEATHER_CHESTPLATE,
            Material.LEATHER_LEGGINGS,
            Material.LEATHER_BOOTS,
            Material.CHAINMAIL_HELMET,
            Material.CHAINMAIL_CHESTPLATE,
            Material.CHAINMAIL_LEGGINGS,
            Material.CHAINMAIL_BOOTS,
            Material.IRON_HELMET,
            Material.IRON_CHESTPLATE,
            Material.IRON_LEGGINGS,
            Material.IRON_BOOTS,
            Material.DIAMOND_HELMET,
            Material.DIAMOND_CHESTPLATE,
            Material.DIAMOND_LEGGINGS,
            Material.DIAMOND_BOOTS,
            Material.GOLD_HELMET,
            Material.GOLD_CHESTPLATE,
            Material.GOLD_LEGGINGS,
            Material.GOLD_BOOTS
    };

    Material[] matsWeapons =
    {
            Material.WOOD_AXE,
            Material.WOOD_SWORD,
            Material.STONE_AXE,
            Material.STONE_SWORD,
            Material.IRON_AXE,
            Material.IRON_SWORD,
            Material.DIAMOND_AXE,
            Material.DIAMOND_SWORD,
            Material.GOLD_AXE,
            Material.GOLD_SWORD,
    };

    @EventHandler
    public void onInvClick(InventoryClickEvent e)
    {
        Player p = (Player)e.getWhoClicked();

        if(e.getInventory().getHolder() != p)
        {
            return;
        }
        else
        {
            if(e.getCursor() != null && e.getCursor().getType() == Material.MAGMA_CREAM && e.getCursor().getItemMeta().getDisplayName().equals("Orb of Alteration"))
            {
                for(Material mat : matsArmor)
                {
                    if(e.getCurrentItem().getType() == mat && e.getCurrentItem().getItemMeta().hasLore())
                    {
                        Random random = new Random();
                        int wepdrop = random.nextInt(2) + 1;
                        int regen = 0;
                        int vit = 0;
                        List<String> lore = e.getCurrentItem().getItemMeta().getLore();
                        String plus = "";
                        String fullname = e.getCurrentItem().getItemMeta().getDisplayName();
                        String type = "";
                        for(String s : lore)
                        {
                            if(s.contains("Common") || s.contains("Uncommon") || s.contains("Rare") || s.contains("Unique"))
                            {
                                type = s;
                            }
                        }

                        if(fullname.startsWith("[+"))
                        {
                            plus = fullname.substring(0, 7);
                        }
                        if(e.getCursor().getAmount() > 1)
                        {
                            e.getCursor().setAmount(e.getCursor().getAmount() - 1);
                        }
                        else if(e.getCursor().getAmount() == 1)
                        {
                            e.setCursor(null);
                        }

                        if(mat.name().contains("gold"))
                        {
                            regen = random.nextInt(21) + 90;
                            vit = random.nextInt(161) + 150;
                        }
                        else if(mat.name().contains("diamond"))
                        {
                            regen = random.nextInt(16) + 60;
                            vit = random.nextInt(91) + 60;
                        }
                        else if(mat.name().contains("iron"))
                        {
                            regen = random.nextInt(11) + 40;
                            vit = random.nextInt(41) + 20;
                        }
                        else if(mat.name().contains("chainmail"))
                        {
                            regen = random.nextInt(5) + 5;
                            vit = random.nextInt(6) + 5;
                        }
                        else if(mat.name().contains("leather"))
                        {
                            regen = random.nextInt(3) + 5;
                            vit = random.nextInt(2) + 5;
                        }

                        if(wepdrop == 1)
                        {
                            ItemStack is = new ItemStack(mat);
                            ItemMeta im = is.getItemMeta();

                            if(mat.name().contains("gold"))
                            {
                                if(mat.equals(matsArmor[17]))
                                {
                                    im.setDisplayName(plus + ChatColor.YELLOW + "Mending Legendary Full Helmet");
                                }
                                else if(mat.equals(matsArmor[18]))
                                {
                                    im.setDisplayName(plus + ChatColor.YELLOW + "Mending Legendary Chestplate");
                                }
                                else if(mat.equals(matsArmor[19]))
                                {
                                    im.setDisplayName(plus + ChatColor.YELLOW + "Mending Legendary Leggings");
                                }
                                else if(mat.equals(matsArmor[20]))
                                {
                                    im.setDisplayName(plus + ChatColor.YELLOW + "Mending Legendary Boots");
                                }
                            }
                            else if(mat.name().contains("diamond"))
                            {
                                if(mat.equals(matsArmor[13]))
                                {
                                    im.setDisplayName(plus + ChatColor.LIGHT_PURPLE + "Mending Magic Full Helmet");
                                }
                                else if(mat.equals(matsArmor[14]))
                                {
                                    im.setDisplayName(plus + ChatColor.LIGHT_PURPLE + "Mending Magic Chestplate");
                                }
                                else if(mat.equals(matsArmor[15]))
                                {
                                    im.setDisplayName(plus + ChatColor.LIGHT_PURPLE + "Mending Magic Legging");
                                }
                                else if(mat.equals(matsArmor[16]))
                                {
                                    im.setDisplayName(plus + ChatColor.LIGHT_PURPLE + "Mending Magic Boots");
                                }
                            }
                            else if(mat.name().contains("iron"))
                            {
                                if(mat.equals(matsArmor[9]))
                                {
                                    im.setDisplayName(plus + ChatColor.AQUA + "Mending Full Helmet");
                                }
                                else if(mat.equals(matsArmor[10]))
                                {
                                    im.setDisplayName(plus + ChatColor.AQUA + "Mending Iron Chestplate");
                                }
                                else if(mat.equals(matsArmor[11]))
                                {
                                    im.setDisplayName(plus + ChatColor.AQUA + "Mending Iron Leggings");
                                }
                                else if(mat.equals(matsArmor[12]))
                                {
                                    im.setDisplayName(plus + ChatColor.AQUA + "Mending Iron Boots");
                                }
                            }
                            else if(mat.name().contains("chainmail"))
                            {
                                if(mat.equals(matsArmor[5]))
                                {
                                    im.setDisplayName(plus + ChatColor.GREEN + "Mending Medium Helmet");
                                }
                                else if(mat.equals(matsArmor[6]))
                                {
                                    im.setDisplayName(plus + ChatColor.GREEN + "Mending Chaim Mail");
                                }
                                else if(mat.equals(matsArmor[7]))
                                {
                                    im.setDisplayName(plus + ChatColor.GREEN + "Mending Chain Leggings");
                                }
                                else if(mat.equals(matsArmor[8]))
                                {
                                    im.setDisplayName(plus + ChatColor.GREEN + "Mending Chain Boots");
                                }
                            }
                            else if(mat.name().contains("leather"))
                            {
                                if(mat.equals(matsArmor[1]))
                                {
                                    im.setDisplayName(plus + ChatColor.WHITE + "Mending Leather Coif");
                                }
                                else if(mat.equals(matsArmor[2]))
                                {
                                    im.setDisplayName(plus + ChatColor.WHITE + "Mending Leather Chestplate");
                                }
                                else if(mat.equals(matsArmor[3]))
                                {
                                    im.setDisplayName(plus + ChatColor.WHITE + "Mending Leather Leggings");
                                }
                                else if(mat.equals(matsArmor[4]))
                                {
                                    im.setDisplayName(plus + ChatColor.WHITE + "Mending Leather Boots");
                                }
                            }

                            List<String> lore2 = new ArrayList();
                            for(String s : lore)
                            {
                                if(s.contains("HP REGEN") || s.contains("VIT") || s.contains(type))
                                {

                                }
                                else
                                {
                                    lore2.add(s);
                                }
                            }
                            lore2.add(ChatColor.RED + "HP REGEN: +" + regen + " HP/s");
                            lore2.add(type);
                            im.setLore(lore2);
                            is.setItemMeta(im);
                            e.setCurrentItem(is);
                            p.getWorld().playEffect(p.getLocation(), Effect.EXTINGUISH, 0);
                            ParticleEffects.LAVA.display(p.getEyeLocation(), 0D, 0F, 0F, 0F, 0F, 10);
                            e.setCancelled(true);
                        }
                        else if(wepdrop == 2)
                        {
                            ItemStack is = new ItemStack(mat);
                            ItemMeta im = is.getItemMeta();

                            if(mat.name().contains("gold"))
                            {
                                if(mat.equals(matsArmor[17]))
                                {
                                    im.setDisplayName(plus + ChatColor.YELLOW + "Legendary Full Helmet of Fortitude");
                                }
                                else if(mat.equals(matsArmor[18]))
                                {
                                    im.setDisplayName(plus + ChatColor.YELLOW + "Legendary Chestplate of Fortitude");
                                }
                                else if(mat.equals(matsArmor[19]))
                                {
                                    im.setDisplayName(plus + ChatColor.YELLOW + "Legendary Leggings of Fortitude");
                                }
                                else if(mat.equals(matsArmor[20]))
                                {
                                    im.setDisplayName(plus + ChatColor.YELLOW + "Legendary Boots of Fortitude");
                                }
                            }
                            else if(mat.name().contains("diamond"))
                            {
                                if(mat.equals(matsArmor[13]))
                                {
                                    im.setDisplayName(plus + ChatColor.LIGHT_PURPLE + "Magic Full Helmet of Fortitude");
                                }
                                else if(mat.equals(matsArmor[14]))
                                {
                                    im.setDisplayName(plus + ChatColor.LIGHT_PURPLE + "Magic Chestplate of Fortitude");
                                }
                                else if(mat.equals(matsArmor[15]))
                                {
                                    im.setDisplayName(plus + ChatColor.LIGHT_PURPLE + "Magic Legging of Fortitude");
                                }
                                else if(mat.equals(matsArmor[16]))
                                {
                                    im.setDisplayName(plus + ChatColor.LIGHT_PURPLE + "Magic Boots of Fortitude");
                                }
                            }
                            else if(mat.name().contains("iron"))
                            {
                                if(mat.equals(matsArmor[9]))
                                {
                                    im.setDisplayName(plus + ChatColor.AQUA + "Full Helmet of Fortitude");
                                }
                                else if(mat.equals(matsArmor[10]))
                                {
                                    im.setDisplayName(plus + ChatColor.AQUA + "Iron Chestplate of Fortitude");
                                }
                                else if(mat.equals(matsArmor[11]))
                                {
                                    im.setDisplayName(plus + ChatColor.AQUA + "Iron Leggings of Fortitude");
                                }
                                else if(mat.equals(matsArmor[12]))
                                {
                                    im.setDisplayName(plus + ChatColor.AQUA + "Iron Boots of Fortitude");
                                }
                            }
                            else if(mat.name().contains("chainmail"))
                            {
                                if(mat.equals(matsArmor[5]))
                                {
                                    im.setDisplayName(plus + ChatColor.GREEN + "Medium Helmet of Fortitude");
                                }
                                else if(mat.equals(matsArmor[6]))
                                {
                                    im.setDisplayName(plus + ChatColor.GREEN + "Chaim Mail of Fortitude");
                                }
                                else if(mat.equals(matsArmor[7]))
                                {
                                    im.setDisplayName(plus + ChatColor.GREEN + "Chain Leggings of Fortitude");
                                }
                                else if(mat.equals(matsArmor[8]))
                                {
                                    im.setDisplayName(plus + ChatColor.GREEN + "Chain Boots of Fortitude");
                                }
                            }
                            else if(mat.name().contains("leather"))
                            {
                                if(mat.equals(matsArmor[1]))
                                {
                                    im.setDisplayName(plus + ChatColor.WHITE + "Leather Coif of Fortitude");
                                }
                                else if(mat.equals(matsArmor[2]))
                                {
                                    im.setDisplayName(plus + ChatColor.WHITE + "Leather Chestplate of Fortitude");
                                }
                                else if(mat.equals(matsArmor[3]))
                                {
                                    im.setDisplayName(plus + ChatColor.WHITE + "Leather Leggings of Fortitude");
                                }
                                else if(mat.equals(matsArmor[4]))
                                {
                                    im.setDisplayName(plus + ChatColor.WHITE + "Leather Boots of Fortitude");
                                }
                            }

                            List<String> lore2 = new ArrayList();
                            for(String s : lore)
                            {
                                if(s.contains("HP REGEN") || s.contains("VIT") || s.contains(type))
                                {

                                }
                                else
                                {
                                    lore2.add(s);
                                }
                            }
                            lore2.add(ChatColor.RED + "VIT: +" + vit);
                            lore2.add(type);
                            im.setLore(lore2);
                            is.setItemMeta(im);
                            e.setCurrentItem(is);
                            p.getWorld().playEffect(p.getLocation(), Effect.EXTINGUISH, 0);
                            ParticleEffects.LAVA.display(p.getEyeLocation(), 0D, 0F, 0F, 0F, 0F, 10);
                            e.setCancelled(true);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInvClick2(InventoryClickEvent e)
    {
        Player p = (Player) e.getWhoClicked();

        if (e.getInventory().getHolder() != p)
        {
            return;
        } else
        {
            if (e.getCursor() != null && e.getCursor().getType() == Material.MAGMA_CREAM && e.getCursor().getItemMeta().getDisplayName().equals("Orb of Alteration"))
            {
                for(Material mat : matsWeapons)
                {
                    if(e.getCurrentItem().getType() == mat && e.getCurrentItem().getItemMeta().hasLore())
                    {
                        e.setCancelled(true);
                        Random random = new Random();
                        int elemamt = 0;
                        int lifeamt = 0;
                        int critamt = 0;
                        int pureamt = 0;
                        int elem = 0;
                        int life = 0;
                        int crit = 0;
                        int pure = 0;
                        List<String> lore = e.getCurrentItem().getItemMeta().getLore();
                        String plus = "";
                        String fullname = e.getCurrentItem().getItemMeta().getDisplayName();
                        String name = "";
                        String type = "";
                        for(String s : lore)
                        {
                            if(s.contains("Common") || s.contains("Uncommon") || s.contains("Rare") || s.contains("Unique"))
                            {
                                type = s;
                            }
                        }

                        if(e.getCursor().getAmount() > 1)
                        {
                            e.getCursor().setAmount(e.getCursor().getAmount() - 1);
                        }
                        else if(e.getCursor().getAmount() == 1)
                        {
                            e.setCursor(null);
                        }

                        if(mat.name().contains("gold"))
                        {
                            if(mat.name().contains("sword"))
                            {
                                elemamt = random.nextInt(31) + 25;
                                lifeamt = random.nextInt(20) + 1;
                                critamt = random.nextInt(11) + 1;
                                pureamt = 0;
                                elem = random.nextInt(12) + 1;
                                life = random.nextInt(4) + 1;
                                crit = random.nextInt(4) + 1;
                                pure = 0;
                                name = ChatColor.YELLOW + "Legendary Sword";
                            }
                            else
                            {
                                elemamt = random.nextInt(31) + 25;
                                lifeamt = random.nextInt(20) + 1;
                                critamt = random.nextInt(11) + 1;
                                pureamt = random.nextInt(31) + 25;
                                elem = random.nextInt(12) + 1;
                                life = random.nextInt(4) + 1;
                                crit = random.nextInt(4) + 1;
                                pure = random.nextInt(4) + 1;
                                name = ChatColor.YELLOW + "Legendary Axe";
                            }
                        }
                        else if(mat.name().contains("diamond"))
                        {
                            if(mat.name().contains("sword"))
                            {
                                elemamt = random.nextInt(11) + 10;
                                lifeamt = random.nextInt(20) + 1;
                                critamt = random.nextInt(11) + 1;
                                pureamt = 0;
                                elem = random.nextInt(12) + 1;
                                life = random.nextInt(4) + 1;
                                crit = random.nextInt(4) + 1;
                                pure = 0;
                                name = ChatColor.LIGHT_PURPLE + "Ancient Sword";
                            }
                            else
                            {
                                elemamt = random.nextInt(21) + 10;
                                lifeamt = random.nextInt(20) + 1;
                                critamt = random.nextInt(11) + 1;
                                pureamt = random.nextInt(21) + 10;
                                elem = random.nextInt(12) + 1;
                                life = random.nextInt(4) + 1;
                                crit = random.nextInt(4) + 1;
                                pure = random.nextInt(4) + 1;
                                name = ChatColor.LIGHT_PURPLE + "Ancient Axe";
                            }
                        }
                        else if(mat.name().contains("iron"))
                        {
                            if(mat.name().contains("sword"))
                            {
                                elemamt = random.nextInt(31) + 25;
                                lifeamt = random.nextInt(20) + 1;
                                critamt = random.nextInt(11) + 1;
                                pureamt = 0;
                                elem = random.nextInt(12) + 1;
                                life = random.nextInt(4) + 1;
                                crit = random.nextInt(4) + 1;
                                pure = 0;
                                name = ChatColor.AQUA + "Magic Sword";
                            }
                            else
                            {
                                elemamt = random.nextInt(6) + 5;
                                lifeamt = random.nextInt(20) + 1;
                                critamt = random.nextInt(11) + 1;
                                pureamt = random.nextInt(6) + 5;
                                elem = random.nextInt(12) + 1;
                                life = random.nextInt(4) + 1;
                                crit = random.nextInt(4) + 1;
                                pure = random.nextInt(4) + 1;
                                name = ChatColor.AQUA + "Magic Waraxe";
                            }
                        }
                        else if(mat.name().contains("stone"))
                        {
                            if(mat.name().contains("sword"))
                            {
                                elemamt = random.nextInt(3) + 5;
                                lifeamt = random.nextInt(10) + 1;
                                critamt = random.nextInt(5) + 1;
                                pureamt = 0;
                                elem = random.nextInt(12) + 1;
                                life = random.nextInt(4) + 1;
                                crit = random.nextInt(4) + 1;
                                pure = 0;
                                name = ChatColor.GREEN + "Great Sword";
                            }
                            else
                            {
                                elemamt = random.nextInt(3) + 5;
                                lifeamt = random.nextInt(10) + 1;
                                critamt = random.nextInt(5) + 1;
                                pureamt = random.nextInt(4) + 5;
                                elem = random.nextInt(3) + 1;
                                life = random.nextInt(2) + 1;
                                crit = random.nextInt(2) + 1;
                                pure = random.nextInt(2) + 1;
                                name = ChatColor.GREEN + "Great Axe";
                            }
                        }
                        else if(mat.name().contains("wood"))
                        {
                            if(mat.name().contains("sword"))
                            {
                                elemamt = random.nextInt(2) + 5;
                                lifeamt = random.nextInt(6) + 1;
                                critamt = random.nextInt(2) + 1;
                                pureamt = 0;
                                elem = random.nextInt(2) + 1;
                                life = random.nextInt(1) + 1;
                                crit = random.nextInt(1) + 1;
                                pure = 0;
                                name = ChatColor.WHITE + "Short Sword";
                            }
                            else
                            {
                                elemamt = random.nextInt(2) + 5;
                                lifeamt = random.nextInt(6) + 1;
                                critamt = random.nextInt(2) + 1;
                                pureamt = random.nextInt(2) + 5;
                                elem = random.nextInt(2) + 1;
                                life = random.nextInt(1) + 1;
                                crit = random.nextInt(1) + 1;
                                pure = random.nextInt(1) + 1;
                                name = ChatColor.WHITE + "Axe";
                            }
                        }

                        List<String> lore2 = new ArrayList();
                        for(String s : lore)
                        {
                            if(s.contains("PURE") || s.contains("LIFE") || s.contains("CRITICAL") || s.contains("ICE") || s.contains("POISON") || s.contains("FIRE") || s.contains(type))
                            {

                            }
                            else
                            {
                                lore2.add(s);
                            }
                        }

                        if (pure == 1)
                        {
                            lore.add(ChatColor.RED + "PURE DMG: +" + pureamt);
                            name = "Pure " + name;
                        }
                        if (life == 1)
                        {
                            lore.add(ChatColor.RED + "LIFE STEAL: " + lifeamt + "%");
                            name = "Vampyric " + name;
                        }
                        if (crit == 1)
                        {
                            lore.add(ChatColor.RED + "CRITICAL HIT: " + critamt +
                                    "%");
                            name = "Deadly " + name;
                        }
                        if (elem == 3)
                        {
                            lore.add(ChatColor.RED + "ICE DMG: +" + elemamt);
                            name = name + " of Ice";
                        }
                        if (elem == 2)
                        {
                            lore.add(ChatColor.RED + "POISON DMG: +" + elemamt);
                            name = name + " of Poison";
                        }
                        if (elem == 1)
                        {
                            lore.add(ChatColor.RED + "FIRE DMG: +" + elemamt);
                            name = name + " of Fire";
                        }

                        lore.add(type);

                        ItemStack is = new ItemStack(mat);
                        ItemMeta im = is.getItemMeta();
                        im.setDisplayName(plus + name);
                        im.setLore(lore2);
                        is.setItemMeta(im);

                        e.setCurrentItem(is);
                        p.getWorld().playEffect(p.getLocation(), Effect.EXTINGUISH, 0);
                        ParticleEffects.LAVA.display(p.getEyeLocation(), 0F, 0F, 0F, 0F, 0F, 10);
                    }
                }
            }
        }
    }
}
