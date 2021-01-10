package by.shulga.softarex.questionnaireportal.web;

import by.shulga.softarex.questionnaireportal.entity.Response;
import by.shulga.softarex.questionnaireportal.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/responses")
public class ResponseController {

    private final ResponseService responseService;

    @Autowired
    public ResponseController(ResponseService responseService) {
        this.responseService = responseService;
    }

    @PostMapping
    public ResponseEntity<Response> saveResponse(@RequestBody Response response) {
        Response savedResponse = responseService.saveResponse(response);
        return new ResponseEntity<>(savedResponse, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Response> findResponse(@PathVariable("id") long id) {
        Response response = responseService.getResponseById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Response>> findAllResponses() {
        List<Response> responses = responseService.getAllResponses();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Response> updateCustomer(@RequestBody Response response) {
        Response updatedResponse = responseService.updateResponse(response);
        return new ResponseEntity<>(updatedResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteResponse(@PathVariable("id") long id) {
        responseService.deleteResponse(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
