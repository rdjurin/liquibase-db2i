package liquibase.ext.db2i.database;

import liquibase.database.DatabaseConnection;
import liquibase.database.core.DB2Database;
import liquibase.exception.DatabaseException;
import liquibase.structure.core.Index;

public class DB2iDatabase extends DB2Database {

    @Override
    public int getPriority() {
        return super.getPriority() + 5;
    }

    @Override
    public boolean isCorrectDatabaseImplementation(final DatabaseConnection conn) throws DatabaseException {
        return conn.getDatabaseProductName().startsWith("DB2 UDB for AS/400");
    }

    @Override
    public String getDefaultDriver(final String url) {
        if (url.startsWith("jdbc:as400")) {
            return "com.ibm.as400.access.AS400JDBCDriver";
        }
        return null;
    }

    @Override
    protected String getDefaultDatabaseProductName() {
        return "DB2i";
    }

    @Override
    public String getShortName() {
        return "db2i";
    }

    @Override
    public boolean supportsSchemas() {
        return true;
    }

    @Override
    public String escapeConstraintName(final String p_constraintName) {
        return escapeObjectName(getDefaultCatalogName(), getDefaultSchemaName(), p_constraintName, Index.class);
    }

}
