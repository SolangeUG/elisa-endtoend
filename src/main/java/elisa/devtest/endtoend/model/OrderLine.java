package elisa.devtest.endtoend.model;


public class OrderLine {
    private long orderLineId;
    private long productId;
    private String productName;
    private int quantity;

    public OrderLine(long orderLineId, long productId, String productName, int quantity) {
        this.orderLineId = orderLineId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
    }

    public long getOrderLineId() {
        return orderLineId;
    }

    public long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }
}
