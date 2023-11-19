package de.plk.core.base.spigot.board;

import de.plk.core.api.spigot.board.IScoreboard;
import de.plk.core.api.spigot.board.IScoreboardManager;
import de.plk.core.api.spigot.board.team.IScoreboardTeam;
import de.plk.core.base.utils.Manager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
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
    private static final Map<Player, IScoreboard> activeScoreboard = new ConcurrentHashMap<>();

    /**
     * The plugin instance.
     */
    private final JavaPlugin plugin;

    /**
     * Construct the scoreboard manager.
     *
     * @param plugin The plugin instance.
     */
    public ScoreboardManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Builds the scoreboard for minecraft.
     *
     * @param scoreboard The simple scoreboard.
     *
     * @return The built minecraft scoreboard.
     */
    private Scoreboard buildSpigotScoreboard(Player player, IScoreboard scoreboard) {
        Scoreboard spigotScoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();

        Objective objective = spigotScoreboard.registerNewObjective(
                "xxx", Criteria.DUMMY,
                scoreboard.getScoreboardTitle()
        );

        objective.setDisplaySlot(scoreboard.getScoreboardType());

        // Setting the rows in the scoreboard at specific positions.
        scoreboard.getRows().forEach((position, row) -> {
            if (row instanceof IScoreboardTeam) {
                objective.getScore(buildTeam((IScoreboardTeam) row, spigotScoreboard).toString()).setScore(position);
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
    public ChatColor buildTeam(IScoreboardTeam scoreboardTeam, Scoreboard scoreboard) {
        Team team = scoreboard.getTeam(scoreboardTeam.getTeamIdentifier());

        if (team == null) {
            team = scoreboard.registerNewTeam(scoreboardTeam.getTeamIdentifier());
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
    public void sendScoreboard(Player player, IScoreboard iScoreboard) {
        player.setScoreboard(buildSpigotScoreboard(player, iScoreboard));
        activeScoreboard.put(player, iScoreboard);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IScoreboard createScoreboard(String scoreboardIdentifier, DisplaySlot displaySlot, String scoreboardTitle) {
        IScoreboard scoreboard = new VersatileScoreboard(scoreboardIdentifier, displaySlot, scoreboardTitle);

        if (elements.contains(scoreboard))
            return scoreboard;

        add(scoreboard);

        return scoreboard;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<IScoreboard> getScoreboardByIdentifier(String scoreboardIdentifier) {
        return Optional.ofNullable(
                getFirstByFilter(scoreboard -> scoreboard.getScoreboardIdentifier().equals(scoreboardIdentifier))
        );
    }

    @Override
    public Optional<IScoreboard> getScoreboardByPlayer(Player player) {
        return Optional.ofNullable(
                activeScoreboard.get(player)
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearScoreboard(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        activeScoreboard.remove(player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerAllListeners() {
        getAll().stream().map(IScoreboard::getScoreboardListener).filter(Objects::nonNull).forEach(scoreboard -> {
            Bukkit.getPluginManager().registerEvents(scoreboard, plugin);
        });
    }

}
