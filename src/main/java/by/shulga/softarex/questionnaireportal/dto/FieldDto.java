package by.shulga.softarex.questionnaireportal.dto;

import by.shulga.softarex.questionnaireportal.entity.FieldType;

public class FieldDto {

    private long id;

    private String label;

    private String fieldType = FieldType.SINGLE_LINE_TEXT.name();

    private boolean required;

    private boolean isActive;

    private long customerId;

    public FieldDto() {
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
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
