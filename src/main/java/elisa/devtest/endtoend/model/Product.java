package elisa.devtest.endtoend.model;

/**
 * A bean class to represent a product information
 */
public class Product {
    private final String id;
    private final Long priceId;
    private final String name;
    private final String description;

    /**
     * Private constructor
     * for json serialization framework
     */
    private Product() {
        this(null, null, null, null);
    }

    /**
     * Constructor
     * @param id product identifier
     * @param priceId price identifier
     * @param name product name
     * @param description product description/detail
     */
    public Product(String id, Long priceId, String name, String description) {
        this.id = id;
        this.priceId = priceId;
        this.name = name;
        this.description = description;
    }

    /**
     * Return product identifier
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Return price identifier
     * @return priceId
     */
    public Long getPriceId() {
        return priceId;
    }

    /**
     * Return product name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Return product description/detail
     * @return description
     */
    public String getDescription() {
        return description;
    }
}
