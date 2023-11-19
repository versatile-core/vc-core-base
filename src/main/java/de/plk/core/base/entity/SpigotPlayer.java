package de.plk.core.base.entity;

import de.plk.core.api.database.meta.Column;
import de.plk.core.api.database.meta.type.DataType;
import de.plk.core.api.entity.ISpigotPlayer;
import de.plk.core.api.spigot.board.IScoreboard;
import de.plk.core.api.spigot.skin.ISkin;
import org.bukkit.entity.Player;

/**
 * @author SoftwareBuilds
 * @since 18.11.2023 19:15
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class SpigotPlayer extends VersatilePlayer<Player> implements ISpigotPlayer {

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
     * The current scoreboard.
     */
    private IScoreboard currentScoreboard;

    /**
     * Construct a game player.
     *
     * @param player The game player.
     */
    public SpigotPlayer(Player player) {
        super(player);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public IScoreboard getScoreboard() {
        return currentScoreboard;
    }

    /**
     * Set the current scoreboard of player.
     *
     * @param scoreboard The new scoreboard.
     */
    public void setCurrentScoreboard(IScoreboard scoreboard) {
        this.currentScoreboard = scoreboard;
    }
}
