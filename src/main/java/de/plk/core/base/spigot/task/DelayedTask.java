package de.plk.core.base.spigot.task;

import de.plk.core.api.AbstractVersatileSpigot;
import de.plk.core.api.code.NotNull;
import de.plk.core.api.task.delayed.IDelayedTask;

/**
 * @author SoftwareBuilds
 * @since 05.11.2023 18:37
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class DelayedTask extends AbstractTask<Runnable> implements IDelayedTask {

    /**
     * The delayed ticks for the scheduler.
     */
    private long delayedTicks;

    /**
     * Defines the delayed task.
     *
     * @param pluginCore The spigot core.
     * @param taskIdentifier The task name.
     */
    public DelayedTask(@NotNull AbstractVersatileSpigot pluginCore, @NotNull String taskIdentifier) {
        super(pluginCore, taskIdentifier);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getDelayedTicks() {
        return delayedTicks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDelayedTicks(long delayedTicks) {
        this.delayedTicks = delayedTicks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        task = BUKKIT_SCHEDULER.runTaskLaterAsynchronously(pluginCore, runnable, delayedTicks);
    }

}
