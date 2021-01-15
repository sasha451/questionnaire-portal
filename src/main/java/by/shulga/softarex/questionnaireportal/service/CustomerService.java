package by.shulga.softarex.questionnaireportal.service;

import by.shulga.softarex.questionnaireportal.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer getCustomerById(long id);

    void deleteCustomer(long id);

    List<Customer> getAllCustomers();

    Customer saveCustomer(Customer customer);

    Customer updateCustomer(long id, Customer customer);
}
