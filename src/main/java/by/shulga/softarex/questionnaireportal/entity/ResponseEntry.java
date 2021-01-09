package by.shulga.softarex.questionnaireportal.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ResponseEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "responseEntry_id")
    private Long id;

    private String responseEntryValue;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "field_id")
    private Field field;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
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

    public Long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseEntry that = (ResponseEntry) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(responseEntryValue, that.responseEntryValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, responseEntryValue);
    }

    @Override
    public String toString() {
        return "ResponseEntry{" +
                "id=" + id +
                ", responseEntryValue='" + responseEntryValue + '\'';
    }
}
