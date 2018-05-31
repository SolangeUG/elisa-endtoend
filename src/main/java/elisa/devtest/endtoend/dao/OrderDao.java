package elisa.devtest.endtoend.dao;

import elisa.devtest.endtoend.model.Customer;
import elisa.devtest.endtoend.model.Order;
import elisa.devtest.endtoend.model.OrderLine;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class OrderDao {

    public List<Order> findOrders() {
        try {
            return createJdbcTemplate()
                    .query("select * from orders",
                            (resultSet, rowNumber) -> new Order(resultSet.getLong("order_id"),
                                                                findCustomer(resultSet.getLong("customer_id")),
                                                                findOrderLines(resultSet.getLong("order_id")))
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    private List<OrderLine> findOrderLines(long orderId) {
        try {
            return createJdbcTemplate()
                    .query("select * from order_line where order_id = ?",
                            new Object[]{orderId},
                            (resultSet, rowNumber) -> new OrderLine(resultSet.getLong("order_line_id"),
                                                                    resultSet.getLong("product_id"),
                                                                    resultSet.getString("product_name"),
                                                                    resultSet.getInt("quantity"))
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    public Customer findCustomer(final long customerId) {
        try {
            return createJdbcTemplate()
                    .queryForObject("select * from customer where customer_id = ?",
                                    new Object[]{customerId},
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

    private JdbcTemplate createJdbcTemplate() {
        return new JdbcTemplate(DBConnection.getDataSource());
    }


    /**
     * Persist an order to the datasource
     * @param order order to be saved
     * @return the newly saved order id
     * @throws DataAccessException in case of error in operation
     */
    public long save(Order order) throws DataAccessException {
        final String orderSql = "INSERT INTO orders(customer_id) VALUES (?)";
        final long customerId = order.getCustomer().getCustomerId();
        KeyHolder keyHolder = new GeneratedKeyHolder();

        // save order
        createJdbcTemplate().update(
                connection -> {
                    PreparedStatement preparedStatement =
                            connection.prepareStatement(orderSql, new String[] {"id"});
                    preparedStatement.setString(1, String.valueOf(customerId));
                    return preparedStatement;
                }, keyHolder);

        final long orderId = keyHolder.getKey().longValue();
        final List<OrderLine> lines = order.getOrderLines();

        // save orderLines if applicable
        if (lines != null && !lines.isEmpty()) {
            final String orderLineSql = "INSERT INTO order_line(order_id, product_id, product_name, quantity) " +
                                        "VALUES(?, ?, ?, ?)";
            createJdbcTemplate().batchUpdate(orderLineSql,
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                            preparedStatement.setLong(1, orderId);
                            preparedStatement.setLong(2, lines.get(i).getProductId());
                            preparedStatement.setString(3, lines.get(i).getProductName());
                            preparedStatement.setLong(4, lines.get(i).getQuantity());
                        }

                        @Override
                        public int getBatchSize() {
                            return lines.size();
                        }
                    }
            );
        }

        return orderId;
    }

    /**
     * Return order with given id
     * @param orderId the id to look for
     * @return corresponding order
     * @throws DataAccessException in case of error in operation
     */
    public Order find(long orderId) throws DataAccessException {
        return createJdbcTemplate()
                .queryForObject("SELECT * FROM orders WHERE order_id = ?",
                        new Object[]{orderId},
                        (resultSet, rowNumber) -> new Order(resultSet.getLong("order_id"),
                                                            findCustomer(resultSet.getLong("customer_id")),
                                                            findOrderLines(resultSet.getLong("order_id")))
                );
    }

}
