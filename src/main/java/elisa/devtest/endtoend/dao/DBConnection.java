package elisa.devtest.endtoend.dao;

import elisa.devtest.endtoend.util.Messages;
import org.h2.jdbcx.JdbcConnectionPool;

import javax.sql.DataSource;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class to establish a DB connection
 */
public class DBConnection {

    private static final Logger LOGGER = Logger.getLogger(DBConnection.class.getName());
    private static DataSource dataSource = null;

    /**
     * Return application data source
     * @return data source
     */
    public static synchronized DataSource getDataSource() {
        if (dataSource == null) {
            try {
                String driverName = "org.h2.Driver";
                String dataSourceUser = "sa";
                String dataSourcePassword = "";
                String dataSrouceUrl = "jdbc:h2:mem:devtest;MODE=Oracle";

                Class.forName(driverName);
                dataSource = JdbcConnectionPool.create(dataSrouceUrl, dataSourceUser, dataSourcePassword);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, Messages.DATASOURCE_CONNECTION_EXCEPTION);
            }
        }

        return dataSource;
    }
}
