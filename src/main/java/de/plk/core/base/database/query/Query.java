package de.plk.core.base.database.query;

import de.plk.core.api.database.IModel;
import de.plk.core.api.database.meta.IModelInformation;
import de.plk.core.api.database.query.CommandType;
import de.plk.core.api.database.query.IQuery;
import de.plk.core.api.database.query.IQueryBuilder;
import de.plk.core.api.utils.IManager;
import de.plk.core.base.database.meta.WhereCondition;
import de.plk.core.base.entity.VersatilePlayer;
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
     * The other or adaptive conditions.
     */
    private final IManager<WhereCondition> adaptiveOrWheres = new Manager<>();

    /**
     * The other and adaptive conditions.
     */
    private final IManager<WhereCondition> adaptiveAndWheres = new Manager<>();

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
     * Get all or adaptive where conditions.
     *
     * @return The or adaptive where conditions.
     */
    public IManager<WhereCondition> getAdaptiveOrWheres() {
        return adaptiveOrWheres;
    }

    /**
     * Get all and adaptive where conditions.
     *
     * @return The and adaptive where conditions.
     */
    public IManager<WhereCondition> getAdaptiveAndWheres() {
        return adaptiveAndWheres;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommand() {
        IModelInformation modelInformation = M.getModelInformation(VersatilePlayer.class);
        modelInformation.getRelations().forEach(relation -> {
            switch (relation.relationType()) {
                case MANY_TO_MANY -> {

                }

                case ONE_TO_MANY -> {
                    IQuery<IModel> query = new QueryBuilder<>().setCommandType(CommandType.SELECT).where(relation.foreignColumn(), IQueryBuilder.Operand.EQUAL, relation.foreignColumn()).build();
                    query.execute();

                    IModel model = query.getResult();

                    if (modelInformation.getFieldFromRelation(relation).getClass().equals(model.getClass())) {
                        
                    }
                }

                case ONE_TO_ONE -> {

                }
            }
        });
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
