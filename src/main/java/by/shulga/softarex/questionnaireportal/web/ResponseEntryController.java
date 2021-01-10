package by.shulga.softarex.questionnaireportal.web;

import by.shulga.softarex.questionnaireportal.entity.ResponseEntry;
import by.shulga.softarex.questionnaireportal.service.ResponseEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/responseEntries")
public class ResponseEntryController {

    private final ResponseEntryService responseEntryService;

    @Autowired
    public ResponseEntryController(ResponseEntryService responseEntryService) {
        this.responseEntryService = responseEntryService;
    }

    @PostMapping
    public ResponseEntity<ResponseEntry> saveResponseEntry(@RequestBody ResponseEntry responseEntry) {
        ResponseEntry savedResponseEntry = responseEntryService.saveResponseEntry(responseEntry);
        return new ResponseEntity<>(savedResponseEntry, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseEntry> findResponseEntry(@PathVariable("id") long id) {
        ResponseEntry responseEntry = responseEntryService.getResponseEntryById(id);
        return new ResponseEntity<>(responseEntry, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ResponseEntry>> findAllResponseEntries() {
        List<ResponseEntry> responseEntries = responseEntryService.getAllResponseEntries();
        return new ResponseEntity<>(responseEntries, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseEntry> updateResponseEntry(@RequestBody ResponseEntry responseEntry) {
        ResponseEntry updatedResponseEntry = responseEntryService.updateResponseEntry(responseEntry);
        return new ResponseEntity<>(updatedResponseEntry, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteResponseEntry(@PathVariable("id") long id) {
        responseEntryService.deleteResponseEntry(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
