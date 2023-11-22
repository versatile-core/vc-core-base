package de.plk.core.base.config;

import de.plk.core.api.code.NotNull;
import de.plk.core.api.code.Nullable;
import de.plk.core.api.config.IConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SoftwareBuilds
 * @since 06.08.2023 01:05
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class Config<T> extends HashMap<String, T> implements IConfig<T> {

    /**
     * Construct a config with contents.
     *
     * @param contents The contents.
     */
    public Config(@NotNull Map<String, T> contents) {
        contents.clear();
        this.putAll(contents);
    }

    /**
     * Construct a config.
     */
    public Config() {}

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, T> getContent() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IConfig<T> append(@NotNull String key, @Nullable T value) {
        if (!containsKey(key))
            put(key, value);

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IConfig<T> disappear(@NotNull String key) {
        if (containsKey(key))
            remove(key);

        return this;
    }

}
