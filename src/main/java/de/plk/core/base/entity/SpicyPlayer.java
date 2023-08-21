package de.plk.core.base.entity;

import de.plk.core.api.database.meta.Column;
import de.plk.core.api.database.meta.Table;
import de.plk.core.api.database.meta.type.DataType;
import de.plk.core.api.entity.ISpicyPlayer;
import de.plk.core.api.language.ILanguage;
import de.plk.core.api.spigot.skin.ISkin;

/**
 * @author SoftwareBuilds
 * @since 06.08.2023 01:19
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
@Table(name = "spicy_players")
public class SpicyPlayer<T> implements ISpicyPlayer<T> {

    /**
     * The game player.
     */
    @Column(
            name = "uuid",
            dataType = DataType.VARCHAR,
            primary = true
    )
    private final T player;

    /**
     * The language.
     */
    @Column(
            name = "language",
            dataType = DataType.VARCHAR
    )
    private ILanguage language;

    /**
     * The player skin.
     */
    @Column(
            name = "skin",
            dataType = DataType.INT,
            foreign = true
    )
    private ISkin skin;

    /**
     * Construct a game player.
     *
     * @param player The game player.
     */
    public SpicyPlayer(T player) {
        this.player = player;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getPlayer() {
        return player;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ILanguage getLanguage() {
        return this.language;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLanguage(ILanguage language) {
        this.language = language;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ISkin getSkin() {
        return skin;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSkin(ISkin skin) {
        this.skin = skin;
    }
}
