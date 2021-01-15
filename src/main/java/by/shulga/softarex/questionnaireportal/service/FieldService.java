package by.shulga.softarex.questionnaireportal.service;

import by.shulga.softarex.questionnaireportal.entity.Field;

import java.util.List;

public interface FieldService {

    Field getFieldById(long id);

    void deleteField(long id);

    List<Field> getAllFields();

    Field saveField(Field field);

    Field updateField(long id, Field field);
}
