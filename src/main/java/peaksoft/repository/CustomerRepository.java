package peaksoft.repository;

import peaksoft.entity.Customer;

import java.util.List;

public interface CustomerRepository {

    void saveCustomer(Customer customer);

    Customer getCustomerById(Long id);

    List<Customer> getAllCustomers();

    void updateCustomer(Long id, Customer newCustomer);

    void deleteCustomerById(Long id);

    public void assignCustomerToAgency(Long customerId, List<Long> agencyIdes);
}
