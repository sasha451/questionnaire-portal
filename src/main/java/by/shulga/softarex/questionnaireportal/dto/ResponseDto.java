package by.shulga.softarex.questionnaireportal.dto;

import java.util.ArrayList;
import java.util.List;

public class ResponseDto {

    private long id;

    private long customerId;

    private List<ResponseEntryDto> responseEntries = new ArrayList<>();

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

    public List<ResponseEntryDto> getResponseEntries() {
        return responseEntries;
    }

    public void setResponseEntries(List<ResponseEntryDto> responseEntries) {
        this.responseEntries = responseEntries;
    }
}
