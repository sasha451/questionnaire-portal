package by.shulga.softarex.questionnaireportal.web;

import by.shulga.softarex.questionnaireportal.dto.CustomerDto;
import by.shulga.softarex.questionnaireportal.dto.FieldDto;
import by.shulga.softarex.questionnaireportal.dto.ResponseDto;
import by.shulga.softarex.questionnaireportal.entity.Customer;
import by.shulga.softarex.questionnaireportal.entity.Field;
import by.shulga.softarex.questionnaireportal.entity.Response;
import by.shulga.softarex.questionnaireportal.mapper.CustomerMapper;
import by.shulga.softarex.questionnaireportal.mapper.FieldMapper;
import by.shulga.softarex.questionnaireportal.mapper.ResponseMapper;
import by.shulga.softarex.questionnaireportal.service.CustomerService;
import by.shulga.softarex.questionnaireportal.service.FieldService;
import by.shulga.softarex.questionnaireportal.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/unregisteredCustomers")
public class UnregisteredCustomerController {

    private final CustomerService customerService;

    private final CustomerMapper customerMapper;

    private final ResponseService responseService;

    private final ResponseMapper responseMapper;

    private SimpMessageSendingOperations messagingTemplate;

    private final FieldService fieldService;

    private final FieldMapper fieldMapper;

    @Autowired
    public UnregisteredCustomerController(CustomerService customerService, CustomerMapper customerMapper,
                                          ResponseService responseService, ResponseMapper responseMapper,
                                          SimpMessageSendingOperations messagingTemplate,
                                          FieldService fieldService, FieldMapper fieldMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
        this.responseService = responseService;
        this.responseMapper = responseMapper;
        this.messagingTemplate = messagingTemplate;
        this.fieldService = fieldService;
        this.fieldMapper = fieldMapper;
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

    @PostMapping(value = "/response")
    public ResponseEntity<ResponseDto> saveResponse(@RequestBody ResponseDto responseDto) throws Exception {
        Response response = responseMapper.toEntity(responseDto);
        Response savedResponse = responseService.saveResponse(response);
        ResponseDto savedResponseDto = responseMapper.toDto(savedResponse);
        messagingTemplate.convertAndSend("/topic/reply", responseDto);
        return new ResponseEntity<>(savedResponseDto, HttpStatus.OK);
    }

    @GetMapping(value = "/byCustomerId", params = "id")
    public ResponseEntity<List<FieldDto>> findAllFieldsByCustomerId(@RequestParam("id") long id) {
        List<Field> fields = fieldService.getAllFieldsByCustomerId(id);
        List<FieldDto> fieldDtoList = fields.stream().map(fieldMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(fieldDtoList, HttpStatus.OK);
    }
}
