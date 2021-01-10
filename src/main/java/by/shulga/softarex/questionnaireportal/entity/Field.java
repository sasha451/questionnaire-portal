package by.shulga.softarex.questionnaireportal.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "field_id")
    private Long id;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ResponseEntry> responseEntryList = new ArrayList<>();

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(nullable = false, unique = true)
    private String label;

    @Enumerated(EnumType.STRING)
    private FieldType fieldType;

    private boolean required;

    private boolean isActive;

    public Field() {}

    public Field(String label, FieldType fieldType, boolean required, boolean isActive, Customer customer) {
        this.label = label;
        this.fieldType = fieldType;
        this.required = required;
        this.isActive = isActive;
        this.customer = customer;
    }

    public Field(List<ResponseEntry> responseEntryList, String label, FieldType fieldType, boolean required,
                 boolean isActive) {
        this.responseEntryList = responseEntryList;
        this.label = label;
        this.fieldType = fieldType;
        this.required = required;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<ResponseEntry> getResponseEntryList() {
        return responseEntryList;
    }

    public void setResponseEntryList(List<ResponseEntry> responseEntryList) {
        this.responseEntryList = responseEntryList;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return required == field.required &&
                isActive == field.isActive &&
                Objects.equals(id, field.id) &&
                Objects.equals(label, field.label) &&
                fieldType == field.fieldType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, fieldType, required, isActive);
    }

    @Override
    public String toString() {
        return "Field{" +
                "id=" + id +
                ", responseEntryList=" + responseEntryList +
                ", label='" + label + '\'' +
                ", fieldType=" + fieldType +
                ", required=" + required +
                ", isActive=" + isActive +
                '}';
    }
}
