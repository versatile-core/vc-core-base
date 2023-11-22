package de.plk.core.base.database.query;

import de.plk.core.api.code.NotNull;
import de.plk.core.api.code.Nullable;
import de.plk.core.api.database.IModel;
import de.plk.core.api.database.meta.Column;
import de.plk.core.api.database.query.CommandType;
import de.plk.core.api.database.query.IQuery;
import de.plk.core.api.database.query.IQueryBuilder;
import de.plk.core.base.database.meta.WhereCondition;

/**
 * @author SoftwareBuilds
 * @since 10.08.2023 11:39
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class QueryBuilder<M extends IModel> implements IQueryBuilder<M> {

    /**
     * The model query.
     */
    @NotNull
    private final Query<M> query = new Query<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public IQueryBuilder<M> setCommandType(CommandType type) {
        query.setCommandType(type);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V> IQueryBuilder<M> where(@NotNull Column column, @Nullable Operand operand, @NotNull V needle) {
        query.setWhereCondition(new WhereCondition<>(column, operand, needle));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V> IQueryBuilder<M> orWhere(@NotNull Column column, @Nullable Operand operand, @NotNull V needle) {
        query.getAdaptiveOrWheres().add(new WhereCondition<>(column, operand, needle));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <V> IQueryBuilder<M> andWhere(@NotNull Column column, @Nullable Operand operand, @NotNull V needle) {
        query.getAdaptiveAndWheres().add(new WhereCondition<>(column, operand, needle));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IQuery<M> build() {
        return query;
    }

}
