package liquibase.ext.db2i.database;

import liquibase.database.AbstractJdbcDatabase;
import liquibase.database.DatabaseConnection;

import org.fest.assertions.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

public class DB2iDatabaseTest {

    @Test
    public void testGetPriority() {
        DB2iDatabase db2iDatabase = new DB2iDatabase();
        Assertions.assertThat(db2iDatabase.getPriority()).isEqualTo(AbstractJdbcDatabase.PRIORITY_DEFAULT + 5);
    }

    @Test
    public void testIsCorrectDatabaseImplementation() throws Exception {
        DB2iDatabase db2iDatabase = new DB2iDatabase();

        DatabaseConnection conn = Mockito.mock(DatabaseConnection.class);
        Mockito.when(conn.getDatabaseProductName()).thenReturn("DB2 UDB for AS/400 Version 8");

        boolean correctDatabaseImplementation = db2iDatabase.isCorrectDatabaseImplementation(conn);

        Mockito.verify(conn).getDatabaseProductName();

        Assertions.assertThat(correctDatabaseImplementation).isTrue();
        
        
        Mockito.when(conn.getDatabaseProductName()).thenReturn("DB2");
        
        boolean incorrectDatabaseImplementation = db2iDatabase.isCorrectDatabaseImplementation(conn);

        Mockito.verify(conn, Mockito.times(2)).getDatabaseProductName();

        Assertions.assertThat(incorrectDatabaseImplementation).isFalse();

    }

}
