package elisa.devtest.endtoend.model;

public class Product {
    private final String id;
    private final Long priceId;
    private final String name;
    private final String description;

    // Empty private constructor for json serialization framework
    private Product() {
        this(null, null, null, null);
    }

    public Product(String id, Long priceId, String name, String description) {
        this.id = id;
        this.priceId = priceId;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public Long getPriceId() {
        return priceId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
