package de.plk.core.base.log;

import de.plk.core.api.code.NotNull;
import de.plk.core.api.log.ILogger;
import de.plk.core.api.log.LogType;

/**
 * @author SoftwareBuilds
 * @since 06.08.2023 01:09
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class Logger implements ILogger {

    /**
     * The logger channel
     */
    @NotNull
    private final String channel;

    /**
     * {@inheritDoc}
     */
    public Logger(@NotNull String channel) {
        this.channel = channel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void log(LogType type, String message) {
        System.out.printf("[%s][%s] > %s%n", channel, type.name(), message);
    }

}
