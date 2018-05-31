package elisa.devtest.endtoend.dao;

import elisa.devtest.endtoend.DbBootstrap;
import elisa.devtest.endtoend.model.Customer;
import elisa.devtest.endtoend.model.Order;
import elisa.devtest.endtoend.model.OrderLine;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * OrderDao class tests
 * @author Solange U. Gasengayire
 */
@DisplayName("OrderDao should")
class OrderDaoTest {

    private OrderDao orderDao = new OrderDao();

    @BeforeAll
    static void setUp() {
        System.setProperty("insertExampleData", "true");
        new DbBootstrap().bootstratp();
    }

    @Test
    @DisplayName("return non empty list of orders from example data")
    void shouldReturnNonEmptyListOfOrdersFromExampleData() {
        List<Order> orders = orderDao.findOrders();
        assertNotNull(orders);
        assertNotEquals(0, orders.size());
    }

    @Test
    @DisplayName("return valid customer for example order")
    void shouldReturnValidCustomerForExampleOrder() {
        List<Order> orders = orderDao.findOrders();
        Order order = orders.get(0);
        assertNotNull(order);
        long id = order.getCustomer().getCustomerId();

        Customer customer = orderDao.findCustomer(id);
        assertNotNull(customer);
        assertEquals(id, customer.getCustomerId());
    }

    @Test
    @DisplayName("save a valid order for example customer")
    void shouldSaveOrderForCustomer() {
        Customer customer = orderDao.findCustomer(1L);
        List<OrderLine> lines = new ArrayList<>();

        Order order = new Order(customer, lines);
        long orderId = orderDao.save(order);
        assertTrue(orderId > 0);
    }

    @Test
    @DisplayName("should successfully save order with order lines")
    void shouldCreateOrderLinesForOrder() {
        Customer customer = orderDao.findCustomer(1L);
        OrderLine line1 = new OrderLine(1, "Nokia Lumia 1020", 4);
        OrderLine line2 = new OrderLine(2, "Nokia Lumia 1520", 2);
        List<OrderLine> lines = Arrays.asList(line1, line2);

        Order order = new Order(customer, lines);
        long orderId = orderDao.save(order);
        assertTrue(orderId > 0);

        Order savedOrder = orderDao.find(orderId);
        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getOrderLines());
        assertEquals(2, savedOrder.getOrderLines().size());
    }
}
