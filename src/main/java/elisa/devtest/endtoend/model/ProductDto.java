package elisa.devtest.endtoend.model;

public class ProductDto {
    private final String productGroup;
    private final String productJson;

    public ProductDto(String productGroup, String productJson) {
        this.productGroup = productGroup;
        this.productJson = productJson;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public String getProductJson() {
        return productJson;
    }
}
