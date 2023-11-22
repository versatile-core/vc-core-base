package de.plk.core.base.utils;

import de.plk.core.api.code.NotNull;
import de.plk.core.api.utils.IIdentifier;

/**
 * @author SoftwareBuilds
 * @since 22.11.2023 22:17
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class Identification implements IIdentifier {

    /**
     * The identifier string.
     */
    @NotNull
    protected final String identification;

    /**
     * Construct an identification for core list elements.
     *
     * @param identification The identification string.
     */
    public Identification(@NotNull String identification) {
        this.identification = identification;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getIdentifier() {
        return this.identification;
    }

}
