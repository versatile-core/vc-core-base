package de.plk.core.base.spigot.task;

import de.plk.core.api.AbstractVersatileSpigot;
import de.plk.core.api.code.NotNull;
import de.plk.core.api.task.ITask;
import de.plk.core.api.task.ITaskManager;
import de.plk.core.api.task.delayed.IDelayedTask;
import de.plk.core.api.task.repeat.IRepeatingTask;
import de.plk.core.base.spigot.task.exception.AlreadyExistsException;
import de.plk.core.base.utils.Manager;

/**
 * @author SoftwareBuilds
 * @since 05.11.2023 18:40
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class TaskManager extends Manager<ITask<?>> implements ITaskManager {

    /**
     * The spigot plugin instance.
     */
    @NotNull
    private final AbstractVersatileSpigot pluginCore;

    /**
     * Defines the manager for bukkit/ spigot tasks.
     *
     * @param pluginCore The spigot core.
     */
    public TaskManager(@NotNull AbstractVersatileSpigot pluginCore) {
        this.pluginCore = pluginCore;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDelayedTask createDelayedTask(String taskIdentifier, long delayedTicks) {
        if (getFirstByIdentifier(taskIdentifier).isEmpty()) {
            throw new AlreadyExistsException("The task " + taskIdentifier + " is exists already.");
        }

        // Creation of delayed task.
        DelayedTask delayedTask = new DelayedTask(pluginCore, taskIdentifier);
        delayedTask.setDelayedTicks(delayedTicks);

        add(delayedTask);

        return delayedTask;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IRepeatingTask createRepeatingTask(@NotNull String taskIdentifier, @NotNull long delayedTicks, @NotNull long repeatingTicks) {
        if (getFirstByIdentifier(taskIdentifier).isEmpty()) {
            throw new AlreadyExistsException("The task " + taskIdentifier + " is exists already.");
        }

        // Creation of repeating task.
        RepeatingTask repeatingTask = new RepeatingTask(pluginCore, taskIdentifier);
        repeatingTask.setDelayedTicks(delayedTicks);
        repeatingTask.setRepeatingTicks(repeatingTicks);

        add(repeatingTask);

        return repeatingTask;
    }

}
