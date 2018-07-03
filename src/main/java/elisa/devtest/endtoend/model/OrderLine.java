package elisa.devtest.endtoend.model;

/**
 * A class to represent an order line information
 */
public class OrderLine {
    private long orderLineId;
    private String productId;
    private String productName;
    private int quantity;

    /**
     * Default no-argument constructor for json serialization framework
     */
    public OrderLine() {}

    /**
     * Constructor
     * @param orderLineId order line identifier
     * @param productId product identifier
     * @param productName product name
     * @param quantity quantity
     */
    public OrderLine(long orderLineId, String productId, String productName, int quantity) {
        this.orderLineId = orderLineId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
    }

    /**
     * Constructor
     * @param productId product id for this order line
     * @param productName product name for this order line
     * @param quantity product quantity
     */
    public OrderLine(String productId, String productName, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
    }

    /**
     * Return order line identifier
     * @return orderLineId
     */
    public long getOrderLineId() {
        return orderLineId;
    }

    /**
     * Return product identifier
     * @return productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Return name of product
     * @return productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Return product quantity
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }
}
