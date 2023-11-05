package de.plk.core.base.spigot.task;

import de.plk.core.api.task.repeat.IRepeatCounter;
import de.plk.core.api.task.repeat.IRepeatingRunnable;
import de.plk.core.api.task.repeat.IRepeatingTask;
import de.plk.core.base.VersatileSpigot;

/**
 * @author SoftwareBuilds
 * @since 05.11.2023 18:37
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class RepeatingTask extends AbstractTask<IRepeatingRunnable> implements IRepeatingTask {

    /**
     * The repeater counter for the scheduler.
     */
    private IRepeatCounter repeatCounter;

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
     * @param taskName The task name.
     */
    public RepeatingTask(VersatileSpigot pluginCore, String taskName) {
        super(pluginCore, taskName);
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
    public void createRepeatingCounter() {
        this.repeatCounter = new IRepeatCounter() {

            /**
             * The counter.
             */
            private int counter;

            @Override
            public int currentCounter() {
                return counter;
            }

            @Override
            public void setCurrentCounter(int counter) {
                this.counter = counter;
            }

            @Override
            public void increment(int amount) {
                this.counter += amount;
            }

            @Override
            public void decrement(int amount) {
                this.counter -= amount;
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createRepeatingCounter(int begin) {
        createRepeatingCounter();
        repeatCounter.setCurrentCounter(begin);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        task = BUKKIT_SCHEDULER.runTaskTimerAsynchronously(pluginCore, () -> runnable.run(repeatCounter), delayedTicks, repeatingTicks);
    }
}
