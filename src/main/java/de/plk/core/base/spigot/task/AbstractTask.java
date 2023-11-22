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
     * Defines the abstract task.
     *
     * @param pluginCore The spigot plugin instance.
     * @param taskIdentifier   The task name.
     */
    public AbstractTask(@NotNull AbstractVersatileSpigot pluginCore, @NotNull String taskIdentifier) {
        super(taskIdentifier);
        this.pluginCore = pluginCore;
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

}
