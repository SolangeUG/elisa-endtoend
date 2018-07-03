package elisa.devtest.endtoend.model;

/**
 * A class to represent a price information
 */
public class Price {
    private final Long id;
    private final Double recurringPrice;
    private final Double oneTimePrice;
    private final Long recurringCount;

    /**
     * Private constructor
     * for json serialization framework
     */
    private Price() {
        this(null, null, null, null);
    }

    /**
     * Construcotr
     * @param id price identifier
     * @param recurringPrice recurring price
     * @param oneTimePrice one time price
     * @param recurringCount number of recurrence
     */
    public Price(Long id, Double recurringPrice, Double oneTimePrice, Long recurringCount) {
        this.id = id;
        this.recurringPrice = recurringPrice;
        this.recurringCount = recurringCount;
        this.oneTimePrice = oneTimePrice;
    }

    /**
     * Return price identifier
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Return recurring price
     * @return recurringPrice
     */
    public Double getRecurringPrice() {
        return recurringPrice;
    }

    /**
     * Return one time price
     * @return oneTimePrice
     */
    public Double getOneTimePrice() {
        return oneTimePrice;
    }

    /**
     * Return number of recurrence
     * @return recurrenceCount
     */
    public Long getRecurringCount() {
        return recurringCount;
    }
}
