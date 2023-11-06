package de.plk.core.base.spigot.inventory;

import de.plk.core.api.spigot.inventory.IInventory;
import de.plk.core.api.spigot.inventory.IInventoryManager;
import de.plk.core.base.utils.Manager;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SoftwareBuilds
 * @since 06.08.2023 20:23
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class InventoryManager extends Manager<IInventory> implements IInventoryManager {

    /**
     * The active inventories on the server.
     */
    private final Map<Player, IInventory> activeInventories = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void openInventory(Player player, IInventory inventory) {
        activeInventories.remove(player);
        activeInventories.put(player, inventory);

        player.openInventory(buildSpigotInventory(inventory));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateInventory(Player player, IInventory inventory) {
        activeInventories.remove(player);
        activeInventories.put(player, inventory);

        player.openInventory(buildSpigotInventory(inventory));
    }

    @Override
    public Listener getInventoryListener() {
        return new InventoryListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void closeInventory(Player player) {
        activeInventories.remove(player);
    }

    /**
     * Builds the inventory for minecraft.
     *
     * @param inventory The simple inventory.
     *
     * @return The built minecraft inventory.
     */
    private static Inventory buildSpigotInventory(IInventory inventory) {
        if (inventory.getInventorySize() % 9 != 0 || inventory.getInventorySize() > 9*5) {
            throw new RuntimeException("Das Inventar ist zu groß oder nicht durch neun teilbar.");
        }

        Inventory spigotInventory = Bukkit.createInventory(
                null, inventory.getInventorySize(), inventory.getInventoryTitle()
        );

        if (inventory.getInventoryContents().size() > spigotInventory.getSize())
            throw new RuntimeException("Du hast mehr items, als wie dein Inventar groß ist.");

        inventory.getInventoryContents().forEach((slot, item) -> spigotInventory.setItem(slot, item.getItemStack()));

        return spigotInventory;
    }
}
