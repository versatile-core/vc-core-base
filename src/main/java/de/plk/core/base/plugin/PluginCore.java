package de.plk.core.base.plugin;

import de.plk.core.api.AbstractVersatileSpigot;
import de.plk.core.api.command.ICommandManager;
import de.plk.core.api.config.IConfigManager;
import de.plk.core.api.database.IDatabaseManager;
import de.plk.core.api.language.ILanguage;
import de.plk.core.api.log.ILogger;
import de.plk.core.api.plugin.IPluginCore;
import de.plk.core.api.spigot.board.IScoreboardManager;
import de.plk.core.api.spigot.game.IGame;
import de.plk.core.api.spigot.inventory.IInventoryManager;
import de.plk.core.api.task.ITaskManager;
import de.plk.core.api.utils.IManager;
import de.plk.core.base.VersatileSpigot;
import de.plk.core.base.command.CommandManager;
import de.plk.core.base.config.ConfigManager;
import de.plk.core.base.log.Logger;
import de.plk.core.base.spigot.board.ScoreboardManager;
import de.plk.core.base.spigot.inventory.InventoryManager;
import de.plk.core.base.spigot.task.TaskManager;
import de.plk.core.base.utils.Manager;

/**
 * @author SoftwareBuilds
 * @since 07.08.2023 20:17
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class PluginCore<P> implements IPluginCore {

    /**
     * The plugin instance.
     */
    private final P plugin;

    /**
     * The command manager.
     */
    private final ICommandManager commandManager;

    /**
     * The database manager.
     */
    private IDatabaseManager databaseManager;

    /**
     * The scoreboard manager.
     */
    private final IScoreboardManager scoreboardManager;

    /**
     * The language manager.
     */
    private final IManager<ILanguage> languageManager;

    /**
     * The game manager.
     */
    private final IManager<IGame> gameManager;

    /**
     * The inventory manager.
     */
    private final InventoryManager inventoryManager;

    /**
     * Construct the plugin core.
     *
     * @param plugin  The instance of sub plugin.
     */
    public PluginCore(P plugin) {
        this.plugin = plugin;
        this.commandManager = new CommandManager<>();
        this.scoreboardManager = new ScoreboardManager();
        this.languageManager = new Manager<>();
        this.gameManager = new Manager<>();
        this.inventoryManager = new InventoryManager();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ICommandManager getCommandManager() {
        return commandManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IManager<IGame> getGameManager() {
        return gameManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IManager<ILanguage> getLanguageManager() {
        return languageManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ILogger createLogger(String channel) {
        return new Logger(channel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IInventoryManager getInventoryManager() {
        return inventoryManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> IConfigManager<T> getConfigManager() {
        return new ConfigManager<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ITaskManager getTaskManager() {
        if (plugin instanceof AbstractVersatileSpigot) {
            return new TaskManager((AbstractVersatileSpigot) plugin);
        }

        return null;
    }

    /**
     * Get the plugin instance.
     *
     * @return The plugin instance.
     */
    public P getPlugin() {
        return plugin;
    }
}
