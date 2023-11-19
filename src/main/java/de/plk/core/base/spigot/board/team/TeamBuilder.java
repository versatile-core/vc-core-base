package de.plk.core.base.spigot.board.team;

import de.plk.core.api.spigot.board.team.IScoreboardTeam;
import de.plk.core.api.spigot.board.team.ITeamBuilder;
import org.bukkit.ChatColor;

/**
 * @author SoftwareBuilds
 * @since 18.11.2023 19:39
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class TeamBuilder implements ITeamBuilder {

    /**
     * The prefix of the team.
     */
    private String prefix;

    /**
     * The suffix of the team.
     */
    private String suffix;

    /**
     * The team identifier.
     */
    private String identifier;

    /**
     * The value string which can be updated.
     */
    private String updatingString;

    /**
     * The entry for team value updates.
     */
    private ChatColor entry;

    /**
     * {@inheritDoc}
     */
    @Override
    public ITeamBuilder setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ITeamBuilder setSuffix(String suffix) {
        this.suffix = suffix;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ITeamBuilder setIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ITeamBuilder setUpdatedString(String s, Object... objects) {
        this.updatingString = updatingString.formatted(objects);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ITeamBuilder setEntry(ChatColor entry) {
        this.entry = entry;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IScoreboardTeam build() {
        return new VersatileTeam(identifier, entry, prefix, suffix, updatingString);
    }

}
