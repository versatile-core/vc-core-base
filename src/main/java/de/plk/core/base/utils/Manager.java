package de.plk.core.base.utils;

import de.plk.core.api.code.NotNull;
import de.plk.core.api.code.Nullable;
import de.plk.core.api.config.IConfig;
import de.plk.core.api.utils.IManager;
import de.plk.core.api.utils.id.IIdentifier;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author SoftwareBuilds
 * @since 08.08.2023 15:25
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class Manager<E extends IIdentifier> implements IManager<E> {

    /**
     * The set of elements.
     */
    @NotNull
    protected final List<E> elements = new CopyOnWriteArrayList<>();

    /**
     * Construct a manager with persistent data.
     *
     * @param config The config.
     */
    public Manager(@NotNull IConfig<E> config) {
        this.loadContentFromConfig(config);
    }

    /**
     * Construct a manager.
     */
    public Manager() {}

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(@NotNull E element) {
        return elements.add(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(@Nullable E element) {
        return elements.remove(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<E> getByFilter(@NotNull Predicate<E> filter) {
        return elements.stream().filter(filter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<E> getFirstByFilter(@NotNull Predicate<E> filter) {
        return getByFilter(filter).findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<E> getAll() {
        return elements;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadContentFromConfig(@NotNull IConfig<E> config) {
        config.getContent().values().forEach(this::add);
    }

}
