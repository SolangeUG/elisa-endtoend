package elisa.devtest.endtoend.dao;

import elisa.devtest.endtoend.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;

/**
 * Customer Data Access Object
 * @author Solange U. Gasengayire
 */
public class CustomerDao {

    /**
     * Return a customer with the given id
     * @param id customer id
     * @return the corresponding customer
     */
    public Customer find(final long id) {
        try {
            return createJdbcTemplate()
                    .queryForObject("SELECT * FROM customer WHERE customer_id = ?",
                            new Object[]{id},
                            (resultSet, rowNumber) -> new Customer(resultSet.getLong("customer_id"),
                                    resultSet.getString("company_name"),
                                    resultSet.getString("street"),
                                    resultSet.getString("postal_code"),
                                    resultSet.getString("city"),
                                    resultSet.getString("country"))
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Customer();
    }

    /**
     * Return a customer with the given company name
     * @param companyName company name
     * @return the corresponding customer
     */
    public Customer find(final String companyName) {
        try {
            return createJdbcTemplate()
                    .queryForObject("SELECT * FROM customer WHERE company_name = ?",
                            new Object[]{companyName},
                            (resultSet, rowNumber) -> new Customer(resultSet.getLong("customer_id"),
                                    resultSet.getString("company_name"),
                                    resultSet.getString("street"),
                                    resultSet.getString("postal_code"),
                                    resultSet.getString("city"),
                                    resultSet.getString("country"))
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Customer();
    }

    /**
     * Save a new customer
     * @param customer customer to save
     * @return savec customer id
     */
    public long save(Customer customer) {
        try {
            final String sql = "INSERT INTO customer(company_name, street, postal_code, city, country) " +
                               "VALUES(?, ?, ?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            createJdbcTemplate().update(
                    connection -> {
                        PreparedStatement statement =
                                connection.prepareStatement(sql, new String[]{"id"});
                        statement.setString(1, customer.getCompanyName());
                        statement.setString(2, customer.getStreet());
                        statement.setString(3, customer.getPostalCode());
                        statement.setString(4, customer.getCity());
                        statement.setString(5, customer.getCountry());
                        return statement;
                    }, keyHolder);
            return keyHolder.getKey().longValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Get JdbcTemplate
     * @return a JdbcTemplate object
     */
    private JdbcTemplate createJdbcTemplate() {
        return new JdbcTemplate(DBConnection.getDataSource());
    }
}
