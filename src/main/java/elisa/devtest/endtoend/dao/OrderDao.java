package elisa.devtest.endtoend.dao;

import elisa.devtest.endtoend.model.Customer;
import elisa.devtest.endtoend.model.Order;
import elisa.devtest.endtoend.model.OrderLine;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;
import java.util.List;

public class OrderDao {

    public List<Order> findOrders() {
        try {
            return createJdbcTemplate().query("select * from orders", (resultSet, rowNumber) -> new Order(resultSet.getLong("order_id"), findCustomer(resultSet.getLong("customer_id")), findOrderLines(resultSet.getLong("order_id"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    private List<OrderLine> findOrderLines(long orderId) {
        try {
            return createJdbcTemplate().query("select * from order_line where order_id = ?", new Object[]{orderId}, (resultSet, rowNumber) -> new OrderLine(resultSet.getLong("order_line_id"), resultSet.getLong("product_id"), resultSet.getString("product_name"), resultSet.getInt("quantity")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    public Customer findCustomer(final long customerId) {
        try {
            return createJdbcTemplate().queryForObject("select * from customer where customer_id = ?", new Object[]{customerId}, (resultSet, rowNumber) -> new Customer(resultSet.getLong("customer_id"), resultSet.getString("company_name"), resultSet.getString("street"), resultSet.getString("postal_code"), resultSet.getString("city"), resultSet.getString("country")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Customer();
    }

    private JdbcTemplate createJdbcTemplate() {
        return new JdbcTemplate(DBConnection.getDataSource());
    }

}
