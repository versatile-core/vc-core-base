package de.plk.core.base.spigot.hologram;

import de.plk.core.api.code.NotNull;
import de.plk.core.api.code.Nullable;
import de.plk.core.api.spigot.hologram.IHologram;
import de.plk.core.base.utils.Identification;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

/**
 * @author SoftwareBuilds
 * @since 18.11.2023 19:59
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class VersatileHologram extends Identification implements IHologram {

    /**
     * The hologram location.
     */
    @NotNull
    private Location hologramLocation;

    /**
     * The hologram lines.
     */
    @NotNull
    private String[] hologramLines;

    /**
     * The hologram is a child or not.
     */
    private boolean small;

    /**
     * The head item.
     */
    @NotNull
    private ItemStack headItem;

    /**
     * Rotation of the head.
     */
    @Nullable
    private Vector headRotation;

    /**
     * Head rotation animation.
     */
    private boolean isAnimated;

    /**
     * Construct a versatile hologram.
     *
     * @param hologramIdentifier The hologram identifier.
     */
    public VersatileHologram(String hologramIdentifier) {
        super(hologramIdentifier);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Location getLocation() {
        return hologramLocation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLocation(@NotNull Location location) {
        this.hologramLocation = location;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getLines() {
        return hologramLines;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLines(@NotNull String[] hologramLines) {
        this.hologramLines = hologramLines;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSmall() {
        return small;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSmall(boolean small) {
        this.small = small;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHeadItem(@NotNull ItemStack itemStack) {
        this.headItem = itemStack;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addHeadRotation(@NotNull Vector headRotation, boolean isAnimated) {
        this.headRotation = headRotation;
        this.isAnimated = isAnimated;
    }

    /**
     * {@inheritDoc}
     */
    public Vector getHeadRotation() {
        return headRotation;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isAnimated() {
        return isAnimated;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemStack getHeadItem() {
        return headItem;
    }
}
