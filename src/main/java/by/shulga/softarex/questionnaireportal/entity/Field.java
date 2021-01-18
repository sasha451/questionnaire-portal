package by.shulga.softarex.questionnaireportal.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Field extends BaseEntity {

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<ResponseEntry> responseEntryList = new ArrayList<>();

    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Option> options = new ArrayList<>();

    @Column(nullable = false, unique = true)
    private String label;

    @Column
    @Enumerated(EnumType.STRING)
    private FieldType fieldType;

    @Column
    private boolean required;

    @Column
    private boolean isActive;

    public Field() {}

    public Field(String label, FieldType fieldType, boolean required, boolean isActive, Customer customer) {
        this.label = label;
        this.fieldType = fieldType;
        this.required = required;
        this.isActive = isActive;
        this.customer = customer;
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

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
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
}
