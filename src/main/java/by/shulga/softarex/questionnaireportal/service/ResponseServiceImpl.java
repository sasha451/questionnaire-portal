package by.shulga.softarex.questionnaireportal.service;

import by.shulga.softarex.questionnaireportal.entity.Response;
import by.shulga.softarex.questionnaireportal.exception.NotFoundException;
import by.shulga.softarex.questionnaireportal.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ResponseServiceImpl implements ResponseService {

    private final ResponseRepository responseRepository;

    @Autowired
    public ResponseServiceImpl(ResponseRepository responseRepository) {
        this.responseRepository = responseRepository;
    }

    @Override
    public Response getResponseById(long id) {
        return responseRepository.findById(id).orElseThrow(() -> new NotFoundException("No response with id " + id));
    }

    @Override
    public void deleteResponse(long id) {
        Response response = getResponseById(id);
        responseRepository.delete(response);
    }

    @Override
    public List<Response> getAllResponses() {
        return responseRepository.findAll();
    }

    @Override
    public List<Response> getAllResponsesByCustomerId(long id) {
        return getAllResponses().stream().filter(response -> response.getCustomer().getId().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public Response saveResponse(Response response) {
        return responseRepository.save(response);
    }

    @Override
    public Response updateResponse(long id, Response response) {
        Response foundResponse = getResponseById(id);
        foundResponse.setCustomer(response.getCustomer());
        foundResponse.setResponseEntryList(response.getResponseEntryList());
        return getResponseById(id);
    }
}
