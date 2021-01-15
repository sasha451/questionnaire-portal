package by.shulga.softarex.questionnaireportal.service;

import by.shulga.softarex.questionnaireportal.entity.Customer;
import by.shulga.softarex.questionnaireportal.exception.NotFoundException;
import by.shulga.softarex.questionnaireportal.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public Customer getCustomerById(long id) {
       return customerRepository.findById(id).orElseThrow(() -> new NotFoundException("No customer with id " + id));
    }

    @Override
    public void deleteCustomer(long id) {
        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        Optional<Customer> foundCustomerOptional = customerRepository.findByEmail(customer.getEmail());
        if (foundCustomerOptional.isEmpty()) {
            return customerRepository.save(customer);
        } else {
            throw new IllegalArgumentException("Customer with email " + customer.getEmail() + " already exists");
        }
    }

    @Override
    public Customer updateCustomer(long id, Customer customer) {
        Customer foundCustomer = getCustomerById(id);
        foundCustomer.setFirstName(customer.getFirstName());
        foundCustomer.setLastName(customer.getLastName());
        foundCustomer.setEmail(customer.getEmail());
        foundCustomer.setPassword(customer.getPassword());
        foundCustomer.setPhoneNumber(customer.getPhoneNumber());
        foundCustomer.setResponseList(customer.getResponseList());
        foundCustomer.setFieldList(customer.getFieldList());
        return customerRepository.save(foundCustomer);
    }
}
