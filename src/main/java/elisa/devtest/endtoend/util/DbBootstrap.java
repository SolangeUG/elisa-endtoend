package elisa.devtest.endtoend.util;


import elisa.devtest.endtoend.dao.DBConnection;
import org.apache.commons.io.IOUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Utility class to bootstrap datasource
 */
public class DbBootstrap {

    /**
     * Load datasource and insert initial data
     */
    public void bootstratp() {
        executeWith("base.sql");
        if(System.getProperty("insertExampleData") != null) {
            executeWith("data.sql");
        }
        executeWithJson("product_dump", "PHONES", "products.json");
        executeWithJson("pricing_dump", "PHONES", "pricing.json");
    }

    /**
     * Execute SQL statements from given file
     * @param fileName SQL input file
     */
    private void executeWith(final String fileName) {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
        Scanner scanner = new Scanner(in, "UTF-8").useDelimiter("\\A");
        String sql = scanner.hasNext() ? scanner.next() : "";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConnection.getDataSource());
        jdbcTemplate.execute(sql);
    }

    /**
     * Insert JSON input content into specified table
     * @param table table name
     * @param groupIdentifier new entry identifier
     * @param jsonFileName JSON input file
     */
    private void executeWithJson(final String table, final String groupIdentifier, final String jsonFileName) {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(jsonFileName);
        String json;
        try {
            json = IOUtils.toString(in);
        } catch (IOException e) {
            throw new RuntimeException(Messages.DATASOURCE_JSON_INSERTION_EXCEPTION, e);
        }
        String statement = "INSERT INTO " + table + " VALUES (?, ?);";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConnection.getDataSource());
        jdbcTemplate.execute(statement, new PreparedJsonStatement(groupIdentifier, json));
    }
}
