package de.plk.core.base.spigot.board;

import de.plk.core.api.spigot.board.IScoreboard;
import de.plk.core.api.spigot.board.IScoreboardManager;
import de.plk.core.api.spigot.board.team.IScoreboardTeam;
import de.plk.core.api.utils.IManager;
import de.plk.core.base.utils.Manager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * @author SoftwareBuilds
 * @since 07.08.2023 14:43
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class ScoreboardManager extends Manager<IScoreboard> implements IScoreboardManager {

    /**
     * Manager for scoreboard teams.
     */
    private final IManager<IScoreboardTeam> scoreboardTeam = new Manager<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendScoreboard(Player player, IScoreboard scoreboard) {
        Scoreboard spigotScoreboard = buildSpigotScoreboard(player, scoreboard);
        player.setScoreboard(spigotScoreboard);
    }

    @Override
    public void updateScoreboard(Player player, IScoreboard scoreboard) {
        scoreboard.getRows().entrySet().stream()
                .filter((e) -> e.getValue() instanceof IScoreboardTeam).forEach((team) -> buildTeam((IScoreboardTeam) team.getValue(), player.getScoreboard()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearScoreboard(Player player) {
        player.setScoreboard(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IManager<IScoreboardTeam> getScoreboardTeamManager() {
        return scoreboardTeam;
    }

    /**
     * Builds the scoreboard for minecraft.
     *
     * @param scoreboard The simple scoreboard.
     *
     * @return The built minecraft scoreboard.
     */
    private Scoreboard buildSpigotScoreboard(Player player, IScoreboard scoreboard) {
        Scoreboard spigotScoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = spigotScoreboard.registerNewObjective("xxx", "yyy");

        objective.setDisplaySlot(scoreboard.getScoreboardType());
        objective.setDisplayName(scoreboard.getScoreboardTitle());

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

        if (team == null)
            team = scoreboard.registerNewTeam(scoreboardTeam.getTeamIdentifier());

        team.setPrefix(scoreboardTeam.getPrefix() + scoreboardTeam.getValue());
        team.setSuffix(scoreboardTeam.getSuffix());

        team.addEntry(scoreboardTeam.getEntry().toString());

        return scoreboardTeam.getEntry();
    }
}
