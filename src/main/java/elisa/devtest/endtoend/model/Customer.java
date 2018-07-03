package elisa.devtest.endtoend.model;

/**
 * A bean class to represent customer information
 */
public class Customer {
    private long customerId;
    private String companyName;
    private String street;
    private String postalCode;
    private String city;
    private String country;

    /**
     * Constructor
     * @param customerId customer identifier
     * @param companyName company name
     * @param street street name
     * @param postalCode postal code
     * @param city city
     * @param country country
     */
    public Customer(long customerId, String companyName, String street, String postalCode, String city, String country) {
        this.customerId = customerId;
        this.companyName = companyName;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    /**
     * Default constructor
     */
    public Customer() {
    }

    /**
     * Constructor
     * @param companyName company name
     * @param street street name
     * @param postalCode postal code
     * @param city city name
     * @param country name of country
     */
    public Customer(String companyName, String street, String postalCode, String city, String country) {
        this.companyName = companyName;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    /**
     * Return customer identifier
     * @return customerId
     */
    public long getCustomerId() {
        return customerId;
    }

    /**
     * Return company name
     * @return companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Return street name
     * @return street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Return postal code
     * @return postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Return city name
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Return country name
     * @return country
     */
    public String getCountry() {
        return country;
    }
}
