package by.shulga.softarex.questionnaireportal.service;

import by.shulga.softarex.questionnaireportal.entity.Field;
import by.shulga.softarex.questionnaireportal.exception.NotFoundException;
import by.shulga.softarex.questionnaireportal.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {

    private final FieldRepository fieldRepository;

    @Autowired
    public FieldServiceImpl(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    @Override
    public Field getFieldById(long id) {
        return fieldRepository.findById(id).orElseThrow(() -> new NotFoundException("No field with id " + id));
    }

    @Override
    public void deleteField(long id) {
        Field field = getFieldById(id);
        fieldRepository.delete(field);
    }

    @Override
    public List<Field> getAllFields() {
        return fieldRepository.findAll();
    }

    @Override
    public List<Field> getAllFieldsByCustomerId(long id) {
        return getAllFields().stream().filter(field -> field.getCustomer().getId().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public Field saveField(Field field) {
        Optional<Field> foundFieldOptional = fieldRepository.findByLabel(field.getLabel());
        if (foundFieldOptional.isEmpty()) {
            return fieldRepository.save(field);
        } else {
            throw new IllegalArgumentException("Field already exists");
        }
    }

    @Override
    public Field updateField(long id, Field field) {
        Field foundField = getFieldById(id);
        foundField.setLabel(field.getLabel());
        foundField.setActive(field.isActive());
        foundField.setRequired(field.isRequired());
        foundField.setFieldType(field.getFieldType());
        foundField.setCustomer(field.getCustomer());
        foundField.setResponseEntryList(field.getResponseEntryList());
        return getFieldById(id);
    }
}
