package elisa.devtest.endtoend.model;


public class OrderLine {
    private long orderLineId;
    private String productId;
    private String productName;
    private int quantity;

    /**
     * Default no-argument constructor for json serialization framework
     */
    public OrderLine() {}

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

    public long getOrderLineId() {
        return orderLineId;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }
}
