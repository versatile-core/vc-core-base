package de.plk.core.base.database.meta;

import de.plk.core.api.database.meta.Column;
import de.plk.core.api.database.query.IQueryBuilder;

/**
 * @author SoftwareBuilds
 * @since 10.08.2023 12:43
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class WhereCondition<V> {

    /**
     * The column to check the condition.
     */
    private final Column column;

    /**
     * The operation to check on the condition.
     */
    private final IQueryBuilder.Operand operand;

    /**
     * The value to check on the condition.
     */
    private final V needle;

    /**
     * Construct a where condition.
     *
     * @param column  The column.
     * @param operand The operation.
     * @param needle  The value.
     */
    public WhereCondition(Column column, IQueryBuilder.Operand operand, V needle) {
        this.column = column;
        this.operand = operand;
        this.needle = needle;
    }

    /**
     * Get the sql command from the condition.
     *
     * @return The sql condition command.
     */
    public String getConditionCommand() {
        return String.format("WHERE %s %s %s", column.name(), operand.name(), needle);
    }
}
