package elisa.devtest.endtoend.model;

public class Customer {
    private long customerId;
    private String companyName;
    private String street;
    private String postalCode;
    private String city;
    private String country;

    public Customer(long customerId, String companyName, String street, String postalCode, String city, String country) {
        this.customerId = customerId;
        this.companyName = companyName;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    public Customer() {
    }

    public long getCustomerId() {
        return customerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
