package de.plk.core.base.spigot.inventory;

import de.plk.core.api.spigot.inventory.IInventory;
import de.plk.core.api.spigot.inventory.IInventoryManager;
import de.plk.core.api.spigot.inventory.InventoryByIdFilter;
import de.plk.core.base.utils.Manager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author SoftwareBuilds
 * @since 06.08.2023 20:23
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class InventoryManager extends Manager<IInventory> implements IInventoryManager {

    /**
     * The active inventories on the server.
     * Concurrent because a lot of players have inventories switching at the same time.
     */
    private final Map<Player, IInventory> activeInventories = new ConcurrentHashMap<>();

    /**
     * The plugin instance.
     */
    private final JavaPlugin plugin;

    /**
     * Construct the inventory manager.
     *
     * @param plugin The plugin instance
     */
    public InventoryManager(JavaPlugin plugin) {
        this.plugin = plugin;

        // Register the base inventory listener.
        Bukkit.getPluginManager().registerEvents(new InventoryListener(this), plugin);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void openInventory(Player player, IInventory inventory) {
        activeInventories.put(player, inventory);

        player.openInventory(buildSpigotInventory(inventory));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IInventory createInventory(String inventoryIdentifier, String title, int inventorySize) {
        IInventory inventory = new VersatileInventory(inventoryIdentifier);
        inventory.setInventoryTitle(title);
        inventory.setInventorySize(inventorySize);

        if (elements.contains(inventory))
            return inventory;

        add(inventory);

        return inventory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IInventory createInventory(String inventoryIdentifier, String title, int inventorySize, boolean fullUnlickable) {
        IInventory inventory = createInventory(inventoryIdentifier, title, inventorySize);

        inventory.setFullUnclickable(true);

        return inventory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Player, IInventory> getAllActiveInventories() {
        return activeInventories;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<IInventory> getInventoryByPlayer(Player player) {
        return Optional.ofNullable(activeInventories.get(player));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<IInventory> getInventoryByIdentifier(InventoryByIdFilter inventoryByIdFilter) {
        return Optional.ofNullable(getFirstByFilter(inventoryByIdFilter));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerAllListeners() {
        getAll().stream().map(IInventory::getInventoryListener).filter(Objects::nonNull).forEach(inventory -> {
            Bukkit.getPluginManager().registerEvents(inventory, plugin);
        });
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
        if (inventory.getInventorySize() % 9 != 0 || inventory.getInventorySize() > 9 * 6) {
            throw new RuntimeException("Das Inventar ist zu groß oder nicht durch neun teilbar.");
        }

        // Create spigot inventory base.
        Inventory spigotInventory = Bukkit.createInventory(
                null, inventory.getInventorySize(), inventory.getInventoryTitle()
        );

        if (inventory.getInventoryContents().size() > spigotInventory.getSize())
            throw new RuntimeException("Du hast mehr items, als wie dein Inventar groß ist.");

        // Set items to the inventory.
        inventory.getInventoryContents().forEach((slot, item) -> spigotInventory.setItem(slot, item.getItemStack()));

        return spigotInventory;
    }

}
