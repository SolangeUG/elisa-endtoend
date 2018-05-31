package elisa.devtest.endtoend.service;

import elisa.devtest.endtoend.dao.OrderDao;
import elisa.devtest.endtoend.exception.OrderProcessingException;
import elisa.devtest.endtoend.model.Customer;
import elisa.devtest.endtoend.model.Order;
import elisa.devtest.endtoend.util.Messages;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Service class for processing product orders
 * @author Solange U. Gasengayire
 */
public class OrderService {

    private OrderDao orderDao = new OrderDao();
    private CustomerService customerService = new CustomerService();

    /**
     * Default no-argument constructor
     */
    public OrderService() {}

    /**
     * Constructor
     * @param orderDao order DAO
     */
    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    /**
     * Save order
     * @param order new order to create
     * @return saved order object
     * @throws OrderProcessingException in case of processing error
     */
    public Order saveOrder(Order order) throws OrderProcessingException {
        try {

            // make sure order has valid customer information
            if (order.getCustomer().getCustomerId() <= 0) {

                // first, look for customer with corresponding company name
                Customer customer = customerService.getCustomer(order.getCustomer().getCompanyName());
                if (customer.getCustomerId() <= 0) {
                    // create customer when not found
                    customer = customerService.saveCustomer(order.getCustomer());
                }
                order.setCustomer(customer);
            }

            long result = orderDao.save(order);
            return orderDao.find(result);
        } catch (DataAccessException exception) {
            throw new OrderProcessingException(Messages.ORDER_SAVING_EXCEPTION, exception);
        }
    }

    /**
     * Return order by id
     * @param orderId id of order to look for
     * @return the corresponding order
     * @throws OrderProcessingException in case of exception/error
     */
    public Order getOrder(long orderId) throws OrderProcessingException {
        try {
            return orderDao.find(orderId);
        } catch (DataAccessException exception) {
            throw new OrderProcessingException(Messages.ORDER_RETRIEVING_EXCEPTION, exception);
        }
    }

    /**
     * Return list of all orders
     * @return all orders
     */
    public List<Order> getOrders() {
        return orderDao.findOrders();
    }
}
