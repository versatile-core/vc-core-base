package de.plk.core.base;

import de.plk.core.api.AbstractVersatileSpigot;
import de.plk.core.api.plugin.IInstanceSpigot;
import de.plk.core.api.plugin.ISpigotPluginCore;
import de.plk.core.base.plugin.SpigotPluginCore;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SoftwareBuilds
 * @since 06.08.2023 13:14
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class VersatileSpigot extends AbstractVersatileSpigot implements IInstanceSpigot {

    /**
     * All plugin instances.
     */
    private static final Map<JavaPlugin, ISpigotPluginCore> instances = new HashMap<>();

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
    public ISpigotPluginCore createPluginCore(AbstractVersatileSpigot plugin) {
        if (instances.containsKey(plugin)) {
            return instances.get(plugin);
        }

        ISpigotPluginCore pluginCore = new SpigotPluginCore(plugin);

        instances.put(plugin, pluginCore);

        return pluginCore;
    }

}
