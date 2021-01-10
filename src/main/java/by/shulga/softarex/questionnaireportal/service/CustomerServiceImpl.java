package by.shulga.softarex.questionnaireportal.service;

import by.shulga.softarex.questionnaireportal.entity.Customer;
import by.shulga.softarex.questionnaireportal.exception.NotFoundException;
import by.shulga.softarex.questionnaireportal.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getCustomerById(long id) {
        Optional<Customer> foundCustomerOptional = customerRepository.findById(id);
        if (foundCustomerOptional.isPresent()) {
            return foundCustomerOptional.get();
        } else {
            throw new NotFoundException("No customer with id " + id);
        }
    }

    @Override
    public void deleteCustomer(long id) {
        this.getCustomerById(id);
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        Optional<Customer> foundCustomer = customerRepository.findByEmail(customer.getEmail());
        return foundCustomer.orElseGet(() -> customerRepository.save(customer));
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Optional<Customer> foundCustomer = customerRepository.findByEmail(customer.getEmail());
        if (foundCustomer.isPresent()) {
            foundCustomer.get().setFirstName(customer.getFirstName());
            foundCustomer.get().setLastName(customer.getLastName());
            foundCustomer.get().setEmail(customer.getEmail());
            foundCustomer.get().setPassword(customer.getPassword());
            foundCustomer.get().setPhoneNumber(customer.getPhoneNumber());
            foundCustomer.get().setResponseList(customer.getResponseList());
            foundCustomer.get().setFieldList(customer.getFieldList());
            customerRepository.save(foundCustomer.get());
            return customerRepository.findByEmail(customer.getEmail()).orElseThrow(() ->
                    new NotFoundException("No such customer"));
        } else {
            throw new NotFoundException("No such customer");
        }
    }
}
