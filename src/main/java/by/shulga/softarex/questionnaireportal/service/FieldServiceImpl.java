package by.shulga.softarex.questionnaireportal.service;

import by.shulga.softarex.questionnaireportal.entity.Field;
import by.shulga.softarex.questionnaireportal.exception.NotFoundException;
import by.shulga.softarex.questionnaireportal.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FieldServiceImpl implements FieldService {

    private final FieldRepository fieldRepository;

    @Autowired
    public FieldServiceImpl(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    @Override
    public Field getFieldById(long id) {
        Optional<Field> foundFieldOptional = fieldRepository.findById(id);
        if (foundFieldOptional.isPresent()) {
            return foundFieldOptional.get();
        } else {
            throw new NotFoundException("No field with id" + id);
        }
    }

    @Override
    public void deleteField(long id) {
        this.getFieldById(id);
        fieldRepository.deleteById(id);
    }

    @Override
    public List<Field> getAllFields() {
        return fieldRepository.findAll();
    }

    @Override
    public Field saveField(Field field) {
        Optional<Field> foundFieldOptional = fieldRepository.findByLabel(field.getLabel());
        return foundFieldOptional.orElseGet(() -> fieldRepository.save(field));
    }

    @Override
    public Field updateField(Field field) {
        Optional<Field> foundFieldOptional = fieldRepository.findByLabel(field.getLabel());
        if (foundFieldOptional.isPresent()) {
            foundFieldOptional.get().setLabel(field.getLabel());
            foundFieldOptional.get().setActive(field.isActive());
            foundFieldOptional.get().setRequired(field.isRequired());
            foundFieldOptional.get().setFieldType(field.getFieldType());
            foundFieldOptional.get().setCustomer(field.getCustomer());
            foundFieldOptional.get().setResponseEntryList(field.getResponseEntryList());
            fieldRepository.save(foundFieldOptional.get());
            return fieldRepository.findByLabel(field.getLabel()).orElseThrow(() ->
                    new NotFoundException("No such field"));
        } else {
            throw new NotFoundException("No such field");
        }
    }
}
