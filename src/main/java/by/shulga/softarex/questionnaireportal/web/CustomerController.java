package by.shulga.softarex.questionnaireportal.web;

import by.shulga.softarex.questionnaireportal.entity.Customer;
import by.shulga.softarex.questionnaireportal.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Customer> findCustomer(@PathVariable("id") long id) {
        Customer customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> findAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(customer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCustomer(@PathVariable("id") long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
