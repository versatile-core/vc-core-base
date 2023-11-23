package de.plk.core.base;

import de.plk.core.api.AbstractVersatileProxy;
import de.plk.core.api.code.NotNull;
import de.plk.core.api.plugin.IProxyPluginCore;
import de.plk.core.api.plugin.instance.IInstanceProxy;
import de.plk.core.base.plugin.ProxyPluginCore;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SoftwareBuilds
 * @since 06.08.2023 01:00
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public final class VersatileProxy extends AbstractVersatileProxy implements IInstanceProxy {

    /**
     * All plugin instances.
     */
    @NotNull
    private static final Map<Plugin, IProxyPluginCore> instances = new HashMap<>();

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
    public IProxyPluginCore createPluginCore(@NotNull AbstractVersatileProxy plugin) {
        if (instances.containsKey(plugin)) {
            return instances.get(plugin);
        }

        IProxyPluginCore pluginCore = new ProxyPluginCore(plugin);

        instances.put(plugin, pluginCore);

        return pluginCore;
    }

}
