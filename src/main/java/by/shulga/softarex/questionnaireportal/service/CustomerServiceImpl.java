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

    private final SendEmailService sendEmailService;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, SendEmailService sendEmailService) {
        this.customerRepository = customerRepository;
        this.sendEmailService = sendEmailService;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public Customer getCustomerById(long id) {
       return customerRepository.findById(id).orElseThrow(() -> new NotFoundException("No customer with id " + id));
    }

    @Override
    public Customer getCustomerByEmailAndPassword(String email, String password) {
        Customer foundCustomer = getCustomerByEmail(email);
        if (foundCustomer.getPassword().equals(password)) {
            return foundCustomer;
        } else {
            throw new NotFoundException("No customer with email " + email + " and this password");
        }
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("No customer with email "
                + email));
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
            String subject = "Questionnaire Portal Registration";
            String text = "Your registration was successful. Welcome to Questionnaire Portal!";
            sendEmailService.sendSimpleMessage(customer.getEmail(), subject, text);
            return customerRepository.save(customer);
        } else {
            throw new IllegalArgumentException("Customer with email " + customer.getEmail() + " already exists");
        }
    }

    @Override
    public Customer updatePassword(long id, String password) {
        Customer foundCustomer = getCustomerById(id);
        foundCustomer.setPassword(password);
        String subject = "Questionnaire Portal Password Change";
        String text = "Your password was successfully changed!";
        sendEmailService.sendSimpleMessage(foundCustomer.getEmail(), subject, text);
        return customerRepository.save(foundCustomer);
    }

    @Override
    public Customer updateCustomer(long id, Customer customer) {
        Customer foundCustomer = getCustomerById(id);
        foundCustomer.setFirstName(customer.getFirstName());
        foundCustomer.setLastName(customer.getLastName());
        foundCustomer.setEmail(customer.getEmail());
        foundCustomer.setPhoneNumber(customer.getPhoneNumber());
        foundCustomer.setResponseList(customer.getResponseList());
        foundCustomer.setFieldList(customer.getFieldList());
        return customerRepository.save(foundCustomer);
    }
}
