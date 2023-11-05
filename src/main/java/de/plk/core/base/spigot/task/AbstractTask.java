package de.plk.core.base.spigot.task;

import de.plk.core.api.task.ITask;
import de.plk.core.base.VersatileSpigot;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

/**
 * @author SoftwareBuilds
 * @since 05.11.2023 20:06
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public abstract class AbstractTask<T> implements ITask<T> {

    /**
     * The bukkit/ spigot scheduler.
     */
    protected static final BukkitScheduler BUKKIT_SCHEDULER = Bukkit.getScheduler();

    /**
     * The spigot plugin instance.
     */
    protected final VersatileSpigot pluginCore;

    /**
     * The task name.
     */
    private final String taskName;

    /**
     * The bukkit/ spigot task.
     */
    protected BukkitTask task;

    /**
     * The execution context for the scheduler.
     */
    protected T runnable;

    /**
     * Defines the abstract task.
     *
     * @param pluginCore The spigot plugin instance.
     * @param taskName The task name.
     */
    public AbstractTask(VersatileSpigot pluginCore, String taskName) {
        this.pluginCore = pluginCore;
        this.taskName = taskName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void taskExecutionContent(T runnable) {
        this.runnable = runnable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTaskName() {
        return taskName;
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
