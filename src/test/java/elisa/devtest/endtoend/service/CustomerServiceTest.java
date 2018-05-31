package elisa.devtest.endtoend.service;

import elisa.devtest.endtoend.dao.CustomerDao;
import elisa.devtest.endtoend.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * CustomerService class unit tests
 * @author Solange U. Gasengayire
 */
@DisplayName("CustomerService should")
class CustomerServiceTest {

    @Mock
    private CustomerDao mockCustomerDao;

    @InjectMocks
    private CustomerService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("invoke CustomerDao::find by company name method")
    void shouldInvokeCustomerDaoFindByCompanyMethod() {
        assertNotNull(mockCustomerDao);
        assertNotNull(service);

        Customer customer = mock(Customer.class);

        when(mockCustomerDao.save(customer)).thenReturn(8L);
        assertEquals(8L, mockCustomerDao.save(customer));

        service.getCustomer("Mobistar");

        // verify that CustomerDao::find by company name method was invoked
        verify(mockCustomerDao).find(any(String.class));
    }

    @Test
    @DisplayName("invoke CustomerDao::find by id name method")
    void shouldInvokeCustomerDaoFindByIdMethod() {
        Customer customer = mock(Customer.class);

        when(mockCustomerDao.save(customer)).thenReturn(4L);
        assertEquals(4L, mockCustomerDao.save(customer));

        service.getCustomer(2L);

        // verify that CustomerDao::find by id name method was invoked
        verify(mockCustomerDao).find(any(Long.class));
    }

    @Test
    @DisplayName("invoke CustomerDao::save method")
    void shouldInvokeCustomerDaoSaveMethod() {
        Customer customer = mock(Customer.class);

        when(mockCustomerDao.find(any(Long.class))).thenReturn(customer);
        assertEquals(customer, mockCustomerDao.find(any(Long.class)));

        service.saveCustomer(customer);

        // verify that CustomerDao::save method was invoked
        verify(mockCustomerDao).save(any(Customer.class));
    }
}
