package me.pyra0.easygui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public interface GUI extends InventoryHolder {
    default boolean onClick(Player p, ItemStack currentItem, ItemStack cursorItem, ClickType clickType, int rawSlot) {
        Boolean result = GUIStack.executeIfGUIStack(currentItem);
        if (result != null) return result;
        return true;
    }
    default void onOpen(Player p) {}
    default void onClose(Player p) {}
}
