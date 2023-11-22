package de.plk.core.base.spigot.task.exception;

import de.plk.core.api.code.NotNull;

/**
 * @author SoftwareBuilds
 * @since 05.11.2023 20:54
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class AlreadyExistsException extends RuntimeException {

    /**
     * {@inheritDoc}
     */
    public AlreadyExistsException(@NotNull String message) {
        super(message);
    }

}
