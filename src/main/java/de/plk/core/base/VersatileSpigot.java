package de.plk.core.base;

import de.plk.core.api.AbstractVersatileSpigot;
import de.plk.core.api.plugin.IInstance;
import de.plk.core.api.plugin.IPluginCore;
import de.plk.core.base.plugin.PluginCore;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SoftwareBuilds
 * @since 06.08.2023 13:14
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class VersatileSpigot extends AbstractVersatileSpigot implements IInstance<JavaPlugin> {

    /**
     * All plugin instances.
     */
    private static final Map<JavaPlugin, IPluginCore> instances = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnable() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDisable() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IPluginCore createPluginCore(JavaPlugin plugin) {
        if (instances.containsKey(plugin)) return instances.get(plugin);

        IPluginCore pluginCore = new PluginCore<>(plugin);

        instances.put(plugin, pluginCore);

        return pluginCore;
    }

}
