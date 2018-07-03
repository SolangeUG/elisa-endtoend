package elisa.devtest.endtoend.model;


import java.util.ArrayList;
import java.util.List;

/**
 * A bean class to represent order information
 */
public class Order {
    private long orderId;
    private Customer customer;
    private List<OrderLine> orderLines = new ArrayList<>();

    /**
     * Constructor
     * @param orderId order identifier
     * @param customer customer
     * @param orderLines order items
     */
    public Order(long orderId, Customer customer, List<OrderLine> orderLines) {
        this.orderId = orderId;
        this.customer = customer;
        this.orderLines = orderLines;
    }

    /**
     * Constructor
     * @param customer customer placing the order
     * @param orderLines list of order lines for this order
     */
    public Order(Customer customer, List<OrderLine> orderLines) {
        this.customer = customer;
        this.orderLines = orderLines;
    }

    /**
     * Default constructor
     */
    public Order() {
    }

    /**
     * return the order identifier
     * @return orderId
     */
    public long getOrderId() {
        return orderId;
    }

    /**
     * Return customer who placed order
     * @return customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Return list of items in this order
     * @return order lines
     */
    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    /**
     * Set customer
     * @param customer customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
