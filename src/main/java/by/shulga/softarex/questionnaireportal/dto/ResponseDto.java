package by.shulga.softarex.questionnaireportal.dto;

import java.util.ArrayList;
import java.util.List;

public class ResponseDto {

    private long id;

    private long customerId;

    private List<ResponseEntryDto> responseEntryDtoList = new ArrayList<>();

    public ResponseDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public List<ResponseEntryDto> getResponseEntryDtoList() {
        return responseEntryDtoList;
    }

    public void setResponseEntryDtoList(List<ResponseEntryDto> responseEntryDtoList) {
        this.responseEntryDtoList = responseEntryDtoList;
    }
}
