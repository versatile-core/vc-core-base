package de.plk.core.base.spigot.board.team;

import de.plk.core.api.spigot.board.team.IScoreboardTeam;
import org.bukkit.ChatColor;

/**
 * @author SoftwareBuilds
 * @since 18.11.2023 19:42
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class VersatileTeam implements IScoreboardTeam {

    /**
     * The team identifier,
     */
    private final String teamIdentifier;

    /**
     * The entry to update the value of the team.
     */
    private final ChatColor entry;

    /**
     * The prefix of the team.
     */
    private final String prefix;

    /**
     * The suffix of the team.
     */
    private final String suffix;

    /**
     * The updated string value of the team.
     */
    private String updatedString;

    /**
     * Construct a versatile scoreboard team.
     *
     * @param teamIdentifier The team identifier.
     * @param entry The entry of to update the team.
     * @param prefix The prefix of the team.
     * @param suffix The suffix of the team.
     * @param updatedString The updated string value of the team.
     */
    public VersatileTeam(String teamIdentifier, ChatColor entry, String prefix, String suffix, String updatedString) {
        this.teamIdentifier = teamIdentifier;
        this.entry = entry;
        this.prefix = prefix;
        this.suffix = suffix;
        this.updatedString = updatedString;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTeamIdentifier() {
        return teamIdentifier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPrefix() {
        return prefix;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(String updatedString, Object... objects) {
        this.updatedString = updatedString.formatted(objects);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSuffix() {
        return suffix;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChatColor getEntry() {
        return entry;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getValue() {
        return updatedString;
    }

}
