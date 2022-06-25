package me.pyra0.easygui;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.WeakHashMap;

public class GUIStack {
    private static final WeakHashMap<ItemStack, GUIStack> guiStackMap = new WeakHashMap<>();
    protected GUIExecutable executable;
    protected ItemStack item;

    public GUIStack(ItemStack item) {
        this.item = item;
        guiStackMap.put(this.item, this);
    }

    public GUIStack(ItemStack item, GUIExecutable executable) {
        this(item);
        this.executable = executable;
    }

    public static Boolean executeIfGUIStack(ItemStack item) {
        if (guiStackMap.containsKey(item)) return guiStackMap.get(item).execute();
        return null;
    }

    public boolean execute() {
        return executable.execute();
    }

    public GUIExecutable getExecutable() {
        return executable;
    }

    public void setExecutable(GUIExecutable executable) {
        this.executable = executable;
    }
}