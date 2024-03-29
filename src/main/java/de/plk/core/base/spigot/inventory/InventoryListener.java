package de.plk.core.base.spigot.inventory;

import de.plk.core.api.code.NotNull;
import de.plk.core.api.spigot.inventory.IInventory;
import de.plk.core.api.spigot.inventory.IInventoryManager;
import de.plk.core.api.spigot.inventory.item.IClickableItem;
import de.plk.core.api.spigot.inventory.item.IItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Map;
import java.util.Optional;

/**
 * @author SoftwareBuilds
 * @since 10.08.2023 13:13
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class InventoryListener implements Listener {

    /**
     * The inventory manager.
     */
    @NotNull
    private final IInventoryManager inventoryManager;

    /**
     * Construct the listener for inventories.
     *
     * @param inventoryManager The inventory manager.
     */
    public InventoryListener(@NotNull IInventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    /**
     * The click event listed to the inventory listener.
     *
     * @param event The click event.
     */
    @EventHandler
    public void handle(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) return;
        if (!(event.getWhoClicked() instanceof Player)) return;

        Optional<IInventory> inventoryOptional = inventoryManager.getInventoryByPlayer(null); // TODO: player selection

        if (inventoryOptional.isPresent()) {
            IInventory inventory = inventoryOptional.get();

            if (inventory.fullUnclickable()) {
                event.setCancelled(true);
            }

            // Iterate the items and accept if it is a clickable one.
            Map<Integer, IItem> contents = inventory.getInventoryContents();
            if (contents.get(event.getSlot()) instanceof IClickableItem) {
                event.setCancelled(true);
                ((IClickableItem) contents.get(event.getSlot())).getClickEvent().onClick(event);
            }
        }
    }

    /**
     * The close event listed to the inventory listener.
     *
     * @param event The close event.
     */
    @EventHandler
    public void handle(PlayerQuitEvent event) {
        inventoryManager.closeInventory(null); // TODO: player selection
    }

    /**
     * The close event listed to the inventory listener.
     *
     * @param event The close event.
     */
    @EventHandler
    public void handle(InventoryCloseEvent event) {
        if (event.getPlayer() instanceof Player) {
            inventoryManager.closeInventory(null); // TODO: (Player) event.getPlayer() selection
        }
    }

}
