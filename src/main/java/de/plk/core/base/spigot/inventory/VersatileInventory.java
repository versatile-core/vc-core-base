package de.plk.core.base.spigot.inventory;

import de.plk.core.api.spigot.inventory.IInventory;
import de.plk.core.api.spigot.inventory.item.IItem;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SoftwareBuilds
 * @since 18.11.2023 19:48
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class VersatileInventory implements IInventory {

    /**
     * The inventory contents.
     */
    private final Map<Integer, IItem> contents = new HashMap<>();

    /**
     * The inventory identifier.
     */
    private final String inventoryIdentifier;

    /**
     * The inventory title.
     */
    private String title;

    /**
     * The inventory size.
     */
    private int inventorySize;

    /**
     * True of the inventory has no moveable items.
     */
    private boolean fullUnclickable;

    /**
     * The inventory listener.
     */
    private Listener listener;

    /**
     * Constructs a versatile inventory.
     *
     * @param inventoryIdentifier The inventory identifier.
     */
    public VersatileInventory(String inventoryIdentifier) {
        this.inventoryIdentifier = inventoryIdentifier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInventoryIdentifier() {
        return inventoryIdentifier;
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
    public void addInventoryListener(Listener listener) {
        this.listener = listener;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Listener getInventoryListener() {
        return listener;
    }

}
