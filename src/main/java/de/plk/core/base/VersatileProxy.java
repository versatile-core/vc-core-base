package de.plk.core.base;

import de.plk.core.api.AbstractVersatileProxy;
import de.plk.core.api.plugin.IInstance;
import de.plk.core.api.plugin.IPluginCore;
import de.plk.core.base.plugin.PluginCore;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SoftwareBuilds
 * @since 06.08.2023 01:00
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public final class VersatileProxy extends AbstractVersatileProxy implements IInstance<Plugin> {

    /**
     * All plugin instances.
     */
    private static final Map<Plugin, IPluginCore> instances = new HashMap<>();

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
    public IPluginCore createPluginCore(Plugin plugin) {
        if (instances.containsKey(plugin)) {
            return instances.get(plugin);
        }

        IPluginCore pluginCore = new PluginCore<>(plugin);

        instances.put(plugin, pluginCore);

        return pluginCore;
    }

}
