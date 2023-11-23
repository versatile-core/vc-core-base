package de.plk.core.base.spigot.task;

import de.plk.core.api.AbstractVersatileSpigot;
import de.plk.core.api.code.NotNull;
import de.plk.core.api.code.Nullable;
import de.plk.core.api.task.ITask;
import de.plk.core.base.utils.Identification;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

/**
 * @author SoftwareBuilds
 * @since 05.11.2023 20:06
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public abstract class AbstractTask<T> extends Identification implements ITask<T> {

    /**
     * The bukkit/ spigot scheduler.
     */
    @NotNull
    protected static final BukkitScheduler BUKKIT_SCHEDULER = Bukkit.getScheduler();

    /**
     * The spigot plugin instance.
     */
    @NotNull
    protected final AbstractVersatileSpigot pluginCore;

    /**
     * The bukkit/ spigot task.
     */
    @Nullable
    protected BukkitTask task;

    /**
     * The execution context for the scheduler.
     */
    @Nullable
    protected T runnable;

    /**
     * True if the scheduler will be run async.
     */
    @NotNull
    protected boolean async;

    /**
     * Defines the abstract task.
     *
     * @param pluginCore The spigot plugin instance.
     * @param taskIdentifier The task name.
     * @param async Set that the scheduler runs async.
     */
    public AbstractTask(
            @NotNull AbstractVersatileSpigot pluginCore,
            @NotNull String taskIdentifier,
            boolean async
    ) {
        super(taskIdentifier);
        this.pluginCore = pluginCore;
        this.async = async;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void taskExecutionContent(@NotNull T runnable) {
        this.runnable = runnable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BukkitTask getTask() {
        return task;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        task.cancel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAsync() {
        return async;
    }

}
