package de.plk.core.base.database;

import de.plk.core.api.config.IConfig;
import de.plk.core.api.database.IDatabaseManager;
import de.plk.core.api.database.IModel;
import de.plk.core.api.database.query.IQuery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author SoftwareBuilds
 * @since 10.08.2023 11:25
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class DatabaseManager implements IDatabaseManager {

    /**
     * The database configuration.
     */
    private final IConfig<String> databaseConfig;

    /**
     * The active database connection.
     */
    private Connection connection = null;

    /**
     * Creates a database manager.
     *
     * @param databaseConfig The active database config.
     */
    public DatabaseManager(IConfig<String> databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Connection getConnection() {
        return connection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void connect() {
        Map<String, String> config = databaseConfig.getContent();

        try {
            connection = getConnWithProps(
                    config.getOrDefault("hostname", "127.0.0.1"),
                    config.getOrDefault("port", "3306"),
                    config.getOrDefault("database", ""),
                    config.getOrDefault("username", "root"),
                    config.getOrDefault("password", "")
            );
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Get the connection with properties.
     *
     * @param host     The hostname.
     * @param port     The port.
     * @param database The database.
     * @param username The username.
     * @param password The password.
     *
     * @return The connection.
     *
     * @throws SQLException If you could not connect.
     */
    private Connection getConnWithProps(
            String host,
            String port,
            String database,
            String username,
            String password
    ) throws SQLException {
        return DriverManager.getConnection(String.format("jdbc:mysql://%s:%s/%s",
                host, port, database
        ), username, password);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isConnected() {
        return connection != null;
    }

    @Override
    public void disconnect() {
        if (!isConnected()) return;

        try {
            connection.close();
            connection = null;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <M extends IModel> M runSync(IQuery<M> query) {
        query.execute();
        return query.getResult();
    }

}
