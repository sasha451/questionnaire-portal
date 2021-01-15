package by.shulga.softarex.questionnaireportal.mapper;

import by.shulga.softarex.questionnaireportal.dto.FieldDto;
import by.shulga.softarex.questionnaireportal.entity.Field;
import by.shulga.softarex.questionnaireportal.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Component
public class FieldMapper {

    private final ModelMapper modelMapper;

    private final CustomerService customerService;

    @Autowired
    public FieldMapper(ModelMapper modelMapper, CustomerService customerService) {
        this.modelMapper = modelMapper;
        this.customerService = customerService;
    }

    @Transactional(propagation= Propagation.REQUIRED, readOnly=true)
    public Field toEntity(FieldDto fieldDto) {
        Field field = Objects.isNull(fieldDto) ? null : modelMapper.map(fieldDto, Field.class);
        if (!Objects.isNull(field)) {
            field.setCustomer(customerService.getCustomerById(fieldDto.getCustomerId()));
            field.getCustomer().getFieldList().add(field);
        }
        return field;
    }

    public FieldDto toDto(Field field) {
        return Objects.isNull(field) ? null : modelMapper.map(field, FieldDto.class);
    }
}
