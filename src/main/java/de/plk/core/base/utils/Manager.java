package de.plk.core.base.utils;

import de.plk.core.api.config.IConfig;
import de.plk.core.api.utils.IManager;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author SoftwareBuilds
 * @since 08.08.2023 15:25
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class Manager<E> implements IManager<E> {

    /**
     * The set of elements.
     */
    private final Set<E> elements = new HashSet<>();

    /**
     * Construct a manager with persistent data.
     *
     * @param config The config.
     */
    public Manager(IConfig<E> config) {
        config.getContent().forEach((key, value) -> add(value));
    }

    /**
     * Construct a manager.
     */
    public Manager() {}

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(E element) {
        return elements.add(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(E element) {
        return elements.remove(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stream<E> getByFilter(Predicate<E> filter) {
        return elements.stream().filter(filter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E getFirstByFilter(Predicate<E> filter) {
        Optional<E> element = getByFilter(filter).findFirst();
        return element.orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<E> getAll() {
        return elements;
    }
}
