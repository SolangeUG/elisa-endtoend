package elisa.devtest.endtoend;


import elisa.devtest.endtoend.dao.DBConnection;
import org.apache.commons.io.IOUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class DbBootstrap {
    public void bootstratp() {
        executeWith("base.sql");
        if(System.getProperty("insertExampleData") != null) {
            executeWith("data.sql");
        }
        executeWithJson("product_dump", "PHONES", "products.json");
        executeWithJson("pricing_dump", "PHONES", "pricing.json");
    }

    private void executeWith(final String fileName) {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
        Scanner scanner = new Scanner(in, "UTF-8").useDelimiter("\\A");
        String sql = scanner.hasNext() ? scanner.next() : "";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConnection.getDataSource());
        jdbcTemplate.execute(sql);
    }

    private void executeWithJson(final String table, final String groupIdentifier, final String jsonFileName) {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(jsonFileName);
        String json = null;
        try {
            json = IOUtils.toString(in);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load json files into database", e);
        }
        String statement = "INSERT INTO " + table + " VALUES (?, ?);";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DBConnection.getDataSource());
        jdbcTemplate.execute(statement, new PreparedJsonStatement(groupIdentifier, json));
    }
}
