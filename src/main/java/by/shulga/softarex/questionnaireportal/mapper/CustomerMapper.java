package by.shulga.softarex.questionnaireportal.mapper;

import by.shulga.softarex.questionnaireportal.dto.CustomerDto;
import by.shulga.softarex.questionnaireportal.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomerMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public CustomerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Customer toEntity(CustomerDto customerDto) {
        return Objects.isNull(customerDto) ? null : modelMapper.map(customerDto, Customer.class);
    }

    public CustomerDto toDto(Customer customer) {
        return Objects.isNull(customer) ? null : modelMapper.map(customer, CustomerDto.class);
    }
}
