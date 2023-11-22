package de.plk.core.base.spigot.board.team;

import de.plk.core.api.code.NotNull;
import de.plk.core.api.code.Nullable;
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
    @NotNull
    private String prefix = "";

    /**
     * The suffix of the team.
     */
    @NotNull
    private String suffix = "";

    /**
     * The team identifier.
     */
    @NotNull
    private String identifier;

    /**
     * The value string which can be updated.
     */
    @Nullable
    private String updatingString;

    /**
     * The entry for team value updates.
     */
    @Nullable
    private ChatColor entry;

    /**
     * {@inheritDoc}
     */
    @Override
    public ITeamBuilder setPrefix(@NotNull String prefix) {
        this.prefix = prefix;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ITeamBuilder setSuffix(@NotNull String suffix) {
        this.suffix = suffix;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ITeamBuilder setIdentifier(@NotNull String identifier) {
        this.identifier = identifier;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ITeamBuilder setUpdatedString(@NotNull String updatingString, @NotNull Object... objects) {
        this.updatingString = updatingString.formatted(objects);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ITeamBuilder setEntry(@NotNull ChatColor entry) {
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
