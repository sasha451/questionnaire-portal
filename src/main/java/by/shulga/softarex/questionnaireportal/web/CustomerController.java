package by.shulga.softarex.questionnaireportal.web;

import by.shulga.softarex.questionnaireportal.dto.CustomerDto;
import by.shulga.softarex.questionnaireportal.entity.Customer;
import by.shulga.softarex.questionnaireportal.mapper.CustomerMapper;
import by.shulga.softarex.questionnaireportal.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

    private final CustomerService customerService;

    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @PostMapping
    public ResponseEntity<CustomerDto> saveCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        Customer savedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(customerMapper.toDto(savedCustomer), HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<CustomerDto> findCustomerByEmail(@PathVariable("email") String email) {
        Customer customer = customerService.getCustomerByEmail(email);
        return new ResponseEntity<>(customerMapper.toDto(customer), HttpStatus.OK);
    }

    @GetMapping(value = "/login", params = {"email", "password"})
    public ResponseEntity<CustomerDto> loginCustomer(@RequestParam("email") String email,
                                                     @RequestParam("password") String password) {
        Customer customer = customerService.getCustomerByEmailAndPassword(email, password);
        return new ResponseEntity<>(customerMapper.toDto(customer), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> findAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerDto> customerDtoList = customers.stream().map(customerMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(customerDtoList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("id") long id,
                                                      @RequestBody CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        return new ResponseEntity<>(customerMapper.toDto(updatedCustomer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCustomer(@PathVariable("id") long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
