package by.shulga.softarex.questionnaireportal.mapper;

import by.shulga.softarex.questionnaireportal.dto.ResponseDto;
import by.shulga.softarex.questionnaireportal.dto.ResponseEntryDto;
import by.shulga.softarex.questionnaireportal.entity.Response;
import by.shulga.softarex.questionnaireportal.entity.ResponseEntry;
import by.shulga.softarex.questionnaireportal.service.CustomerService;
import by.shulga.softarex.questionnaireportal.service.FieldService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ResponseMapper {

    private final ModelMapper modelMapper;

    private final FieldService fieldService;

    private final CustomerService customerService;

    @Autowired
    public ResponseMapper(ModelMapper modelMapper, FieldService fieldService, CustomerService customerService) {
        this.modelMapper = modelMapper;
        this.customerService = customerService;
        this.fieldService = fieldService;
    }

    public Response toEntity(ResponseDto responseDto) {
        Response response = Objects.isNull(responseDto) ? null : modelMapper.map(responseDto, Response.class);
        List<ResponseEntry> responseEntryList = new ArrayList<>();
        if (!Objects.isNull(response)) {
            for(ResponseEntryDto responseEntryDto : responseDto.getResponseEntryDtoList()) {
                ResponseEntry responseEntry = modelMapper.map(responseEntryDto, ResponseEntry.class);
                responseEntry.setField(fieldService.getFieldById(responseEntryDto.getFieldId()));
                responseEntry.setResponse(response);
                responseEntryList.add(responseEntry);
            }
            response.setResponseEntryList(responseEntryList);
            response.setCustomer(customerService.getCustomerById(responseDto.getCustomerId()));
        }
        return response;
    }


    public ResponseDto toDto(Response response) {
        return Objects.isNull(response) ? null : modelMapper.map(response, ResponseDto.class);
    }
}
