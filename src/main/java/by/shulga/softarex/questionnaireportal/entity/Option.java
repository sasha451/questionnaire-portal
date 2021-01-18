package by.shulga.softarex.questionnaireportal.entity;

import javax.persistence.*;

@Entity
public class Option extends BaseEntity {
    
    @Column
    private String optionValue;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "field_id")
    private Field field;

    public Option() {
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
