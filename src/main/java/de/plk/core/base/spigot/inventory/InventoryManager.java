package de.plk.core.base.spigot.inventory;

import de.plk.core.api.AbstractVersatileSpigot;
import de.plk.core.api.code.NotNull;
import de.plk.core.api.entity.ISpigotPlayer;
import de.plk.core.api.spigot.inventory.IInventory;
import de.plk.core.api.spigot.inventory.IInventoryManager;
import de.plk.core.api.utils.IListenable;
import de.plk.core.base.utils.Manager;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

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
    @NotNull
    private final Map<ISpigotPlayer, IInventory> activeInventories = new ConcurrentHashMap<>();

    /**
     * The plugin instance.
     */
    @NotNull
    private final AbstractVersatileSpigot plugin;

    /**
     * Construct the inventory manager.
     *
     * @param plugin The plugin instance
     */
    public InventoryManager(@NotNull AbstractVersatileSpigot plugin) {
        this.plugin = plugin;

        // Register the base inventory listener.
        Bukkit.getPluginManager().registerEvents(new InventoryListener(this), plugin);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void openInventory(@NotNull ISpigotPlayer spigotPlayer, @NotNull IInventory inventory) {
        activeInventories.put(spigotPlayer, inventory);
        spigotPlayer.getPlayer().openInventory(buildSpigotInventory(inventory));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IInventory createInventory(
            @NotNull String inventoryIdentifier,
            @NotNull String title,
            int inventorySize
    ) {
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
    public IInventory createInventory(
            @NotNull String inventoryIdentifier,
            @NotNull String title,
            int inventorySize,
            boolean fullUnlickable
    ) {
        IInventory inventory = createInventory(inventoryIdentifier, title, inventorySize);

        inventory.setFullUnclickable(true);

        return inventory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<ISpigotPlayer, IInventory> getAllActiveInventories() {
        return activeInventories;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<IInventory> getInventoryByPlayer(@NotNull ISpigotPlayer spigotPlayer) {
        return Optional.ofNullable(activeInventories.get(spigotPlayer));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerAllListeners() {
        getAll().stream().map(IListenable::getListener).filter(Objects::nonNull).forEach(inventory ->
            Bukkit.getPluginManager().registerEvents(inventory, plugin)
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void closeInventory(@NotNull ISpigotPlayer spigotPlayer) {
        activeInventories.remove(spigotPlayer);
    }

    /**
     * Builds the inventory for minecraft.
     *
     * @param inventory The simple inventory.
     *
     * @return The built minecraft inventory.
     */
    private static Inventory buildSpigotInventory(@NotNull IInventory inventory) {
        if (inventory.getInventorySize() % 9 != 0 || inventory.getInventorySize() > 9 * 6) {
            throw new RuntimeException("The inventory is too large or cannot be divided by nine.");
        }

        // Create spigot inventory base.
        Inventory spigotInventory = Bukkit.createInventory(
                null,
                inventory.getInventorySize(),
                inventory.getInventoryTitle()
        );

        if (inventory.getInventoryContents().size() > spigotInventory.getSize())
            throw new RuntimeException("You have more items than the size of your inventory");

        // Set items to the inventory.
        inventory.getInventoryContents().forEach((slot, item) ->
                spigotInventory.setItem(slot, item.getItemStack())
        );

        return spigotInventory;
    }

}
