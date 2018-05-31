package elisa.devtest.endtoend.service;

import elisa.devtest.endtoend.dao.CustomerDao;
import elisa.devtest.endtoend.model.Customer;

/**
 * Service class for processing customer related operations
 * @author Solange U. Gasengayire
 */
public class CustomerService {

    private CustomerDao customerDao = new CustomerDao();

    /**
     * Default no-argument constructor
     */
    public CustomerService() {}

    /**
     * Constructor
     * @param customerDao customer DAO
     */
    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /**
     * Return customer with given company name
     * @param companyName company name
     * @return the corresponding customer
     */
    public Customer getCustomer(String companyName) {
        return customerDao.find(companyName);
    }

    /**
     * Return customer with given id
     * @param customerId customer id
     * @return the corresponding customer
     */
    public Customer getCustomer(long customerId) {
        return customerDao.find(customerId);
    }

    /**
     * Save new customer
     * @param customer customer to save
     * @return newly saved customer
     */
    public Customer saveCustomer(Customer customer) {
        long resultId = customerDao.save(customer);
        return customerDao.find(resultId);
    }
}
