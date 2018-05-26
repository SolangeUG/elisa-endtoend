package elisa.devtest.endtoend.model;

public class PriceDto {
    private final String pricingGroup;
    private final String priceJson;

    public PriceDto(String pricingGroup, String priceJson) {
        this.pricingGroup = pricingGroup;
        this.priceJson = priceJson;
    }

    public String getPricingGroup() {
        return pricingGroup;
    }

    public String getPriceJson() {
        return priceJson;
    }
}
