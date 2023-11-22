package de.plk.core.base.plugin;

import de.plk.core.api.AbstractVersatileProxy;
import de.plk.core.api.code.NotNull;
import de.plk.core.api.plugin.IProxyPluginCore;

/**
 * @author SoftwareBuilds
 * @since 07.08.2023 20:17
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class ProxyPluginCore extends PluginCore<AbstractVersatileProxy> implements IProxyPluginCore {

    /**
     * Construct the plugin core.
     *
     * @param plugin The instance of sub plugin.
     */
    public ProxyPluginCore(@NotNull AbstractVersatileProxy plugin) {
        super(plugin);
    }

}
