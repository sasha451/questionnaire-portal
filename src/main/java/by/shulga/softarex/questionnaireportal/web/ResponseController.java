package by.shulga.softarex.questionnaireportal.web;

import by.shulga.softarex.questionnaireportal.dto.ResponseDto;
import by.shulga.softarex.questionnaireportal.entity.Response;
import by.shulga.softarex.questionnaireportal.mapper.ResponseMapper;
import by.shulga.softarex.questionnaireportal.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/responses")
@CrossOrigin(origins = "http://localhost:4200")
public class ResponseController {

    private final ResponseService responseService;

    private final ResponseMapper responseMapper;

    @Autowired
    public ResponseController(ResponseService responseService, ResponseMapper responseMapper) {
        this.responseService = responseService;
        this.responseMapper = responseMapper;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> saveResponse(@RequestBody ResponseDto responseDto) {
        Response response = responseMapper.toEntity(responseDto);
        Response savedResponse = responseService.saveResponse(response);
        return new ResponseEntity<>(responseMapper.toDto(savedResponse), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> findResponse(@PathVariable("id") long id) {
        Response response = responseService.getResponseById(id);
        return new ResponseEntity<>(responseMapper.toDto(response), HttpStatus.OK);
    }

    @GetMapping(value = "/byCustomerId", params = "id")
    public ResponseEntity<List<ResponseDto>> findAllResponsesByCustomerId(@RequestParam("id") long id) {
        List<Response> responseList = responseService.getAllResponsesByCustomerId(id);
        List<ResponseDto> responseDtoList = responseList.stream().map(responseMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ResponseDto>> findAllResponses() {
        List<Response> responses = responseService.getAllResponses();
        List<ResponseDto> responseDtoList = responses.stream().map(responseMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateCustomer(@PathVariable("id") long id,
                                                      @RequestBody ResponseDto responseDto) {
        Response response = responseMapper.toEntity(responseDto);
        Response updatedResponse = responseService.updateResponse(id, response);
        return new ResponseEntity<>(responseMapper.toDto(updatedResponse), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteResponse(@PathVariable("id") long id) {
        responseService.deleteResponse(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
