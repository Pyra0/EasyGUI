package me.pyra0.easygui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.Plugin;

public class GUIListener implements Listener {

    private final Plugin plugin;
    private boolean registered;

    public GUIListener(Plugin pl) {
        this.plugin = pl;
        this.registered = false;
    }

    public void register() {
        if (!registered) {
            Bukkit.getPluginManager().registerEvents(this, this.plugin);
            registered = true;
        }
    }

    public void unregister() {
        if (registered) {
            HandlerList.unregisterAll(this);
            registered = false;
        }
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent e) {
        InventoryHolder holder = e.getInventory().getHolder();
        if (holder instanceof GUI) ((GUI) holder).onOpen((Player) e.getPlayer());
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        InventoryHolder holder = e.getInventory().getHolder();
        if (holder instanceof GUI) {
            GUI gui = (GUI) holder;
            boolean result = gui.onClick((Player) e.getWhoClicked(), e.getCurrentItem(), e.getCursor(), e.getClick(), e.getRawSlot());
            e.setCancelled(result);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        InventoryHolder holder = e.getInventory().getHolder();
        if (holder instanceof GUI) ((GUI) holder).onClose((Player) e.getPlayer());
    }
}
