package de.plk.core.base.plugin;

import de.plk.core.api.AbstractVersatileSpigot;
import de.plk.core.api.plugin.ISpigotPluginCore;
import de.plk.core.api.spigot.board.IScoreboardManager;
import de.plk.core.api.spigot.hologram.IHologramManager;
import de.plk.core.api.spigot.inventory.IInventoryManager;
import de.plk.core.api.task.ITaskManager;
import de.plk.core.base.spigot.board.ScoreboardManager;
import de.plk.core.base.spigot.hologram.HologramManager;
import de.plk.core.base.spigot.inventory.InventoryManager;
import de.plk.core.base.spigot.task.TaskManager;

/**
 * @author SoftwareBuilds
 * @since 19.11.2023 18:30
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class SpigotPluginCore extends PluginCore<AbstractVersatileSpigot> implements ISpigotPluginCore {

    /**
     * The task manager.
     */
    private TaskManager taskManager;

    /**
     * The inventory manager.
     */
    private InventoryManager inventoryManager;

    /**
     * The scoreboard manager.
     */
    private ScoreboardManager scoreboardManager;

    /**
     * The hologram manager.
     */
    private HologramManager hologramManager;

    /**
     * Construct the plugin core.
     *
     * @param plugin The instance of sub plugin.
     */
    public SpigotPluginCore(AbstractVersatileSpigot plugin) {
        super(plugin);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IScoreboardManager getScoreboardManager() {
        if (scoreboardManager == null && getPlugin() != null) {
            this.scoreboardManager = new ScoreboardManager(getPlugin());
        }

        return scoreboardManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IInventoryManager getInventoryManager() {
        if (inventoryManager == null && getPlugin() != null) {
            this.inventoryManager = new InventoryManager(getPlugin());
        }

        return inventoryManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ITaskManager getTaskManager() {
        if (taskManager == null && getPlugin() != null) {
            this.taskManager = new TaskManager(getPlugin());
        }

        return taskManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IHologramManager getHologramManager() {
        if (hologramManager == null && getPlugin() != null) {
            this.hologramManager = new HologramManager();
        }

        return hologramManager;
    }

}
