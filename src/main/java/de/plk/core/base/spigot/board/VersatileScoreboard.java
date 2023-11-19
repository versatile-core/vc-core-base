package de.plk.core.base.spigot.board;

import de.plk.core.api.spigot.board.IRow;
import de.plk.core.api.spigot.board.IScoreboard;
import de.plk.core.api.spigot.board.team.IScoreboardTeam;
import de.plk.core.api.spigot.board.team.ITeamBuilder;
import de.plk.core.api.spigot.board.team.TeamIdentifierFilter;
import de.plk.core.api.utils.IManager;
import de.plk.core.base.utils.Manager;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author SoftwareBuilds
 * @since 18.11.2023 19:23
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class VersatileScoreboard implements IScoreboard {

    /**
     * The rows of the scoreboard.
     */
    private final Map<Integer, IRow> scoreboardRows = new HashMap<>();

    /**
     * The scoreboard team manager.
     */
    private final IManager<IScoreboardTeam> scoreboardTeamManager = new Manager<>();

    /**
     * The scoreboard identifier.
     */
    private final String scoreboardIdentifier;

    /**
     * The display type of the scoreboard.
     */
    private final DisplaySlot scoreboardType;

    /**
     * The title of the scoreboard.
     */
    private String title;

    /**
     * The listener for the scoreboard.
     */
    private Listener scoreboardListener;

    /**
     * Construct a versatile scoreboard.
     *
     * @param scoreboardIdentifier The scoreboard identifier.
     * @param scoreboardType The scoreboard display type.
     * @param title The scoreboard title.
     */
    public VersatileScoreboard(String scoreboardIdentifier, DisplaySlot scoreboardType, String title) {
        this.scoreboardIdentifier = scoreboardIdentifier;
        this.scoreboardType = scoreboardType;
        this.title = title;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getScoreboardIdentifier() {
        return scoreboardIdentifier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DisplaySlot getScoreboardType() {
        return scoreboardType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getScoreboardTitle() {
        return title;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Integer, IRow> getRows() {
        return scoreboardRows;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<IScoreboardTeam> getScoreboardTeam(TeamIdentifierFilter teamIdentifierFilter) {
        return scoreboardTeamManager.getByFilter(teamIdentifierFilter).findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Listener getScoreboardListener() {
        return scoreboardListener;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ITeamBuilder getTeamBuilder() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addScoreboardListener(Listener listener) {
        this.scoreboardListener = listener;
    }

    @Override
    public void setScoreboardTitle(String scoreboardTitle) {
        this.title = scoreboardTitle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addRow(int position, IRow row) {
        if (!getRows().containsKey(position))
         this.scoreboardRows.put(position, row);
    }

}
