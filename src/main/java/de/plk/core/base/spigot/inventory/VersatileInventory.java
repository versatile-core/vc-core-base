package de.plk.core.base.spigot.inventory;

import de.plk.core.api.code.NotNull;
import de.plk.core.api.code.Nullable;
import de.plk.core.api.spigot.inventory.IInventory;
import de.plk.core.api.spigot.inventory.item.IItem;
import de.plk.core.base.utils.Identification;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SoftwareBuilds
 * @since 18.11.2023 19:48
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class VersatileInventory extends Identification implements IInventory {

    /**
     * The inventory contents.
     */
    @NotNull
    private final Map<Integer, IItem> contents = new HashMap<>();

    /**
     * The inventory title.
     */
    @NotNull
    private String title;

    /**
     * The inventory size.
     */
    private int inventorySize;

    /**
     * True of the inventory has no movable items.
     */
    private boolean fullUnclickable;

    /**
     * The inventory listener.
     */
    @Nullable
    private Listener listener;

    /**
     * Constructs a versatile inventory.
     *
     * @param inventoryIdentifier The inventory identifier.
     */
    public VersatileInventory(@NotNull String inventoryIdentifier) {
        super(inventoryIdentifier);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInventoryTitle() {
        return title;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInventoryTitle(String title) {
        this.title = title;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getInventorySize() {
        return inventorySize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInventorySize(int inventorySize) {
        this.inventorySize = inventorySize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean fullUnclickable() {
        return fullUnclickable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFullUnclickable(boolean fullUnclickable) {
        this.fullUnclickable = fullUnclickable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Integer, IItem> getInventoryContents() {
        return contents;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Listener getListener() {
        return listener;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setListener(Listener listener) {
        this.listener = listener;
    }

}
