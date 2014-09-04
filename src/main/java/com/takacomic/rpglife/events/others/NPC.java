package com.takacomic.rpglife.events.others;

import com.takacomic.rpglife.RPGLife;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NPC implements Listener
{
    public RPGLife plugin;
    List<String> withdraw = new ArrayList();

    public NPC(RPGLife plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBankClick(PlayerInteractEntityEvent e)
    {
        if ((e.getRightClicked() instanceof HumanEntity))
        {
            HumanEntity p = (HumanEntity)e.getRightClicked();
            if (p.getName().equals("Banker"))
            {
                e.getPlayer().openInventory(e.getPlayer().getEnderChest());
                e.getPlayer()
                        .sendMessage(

                                ChatColor.GRAY +
                                        "Banker: " +
                                        ChatColor.WHITE +
                                        "Use these bank chests to store your precious items.");
            }
            else if (p.getName().equals("Healer"))
            {
                e.getPlayer().setHealth(e.getPlayer().getMaxHealth());
                e.getPlayer().sendMessage(
                        ChatColor.GRAY + "Healer: " + ChatColor.WHITE +
                                "You have been healed");
            }
            else if (p.getName().equals("Item Vendor"))
            {
                e.getPlayer().sendMessage(
                        ChatColor.GRAY +
                                "Item Vendor: " +
                                ChatColor.WHITE +
                                "Please store your gems in your bank before buying items from me.");
                Inventory inv = Bukkit.getServer().createInventory(null, 18,
                        "Item Vendor");
                ItemStack orb = new ItemStack(Material.MAGMA_CREAM);
                ItemMeta orbmeta = orb.getItemMeta();
                orbmeta.setDisplayName(ChatColor.LIGHT_PURPLE +
                        "Orb of Alteration");
                orbmeta.setLore(
                        Arrays.asList(new String[]{ChatColor.GRAY + "Randomizes stats of selected equipment.", ChatColor.GREEN + "Price: " + ChatColor.WHITE +
                                "2000g"}));
                orb.setItemMeta(orbmeta);
                inv.addItem(new ItemStack[] { orb });

                ItemStack t1wepscroll = new ItemStack(Material.EMPTY_MAP);
                ItemMeta t1wepscrollmeta = t1wepscroll.getItemMeta();
                t1wepscrollmeta.setDisplayName(ChatColor.WHITE.toString() +
                        ChatColor.BOLD + "Scroll:" + ChatColor.WHITE +
                        " Enchant Wooden Weapon");
                t1wepscrollmeta
                        .setLore(
                                Arrays.asList(new String[] {
                                        ChatColor.RED + "+5% DMG",
                                        ChatColor.GRAY.toString() +

                                                ChatColor.ITALIC +
                                                "Weapon will VANISH if enchant above +3 FAILS.",
                                        ChatColor.GREEN + "Price: " +
                                                ChatColor.WHITE + "3000g" }));
                t1wepscroll.setItemMeta(t1wepscrollmeta);
                inv.addItem(new ItemStack[] { t1wepscroll });

                ItemStack t1scroll = new ItemStack(Material.EMPTY_MAP);
                ItemMeta t1scrollmeta = t1scroll.getItemMeta();
                t1scrollmeta.setDisplayName(ChatColor.WHITE.toString() +
                        ChatColor.BOLD + "Scroll:" + ChatColor.WHITE +
                        " Enchant Leather Armor");
                t1scrollmeta
                        .setLore(
                                Arrays.asList(new String[] {
                                        ChatColor.RED + "+5% HP",
                                        ChatColor.RED + "+5% HP REGEN",
                                        ChatColor.GRAY + "   - OR -",
                                        ChatColor.RED + "+5% VIT",
                                        ChatColor.GRAY.toString() +

                                                ChatColor.ITALIC +
                                                "Armor will VANISH if enchant above +3 FAILS.",
                                        ChatColor.GREEN + "Price: " +
                                                ChatColor.WHITE + "2000g" }));
                t1scroll.setItemMeta(t1scrollmeta);
                inv.addItem(new ItemStack[] { t1scroll });
                e.getPlayer().openInventory(inv);

                ItemStack t2wepscroll = new ItemStack(Material.EMPTY_MAP);
                ItemMeta t2wepscrollmeta = t2wepscroll.getItemMeta();
                t2wepscrollmeta.setDisplayName(ChatColor.WHITE.toString() +
                        ChatColor.BOLD + "Scroll:" + ChatColor.GREEN +
                        " Enchant Stone Weapon");
                t2wepscrollmeta
                        .setLore(
                                Arrays.asList(new String[] {
                                        ChatColor.RED + "+5% DMG",
                                        ChatColor.GRAY.toString() +

                                                ChatColor.ITALIC +
                                                "Weapon will VANISH if enchant above +3 FAILS.",
                                        ChatColor.GREEN + "Price: " +
                                                ChatColor.WHITE + "3000g" }));
                t2wepscroll.setItemMeta(t2wepscrollmeta);
                inv.addItem(new ItemStack[] { t2wepscroll });

                ItemStack t2scroll = new ItemStack(Material.EMPTY_MAP);
                ItemMeta t2scrollmeta = t2scroll.getItemMeta();
                t2scrollmeta.setDisplayName(ChatColor.WHITE.toString() +
                        ChatColor.BOLD + "Scroll:" + ChatColor.GREEN +
                        " Enchant Chainmail Armor");
                t2scrollmeta
                        .setLore(
                                Arrays.asList(new String[] {
                                        ChatColor.RED + "+5% HP",
                                        ChatColor.RED + "+5% HP REGEN",
                                        ChatColor.GRAY + "   - OR -",
                                        ChatColor.RED + "+5% VIT",
                                        ChatColor.GRAY.toString() +

                                                ChatColor.ITALIC +
                                                "Armor will VANISH if enchant above +3 FAILS.",
                                        ChatColor.GREEN + "Price: " +
                                                ChatColor.WHITE + "2000g" }));
                t2scroll.setItemMeta(t2scrollmeta);
                inv.addItem(new ItemStack[] { t2scroll });
                e.getPlayer().openInventory(inv);

                ItemStack t3wepscroll = new ItemStack(Material.EMPTY_MAP);
                ItemMeta t3wepscrollmeta = t3wepscroll.getItemMeta();
                t3wepscrollmeta.setDisplayName(ChatColor.WHITE.toString() +
                        ChatColor.BOLD + "Scroll:" + ChatColor.AQUA +
                        " Enchant Iron Weapon");
                t3wepscrollmeta
                        .setLore(
                                Arrays.asList(new String[] {
                                        ChatColor.RED + "+5% DMG",
                                        ChatColor.GRAY.toString() +
                                                ChatColor.ITALIC +
                                                "Weapon will VANISH if enchant above +3 FAILS.",
                                        ChatColor.GREEN + "Price: " +
                                                ChatColor.WHITE + "3000g" }));
                t3wepscroll.setItemMeta(t3wepscrollmeta);
                inv.addItem(new ItemStack[] { t3wepscroll });

                ItemStack t3scroll = new ItemStack(Material.EMPTY_MAP);
                ItemMeta t3scrollmeta = t3scroll.getItemMeta();
                t3scrollmeta.setDisplayName(ChatColor.WHITE.toString() +
                        ChatColor.BOLD + "Scroll:" + ChatColor.AQUA +
                        " Enchant Iron Armor");
                t3scrollmeta
                        .setLore(
                                Arrays.asList(new String[] {
                                        ChatColor.RED + "+5% HP",
                                        ChatColor.RED + "+5% HP REGEN",
                                        ChatColor.GRAY + "   - OR -",
                                        ChatColor.RED + "+5% VIT",
                                        ChatColor.GRAY.toString() +

                                                ChatColor.ITALIC +
                                                "Armor will VANISH if enchant above +3 FAILS.",
                                        ChatColor.GREEN + "Price: " +
                                                ChatColor.WHITE + "2000g" }));
                t3scroll.setItemMeta(t3scrollmeta);
                inv.addItem(new ItemStack[] { t3scroll });

                ItemStack t4wepscroll = new ItemStack(Material.EMPTY_MAP);
                ItemMeta t4wepscrollmeta = t4wepscroll.getItemMeta();
                t4wepscrollmeta.setDisplayName(ChatColor.WHITE +
                        ChatColor.BOLD.toString() + "Scroll:" + ChatColor.LIGHT_PURPLE +
                        " Enchant Diamond Weapon");
                t4wepscrollmeta
                        .setLore(
                                Arrays.asList(new String[] {
                                        ChatColor.RED + "+5% DMG",
                                        ChatColor.GRAY.toString() +

                                                ChatColor.ITALIC +
                                                "Weapon will VANISH if enchant above +3 FAILS.",
                                        ChatColor.GREEN + "Price: " +
                                                ChatColor.WHITE + "3000g" }));
                t4wepscroll.setItemMeta(t4wepscrollmeta);
                inv.addItem(new ItemStack[] { t4wepscroll });

                ItemStack t4scroll = new ItemStack(Material.EMPTY_MAP);
                ItemMeta t4scrollmeta = t4scroll.getItemMeta();
                t4scrollmeta.setDisplayName(ChatColor.WHITE +
                        ChatColor.BOLD.toString() + "Scroll:" + ChatColor.LIGHT_PURPLE +
                        " Enchant Diamond Armor");
                t4scrollmeta
                        .setLore(
                                Arrays.asList(new String[] {
                                        ChatColor.RED + "+5% HP",
                                        ChatColor.RED + "+5% HP REGEN",
                                        ChatColor.GRAY + "   - OR -",
                                        ChatColor.RED + "+5% VIT",
                                        ChatColor.GRAY.toString() +

                                                ChatColor.ITALIC +
                                                "Armor will VANISH if enchant above +3 FAILS.",
                                        ChatColor.GREEN + "Price: " +
                                                ChatColor.WHITE + "2000g" }));
                t4scroll.setItemMeta(t4scrollmeta);
                inv.addItem(new ItemStack[] { t4scroll });

                ItemStack t5wepscroll = new ItemStack(Material.EMPTY_MAP);
                ItemMeta t5wepscrollmeta = t5wepscroll.getItemMeta();
                t5wepscrollmeta.setDisplayName(ChatColor.WHITE.toString() +
                        ChatColor.BOLD + "Scroll:" + ChatColor.YELLOW +
                        " Enchant Gold Weapon");
                t5wepscrollmeta
                        .setLore(
                                Arrays.asList(new String[] {
                                        ChatColor.RED + "+5% DMG",
                                        ChatColor.GRAY.toString() +

                                                ChatColor.ITALIC +
                                                "Weapon will VANISH if enchant above +3 FAILS.",
                                        ChatColor.GREEN + "Price: " +
                                                ChatColor.WHITE + "3000g" }));
                t5wepscroll.setItemMeta(t5wepscrollmeta);
                inv.addItem(new ItemStack[] { t5wepscroll });

                ItemStack t5scroll = new ItemStack(Material.EMPTY_MAP);
                ItemMeta t5scrollmeta = t5scroll.getItemMeta();
                t5scrollmeta.setDisplayName(ChatColor.WHITE +
                        ChatColor.BOLD.toString() + "Scroll:" + ChatColor.YELLOW +
                        " Enchant Gold Armor");
                t5scrollmeta
                        .setLore(
                                Arrays.asList(new String[] {
                                        ChatColor.RED + "+5% HP",
                                        ChatColor.RED + "+5% HP REGEN",
                                        ChatColor.GRAY + "   - OR -",
                                        ChatColor.RED + "+5% VIT",
                                        ChatColor.GRAY.toString() +

                                                ChatColor.ITALIC +
                                                "Armor will VANISH if enchant above +3 FAILS.",
                                        ChatColor.GREEN + "Price: " +
                                                ChatColor.WHITE + "2000g" }));
                t5scroll.setItemMeta(t5scrollmeta);
                inv.addItem(new ItemStack[] { t5scroll });
            }
            else if (p.getName().equals("Fisherman"))
            {
                Inventory inv = Bukkit.getServer().createInventory(null, 9,
                        "Fisherman");

                ItemStack t1F = new ItemStack(Material.RAW_FISH);
                ItemMeta t1fishmeta = t1F.getItemMeta();
                t1fishmeta.setDisplayName(ChatColor.WHITE +
                        "Raw Sardine of Quickness");
                List<String> t1lore = new ArrayList();
                t1lore.add(ChatColor.RED + "SPEED (I) BUFF" + ChatColor.GRAY +
                        " (60s)");
                t1lore.add(ChatColor.RED + "-30% HUNGER" + ChatColor.GRAY +
                        " (instant)");
                t1lore.add(ChatColor.GRAY.toString() + ChatColor.ITALIC +
                        "A Fish from a can.");
                t1lore.add(ChatColor.GREEN + "Price: " + ChatColor.WHITE +
                        "100g");
                t1fishmeta.setLore(t1lore);
                t1F.setItemMeta(t1fishmeta);
                inv.addItem(new ItemStack[] { t1F });

                ItemStack t2F = new ItemStack(Material.RAW_FISH);
                ItemMeta t2fishmeta = t2F.getItemMeta();
                t2fishmeta.setDisplayName(ChatColor.GREEN +
                        "Raw Trout of Beast Swiftness");
                List<String> t2lore = new ArrayList();
                t2lore.add(ChatColor.RED + "SPEED (II) BUFF" + ChatColor.GRAY +
                        " (15s)");
                t2lore.add(ChatColor.RED + "-30% HUNGER" + ChatColor.GRAY +
                        " (instant)");
                t2lore.add(ChatColor.GRAY.toString() + ChatColor.ITALIC +
                        "A beautiful rainbow trout.");
                t2lore.add(ChatColor.GREEN + "Price: " + ChatColor.WHITE +
                        "200g");
                t2fishmeta.setLore(t2lore);
                t2F.setItemMeta(t2fishmeta);
                inv.addItem(new ItemStack[] { t2F });

                ItemStack t3F = new ItemStack(Material.RAW_FISH);
                ItemMeta t3fishmeta = t3F.getItemMeta();
                t3fishmeta.setDisplayName(ChatColor.AQUA +
                        "Raw Salmon of Lasting Agility");
                List<String> t3lore = new ArrayList();
                t3lore.add(ChatColor.RED + "SPEED (II) BUFF" + ChatColor.GRAY +
                        " (30s)");
                t3lore.add(ChatColor.RED + "-30% HUNGER" + ChatColor.GRAY +
                        " (instant)");
                t3lore.add(ChatColor.GRAY.toString() + ChatColor.ITALIC +
                        "A beautiful jumping fish.");
                t3lore.add(ChatColor.GREEN + "Price: " + ChatColor.WHITE +
                        "300g");
                t3fishmeta.setLore(t3lore);
                t3F.setItemMeta(t3fishmeta);
                inv.addItem(new ItemStack[] { t3F });

                ItemStack t4F = new ItemStack(Material.RAW_FISH);
                ItemMeta t4fishmeta = t4F.getItemMeta();
                t4fishmeta.setDisplayName(ChatColor.LIGHT_PURPLE +
                        "Raw Lobster of Bursting Agility");
                List<String> t4lore = new ArrayList();
                t4lore.add(ChatColor.RED + "SPEED (III) BUFF" + ChatColor.GRAY +
                        " (15s)");
                t4lore.add(ChatColor.RED + "-40% HUNGER" + ChatColor.GRAY +
                        " (instant)");
                t4lore.add(ChatColor.GRAY.toString() + ChatColor.ITALIC +
                        "A large red crustacean.");
                t4lore.add(ChatColor.GREEN + "Price: " + ChatColor.WHITE +
                        "400g");
                t4fishmeta.setLore(t4lore);
                t4F.setItemMeta(t4fishmeta);
                inv.addItem(new ItemStack[] { t4F });

                ItemStack t5F = new ItemStack(Material.RAW_FISH);
                ItemMeta t5fishmeta = t5F.getItemMeta();
                t5fishmeta.setDisplayName(ChatColor.YELLOW +
                        "Raw Swordfish of Godlike Speed");
                List<String> t5lore = new ArrayList();
                t5lore.add(ChatColor.RED + "SPEED (III) BUFF" + ChatColor.GRAY +
                        " (30s)");
                t5lore.add(ChatColor.RED + "-50% HUNGER" + ChatColor.GRAY +
                        " (instant)");
                t5lore.add(ChatColor.GRAY.toString() + ChatColor.ITALIC +
                        "An elongated fish with a long bill.");
                t5lore.add(ChatColor.GREEN + "Price: " + ChatColor.WHITE +
                        "500g");
                t5fishmeta.setLore(t5lore);
                t5F.setItemMeta(t5fishmeta);
                inv.addItem(new ItemStack[] { t5F });

                ItemStack t6F = new ItemStack(Material.RAW_FISH);
                ItemMeta t6fishmeta = t6F.getItemMeta();
                t6fishmeta.setDisplayName(ChatColor.DARK_PURPLE +
                        "Raw Breem of Amazing Stamina");
                List<String> t6lore = new ArrayList();
                t6lore.add(ChatColor.RED + "SPEED (IV) BUFF" + ChatColor.GRAY +
                        " (45s)");
                t6lore.add(ChatColor.RED + "-50% HUNGER" + ChatColor.GRAY +
                        " (instant)");
                t6lore.add(ChatColor.GRAY.toString() + ChatColor.ITALIC +
                        "The Banana of the Ocean.");
                t6lore.add(ChatColor.GREEN + "Price: " + ChatColor.WHITE +
                        "600g");
                t6fishmeta.setLore(t6lore);
                t6F.setItemMeta(t6fishmeta);
                inv.addItem(new ItemStack[] { t6F });

                ItemStack t7F = new ItemStack(Material.RAW_FISH);
                ItemMeta t7fishmeta = t7F.getItemMeta();
                t7fishmeta.setDisplayName(ChatColor.DARK_GREEN +
                        "Raw Tuna of Insane Stamina");
                List<String> t7lore = new ArrayList();
                t7lore.add(ChatColor.RED + "SPEED (IV) BUFF" + ChatColor.GRAY +
                        " (60s)");
                t7lore.add(ChatColor.RED + "-50% HUNGER" + ChatColor.GRAY +
                        " (instant)");
                t7lore.add(ChatColor.GRAY.toString() + ChatColor.ITALIC +
                        "A Mighty fast machine.");
                t7lore.add(ChatColor.GREEN + "Price: " + ChatColor.WHITE +
                        "700g");
                t7fishmeta.setLore(t7lore);
                t7F.setItemMeta(t7fishmeta);
                inv.addItem(new ItemStack[] { t7F });

                e.getPlayer().openInventory(inv);
            }
            else if (p.getName().equals("Pickaxe Vendor"))
            {
                Inventory inv = Bukkit.getServer().createInventory(null, 9,
                        "Pickaxe Vendor");
                ItemStack P = new ItemStack(Material.GOLD_PICKAXE);
                ItemMeta pickmeta = P.getItemMeta();
                pickmeta.setDisplayName(ChatColor.YELLOW + "Master Pickaxe");
                List<String> lore = new ArrayList();
                lore.add(ChatColor.RED + "TREASURE FIND: 1%");
                lore.add(ChatColor.GRAY.toString() + ChatColor.ITALIC +
                        "A pickaxe made out of gold.");
                lore.add(ChatColor.GREEN + "Price: " + ChatColor.WHITE +
                        "5000g");
                pickmeta.setLore(lore);
                P.setItemMeta(pickmeta);
                inv.addItem(new ItemStack[] { P });
                e.getPlayer().openInventory(inv);
            }
        }
    }

    @EventHandler
    public void onInvOpen(InventoryOpenEvent e)
    {
        if (e.getInventory().getType() == InventoryType.ENDER_CHEST)
        {
            Inventory inv = e.getPlayer().getEnderChest();
            Bukkit.createInventory(null, 54, "EnderChest");
            ItemStack gem = new ItemStack(Material.EMERALD);
            ItemMeta im = gem.getItemMeta();
            im.setDisplayName(ChatColor.GREEN.toString() +


                    (int)this.plugin.econ.getBalance(e.getPlayer().getName()) +
                    ChatColor.GREEN + ChatColor.BOLD + " GEM(s)");
            im.setLore(
                    Arrays.asList(new String[] {ChatColor.GRAY +
                            "Right Click to create " + ChatColor.GREEN +
                            "A GEM NOTE" }));
            gem.setItemMeta(im);
            inv.setItem(26, gem);
        }
    }

    public static int getGemsFromLore(ItemStack item, String value)
    {
        int returnVal = 0;
        ItemMeta meta = item.getItemMeta();
        try
        {
            List<?> lore = meta.getLore();
            if ((lore != null) && (((String)lore.get(0)).contains(value)))
            {
                String vals = ((String)lore.get(0)).split(": ")[1];
                vals = ChatColor.stripColor(vals);
                vals = vals.replace(" Gems", "").trim().toString();
                returnVal = Integer.parseInt(vals.trim());
            }
        }
        catch (Exception localException) {}
        return returnVal;
    }

    @EventHandler
    public void onPromptAmount(AsyncPlayerChatEvent e)
    {
        Player p = e.getPlayer();
        if (!this.withdraw.contains(p.getName())) {
            return;
        }
        e.setCancelled(true);
        String message = e.getMessage();
        if ((e.getMessage().equalsIgnoreCase("cancel")) &&
                (this.withdraw.contains(p.getName())))
        {
            for (int i = 0; i < this.withdraw.size(); i++) {
                this.withdraw.remove(p.getName());
            }
            p.sendMessage(ChatColor.RED + "Withdrawl operation - " +
                    ChatColor.BOLD + "CANCELLED");
            return;
        }
        int amt = 0;
        try
        {
            amt = Integer.parseInt(message);
            if (amt > this.plugin.econ.getBalance(p.getName()))
            {
                p.sendMessage(ChatColor.GRAY +
                        "You cannot withdraw more GEMS than you have stored.");
            }
            else if (amt <= 0)
            {
                p.sendMessage(ChatColor.RED +
                        "You must enter a POSIVIVE amount.");
            }
            else
            {
                for (int i = 0; i < this.withdraw.size(); i++) {
                    this.withdraw.remove(p.getName());
                }
                this.plugin.econ.withdrawPlayer(p.getName(), amt);
                ItemStack gem = new ItemStack(Material.PAPER);
                ItemMeta im = gem.getItemMeta();
                im.setDisplayName(ChatColor.GREEN + "Bank Note");
                im.setLore(Arrays.asList(new String[] {
                        ChatColor.WHITE.toString() + ChatColor.BOLD + "Value: " +
                                ChatColor.WHITE + amt + " Gems",
                        ChatColor.GRAY + "Exchange at any bank for GEM(s)" }));
                gem.setItemMeta(im);
                p.getInventory().setItem(p.getInventory().firstEmpty(), gem);
                p.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD +
                        "New Balance: " + ChatColor.GREEN +
                        (int)this.plugin.econ.getBalance(p.getName()) +
                        " Gem(s)");
                p.playSound(p.getLocation(), Sound.DIG_WOOL, 1.0F, 1.0F);
            }
        }
        catch (NumberFormatException ex)
        {
            p.sendMessage(ChatColor.RED +
                    "Please enter a NUMBER, the amount you'd like to WITHDRAW from your bank account. Or type 'cancel' to void the withdrawl.");
        }
    }

    @EventHandler
    public void onInvClick(final InventoryClickEvent e)
    {
        final Player p = (Player)e.getWhoClicked();
        if ((e.getCurrentItem() != null) &&
                (e.getCurrentItem().getType() == Material.PAPER) &&
                (e.getCurrentItem().getItemMeta().hasLore()) &&
                (e.getCursor().getType() == Material.PAPER) &&
                (e.getCursor().getItemMeta().hasLore()))
        {
            e.setCancelled(true);
            int first = getGemsFromLore(e.getCurrentItem(), "Value");
            int second = getGemsFromLore(e.getCursor(), "Value");
            ItemStack gem = new ItemStack(Material.PAPER);
            ItemMeta im = gem.getItemMeta();
            im.setDisplayName(ChatColor.GREEN + "Bank Note");
            im.setLore(Arrays.asList(new String[] {
                    ChatColor.WHITE.toString() + ChatColor.BOLD + "Value: " +
                            ChatColor.WHITE + (first + second) + " Gems",
                    ChatColor.GRAY + "Exchange at any bank for GEM(s)" }));
            gem.setItemMeta(im);
            e.setCurrentItem(gem);
            e.setCursor(null);
            p.playSound(p.getLocation(), Sound.DIG_WOOL, 1.0F, 1.0F);
        }
        if (e.getInventory().getType() == InventoryType.ENDER_CHEST)
        {
            if ((e.getCurrentItem() != null) &&
                    (e.getCurrentItem().getType() == Material.EMERALD) &&
                    (e.getCurrentItem().getItemMeta().hasDisplayName()) &&
                    (!e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Gem")))
            {
                e.setCancelled(true);
                if (e.getClick() == ClickType.RIGHT)
                {
                    p.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD +
                            "Current Balance: " + ChatColor.GREEN +
                            (int)this.plugin.econ.getBalance(p.getName()) +
                            " Gem(s)");
                    p.closeInventory();
                    for (int i = 0; i < this.withdraw.size(); i++) {
                        this.withdraw.remove(p.getName());
                    }
                    this.withdraw.add(p.getName());
                    p.sendMessage(ChatColor.GRAY +
                            "Please enter the amount you'd like To CONVERT into a gem note. Alternatively, type " +
                            ChatColor.RED + "'cancel'" + ChatColor.GRAY +
                            " to void this operation.");
                }
            }
            new BukkitRunnable()
            {
                public void run()
                {
                    if (e.getInventory().contains(Material.EMERALD)) {
                        for (int i = 0; i < e.getInventory().getSize(); i++) {
                            if ((e.getInventory().getItem(i) != null) &&
                                    (e.getInventory().getItem(i).getType() == Material.EMERALD) &&
                                    (i != 26))
                            {
                                int amt = e.getInventory().getItem(i)
                                        .getAmount();
                                NPC.this.plugin.econ.depositPlayer(p.getName(),
                                        amt);
                                e.getInventory().removeItem(
                                        new ItemStack[] {e.getInventory()
                                                .getItem(i) });
                                p.playSound(p.getLocation(),
                                        Sound.SUCCESSFUL_HIT, 1.0F, 1.0F);
                                ItemStack gem = new ItemStack(Material.EMERALD);
                                ItemMeta im = gem.getItemMeta();
                                im.setDisplayName(ChatColor.GREEN.toString() +


                                        (int)NPC.this.plugin.econ.getBalance(p.getName()) +
                                        ChatColor.GREEN + ChatColor.BOLD +
                                        " GEM(s)");
                                im.setLore(
                                        Arrays.asList(new String[] {ChatColor.GRAY +
                                                "Right Click to create " +
                                                ChatColor.GREEN +
                                                "A GEM NOTE" }));
                                gem.setItemMeta(im);
                                e.getInventory().setItem(26, gem);
                                p.updateInventory();
                                p.sendMessage(ChatColor.GREEN.toString() +

                                        ChatColor.BOLD +
                                        "+" +
                                        ChatColor.GREEN +
                                        amt +
                                        ChatColor.GREEN +
                                        ChatColor.BOLD +
                                        "G" +
                                        ChatColor.GREEN +
                                        ", " +
                                        ChatColor.BOLD +
                                        "New Balance: " +
                                        ChatColor.GREEN +

                                        (int)NPC.this.plugin.econ.getBalance(p.getName()) +
                                        " Gem(s)");
                            }
                        }
                    }
                    if (e.getInventory().contains(Material.PAPER)) {
                        for (int i = 0; i < e.getInventory().getSize(); i++) {
                            if ((e.getInventory().getItem(i) != null) &&
                                    (e.getInventory().getItem(i).getType() == Material.PAPER))
                            {
                                int amt = NPC.getGemsFromLore(e.getInventory()
                                        .getItem(i), "Value");
                                NPC.this.plugin.econ.depositPlayer(p.getName(),
                                        amt);
                                e.getInventory().removeItem(
                                        new ItemStack[] {e.getInventory()
                                                .getItem(i) });
                                p.playSound(p.getLocation(),
                                        Sound.SUCCESSFUL_HIT, 1.0F, 1.0F);
                                ItemStack gem = new ItemStack(Material.EMERALD);
                                ItemMeta im = gem.getItemMeta();
                                im.setDisplayName(ChatColor.GREEN.toString() +


                                        (int)NPC.this.plugin.econ.getBalance(p.getName()) +
                                        ChatColor.GREEN + ChatColor.BOLD +
                                        " GEM(s)");
                                im.setLore(
                                        Arrays.asList(new String[] {ChatColor.GRAY +
                                                "Right Click to create " +
                                                ChatColor.GREEN +
                                                "A GEM NOTE" }));
                                gem.setItemMeta(im);
                                e.getInventory().setItem(26, gem);
                                p.updateInventory();
                                p.sendMessage(ChatColor.GREEN.toString() +

                                        ChatColor.BOLD +
                                        "+" +
                                        ChatColor.GREEN +
                                        amt +
                                        ChatColor.GREEN +
                                        ChatColor.BOLD +
                                        "G" +
                                        ChatColor.GREEN +
                                        ", " +
                                        ChatColor.BOLD +
                                        "New Balance: " +
                                        ChatColor.GREEN +

                                        (int)NPC.this.plugin.econ.getBalance(p.getName()) +
                                        " Gem(s)");
                            }
                        }
                    }
                }
            }.runTaskLater(this.plugin, 1L);
        }
        if (e.getInventory().getTitle().equals("Item Vendor"))
        {
            e.setCancelled(true);
            if ((e.getCurrentItem().getType() == Material.MAGMA_CREAM) &&
                    (e.getCurrentItem().getItemMeta().hasLore()) &&
                    (e.getCurrentItem().getItemMeta().getLore().contains(ChatColor.GREEN + "Price: " + ChatColor.WHITE + "2000g")))
            {
                if (this.plugin.econ.getBalance(p.getName()) >= 2000.0D)
                {
                    ItemStack orb = new ItemStack(Material.MAGMA_CREAM);
                    ItemMeta orbmeta = orb.getItemMeta();
                    orbmeta.setDisplayName(ChatColor.LIGHT_PURPLE +
                            "Orb of Alteration");
                    orbmeta.setLore(Arrays.asList(new String[] {ChatColor.GRAY +
                            "Randomizes stats of selected equipment." }));
                    orb.setItemMeta(orbmeta);
                    p.getInventory().addItem(new ItemStack[] { orb });
                    this.plugin.econ.withdrawPlayer(p.getName(), 2000.0D);
                    p.sendMessage(ChatColor.RED + "-2000" + ChatColor.BOLD +
                            "G");
                    return;
                }
                p.sendMessage(ChatColor.RED +
                        "You don't have enough GEM(s) for 1x of this item.");
                p.sendMessage(ChatColor.RED + "COST: 2000");
                p.closeInventory();

                return;
            }
            if ((e.getCurrentItem().getType() == Material.EMPTY_MAP) &&
                    (e.getCurrentItem().getItemMeta().hasLore()) &&
                    (e.getCurrentItem().getItemMeta().getLore().contains(ChatColor.GREEN + "Price: " + ChatColor.WHITE + "2000g")))
            {
                if (this.plugin.econ.getBalance(p.getName()) >= 2000.0D)
                {
                    ItemStack scroll = new ItemStack(Material.EMPTY_MAP);
                    ItemMeta scrollmeta = scroll.getItemMeta();
                    if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Gold")) {
                        scrollmeta.setDisplayName(ChatColor.WHITE.toString() +
                                ChatColor.BOLD + "Scroll:" + ChatColor.YELLOW +
                                " Enchant Gold Armor");
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Iron")) {
                        scrollmeta.setDisplayName(ChatColor.WHITE.toString() +
                                ChatColor.BOLD + "Scroll:" + ChatColor.AQUA +
                                " Enchant Iron Armor");
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Diamond")) {
                        scrollmeta.setDisplayName(ChatColor.WHITE.toString() +
                                ChatColor.BOLD + "Scroll:" +
                                ChatColor.LIGHT_PURPLE +
                                " Enchant Diamond Armor");
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Chainmail")) {
                        scrollmeta.setDisplayName(ChatColor.WHITE.toString() +
                                ChatColor.BOLD + "Scroll:" +
                                ChatColor.GREEN +
                                " Enchant Chainmail Armor");
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Leather")) {
                        scrollmeta.setDisplayName(ChatColor.WHITE.toString() +
                                ChatColor.BOLD + "Scroll:" +
                                ChatColor.WHITE +
                                " Enchant Leather Armor");
                    }
                    scrollmeta.setLore(
                            Arrays.asList(new String[] {
                                    ChatColor.RED + "+5% HP",
                                    ChatColor.RED + "+5% HP REGEN",
                                    ChatColor.GRAY + "   - OR -",
                                    ChatColor.RED + "+5% VIT",
                                    ChatColor.GRAY.toString() +

                                            ChatColor.ITALIC +
                                            "Armor will VANISH if enchant above +3 FAILS." }));
                    scroll.setItemMeta(scrollmeta);
                    p.getInventory().addItem(new ItemStack[] { scroll });
                    this.plugin.econ.withdrawPlayer(p.getName(), 2000.0D);
                    p.sendMessage(ChatColor.RED + "-2000" + ChatColor.BOLD +
                            "G");
                    return;
                }
                p.sendMessage(ChatColor.RED +
                        "You don't have enough GEM(s) for 1x of this item.");
                p.sendMessage(ChatColor.RED + "COST: 2000");
                p.closeInventory();

                return;
            }
            if ((e.getCurrentItem().getType() == Material.EMPTY_MAP) &&
                    (e.getCurrentItem().getItemMeta().hasLore()) &&
                    (e.getCurrentItem().getItemMeta().getLore().contains(ChatColor.GREEN + "Price: " + ChatColor.WHITE + "3000g"))) {
                if (this.plugin.econ.getBalance(p.getName()) >= 3000.0D)
                {
                    ItemStack scroll = new ItemStack(Material.EMPTY_MAP);
                    ItemMeta scrollmeta = scroll.getItemMeta();
                    if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Gold")) {
                        scrollmeta.setDisplayName(ChatColor.WHITE.toString() +
                                ChatColor.BOLD + "Scroll:" + ChatColor.YELLOW +
                                " Enchant Gold Weapon");
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Iron")) {
                        scrollmeta.setDisplayName(ChatColor.WHITE.toString() +
                                ChatColor.BOLD + "Scroll:" + ChatColor.AQUA +
                                " Enchant Iron Weapon");
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Diamond")) {
                        scrollmeta.setDisplayName(ChatColor.WHITE.toString() +
                                ChatColor.BOLD + "Scroll:" +
                                ChatColor.LIGHT_PURPLE +
                                " Enchant Diamond Weapon");
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Stone")) {
                        scrollmeta.setDisplayName(ChatColor.WHITE.toString() +
                                ChatColor.BOLD + "Scroll:" +
                                ChatColor.GREEN +
                                " Enchant Stone Weapon");
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Wood")) {
                        scrollmeta.setDisplayName(ChatColor.WHITE.toString() +
                                ChatColor.BOLD + "Scroll:" +
                                ChatColor.WHITE +
                                " Enchant Wooden Weapon");
                    }
                    scrollmeta.setLore(
                            Arrays.asList(new String[] {
                                    ChatColor.RED + "+5% DMG",
                                    ChatColor.GRAY.toString() +

                                            ChatColor.ITALIC +
                                            "Weapon will VANISH if enchant above +3 FAILS." }));
                    scroll.setItemMeta(scrollmeta);
                    p.getInventory().addItem(new ItemStack[] { scroll });
                    this.plugin.econ.withdrawPlayer(p.getName(), 3000.0D);
                    p.sendMessage(ChatColor.RED + "-3000" + ChatColor.BOLD +
                            "G");
                }
                else
                {
                    p.sendMessage(ChatColor.RED +
                            "You don't have enough GEM(s) for 1x of this item.");
                    p.sendMessage(ChatColor.RED + "COST: 3000");
                    p.closeInventory();
                }
            }
        }
        else if (e.getInventory().getTitle().equals("Fisherman"))
        {
            e.setCancelled(true);
            if ((e.getCurrentItem().getType() == Material.RAW_FISH) &&
                    (e.getCurrentItem().getItemMeta().hasLore()) &&
                    (e.getCurrentItem().getItemMeta().getLore().contains(ChatColor.GREEN + "Price: " + ChatColor.WHITE + "100g")))
            {
                if (this.plugin.econ.getBalance(p.getName()) >= 100.0D)
                {
                    ItemStack t3F = new ItemStack(Material.RAW_FISH);
                    ItemMeta t3fishmeta = t3F.getItemMeta();
                    t3fishmeta.setDisplayName(ChatColor.WHITE +
                            "Raw Sardine of Quickness");
                    List<String> t3lore = new ArrayList();
                    t3lore.add(ChatColor.RED + "SPEED (I) BUFF" +
                            ChatColor.GRAY + " (60s)");
                    t3lore.add(ChatColor.RED + "-30% HUNGER" + ChatColor.GRAY +
                            " (instant)");
                    t3lore.add(ChatColor.GRAY.toString() + ChatColor.ITALIC +
                            "A Fish from a can.");
                    t3fishmeta.setLore(t3lore);
                    t3F.setItemMeta(t3fishmeta);
                    p.getInventory().addItem(new ItemStack[] { t3F });
                    this.plugin.econ.withdrawPlayer(p.getName(), 100.0D);
                    p.sendMessage(ChatColor.RED + "-100" + ChatColor.BOLD + "G");
                    return;
                }
                p.sendMessage(ChatColor.RED +
                        "You don't have enough GEM(s) for 1x of this item.");
                p.sendMessage(ChatColor.RED + "COST: 100");
                p.closeInventory();

                return;
            }
            if ((e.getCurrentItem().getType() == Material.RAW_FISH) &&
                    (e.getCurrentItem().getItemMeta().hasLore()) &&
                    (e.getCurrentItem().getItemMeta().getLore().contains(ChatColor.GREEN + "Price: " + ChatColor.WHITE + "200g")))
            {
                if (this.plugin.econ.getBalance(p.getName()) >= 200.0D)
                {
                    ItemStack t3F = new ItemStack(Material.RAW_FISH);
                    ItemMeta t3fishmeta = t3F.getItemMeta();
                    t3fishmeta.setDisplayName(ChatColor.GREEN +
                            "Raw Trout of Beast Swiftness");
                    List<String> t3lore = new ArrayList();
                    t3lore.add(ChatColor.RED + "SPEED (II) BUFF" +
                            ChatColor.GRAY + " (15s)");
                    t3lore.add(ChatColor.RED + "-30% HUNGER" + ChatColor.GRAY +
                            " (instant)");
                    t3lore.add(ChatColor.GRAY.toString() + ChatColor.ITALIC +
                            "A beautiful rainbow trout.");
                    t3fishmeta.setLore(t3lore);
                    t3F.setItemMeta(t3fishmeta);
                    p.getInventory().addItem(new ItemStack[] { t3F });
                    this.plugin.econ.withdrawPlayer(p.getName(), 200.0D);
                    p.sendMessage(ChatColor.RED + "-200" + ChatColor.BOLD + "G");
                    return;
                }
                p.sendMessage(ChatColor.RED +
                        "You don't have enough GEM(s) for 1x of this item.");
                p.sendMessage(ChatColor.RED + "COST: 200");
                p.closeInventory();

                return;
            }
            if ((e.getCurrentItem().getType() == Material.RAW_FISH) &&
                    (e.getCurrentItem().getItemMeta().hasLore()) &&
                    (e.getCurrentItem().getItemMeta().getLore().contains(ChatColor.GREEN + "Price: " + ChatColor.WHITE + "300g")))
            {
                if (this.plugin.econ.getBalance(p.getName()) >= 300.0D)
                {
                    ItemStack t3F = new ItemStack(Material.RAW_FISH);
                    ItemMeta t3fishmeta = t3F.getItemMeta();
                    t3fishmeta.setDisplayName(ChatColor.AQUA +
                            "Raw Salmon of Lasting Agility");
                    List<String> t3lore = new ArrayList();
                    t3lore.add(ChatColor.RED + "SPEED (II) BUFF" +
                            ChatColor.GRAY + " (30s)");
                    t3lore.add(ChatColor.RED + "-30% HUNGER" + ChatColor.GRAY +
                            " (instant)");
                    t3lore.add(ChatColor.GRAY.toString() + ChatColor.ITALIC +
                            "A beautiful jumping fish.");
                    t3fishmeta.setLore(t3lore);
                    t3F.setItemMeta(t3fishmeta);
                    p.getInventory().addItem(new ItemStack[] { t3F });
                    this.plugin.econ.withdrawPlayer(p.getName(), 300.0D);
                    p.sendMessage(ChatColor.RED + "-300" + ChatColor.BOLD + "G");
                    return;
                }
                p.sendMessage(ChatColor.RED +
                        "You don't have enough GEM(s) for 1x of this item.");
                p.sendMessage(ChatColor.RED + "COST: 300");
                p.closeInventory();

                return;
            }
            if ((e.getCurrentItem().getType() == Material.RAW_FISH) &&
                    (e.getCurrentItem().getItemMeta().hasLore()) &&
                    (e.getCurrentItem().getItemMeta().getLore().contains(ChatColor.GREEN + "Price: " + ChatColor.WHITE + "400g")))
            {
                if (this.plugin.econ.getBalance(p.getName()) >= 400.0D)
                {
                    ItemStack t3F = new ItemStack(Material.RAW_FISH);
                    ItemMeta t3fishmeta = t3F.getItemMeta();
                    t3fishmeta.setDisplayName(ChatColor.LIGHT_PURPLE +
                            "Raw Lobster of Bursting Agility");
                    List<String> t3lore = new ArrayList();
                    t3lore.add(ChatColor.RED + "SPEED (III) BUFF" +
                            ChatColor.GRAY + " (15s)");
                    t3lore.add(ChatColor.RED + "-40% HUNGER" + ChatColor.GRAY +
                            " (instant)");
                    t3lore.add(ChatColor.GRAY.toString() + ChatColor.ITALIC +
                            "A large red crustacean.");
                    t3fishmeta.setLore(t3lore);
                    t3F.setItemMeta(t3fishmeta);
                    p.getInventory().addItem(new ItemStack[] { t3F });
                    this.plugin.econ.withdrawPlayer(p.getName(), 400.0D);
                    p.sendMessage(ChatColor.RED + "-400" + ChatColor.BOLD + "G");
                    return;
                }
                p.sendMessage(ChatColor.RED +
                        "You don't have enough GEM(s) for 1x of this item.");
                p.sendMessage(ChatColor.RED + "COST: 400");
                p.closeInventory();

                return;
            }
            if ((e.getCurrentItem().getType() == Material.RAW_FISH) &&
                    (e.getCurrentItem().getItemMeta().hasLore()) &&
                    (e.getCurrentItem().getItemMeta().getLore().contains(ChatColor.GREEN + "Price: " + ChatColor.WHITE + "500g")))
            {
                if (this.plugin.econ.getBalance(p.getName()) >= 500.0D)
                {
                    ItemStack t3F = new ItemStack(Material.RAW_FISH);
                    ItemMeta t3fishmeta = t3F.getItemMeta();
                    t3fishmeta.setDisplayName(ChatColor.YELLOW +
                            "Raw Swordfish of Godlike Speed");
                    List<String> t3lore = new ArrayList();
                    t3lore.add(ChatColor.RED + "SPEED (III) BUFF" +
                            ChatColor.GRAY + " (30s)");
                    t3lore.add(ChatColor.RED + "-50% HUNGER" + ChatColor.GRAY +
                            " (instant)");
                    t3lore.add(ChatColor.GRAY.toString() + ChatColor.ITALIC +
                            "An elongated fish with a long bill.");
                    t3fishmeta.setLore(t3lore);
                    t3F.setItemMeta(t3fishmeta);
                    p.getInventory().addItem(new ItemStack[] { t3F });
                    this.plugin.econ.withdrawPlayer(p.getName(), 500.0D);
                    p.sendMessage(ChatColor.RED + "-500" + ChatColor.BOLD + "G");
                    return;
                }
                p.sendMessage(ChatColor.RED +
                        "You don't have enough GEM(s) for 1x of this item.");
                p.sendMessage(ChatColor.RED + "COST: 500");
                p.closeInventory();

                return;
            }
            if ((e.getCurrentItem().getType() == Material.RAW_FISH) &&
                    (e.getCurrentItem().getItemMeta().hasLore()) &&
                    (e.getCurrentItem().getItemMeta().getLore().contains(ChatColor.GREEN + "Price: " + ChatColor.WHITE + "600g")))
            {
                if (this.plugin.econ.getBalance(p.getName()) >= 600.0D)
                {
                    ItemStack t3F = new ItemStack(Material.RAW_FISH);
                    ItemMeta t3fishmeta = t3F.getItemMeta();
                    t3fishmeta.setDisplayName(ChatColor.DARK_PURPLE +
                            "Raw Breem of Amazing Stamina");
                    List<String> t3lore = new ArrayList();
                    t3lore.add(ChatColor.RED + "SPEED (IV) BUFF" +
                            ChatColor.GRAY + " (45s)");
                    t3lore.add(ChatColor.RED + "-50% HUNGER" + ChatColor.GRAY +
                            " (instant)");
                    t3lore.add(ChatColor.GRAY.toString() + ChatColor.ITALIC +
                            "The Banana of the Ocean.");
                    t3fishmeta.setLore(t3lore);
                    t3F.setItemMeta(t3fishmeta);
                    p.getInventory().addItem(new ItemStack[] { t3F });
                    this.plugin.econ.withdrawPlayer(p.getName(), 600.0D);
                    p.sendMessage(ChatColor.RED + "-600" + ChatColor.BOLD + "G");
                    return;
                }
                p.sendMessage(ChatColor.RED +
                        "You don't have enough GEM(s) for 1x of this item.");
                p.sendMessage(ChatColor.RED + "COST: 600");
                p.closeInventory();

                return;
            }
            if ((e.getCurrentItem().getType() == Material.RAW_FISH) &&
                    (e.getCurrentItem().getItemMeta().hasLore()) &&
                    (e.getCurrentItem().getItemMeta().getLore().contains(ChatColor.GREEN + "Price: " + ChatColor.WHITE + "700g"))) {
                if (this.plugin.econ.getBalance(p.getName()) >= 700.0D)
                {
                    ItemStack t3F = new ItemStack(Material.RAW_FISH);
                    ItemMeta t3fishmeta = t3F.getItemMeta();
                    t3fishmeta.setDisplayName(ChatColor.DARK_GREEN +
                            "Raw Tuna of Insane Stamina");
                    List<String> t3lore = new ArrayList();
                    t3lore.add(ChatColor.RED + "SPEED (IV) BUFF" +
                            ChatColor.GRAY + " (60s)");
                    t3lore.add(ChatColor.RED + "-50% HUNGER" + ChatColor.GRAY +
                            " (instant)");
                    t3lore.add(ChatColor.GRAY.toString() + ChatColor.ITALIC +
                            "A Mighty fast machine.");
                    t3fishmeta.setLore(t3lore);
                    t3F.setItemMeta(t3fishmeta);
                    p.getInventory().addItem(new ItemStack[] { t3F });
                    this.plugin.econ.withdrawPlayer(p.getName(), 700.0D);
                    p.sendMessage(ChatColor.RED + "-700" + ChatColor.BOLD + "G");
                }
                else
                {
                    p.sendMessage(ChatColor.RED +
                            "You don't have enough GEM(s) for 1x of this item.");
                    p.sendMessage(ChatColor.RED + "COST: 700");
                    p.closeInventory();
                }
            }
        }
        else if (e.getInventory().getTitle().equals("Pickaxe Vendor"))
        {
            e.setCancelled(true);
            if ((e.getCurrentItem().getType() == Material.GOLD_PICKAXE) &&
                    (e.getCurrentItem().getItemMeta().hasLore()) &&
                    (e.getCurrentItem().getItemMeta().getLore().contains(ChatColor.GREEN + "Price: " + ChatColor.WHITE + "5000g"))) {
                if (this.plugin.econ.getBalance(p.getName()) >= 5000.0D)
                {
                    ItemStack P = new ItemStack(Material.GOLD_PICKAXE);
                    ItemMeta pickmeta = P.getItemMeta();
                    pickmeta.setDisplayName(ChatColor.YELLOW + "Master Pickaxe");
                    List<String> lore = new ArrayList();
                    lore.add(ChatColor.RED + "TREASURE FIND: 1%");
                    lore.add(ChatColor.GRAY.toString() + ChatColor.ITALIC +
                            "A pickaxe made out of gold.");
                    pickmeta.setLore(lore);
                    P.setItemMeta(pickmeta);
                    p.getInventory().addItem(new ItemStack[] { P });
                    this.plugin.econ.withdrawPlayer(p.getName(), 5000.0D);
                    p.sendMessage(ChatColor.RED + "-5000" + ChatColor.BOLD +
                            "G");
                }
                else
                {
                    p.sendMessage(ChatColor.RED +
                            "You don't have enough GEM(s) for 1x of this item.");
                    p.sendMessage(ChatColor.RED + "COST: 5000");
                    p.closeInventory();
                }
            }
        }
    }
}
