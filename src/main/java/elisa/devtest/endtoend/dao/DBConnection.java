package elisa.devtest.endtoend.dao;

import org.h2.jdbcx.JdbcConnectionPool;

import javax.sql.DataSource;

public class DBConnection {

    private static DataSource dataSource = null;

    public static synchronized DataSource getDataSource() {
        if (dataSource == null) {
            try {
                Class.forName("org.h2.Driver");
                dataSource = JdbcConnectionPool.create("jdbc:h2:mem:devtest;MODE=Oracle", "sa", "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return dataSource;
    }
}
