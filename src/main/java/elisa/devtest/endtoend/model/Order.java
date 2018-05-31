package elisa.devtest.endtoend.model;


import java.util.ArrayList;
import java.util.List;

public class Order {
    private long orderId;
    private Customer customer;
    private List<OrderLine> orderLines = new ArrayList<>();

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

    public long getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

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
