package de.plk.core.base.spigot.hologram;

import de.plk.core.api.spigot.hologram.IHologram;
import org.bukkit.Location;

/**
 * @author SoftwareBuilds
 * @since 18.11.2023 19:59
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class VersatileHologram implements IHologram {

    /**
     * The hologram identifier.
     */
    private final String hologramIdentifier;

    /**
     * The hologram location.
     */
    private Location hologramLocation;

    /**
     * The hologram lines.
     */
    private String[] hologramLines;

    /**
     * The hologram is a child or not.
     */
    private boolean small;

    /**
     * Construct a versatile hologram.
     *
     * @param hologramIdentifier The hologram identifier.
     */
    public VersatileHologram(String hologramIdentifier) {
        this.hologramIdentifier = hologramIdentifier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHologramIdentifier() {
        return hologramIdentifier;
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
    public void setLocation(Location location) {
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
    public void setLines(String[] hologramLines) {
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

}
