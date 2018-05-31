package elisa.devtest.endtoend.dao;

import elisa.devtest.endtoend.DbBootstrap;
import elisa.devtest.endtoend.model.Customer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * CustomerDAO class tests
 * @author Solange U. Gasengayire
 */
@DisplayName("CustomerDAO should")
class CustomerDaoTest {

    private CustomerDao customerDao = new CustomerDao();

    @BeforeAll
    static void setUp() {
        System.setProperty("insertExampleData", "true");
        new DbBootstrap().bootstratp();
    }

    @Test
    @DisplayName("return customer from example data")
    void shouldReturnCustomerFromExampleData() {
        Customer customer = customerDao.find(1L);
        assertNotNull(customer);
    }

    @Test
    @DisplayName("create new customer with provided information")
    void shouldCreateNewCustomer() {
        Customer customer = new Customer(
                "Mobistar", "Avenue Louise 404", "1000", "Brussels", "Belgium");
        long customerId = customerDao.save(customer);
        assertTrue(customerId > 0);
    }
}
