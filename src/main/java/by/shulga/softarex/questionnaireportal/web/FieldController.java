package by.shulga.softarex.questionnaireportal.web;

import by.shulga.softarex.questionnaireportal.entity.Field;
import by.shulga.softarex.questionnaireportal.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fields")
public class FieldController {

    private final FieldService fieldService;

    @Autowired
    public FieldController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @PostMapping
    public ResponseEntity<Field> saveField(@RequestBody Field field) {
        Field savedField = fieldService.saveField(field);
        return new ResponseEntity<>(savedField, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Field> findField(@PathVariable("id") long id) {
        Field field = fieldService.getFieldById(id);
        return new ResponseEntity<>(field, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Field>> findAllFields() {
        List<Field> fields = fieldService.getAllFields();
        return new ResponseEntity<>(fields, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Field> updateField(@RequestBody Field field) {
        Field updatedField = fieldService.updateField(field);
        return new ResponseEntity<>(updatedField, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteResponseEntry(@PathVariable("id") long id) {
        fieldService.deleteField(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
