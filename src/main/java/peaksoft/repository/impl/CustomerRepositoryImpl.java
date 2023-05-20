package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Agency;
import peaksoft.entity.Customer;
import peaksoft.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void saveCustomer(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return entityManager.createQuery("select c from Customer c", Customer.class).getResultList();
    }

    @Override
    public void updateCustomer(Long id, Customer newCustomer) {
        Customer customer = entityManager.find(Customer.class, id);
        customer.setName(newCustomer.getName());
        customer.setSurname(newCustomer.getSurname());
        customer.setEmail(newCustomer.getEmail());
        customer.setDateOfBirth(newCustomer.getDateOfBirth());
        customer.setGender(newCustomer.getGender());
        customer.setPhoneNumber(newCustomer.getPhoneNumber());
        entityManager.merge(customer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        entityManager.remove(entityManager.find(Customer.class, id));
    }

    @Override
    public void assignCustomerToAgency(Long customerId, List<Long> agencyIdes) {
        Customer customer = entityManager.find(Customer.class, customerId);
        List<Agency> agencies = new ArrayList<>();
        for (Long id : agencyIdes){
            Agency agency = entityManager.find(Agency.class, id);
            agencies.add(agency);
            agency.getCustomers().add(customer);
        }
        customer.setAgencies(agencies);
        entityManager.merge(customer);
    }
}
