package by.shulga.softarex.questionnaireportal.dto;

public class OptionDto {

    private long id;

    private String optionValue;

    public OptionDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }
}
