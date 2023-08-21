package de.plk.core.base.database.query;

import de.plk.core.api.database.IModel;
import de.plk.core.api.database.query.CommandType;
import de.plk.core.api.database.query.IQuery;
import de.plk.core.api.utils.IManager;
import de.plk.core.base.database.meta.WhereCondition;
import de.plk.core.base.utils.Manager;

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
    private WhereCondition whereCondition;

    /**
     * The other adaptive conditions.
     */
    private final IManager<WhereCondition> adaptiveWheres = new Manager<>();

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
    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    /**
     * Get all adaptive where conditions.
     *
     * @return The adaptive where conditions.
     */
    public IManager<WhereCondition> getAdaptiveWheres() {
        return adaptiveWheres;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommand() {
        return null;
    }

    /**
     * Execute the query on the database.
     */
    public void execute() {

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
    public void setWhereCondition(WhereCondition whereCondition) {
        this.whereCondition = whereCondition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public M getResult() {
        return null;
    }
}
