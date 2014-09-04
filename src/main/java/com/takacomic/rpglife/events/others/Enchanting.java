package com.takacomic.rpglife.events.others;

import com.takacomic.rpglife.events.damage.DamageEvents;
import com.takacomic.rpglife.handlers.ParticleEffects;
import org.bukkit.*;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enchanting implements Listener
{
    @EventHandler
    public void onInvClick(InventoryClickEvent e) throws Exception
    {
        if(e.getWhoClicked() instanceof Player)
        {
            Player p = (Player) e.getWhoClicked();
            Random random = new Random();

            if(e.getInventory().getHolder() != p)
            {
                return;
            }
            else
            {
                if(e.getCursor() != null)
                {
                    if(e.getCursor().getType() == Material.EMPTY_MAP && e.getCursor().getItemMeta().getDisplayName() != null)
                    {
                        if(e.getCursor().getItemMeta().getDisplayName().equals("Scroll: Enchant Leather Armor"))
                        {
                            if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta().hasLore() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getType() == Material.LEATHER_BOOTS || e.getCurrentItem().getType() == Material.LEATHER_CHESTPLATE || e.getCurrentItem().getType() == Material.LEATHER_LEGGINGS || e.getCurrentItem().getType() == Material.LEATHER_HELMET)
                            {
                                List<String> lore = e.getCurrentItem().getItemMeta().getLore();
                                String name = e.getCurrentItem().getItemMeta().getDisplayName();
                                String cleanedName = name.substring(7);
                                double beforeHP = DamageEvents.getValueFromLore(e.getCurrentItem(), "HP");

                                if(!name.startsWith("[+"))
                                {
                                    if(e.getCursor().getAmount() > 1)
                                    {
                                        e.getCursor().setAmount(e.getCursor().getAmount() - 1);
                                    }
                                    else if(e.getCursor().getAmount() == 1)
                                    {
                                        e.setCursor(null);
                                    }

                                    Firework firework = (Firework)p.getWorld().spawn(p.getLocation(), Firework.class);
                                    FireworkMeta fireworkMeta = firework.getFireworkMeta();
                                    ItemStack is = new ItemStack(e.getCurrentItem().getType());
                                    ItemMeta im = is.getItemMeta();
                                    double added = beforeHP * 0.05D;
                                    int newHP = (int)(beforeHP + added);

                                    for(int i = 0; i <= lore.size(); i++)
                                    {
                                        if(lore.get(i).contains("HP"))
                                        {
                                            lore.set(i, ChatColor.RED + "HP: +" + newHP);
                                        }
                                    }

                                    im.setDisplayName(ChatColor.RED + "[+1]" + name);
                                    im.setLore(lore);
                                    fireworkMeta.addEffects(new FireworkEffect[]
                                            {
                                            FireworkEffect.builder().withColor(Color.YELLOW).with(FireworkEffect.Type.BALL).build()
                                            });
                                    fireworkMeta.setPower(0);
                                    firework.setFireworkMeta(fireworkMeta);
                                    e.setCancelled(true);
                                    return;
                                }
                                else
                                {
                                    for(int i = 1; i <= 8; i++)
                                    {
                                        if(i >= 3)
                                        {
                                            int drop = random.nextInt(100) + 1;

                                            if(drop <= 10 * i)
                                            {
                                                p.getWorld().playEffect(p.getLocation(), Effect.EXTINGUISH, 0);
                                                ParticleEffects.LAVA.display(p.getEyeLocation(), 0D, 0F, 0F, 0F, 5, 10);
                                                e.setCurrentItem(null);
                                                return;
                                            }
                                        }
                                        if(name.startsWith("[+" + i + "]"))
                                        {
                                            if(e.getCursor().getAmount() > 1)
                                            {
                                                e.getCursor().setAmount(e.getCursor().getAmount() - 1);
                                            }
                                            else if(e.getCursor().getAmount() == 1)
                                            {
                                                e.setCursor(null);
                                            }

                                            Firework firework = (Firework)p.getWorld().spawn(p.getLocation(), Firework.class);
                                            FireworkMeta fireworkMeta = firework.getFireworkMeta();
                                            ItemStack is = new ItemStack(e.getCurrentItem().getType());
                                            ItemMeta im = is.getItemMeta();
                                            double added = beforeHP * 0.05D;
                                            int newHP = (int)(beforeHP + added);
                                            i += 1;

                                            for(int m = 0; i <= lore.size(); m++)
                                            {
                                                if(lore.get(m).contains("HP"))
                                                {
                                                    lore.set(m, ChatColor.RED + "HP: +" + newHP);
                                                }
                                            }

                                            im.setDisplayName(ChatColor.RED + "[+" + i + "]" + cleanedName);
                                            im.setLore(lore);
                                            fireworkMeta.addEffects(new FireworkEffect[]
                                                    {
                                                            FireworkEffect.builder().withColor(Color.YELLOW).with(FireworkEffect.Type.BALL).build()
                                                    });
                                            fireworkMeta.setPower(0);
                                            firework.setFireworkMeta(fireworkMeta);
                                            e.setCancelled(true);
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                        else if(e.getCursor().getItemMeta().getDisplayName().equals("Scroll: Enchant Chainmail Armor"))
                        {
                            if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta().hasLore() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getType() == Material.CHAINMAIL_BOOTS || e.getCurrentItem().getType() == Material.CHAINMAIL_CHESTPLATE || e.getCurrentItem().getType() == Material.CHAINMAIL_LEGGINGS || e.getCurrentItem().getType() == Material.CHAINMAIL_HELMET)
                            {
                                List<String> lore = e.getCurrentItem().getItemMeta().getLore();
                                String name = e.getCurrentItem().getItemMeta().getDisplayName();
                                String cleanedName = name.substring(7);
                                double beforeHP = DamageEvents.getValueFromLore(e.getCurrentItem(), "HP");

                                if(!name.startsWith("[+"))
                                {
                                    if(e.getCursor().getAmount() > 1)
                                    {
                                        e.getCursor().setAmount(e.getCursor().getAmount() - 1);
                                    }
                                    else if(e.getCursor().getAmount() == 1)
                                    {
                                        e.setCursor(null);
                                    }

                                    Firework firework = (Firework)p.getWorld().spawn(p.getLocation(), Firework.class);
                                    FireworkMeta fireworkMeta = firework.getFireworkMeta();
                                    ItemStack is = new ItemStack(e.getCurrentItem().getType());
                                    ItemMeta im = is.getItemMeta();
                                    double added = beforeHP * 0.05D;
                                    int newHP = (int)(beforeHP + added);

                                    for(int i = 0; i <= lore.size(); i++)
                                    {
                                        if(lore.get(i).contains("HP"))
                                        {
                                            lore.set(i, ChatColor.RED + "HP: +" + newHP);
                                        }
                                    }

                                    im.setDisplayName(ChatColor.RED + "[+1]" + name);
                                    im.setLore(lore);
                                    fireworkMeta.addEffects(new FireworkEffect[]
                                            {
                                                    FireworkEffect.builder().withColor(Color.YELLOW).with(FireworkEffect.Type.BALL).build()
                                            });
                                    fireworkMeta.setPower(0);
                                    firework.setFireworkMeta(fireworkMeta);
                                    e.setCancelled(true);
                                    return;
                                }
                                else
                                {
                                    for(int i = 1; i <= 8; i++)
                                    {
                                        if(i >= 3)
                                        {
                                            int drop = random.nextInt(100) + 1;

                                            if(drop <= 10 * i)
                                            {
                                                p.getWorld().playEffect(p.getLocation(), Effect.EXTINGUISH, 0);
                                                ParticleEffects.LAVA.display(p.getEyeLocation(), 0D, 0F, 0F, 0F, 5, 10);
                                                e.setCurrentItem(null);
                                                return;
                                            }
                                        }
                                        if(name.startsWith("[+" + i + "]"))
                                        {
                                            if(e.getCursor().getAmount() > 1)
                                            {
                                                e.getCursor().setAmount(e.getCursor().getAmount() - 1);
                                            }
                                            else if(e.getCursor().getAmount() == 1)
                                            {
                                                e.setCursor(null);
                                            }

                                            Firework firework = (Firework)p.getWorld().spawn(p.getLocation(), Firework.class);
                                            FireworkMeta fireworkMeta = firework.getFireworkMeta();
                                            ItemStack is = new ItemStack(e.getCurrentItem().getType());
                                            ItemMeta im = is.getItemMeta();
                                            double added = beforeHP * 0.05D;
                                            int newHP = (int)(beforeHP + added);
                                            i += 1;

                                            for(int m = 0; i <= lore.size(); m++)
                                            {
                                                if(lore.get(m).contains("HP"))
                                                {
                                                    lore.set(m, ChatColor.RED + "HP: +" + newHP);
                                                }
                                            }

                                            im.setDisplayName(ChatColor.RED + "[+" + i + "]" + cleanedName);
                                            im.setLore(lore);
                                            fireworkMeta.addEffects(new FireworkEffect[]
                                                    {
                                                            FireworkEffect.builder().withColor(Color.YELLOW).with(FireworkEffect.Type.BALL).build()
                                                    });
                                            fireworkMeta.setPower(0);
                                            firework.setFireworkMeta(fireworkMeta);
                                            e.setCancelled(true);
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                        else if(e.getCursor().getItemMeta().getDisplayName().equals("Scroll: Enchant Iron Armor"))
                        {
                            if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta().hasLore() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getType() == Material.IRON_BOOTS || e.getCurrentItem().getType() == Material.IRON_CHESTPLATE || e.getCurrentItem().getType() == Material.IRON_LEGGINGS || e.getCurrentItem().getType() == Material.IRON_HELMET)
                            {
                                List<String> lore = e.getCurrentItem().getItemMeta().getLore();
                                String name = e.getCurrentItem().getItemMeta().getDisplayName();
                                String cleanedName = name.substring(7);
                                double beforeHP = DamageEvents.getValueFromLore(e.getCurrentItem(), "HP");

                                if(!name.startsWith("[+"))
                                {
                                    if(e.getCursor().getAmount() > 1)
                                    {
                                        e.getCursor().setAmount(e.getCursor().getAmount() - 1);
                                    }
                                    else if(e.getCursor().getAmount() == 1)
                                    {
                                        e.setCursor(null);
                                    }

                                    Firework firework = (Firework)p.getWorld().spawn(p.getLocation(), Firework.class);
                                    FireworkMeta fireworkMeta = firework.getFireworkMeta();
                                    ItemStack is = new ItemStack(e.getCurrentItem().getType());
                                    ItemMeta im = is.getItemMeta();
                                    double added = beforeHP * 0.05D;
                                    int newHP = (int)(beforeHP + added);

                                    for(int i = 0; i <= lore.size(); i++)
                                    {
                                        if(lore.get(i).contains("HP"))
                                        {
                                            lore.set(i, ChatColor.RED + "HP: +" + newHP);
                                        }
                                    }

                                    im.setDisplayName(ChatColor.RED + "[+1]" + name);
                                    im.setLore(lore);
                                    fireworkMeta.addEffects(new FireworkEffect[]
                                            {
                                                    FireworkEffect.builder().withColor(Color.YELLOW).with(FireworkEffect.Type.BALL).build()
                                            });
                                    fireworkMeta.setPower(0);
                                    firework.setFireworkMeta(fireworkMeta);
                                    e.setCancelled(true);
                                    return;
                                }
                                else
                                {
                                    for(int i = 1; i <= 8; i++)
                                    {
                                        if(i >= 3)
                                        {
                                            int drop = random.nextInt(100) + 1;

                                            if(drop <= 10 * i)
                                            {
                                                p.getWorld().playEffect(p.getLocation(), Effect.EXTINGUISH, 0);
                                                ParticleEffects.LAVA.display(p.getEyeLocation(), 0D, 0F, 0F, 0F, 5, 10);
                                                e.setCurrentItem(null);
                                                return;
                                            }
                                        }
                                        if(name.startsWith("[+" + i + "]"))
                                        {
                                            if(e.getCursor().getAmount() > 1)
                                            {
                                                e.getCursor().setAmount(e.getCursor().getAmount() - 1);
                                            }
                                            else if(e.getCursor().getAmount() == 1)
                                            {
                                                e.setCursor(null);
                                            }

                                            Firework firework = (Firework)p.getWorld().spawn(p.getLocation(), Firework.class);
                                            FireworkMeta fireworkMeta = firework.getFireworkMeta();
                                            ItemStack is = new ItemStack(e.getCurrentItem().getType());
                                            ItemMeta im = is.getItemMeta();
                                            double added = beforeHP * 0.05D;
                                            int newHP = (int)(beforeHP + added);
                                            i += 1;

                                            for(int m = 0; i <= lore.size(); m++)
                                            {
                                                if(lore.get(m).contains("HP"))
                                                {
                                                    lore.set(m, ChatColor.RED + "HP: +" + newHP);
                                                }
                                            }

                                            im.setDisplayName(ChatColor.RED + "[+" + i + "]" + cleanedName);
                                            im.setLore(lore);
                                            fireworkMeta.addEffects(new FireworkEffect[]
                                                    {
                                                            FireworkEffect.builder().withColor(Color.YELLOW).with(FireworkEffect.Type.BALL).build()
                                                    });
                                            fireworkMeta.setPower(0);
                                            firework.setFireworkMeta(fireworkMeta);
                                            e.setCancelled(true);
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                        else if(e.getCursor().getItemMeta().getDisplayName().equals("Scroll: Enchant Diamond Armor"))
                        {
                            if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta().hasLore() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getType() == Material.DIAMOND_BOOTS || e.getCurrentItem().getType() == Material.DIAMOND_CHESTPLATE || e.getCurrentItem().getType() == Material.DIAMOND_LEGGINGS || e.getCurrentItem().getType() == Material.DIAMOND_HELMET)
                            {
                                List<String> lore = e.getCurrentItem().getItemMeta().getLore();
                                String name = e.getCurrentItem().getItemMeta().getDisplayName();
                                String cleanedName = name.substring(7);
                                double beforeHP = DamageEvents.getValueFromLore(e.getCurrentItem(), "HP");

                                if(!name.startsWith("[+"))
                                {
                                    if(e.getCursor().getAmount() > 1)
                                    {
                                        e.getCursor().setAmount(e.getCursor().getAmount() - 1);
                                    }
                                    else if(e.getCursor().getAmount() == 1)
                                    {
                                        e.setCursor(null);
                                    }

                                    Firework firework = (Firework)p.getWorld().spawn(p.getLocation(), Firework.class);
                                    FireworkMeta fireworkMeta = firework.getFireworkMeta();
                                    ItemStack is = new ItemStack(e.getCurrentItem().getType());
                                    ItemMeta im = is.getItemMeta();
                                    double added = beforeHP * 0.05D;
                                    int newHP = (int)(beforeHP + added);

                                    for(int i = 0; i <= lore.size(); i++)
                                    {
                                        if(lore.get(i).contains("HP"))
                                        {
                                            lore.set(i, ChatColor.RED + "HP: +" + newHP);
                                        }
                                    }

                                    im.setDisplayName(ChatColor.RED + "[+1]" + name);
                                    im.setLore(lore);
                                    fireworkMeta.addEffects(new FireworkEffect[]
                                            {
                                                    FireworkEffect.builder().withColor(Color.YELLOW).with(FireworkEffect.Type.BALL).build()
                                            });
                                    fireworkMeta.setPower(0);
                                    firework.setFireworkMeta(fireworkMeta);
                                    e.setCancelled(true);
                                    return;
                                }
                                else
                                {
                                    for(int i = 1; i <= 8; i++)
                                    {
                                        if(i >= 3)
                                        {
                                            int drop = random.nextInt(100) + 1;

                                            if(drop <= 10 * i)
                                            {
                                                p.getWorld().playEffect(p.getLocation(), Effect.EXTINGUISH, 0);
                                                ParticleEffects.LAVA.display(p.getEyeLocation(), 0D, 0F, 0F, 0F, 5, 10);
                                                e.setCurrentItem(null);
                                                return;
                                            }
                                        }
                                        if(name.startsWith("[+" + i + "]"))
                                        {
                                            if(e.getCursor().getAmount() > 1)
                                            {
                                                e.getCursor().setAmount(e.getCursor().getAmount() - 1);
                                            }
                                            else if(e.getCursor().getAmount() == 1)
                                            {
                                                e.setCursor(null);
                                            }

                                            Firework firework = (Firework)p.getWorld().spawn(p.getLocation(), Firework.class);
                                            FireworkMeta fireworkMeta = firework.getFireworkMeta();
                                            ItemStack is = new ItemStack(e.getCurrentItem().getType());
                                            ItemMeta im = is.getItemMeta();
                                            double added = beforeHP * 0.05D;
                                            int newHP = (int)(beforeHP + added);
                                            i += 1;

                                            for(int m = 0; i <= lore.size(); m++)
                                            {
                                                if(lore.get(m).contains("HP"))
                                                {
                                                    lore.set(m, ChatColor.RED + "HP: +" + newHP);
                                                }
                                            }

                                            im.setDisplayName(ChatColor.RED + "[+" + i + "]" + cleanedName);
                                            im.setLore(lore);
                                            fireworkMeta.addEffects(new FireworkEffect[]
                                                    {
                                                            FireworkEffect.builder().withColor(Color.YELLOW).with(FireworkEffect.Type.BALL).build()
                                                    });
                                            fireworkMeta.setPower(0);
                                            firework.setFireworkMeta(fireworkMeta);
                                            e.setCancelled(true);
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                        else if(e.getCursor().getItemMeta().getDisplayName().equals("Scroll: Enchant Gold Armor"))
                        {
                            if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta().hasLore() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getType() == Material.GOLD_BOOTS || e.getCurrentItem().getType() == Material.GOLD_CHESTPLATE || e.getCurrentItem().getType() == Material.GOLD_LEGGINGS || e.getCurrentItem().getType() == Material.GOLD_HELMET)
                            {
                                List<String> lore = e.getCurrentItem().getItemMeta().getLore();
                                String name = e.getCurrentItem().getItemMeta().getDisplayName();
                                String cleanedName = name.substring(7);
                                double beforeHP = DamageEvents.getValueFromLore(e.getCurrentItem(), "HP");

                                if(!name.startsWith("[+"))
                                {
                                    if(e.getCursor().getAmount() > 1)
                                    {
                                        e.getCursor().setAmount(e.getCursor().getAmount() - 1);
                                    }
                                    else if(e.getCursor().getAmount() == 1)
                                    {
                                        e.setCursor(null);
                                    }

                                    Firework firework = (Firework)p.getWorld().spawn(p.getLocation(), Firework.class);
                                    FireworkMeta fireworkMeta = firework.getFireworkMeta();
                                    ItemStack is = new ItemStack(e.getCurrentItem().getType());
                                    ItemMeta im = is.getItemMeta();
                                    double added = beforeHP * 0.05D;
                                    int newHP = (int)(beforeHP + added);

                                    for(int i = 0; i <= lore.size(); i++)
                                    {
                                        if(lore.get(i).contains("HP"))
                                        {
                                            lore.set(i, ChatColor.RED + "HP: +" + newHP);
                                        }
                                    }

                                    im.setDisplayName(ChatColor.RED + "[+1]" + name);
                                    im.setLore(lore);
                                    fireworkMeta.addEffects(new FireworkEffect[]
                                            {
                                                    FireworkEffect.builder().withColor(Color.YELLOW).with(FireworkEffect.Type.BALL).build()
                                            });
                                    fireworkMeta.setPower(0);
                                    firework.setFireworkMeta(fireworkMeta);
                                    e.setCancelled(true);
                                    return;
                                }
                                else
                                {
                                    for(int i = 1; i <= 8; i++)
                                    {
                                        if(i >= 3)
                                        {
                                            int drop = random.nextInt(100) + 1;

                                            if(drop <= 10 * i)
                                            {
                                                p.getWorld().playEffect(p.getLocation(), Effect.EXTINGUISH, 0);
                                                ParticleEffects.LAVA.display(p.getEyeLocation(), 0D, 0F, 0F, 0F, 5, 10);
                                                e.setCurrentItem(null);
                                                return;
                                            }
                                        }
                                        if(name.startsWith("[+" + i + "]"))
                                        {
                                            if(e.getCursor().getAmount() > 1)
                                            {
                                                e.getCursor().setAmount(e.getCursor().getAmount() - 1);
                                            }
                                            else if(e.getCursor().getAmount() == 1)
                                            {
                                                e.setCursor(null);
                                            }

                                            Firework firework = (Firework)p.getWorld().spawn(p.getLocation(), Firework.class);
                                            FireworkMeta fireworkMeta = firework.getFireworkMeta();
                                            ItemStack is = new ItemStack(e.getCurrentItem().getType());
                                            ItemMeta im = is.getItemMeta();
                                            double added = beforeHP * 0.05D;
                                            int newHP = (int)(beforeHP + added);
                                            i += 1;

                                            for(int m = 0; i <= lore.size(); m++)
                                            {
                                                if(lore.get(m).contains("HP"))
                                                {
                                                    lore.set(m, ChatColor.RED + "HP: +" + newHP);
                                                }
                                            }

                                            im.setDisplayName(ChatColor.RED + "[+" + i + "]" + cleanedName);
                                            im.setLore(lore);
                                            fireworkMeta.addEffects(new FireworkEffect[]
                                                    {
                                                            FireworkEffect.builder().withColor(Color.YELLOW).with(FireworkEffect.Type.BALL).build()
                                                    });
                                            fireworkMeta.setPower(0);
                                            firework.setFireworkMeta(fireworkMeta);
                                            e.setCancelled(true);
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                        else if(e.getCursor().getItemMeta().getDisplayName().equals("Scroll: Enchant Wooden Weapon"))
                        {
                            if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta().hasLore() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getType() == Material.WOOD_AXE || e.getCurrentItem().getType() == Material.WOOD_SWORD)
                            {
                                List<String> lore = e.getCurrentItem().getItemMeta().getLore();
                                String name = e.getCurrentItem().getItemMeta().getDisplayName();
                                String cleanedName = name.substring(7);
                                int beforeMin = DamageEvents.getMinValueFromLore(e.getCurrentItem(), "DMG");
                                int beforeMax = DamageEvents.getMaxValueFromLore(e.getCurrentItem(), "DMG");

                                if(!name.startsWith("[+"))
                                {
                                    if(e.getCursor().getAmount() > 1)
                                    {
                                        e.getCursor().setAmount(e.getCursor().getAmount() - 1);
                                    }
                                    else if(e.getCursor().getAmount() == 1)
                                    {
                                        e.setCursor(null);
                                    }

                                    Firework firework = (Firework)p.getWorld().spawn(p.getLocation(), Firework.class);
                                    FireworkMeta fireworkMeta = firework.getFireworkMeta();
                                    ItemStack is = new ItemStack(e.getCurrentItem().getType());
                                    ItemMeta im = is.getItemMeta();
                                    double addedMin = beforeMin * 0.05D;
                                    double addedMax = beforeMax * 0.05D;
                                    int newMin = (int)(beforeMin + addedMin);
                                    int newMax = (int)(beforeMax + addedMax);

                                    for(int i = 0; i <= lore.size(); i++)
                                    {
                                        if(lore.get(i).contains("HP"))
                                        {
                                            lore.set(i, ChatColor.RED + "DMG: +" + newMin + " - " + newMax);
                                        }
                                    }

                                    im.setDisplayName(ChatColor.RED + "[+1]" + name);
                                    im.setLore(lore);
                                    fireworkMeta.addEffects(new FireworkEffect[]
                                            {
                                                    FireworkEffect.builder().withColor(Color.YELLOW).with(FireworkEffect.Type.BALL).build()
                                            });
                                    fireworkMeta.setPower(0);
                                    firework.setFireworkMeta(fireworkMeta);
                                    e.setCancelled(true);
                                    return;
                                }
                                else
                                {
                                    for(int i = 1; i <= 8; i++)
                                    {
                                        if(i >= 3)
                                        {
                                            int drop = random.nextInt(100) + 1;

                                            if(drop <= 10 * i)
                                            {
                                                p.getWorld().playEffect(p.getLocation(), Effect.EXTINGUISH, 0);
                                                ParticleEffects.LAVA.display(p.getEyeLocation(), 0D, 0F, 0F, 0F, 5, 10);
                                                e.setCurrentItem(null);
                                                return;
                                            }
                                        }
                                        if(name.startsWith("[+" + i + "]"))
                                        {
                                            if(e.getCursor().getAmount() > 1)
                                            {
                                                e.getCursor().setAmount(e.getCursor().getAmount() - 1);
                                            }
                                            else if(e.getCursor().getAmount() == 1)
                                            {
                                                e.setCursor(null);
                                            }

                                            Firework firework = (Firework)p.getWorld().spawn(p.getLocation(), Firework.class);
                                            FireworkMeta fireworkMeta = firework.getFireworkMeta();
                                            ItemStack is = new ItemStack(e.getCurrentItem().getType());
                                            ItemMeta im = is.getItemMeta();
                                            double addedMin = beforeMin * 0.05D;
                                            double addedMax = beforeMax * 0.05D;
                                            int newMin = (int)(beforeMin + addedMin);
                                            int newMax = (int)(beforeMax + addedMax);
                                            i += 1;

                                            for(int m = 0; i <= lore.size(); m++)
                                            {
                                                if(lore.get(m).contains("DMG"))
                                                {
                                                    lore.set(m, ChatColor.RED + "DMG: +" + newMin + " - " + newMax);
                                                }
                                            }

                                            im.setDisplayName(ChatColor.RED + "[+" + i + "]" + cleanedName);
                                            im.setLore(lore);
                                            fireworkMeta.addEffects(new FireworkEffect[]
                                                    {
                                                            FireworkEffect.builder().withColor(Color.YELLOW).with(FireworkEffect.Type.BALL).build()
                                                    });
                                            fireworkMeta.setPower(0);
                                            firework.setFireworkMeta(fireworkMeta);
                                            e.setCancelled(true);
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                        else if(e.getCursor().getItemMeta().getDisplayName().equals("Scroll: Enchant Stone Weapon"))
                        {
                            if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta().hasLore() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getType() == Material.STONE_AXE || e.getCurrentItem().getType() == Material.STONE_SWORD)
                            {
                                List<String> lore = e.getCurrentItem().getItemMeta().getLore();
                                String name = e.getCurrentItem().getItemMeta().getDisplayName();
                                String cleanedName = name.substring(7);
                                int beforeMin = DamageEvents.getMinValueFromLore(e.getCurrentItem(), "DMG");
                                int beforeMax = DamageEvents.getMaxValueFromLore(e.getCurrentItem(), "DMG");

                                if(!name.startsWith("[+"))
                                {
                                    if(e.getCursor().getAmount() > 1)
                                    {
                                        e.getCursor().setAmount(e.getCursor().getAmount() - 1);
                                    }
                                    else if(e.getCursor().getAmount() == 1)
                                    {
                                        e.setCursor(null);
                                    }

                                    Firework firework = (Firework)p.getWorld().spawn(p.getLocation(), Firework.class);
                                    FireworkMeta fireworkMeta = firework.getFireworkMeta();
                                    ItemStack is = new ItemStack(e.getCurrentItem().getType());
                                    ItemMeta im = is.getItemMeta();
                                    double addedMin = beforeMin * 0.05D;
                                    double addedMax = beforeMax * 0.05D;
                                    int newMin = (int)(beforeMin + addedMin);
                                    int newMax = (int)(beforeMax + addedMax);

                                    for(int i = 0; i <= lore.size(); i++)
                                    {
                                        if(lore.get(i).contains("HP"))
                                        {
                                            lore.set(i, ChatColor.RED + "DMG: +" + newMin + " - " + newMax);
                                        }
                                    }

                                    im.setDisplayName(ChatColor.RED + "[+1]" + name);
                                    im.setLore(lore);
                                    fireworkMeta.addEffects(new FireworkEffect[]
                                            {
                                                    FireworkEffect.builder().withColor(Color.YELLOW).with(FireworkEffect.Type.BALL).build()
                                            });
                                    fireworkMeta.setPower(0);
                                    firework.setFireworkMeta(fireworkMeta);
                                    e.setCancelled(true);
                                    return;
                                }
                                else
                                {
                                    for(int i = 1; i <= 8; i++)
                                    {
                                        if(i >= 3)
                                        {
                                            int drop = random.nextInt(100) + 1;

                                            if(drop <= 10 * i)
                                            {
                                                p.getWorld().playEffect(p.getLocation(), Effect.EXTINGUISH, 0);
                                                ParticleEffects.LAVA.display(p.getEyeLocation(), 0D, 0F, 0F, 0F, 5, 10);
                                                e.setCurrentItem(null);
                                                return;
                                            }
                                        }
                                        if(name.startsWith("[+" + i + "]"))
                                        {
                                            if(e.getCursor().getAmount() > 1)
                                            {
                                                e.getCursor().setAmount(e.getCursor().getAmount() - 1);
                                            }
                                            else if(e.getCursor().getAmount() == 1)
                                            {
                                                e.setCursor(null);
                                            }

                                            Firework firework = (Firework)p.getWorld().spawn(p.getLocation(), Firework.class);
                                            FireworkMeta fireworkMeta = firework.getFireworkMeta();
                                            ItemStack is = new ItemStack(e.getCurrentItem().getType());
                                            ItemMeta im = is.getItemMeta();
                                            double addedMin = beforeMin * 0.05D;
                                            double addedMax = beforeMax * 0.05D;
                                            int newMin = (int)(beforeMin + addedMin);
                                            int newMax = (int)(beforeMax + addedMax);
                                            i += 1;

                                            for(int m = 0; i <= lore.size(); m++)
                                            {
                                                if(lore.get(m).contains("DMG"))
                                                {
                                                    lore.set(m, ChatColor.RED + "DMG: +" + newMin + " - " + newMax);
                                                }
                                            }

                                            im.setDisplayName(ChatColor.RED + "[+" + i + "]" + cleanedName);
                                            im.setLore(lore);
                                            fireworkMeta.addEffects(new FireworkEffect[]
                                                    {
                                                            FireworkEffect.builder().withColor(Color.YELLOW).with(FireworkEffect.Type.BALL).build()
                                                    });
                                            fireworkMeta.setPower(0);
                                            firework.setFireworkMeta(fireworkMeta);
                                            e.setCancelled(true);
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                        else if(e.getCursor().getItemMeta().getDisplayName().equals("Scroll: Enchant Iron Weapon"))
                        {
                            if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta().hasLore() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getType() == Material.IRON_AXE || e.getCurrentItem().getType() == Material.IRON_SWORD)
                            {
                                List<String> lore = e.getCurrentItem().getItemMeta().getLore();
                                String name = e.getCurrentItem().getItemMeta().getDisplayName();
                                String cleanedName = name.substring(7);
                                int beforeMin = DamageEvents.getMinValueFromLore(e.getCurrentItem(), "DMG");
                                int beforeMax = DamageEvents.getMaxValueFromLore(e.getCurrentItem(), "DMG");

                                if(!name.startsWith("[+"))
                                {
                                    if(e.getCursor().getAmount() > 1)
                                    {
                                        e.getCursor().setAmount(e.getCursor().getAmount() - 1);
                                    }
                                    else if(e.getCursor().getAmount() == 1)
                                    {
                                        e.setCursor(null);
                                    }

                                    Firework firework = (Firework)p.getWorld().spawn(p.getLocation(), Firework.class);
                                    FireworkMeta fireworkMeta = firework.getFireworkMeta();
                                    ItemStack is = new ItemStack(e.getCurrentItem().getType());
                                    ItemMeta im = is.getItemMeta();
                                    double addedMin = beforeMin * 0.05D;
                                    double addedMax = beforeMax * 0.05D;
                                    int newMin = (int)(beforeMin + addedMin);
                                    int newMax = (int)(beforeMax + addedMax);

                                    for(int i = 0; i <= lore.size(); i++)
                                    {
                                        if(lore.get(i).contains("HP"))
                                        {
                                            lore.set(i, ChatColor.RED + "DMG: +" + newMin + " - " + newMax);
                                        }
                                    }

                                    im.setDisplayName(ChatColor.RED + "[+1]" + name);
                                    im.setLore(lore);
                                    fireworkMeta.addEffects(new FireworkEffect[]
                                            {
                                                    FireworkEffect.builder().withColor(Color.YELLOW).with(FireworkEffect.Type.BALL).build()
                                            });
                                    fireworkMeta.setPower(0);
                                    firework.setFireworkMeta(fireworkMeta);
                                    e.setCancelled(true);
                                    return;
                                }
                                else
                                {
                                    for(int i = 1; i <= 8; i++)
                                    {
                                        if(i >= 3)
                                        {
                                            int drop = random.nextInt(100) + 1;

                                            if(drop <= 10 * i)
                                            {
                                                p.getWorld().playEffect(p.getLocation(), Effect.EXTINGUISH, 0);
                                                ParticleEffects.LAVA.display(p.getEyeLocation(), 0D, 0F, 0F, 0F, 5, 10);
                                                e.setCurrentItem(null);
                                                return;
                                            }
                                        }
                                        if(name.startsWith("[+" + i + "]"))
                                        {
                                            if(e.getCursor().getAmount() > 1)
                                            {
                                                e.getCursor().setAmount(e.getCursor().getAmount() - 1);
                                            }
                                            else if(e.getCursor().getAmount() == 1)
                                            {
                                                e.setCursor(null);
                                            }

                                            Firework firework = (Firework)p.getWorld().spawn(p.getLocation(), Firework.class);
                                            FireworkMeta fireworkMeta = firework.getFireworkMeta();
                                            ItemStack is = new ItemStack(e.getCurrentItem().getType());
                                            ItemMeta im = is.getItemMeta();
                                            double addedMin = beforeMin * 0.05D;
                                            double addedMax = beforeMax * 0.05D;
                                            int newMin = (int)(beforeMin + addedMin);
                                            int newMax = (int)(beforeMax + addedMax);
                                            i += 1;

                                            for(int m = 0; i <= lore.size(); m++)
                                            {
                                                if(lore.get(m).contains("DMG"))
                                                {
                                                    lore.set(m, ChatColor.RED + "DMG: +" + newMin + " - " + newMax);
                                                }
                                            }

                                            im.setDisplayName(ChatColor.RED + "[+" + i + "]" + cleanedName);
                                            im.setLore(lore);
                                            fireworkMeta.addEffects(new FireworkEffect[]
                                                    {
                                                            FireworkEffect.builder().withColor(Color.YELLOW).with(FireworkEffect.Type.BALL).build()
                                                    });
                                            fireworkMeta.setPower(0);
                                            firework.setFireworkMeta(fireworkMeta);
                                            e.setCancelled(true);
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                        else if(e.getCursor().getItemMeta().getDisplayName().equals("Scroll: Enchant Diamond Weapon"))
                        {
                            if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta().hasLore() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getType() == Material.DIAMOND_AXE || e.getCurrentItem().getType() == Material.DIAMOND_SWORD)
                            {
                                List<String> lore = e.getCurrentItem().getItemMeta().getLore();
                                String name = e.getCurrentItem().getItemMeta().getDisplayName();
                                String cleanedName = name.substring(7);
                                int beforeMin = DamageEvents.getMinValueFromLore(e.getCurrentItem(), "DMG");
                                int beforeMax = DamageEvents.getMaxValueFromLore(e.getCurrentItem(), "DMG");

                                if(!name.startsWith("[+"))
                                {
                                    if(e.getCursor().getAmount() > 1)
                                    {
                                        e.getCursor().setAmount(e.getCursor().getAmount() - 1);
                                    }
                                    else if(e.getCursor().getAmount() == 1)
                                    {
                                        e.setCursor(null);
                                    }

                                    Firework firework = (Firework)p.getWorld().spawn(p.getLocation(), Firework.class);
                                    FireworkMeta fireworkMeta = firework.getFireworkMeta();
                                    ItemStack is = new ItemStack(e.getCurrentItem().getType());
                                    ItemMeta im = is.getItemMeta();
                                    double addedMin = beforeMin * 0.05D;
                                    double addedMax = beforeMax * 0.05D;
                                    int newMin = (int)(beforeMin + addedMin);
                                    int newMax = (int)(beforeMax + addedMax);

                                    for(int i = 0; i <= lore.size(); i++)
                                    {
                                        if(lore.get(i).contains("HP"))
                                        {
                                            lore.set(i, ChatColor.RED + "DMG: +" + newMin + " - " + newMax);
                                        }
                                    }

                                    im.setDisplayName(ChatColor.RED + "[+1]" + name);
                                    im.setLore(lore);
                                    fireworkMeta.addEffects(new FireworkEffect[]
                                            {
                                                    FireworkEffect.builder().withColor(Color.YELLOW).with(FireworkEffect.Type.BALL).build()
                                            });
                                    fireworkMeta.setPower(0);
                                    firework.setFireworkMeta(fireworkMeta);
                                    e.setCancelled(true);
                                    return;
                                }
                                else
                                {
                                    for(int i = 1; i <= 8; i++)
                                    {
                                        if(i >= 3)
                                        {
                                            int drop = random.nextInt(100) + 1;

                                            if(drop <= 10 * i)
                                            {
                                                p.getWorld().playEffect(p.getLocation(), Effect.EXTINGUISH, 0);
                                                ParticleEffects.LAVA.display(p.getEyeLocation(), 0D, 0F, 0F, 0F, 5, 10);
                                                e.setCurrentItem(null);
                                                return;
                                            }
                                        }
                                        if(name.startsWith("[+" + i + "]"))
                                        {
                                            if(e.getCursor().getAmount() > 1)
                                            {
                                                e.getCursor().setAmount(e.getCursor().getAmount() - 1);
                                            }
                                            else if(e.getCursor().getAmount() == 1)
                                            {
                                                e.setCursor(null);
                                            }

                                            Firework firework = (Firework)p.getWorld().spawn(p.getLocation(), Firework.class);
                                            FireworkMeta fireworkMeta = firework.getFireworkMeta();
                                            ItemStack is = new ItemStack(e.getCurrentItem().getType());
                                            ItemMeta im = is.getItemMeta();
                                            double addedMin = beforeMin * 0.05D;
                                            double addedMax = beforeMax * 0.05D;
                                            int newMin = (int)(beforeMin + addedMin);
                                            int newMax = (int)(beforeMax + addedMax);
                                            i += 1;

                                            for(int m = 0; i <= lore.size(); m++)
                                            {
                                                if(lore.get(m).contains("DMG"))
                                                {
                                                    lore.set(m, ChatColor.RED + "DMG: +" + newMin + " - " + newMax);
                                                }
                                            }

                                            im.setDisplayName(ChatColor.RED + "[+" + i + "]" + cleanedName);
                                            im.setLore(lore);
                                            fireworkMeta.addEffects(new FireworkEffect[]
                                                    {
                                                            FireworkEffect.builder().withColor(Color.YELLOW).with(FireworkEffect.Type.BALL).build()
                                                    });
                                            fireworkMeta.setPower(0);
                                            firework.setFireworkMeta(fireworkMeta);
                                            e.setCancelled(true);
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                        else if(e.getCursor().getItemMeta().getDisplayName().equals("Scroll: Enchant Gold Weapon"))
                        {
                            if (e.getCurrentItem() != null && e.getCurrentItem().getItemMeta().hasLore() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getType() == Material.GOLD_AXE || e.getCurrentItem().getType() == Material.GOLD_SWORD)
                            {
                                List<String> lore = e.getCurrentItem().getItemMeta().getLore();
                                String name = e.getCurrentItem().getItemMeta().getDisplayName();
                                String cleanedName = name.substring(7);
                                int beforeMin = DamageEvents.getMinValueFromLore(e.getCurrentItem(), "DMG");
                                int beforeMax = DamageEvents.getMaxValueFromLore(e.getCurrentItem(), "DMG");

                                if(!name.startsWith("[+"))
                                {
                                    if(e.getCursor().getAmount() > 1)
                                    {
                                        e.getCursor().setAmount(e.getCursor().getAmount() - 1);
                                    }
                                    else if(e.getCursor().getAmount() == 1)
                                    {
                                        e.setCursor(null);
                                    }

                                    Firework firework = (Firework)p.getWorld().spawn(p.getLocation(), Firework.class);
                                    FireworkMeta fireworkMeta = firework.getFireworkMeta();
                                    ItemStack is = new ItemStack(e.getCurrentItem().getType());
                                    ItemMeta im = is.getItemMeta();
                                    double addedMin = beforeMin * 0.05D;
                                    double addedMax = beforeMax * 0.05D;
                                    int newMin = (int)(beforeMin + addedMin);
                                    int newMax = (int)(beforeMax + addedMax);

                                    for(int i = 0; i <= lore.size(); i++)
                                    {
                                        if(lore.get(i).contains("HP"))
                                        {
                                            lore.set(i, ChatColor.RED + "DMG: +" + newMin + " - " + newMax);
                                        }
                                    }

                                    im.setDisplayName(ChatColor.RED + "[+1]" + name);
                                    im.setLore(lore);
                                    fireworkMeta.addEffects(new FireworkEffect[]
                                            {
                                                    FireworkEffect.builder().withColor(Color.YELLOW).with(FireworkEffect.Type.BALL).build()
                                            });
                                    fireworkMeta.setPower(0);
                                    firework.setFireworkMeta(fireworkMeta);
                                    e.setCancelled(true);
                                    return;
                                }
                                else
                                {
                                    for(int i = 1; i <= 8; i++)
                                    {
                                        if(i >= 3)
                                        {
                                            int drop = random.nextInt(100) + 1;

                                            if(drop <= 10 * i)
                                            {
                                                p.getWorld().playEffect(p.getLocation(), Effect.EXTINGUISH, 0);
                                                ParticleEffects.LAVA.display(p.getEyeLocation(), 0D, 0F, 0F, 0F, 5, 10);
                                                e.setCurrentItem(null);
                                                return;
                                            }
                                        }
                                        if(name.startsWith("[+" + i + "]"))
                                        {
                                            if(e.getCursor().getAmount() > 1)
                                            {
                                                e.getCursor().setAmount(e.getCursor().getAmount() - 1);
                                            }
                                            else if(e.getCursor().getAmount() == 1)
                                            {
                                                e.setCursor(null);
                                            }

                                            Firework firework = (Firework)p.getWorld().spawn(p.getLocation(), Firework.class);
                                            FireworkMeta fireworkMeta = firework.getFireworkMeta();
                                            ItemStack is = new ItemStack(e.getCurrentItem().getType());
                                            ItemMeta im = is.getItemMeta();
                                            double addedMin = beforeMin * 0.05D;
                                            double addedMax = beforeMax * 0.05D;
                                            int newMin = (int)(beforeMin + addedMin);
                                            int newMax = (int)(beforeMax + addedMax);
                                            i += 1;

                                            for(int m = 0; i <= lore.size(); m++)
                                            {
                                                if(lore.get(m).contains("DMG"))
                                                {
                                                    lore.set(m, ChatColor.RED + "DMG: +" + newMin + " - " + newMax);
                                                }
                                            }

                                            im.setDisplayName(ChatColor.RED + "[+" + i + "]" + cleanedName);
                                            im.setLore(lore);
                                            fireworkMeta.addEffects(new FireworkEffect[]
                                                    {
                                                            FireworkEffect.builder().withColor(Color.YELLOW).with(FireworkEffect.Type.BALL).build()
                                                    });
                                            fireworkMeta.setPower(0);
                                            firework.setFireworkMeta(fireworkMeta);
                                            e.setCancelled(true);
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
