package de.plk.core.base.spigot.board;

import de.plk.core.api.AbstractVersatileSpigot;
import de.plk.core.api.code.NotNull;
import de.plk.core.api.entity.ISpigotPlayer;
import de.plk.core.api.spigot.board.IScoreboard;
import de.plk.core.api.spigot.board.IScoreboardManager;
import de.plk.core.api.spigot.board.team.IScoreboardTeam;
import de.plk.core.api.utils.IListenable;
import de.plk.core.base.entity.SpigotPlayer;
import de.plk.core.base.utils.Manager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author SoftwareBuilds
 * @since 07.08.2023 14:43
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class ScoreboardManager extends Manager<IScoreboard> implements IScoreboardManager {

    /**
     * The concurrent map for active scoreboard.
     * Concurrent because a lot of players have scoreboard switching at the same time.
     */
    @NotNull
    private static final Map<ISpigotPlayer, IScoreboard> activeScoreboard = new ConcurrentHashMap<>();

    /**
     * The plugin instance.
     */
    @NotNull
    private final AbstractVersatileSpigot plugin;

    /**
     * Construct the scoreboard manager.
     *
     * @param plugin The plugin instance.
     */
    public ScoreboardManager(@NotNull AbstractVersatileSpigot plugin) {
        this.plugin = plugin;
    }

    /**
     * Builds the scoreboard for minecraft.
     *
     * @param scoreboard The simple scoreboard.
     *
     * @return The built minecraft scoreboard.
     */
    @NotNull
    private Scoreboard buildSpigotScoreboard(@NotNull IScoreboard scoreboard) {
        Scoreboard spigotScoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();

        Objective objective = spigotScoreboard.registerNewObjective(
                UUID.randomUUID().toString(), Criteria.DUMMY,
                scoreboard.getScoreboardTitle()
        );

        objective.setDisplaySlot(scoreboard.getScoreboardType());

        // Setting the rows in the scoreboard at specific positions.
        scoreboard.getRows().forEach((position, row) -> {
            if (row instanceof IScoreboardTeam) {
                objective
                        .getScore(buildTeam((IScoreboardTeam) row, spigotScoreboard).toString())
                        .setScore(position);

            } else objective.getScore(row.getValue()).setScore(position);
        });

        return spigotScoreboard;
    }

    /**
     * Build the team for scoreboard.
     *
     * @param scoreboardTeam The scoreboard team.
     * @param scoreboard     The spigot scoreboard.
     *
     * @return The chat color for entry update.
     */
    public ChatColor buildTeam(@NotNull IScoreboardTeam scoreboardTeam, @NotNull Scoreboard scoreboard) {
        Team team = scoreboard.getTeam(scoreboardTeam.getIdentifier());

        if (team == null) {
            team = scoreboard.registerNewTeam(scoreboardTeam.getIdentifier());
        }

        team.setPrefix(scoreboardTeam.getPrefix() + scoreboardTeam.getValue());
        team.setSuffix(scoreboardTeam.getSuffix());

        team.addEntry(scoreboardTeam.getEntry().toString());

        return scoreboardTeam.getEntry();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendScoreboard(ISpigotPlayer spigotPlayer, IScoreboard scoreboard) {
        spigotPlayer.getPlayer().setScoreboard(buildSpigotScoreboard(scoreboard));
        activeScoreboard.put(spigotPlayer, scoreboard);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IScoreboard createScoreboard(
            @NotNull String scoreboardIdentifier,
            @NotNull DisplaySlot displaySlot,
            @NotNull String scoreboardTitle
    ) {
        IScoreboard scoreboard = new VersatileScoreboard(scoreboardIdentifier, displaySlot, scoreboardTitle);

        if (elements.contains(scoreboard))
            return scoreboard;

        add(scoreboard);

        return scoreboard;
    }

    @Override
    public Optional<IScoreboard> getScoreboardByPlayer(@NotNull ISpigotPlayer spigotPlayer) {
        return Optional.ofNullable(activeScoreboard.get(spigotPlayer));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearScoreboard(ISpigotPlayer spigotPlayer) {
        spigotPlayer.getPlayer().setScoreboard(Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard());
        ((SpigotPlayer) spigotPlayer).setCurrentScoreboard(null);
        activeScoreboard.remove(spigotPlayer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerAllListeners() {
        getAll().stream().map(IListenable::getListener).filter(Objects::nonNull).forEach(scoreboard ->
            Bukkit.getPluginManager().registerEvents(scoreboard, plugin)
        );
    }

}
