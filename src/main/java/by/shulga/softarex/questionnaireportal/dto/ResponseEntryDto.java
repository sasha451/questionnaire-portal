package by.shulga.softarex.questionnaireportal.dto;

public class ResponseEntryDto {

    private long id;

    private String responseEntryValue;

    private long fieldId;

    public ResponseEntryDto() {
    }

    public String getResponseEntryValue() {
        return responseEntryValue;
    }

    public void setResponseEntryValue(String responseEntryValue) {
        this.responseEntryValue = responseEntryValue;
    }

    public long getFieldId() {
        return fieldId;
    }

    public void setFieldId(long fieldId) {
        this.fieldId = fieldId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
