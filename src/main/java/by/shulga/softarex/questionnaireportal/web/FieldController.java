package by.shulga.softarex.questionnaireportal.web;

import by.shulga.softarex.questionnaireportal.dto.FieldDto;
import by.shulga.softarex.questionnaireportal.entity.Field;
import by.shulga.softarex.questionnaireportal.mapper.FieldMapper;
import by.shulga.softarex.questionnaireportal.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/fields")
public class FieldController {

    private final FieldService fieldService;

    private final FieldMapper fieldMapper;

    @Autowired
    public FieldController(FieldService fieldService, FieldMapper fieldMapper) {
        this.fieldService = fieldService;
        this.fieldMapper = fieldMapper;
    }

    @PostMapping
    public ResponseEntity<FieldDto> saveField(@RequestBody FieldDto fieldDto) {
        Field field = fieldMapper.toEntity(fieldDto);
        Field savedField = fieldService.saveField(field);
        return new ResponseEntity<>(fieldMapper.toDto(savedField), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FieldDto> findField(@PathVariable("id") long id) {
        Field field = fieldService.getFieldById(id);
        return new ResponseEntity<>(fieldMapper.toDto(field), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FieldDto>> findAllFields() {
        List<Field> fields = fieldService.getAllFields();
        List<FieldDto> fieldDtoList = fields.stream().map(fieldMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(fieldDtoList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FieldDto> updateField(@PathVariable("id") long id, @RequestBody FieldDto fieldDto) {
        Field field = fieldMapper.toEntity(fieldDto);
        Field updatedField = fieldService.updateField(id, field);
        return new ResponseEntity<>(fieldMapper.toDto(updatedField), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteField(@PathVariable("id") long id) {
        fieldService.deleteField(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
