package de.plk.core.base.entity;

import de.plk.core.api.database.IModel;
import de.plk.core.api.database.meta.Column;
import de.plk.core.api.database.meta.Table;
import de.plk.core.api.database.meta.type.DataType;

import java.util.List;

/**
 * @author SoftwareBuilds
 * @since 06.08.2023 01:19
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
@Table(name = "versatile_groups")
public class VersatileGroup implements IModel {

    /**
     * The game player.
     */
    @Column(
            name = "groupIdentifier",
            dataType = DataType.VARCHAR,
            primary = true
    )
    private String groupIdentifier;

    /**
     * The group members list.
     */
    @Column(
            name = "uuid",
            dataType = DataType.VARCHAR
    )
    private final List<VersatilePlayer> members;

    /**
     * Create the versatile group model.
     */
    public VersatileGroup(List<VersatilePlayer> members) {
        this.members = members;
    }

    /**
     * Get the members of the group.
     *
     * @return The member of the group.
     */
    public List<VersatilePlayer> getMembers() {
        return members;
    }

}
