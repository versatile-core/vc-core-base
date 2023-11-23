package de.plk.core.base.spigot.board.team;

import de.plk.core.api.code.NotNull;
import de.plk.core.api.code.Nullable;
import de.plk.core.api.spigot.board.team.IScoreboardTeam;
import de.plk.core.base.utils.Identification;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;

/**
 * @author SoftwareBuilds
 * @since 18.11.2023 19:42
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class VersatileTeam extends Identification implements IScoreboardTeam {

    /**
     * The entry to update the value of the team.
     */
    @NotNull
    private final ChatColor entry;

    /**
     * The prefix of the team.
     */
    @NotNull
    private final String prefix;

    /**
     * The suffix of the team.
     */
    @NotNull
    private final String suffix;

    /**
     * The updated string value of the team.
     */
    @NotNull
    private String updatedString;

    /**
     * The listener for this team.
     */
    @Nullable
    private Listener teamListener;

    /**
     * Construct a versatile scoreboard team.
     *
     * @param teamIdentifier The team identifier.
     * @param entry The entry of to update the team.
     * @param prefix The prefix of the team.
     * @param suffix The suffix of the team.
     * @param updatedString The updated string value of the team.
     */
    public VersatileTeam(
            @NotNull String teamIdentifier,
            @NotNull ChatColor entry,
            @NotNull String prefix,
            @NotNull String suffix,
            @NotNull String updatedString
    ) {
        super(teamIdentifier);

        this.entry = entry;
        this.prefix = prefix;
        this.suffix = suffix;
        this.updatedString = updatedString;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Listener getListener() {
        return teamListener;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setListener(Listener listener) {
        this.teamListener = listener;
    }
}
