package de.plk.core.base.database.query;

import de.plk.core.api.code.NotNull;
import de.plk.core.api.database.IModel;
import de.plk.core.api.database.meta.IModelInformation;
import de.plk.core.api.database.query.CommandType;
import de.plk.core.api.database.query.IQuery;
import de.plk.core.base.database.meta.WhereCondition;
import de.plk.core.base.entity.VersatilePlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SoftwareBuilds
 * @since 10.08.2023 11:36
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class Query<M extends IModel> implements IQuery<M> {

    /**
     * The command type.
     */
    private CommandType commandType;

    /**
     * The where condition.
     */
    private WhereCondition<?> whereCondition;

    /**
     * The other or adaptive conditions.
     */
    @NotNull
    private final List<WhereCondition<?>> adaptiveOrWheres = new ArrayList<>();

    /**
     * The other and adaptive conditions.
     */
    @NotNull
    private final List<WhereCondition<?>> adaptiveAndWheres = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandType getCommandType() {
        return commandType;
    }

    /**
     * Set the command type.
     *
     * @param commandType The command type.
     */
    public void setCommandType(@NotNull CommandType commandType) {
        this.commandType = commandType;
    }

    /**
     * Get all or adaptive where conditions.
     *
     * @return The or adaptive where conditions.
     */
    public List<WhereCondition<?>> getAdaptiveOrWheres() {
        return adaptiveOrWheres;
    }

    /**
     * Get all and adaptive where conditions.
     *
     * @return The and adaptive where conditions.
     */
    public List<WhereCondition<?>> getAdaptiveAndWheres() {
        return adaptiveAndWheres;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommand() {
        IModelInformation modelInformation = M.getModelInformation(VersatilePlayer.class);

        modelInformation.getRelations().forEach(relation -> {
            // TODO: Load related things.
        });

        return null;
    }

    /**
     * Execute the query on the database.
     */
    public void execute() {
        // TODO: Execute the sql command.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUpdate() {
        return commandType != CommandType.SELECT;
    }

    /**
     * Set the where condition.
     *
     * @param whereCondition The where condition.
     */
    public void setWhereCondition(@NotNull WhereCondition<?> whereCondition) {
        this.whereCondition = whereCondition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public M getResult() {
        // TODO: Return the result.
        return null;
    }

}
