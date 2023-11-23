package de.plk.core.base.spigot.board;

import de.plk.core.api.code.NotNull;
import de.plk.core.api.code.Nullable;
import de.plk.core.api.spigot.board.IRow;
import de.plk.core.api.spigot.board.IScoreboard;
import de.plk.core.api.spigot.board.team.ITeamBuilder;
import de.plk.core.base.spigot.board.team.TeamBuilder;
import de.plk.core.base.utils.Identification;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SoftwareBuilds
 * @since 18.11.2023 19:23
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class VersatileScoreboard extends Identification implements IScoreboard {

    /**
     * The rows of the scoreboard.
     */
    @NotNull
    private final Map<Integer, IRow> scoreboardRows = new HashMap<>();

    /**
     * The display type of the scoreboard.
     */
    @NotNull
    private final DisplaySlot scoreboardType;

    /**
     * The clear line counter,
     */
    private int clearLineCounter;

    /**
     * The title of the scoreboard.
     */
    @NotNull
    private String title;

    /**
     * The scoreboard listener.
     */
    @Nullable
    private Listener scoreboardListener;

    /**
     * Construct a versatile scoreboard.
     *
     * @param scoreboardIdentifier The scoreboard identifier.
     * @param scoreboardType The scoreboard display type.
     * @param title The scoreboard title.
     */
    public VersatileScoreboard(
            @NotNull String scoreboardIdentifier,
            @NotNull DisplaySlot scoreboardType,
            @NotNull String title
    ) {
        super(scoreboardIdentifier);

        this.scoreboardType = scoreboardType;
        this.title = title;
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
    public ITeamBuilder getTeamBuilder() {
        return new TeamBuilder();
    }

    @Override
    public void setScoreboardTitle(@NotNull String scoreboardTitle) {
        this.title = scoreboardTitle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addRow(int position, @NotNull IRow row) {
        if (!getRows().containsKey(position))
         this.scoreboardRows.put(position, row);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void blankLine(int row) {
        clearLineCounter++;
        addRow(row, () -> ChatColor.BLACK.toString().repeat(clearLineCounter));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Listener getListener() {
        return scoreboardListener;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setListener(@NotNull Listener listener) {
        this.scoreboardListener = listener;
    }
}
