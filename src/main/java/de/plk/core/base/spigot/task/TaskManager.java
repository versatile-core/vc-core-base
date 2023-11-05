package de.plk.core.base.spigot.task;

import de.plk.core.api.task.ITask;
import de.plk.core.api.task.ITaskIdentifier;
import de.plk.core.api.task.ITaskManager;
import de.plk.core.api.task.TaskByIdFilter;
import de.plk.core.api.task.delayed.IDelayedTask;
import de.plk.core.api.task.repeat.IRepeatingTask;
import de.plk.core.base.VersatileSpigot;
import de.plk.core.base.spigot.task.exception.AlreadyExistsException;
import de.plk.core.base.utils.Manager;

/**
 * @author SoftwareBuilds
 * @since 05.11.2023 18:40
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class TaskManager extends Manager<ITaskIdentifier> implements ITaskManager {

    /**
     * The spigot plugin instance.
     */
    private final VersatileSpigot pluginCore;

    /**
     * Defines the manager for bukkit/ spigot tasks.
     *
     * @param pluginCore The spigot core.
     */
    public TaskManager(VersatileSpigot pluginCore) {
        this.pluginCore = pluginCore;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDelayedTask createDelayedTask(String taskName, long delayedTicks) {
        if (getFirstByFilter(new TaskByIdFilter(taskName)) != null) {
            throw new AlreadyExistsException("The task " + taskName + " is exists already.");
        }

        DelayedTask delayedTask = new DelayedTask(pluginCore, taskName);
        delayedTask.setDelayedTicks(delayedTicks);

        add(delayedTask);

        return delayedTask;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IRepeatingTask createRepeatingTask(String taskName, long delayedTicks, long repeatingTicks) {
        if (getFirstByFilter(new TaskByIdFilter(taskName)) != null) {
            throw new AlreadyExistsException("The task " + taskName + " is exists already.");
        }

        RepeatingTask repeatingTask = new RepeatingTask(pluginCore, taskName);
        repeatingTask.setDelayedTicks(delayedTicks);
        repeatingTask.setRepeatingTicks(repeatingTicks);

        add(repeatingTask);

        return repeatingTask;
    }
}
