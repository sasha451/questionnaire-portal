package by.shulga.softarex.questionnaireportal.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    private Type type;

    private boolean required;

    private boolean isActive;

    public Field() {
    }

    public Field(String label, Type type, boolean required, boolean isActive) {
        this.label = label;
        this.type = type;
        this.required = required;
        this.isActive = isActive;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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
    public String toString() {
        return "Field{" +
                "label='" + label + '\'' +
                ", type=" + type +
                ", required=" + required +
                ", isActive=" + isActive +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return required == field.required &&
                isActive == field.isActive &&
                Objects.equals(label, field.label) &&
                type == field.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, type, required, isActive);
    }
}
