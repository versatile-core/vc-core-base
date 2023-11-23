package de.plk.core.base.spigot.task;

import de.plk.core.api.AbstractVersatileSpigot;
import de.plk.core.api.code.NotNull;
import de.plk.core.api.task.repeat.IRepeatCounter;
import de.plk.core.api.task.repeat.IRepeatingRunnable;
import de.plk.core.api.task.repeat.IRepeatingTask;

/**
 * @author SoftwareBuilds
 * @since 05.11.2023 18:37
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class RepeatingTask extends AbstractTask<IRepeatingRunnable> implements IRepeatingTask {

    /**
     * The repeater counter for the scheduler.
     */
    @NotNull
    private final IRepeatCounter repeatCounter;

    /**
     * The delayed ticks for the scheduler.
     */
    private long delayedTicks;

    /**
     * The repeating ticks for the scheduler.
     */
    private long repeatingTicks;

    /**
     * Defines the repeating task.
     *
     * @param pluginCore The spigot core.
     * @param taskIdentifier The task name.
     * @param async Set that the scheduler runs async
     */
    public RepeatingTask(
            @NotNull AbstractVersatileSpigot pluginCore,
            @NotNull String taskIdentifier,
            boolean async
    ) {
        super(pluginCore, taskIdentifier, async);

        this.repeatCounter = new IRepeatCounter() {

            /**
             * The actual repeating counter.
             */
            private int counter = 0;

            /**
             * {@inheritDoc}
             */
            @Override
            public int currentCounter() {
                return counter;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void setCurrentCounter(int counter) {
                this.counter = counter;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void increment(int increment) {
                this.counter += increment;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void decrement(int decrement) {
                this.counter -= decrement;
            }
        };
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
    public long getRepeatingTicks() {
        return repeatingTicks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRepeatingTicks(long repeatingTicks) {
       this.repeatingTicks = repeatingTicks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRepeatingCounterBegin(int begin) {
        this.repeatCounter.setCurrentCounter(begin);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IRepeatCounter getTheRepeatingCounter() {
        return repeatCounter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        final Runnable taskRunnable = () -> runnable.run(repeatCounter);
        task = async
                ? BUKKIT_SCHEDULER.runTaskTimerAsynchronously(pluginCore, taskRunnable, delayedTicks, repeatingTicks)
                : BUKKIT_SCHEDULER.runTaskTimer(pluginCore, taskRunnable, delayedTicks, repeatingTicks);
    }

}
