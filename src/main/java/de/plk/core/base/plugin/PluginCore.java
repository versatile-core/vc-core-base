package de.plk.core.base.plugin;

import de.plk.core.api.code.NotNull;
import de.plk.core.api.command.ICommandManager;
import de.plk.core.api.config.IConfigManager;
import de.plk.core.api.database.IDatabaseManager;
import de.plk.core.api.language.ILanguage;
import de.plk.core.api.log.ILogger;
import de.plk.core.api.plugin.IPluginCore;
import de.plk.core.api.spigot.game.IGame;
import de.plk.core.api.utils.IManager;
import de.plk.core.base.command.CommandManager;
import de.plk.core.base.config.ConfigManager;
import de.plk.core.base.log.Logger;
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
    @NotNull
    private final P plugin;

    /**
     * The command manager.
     */
    @NotNull
    private final ICommandManager commandManager;

    /**
     * The database manager.
     */
    @NotNull
    private IDatabaseManager databaseManager;

    /**
     * The language manager.
     */
    @NotNull
    private final IManager<ILanguage> languageManager;

    /**
     * The game manager.
     */
    @NotNull
    private final IManager<IGame> gameManager;

    /**
     * Construct the plugin core.
     *
     * @param plugin  The instance of sub plugin.
     */
    public PluginCore(@NotNull P plugin) {
        this.plugin = plugin;

        this.commandManager = new CommandManager();
        this.languageManager = new Manager<>();
        this.gameManager = new Manager<>();
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
    public <T> IConfigManager<T> getConfigManager() {
        return new ConfigManager<>();
    }

    /**
     * Get the plugin instance.
     *
     * @return The plugin instance.
     */
    @NotNull
    public P getPlugin() {
        return plugin;
    }

}
