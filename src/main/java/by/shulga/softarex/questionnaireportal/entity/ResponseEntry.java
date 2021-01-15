package by.shulga.softarex.questionnaireportal.entity;

import javax.persistence.*;

@Entity
public class ResponseEntry extends BaseEntity {

    @Column
    private String responseEntryValue;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "field_id")
    private Field field;

    @ManyToOne(optional = false, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "response_id")
    private Response response;

    public ResponseEntry() {}

    public ResponseEntry(String responseEntryValue, Field field, Response response) {
        this.responseEntryValue = responseEntryValue;
        this.field = field;
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getResponseEntryValue() {
        return responseEntryValue;
    }

    public void setResponseEntryValue(String responseEntryValue) {
        this.responseEntryValue = responseEntryValue;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
