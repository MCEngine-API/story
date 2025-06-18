package io.github.mcengine.story.api.database;

import java.sql.Connection;

/**
 * Interface for database operations in MCEngineStory.
 * Provides method contracts for managing database connections.
 */
public interface IMCEngineStoryDB {

    /**
     * Get a connection to the database.
     *
     * @return the SQL Connection
     */
    Connection getDBConnection();
}
