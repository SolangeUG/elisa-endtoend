package elisa.devtest.endtoend.model;

public class Price {
    private final Long id;
    private final Double recurringPrice;
    private final Double oneTimePrice;
    private final Long recurringCount;

    // Empty private constructor for json serialization framework
    private Price() { this(null, null, null, null);}

    public Price(Long id, Double recurringPrice, Double oneTimePrice, Long recurringCount) {
        this.id = id;
        this.recurringPrice = recurringPrice;
        this.recurringCount = recurringCount;
        this.oneTimePrice = oneTimePrice;
    }

    public Long getId() {
        return id;
    }

    public Double getRecurringPrice() {
        return recurringPrice;
    }

    public Double getOneTimePrice() {
        return oneTimePrice;
    }

    public Long getRecurringCount() {
        return recurringCount;
    }
}
