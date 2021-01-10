package by.shulga.softarex.questionnaireportal.service;

import by.shulga.softarex.questionnaireportal.entity.Response;
import by.shulga.softarex.questionnaireportal.exception.NotFoundException;
import by.shulga.softarex.questionnaireportal.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponseServiceImpl implements ResponseService {

    private final ResponseRepository responseRepository;

    @Autowired
    public ResponseServiceImpl(ResponseRepository responseRepository) {
        this.responseRepository = responseRepository;
    }

    @Override
    public Response getResponseById(long id) {
        Optional<Response> foundResponseOptional = responseRepository.findById(id);
        return foundResponseOptional.orElseThrow(() -> new NotFoundException("No response with id " + id));
    }

    @Override
    public void deleteResponse(long id) {
        this.getResponseById(id);
        responseRepository.deleteById(id);
    }

    @Override
    public List<Response> getAllResponses() {
        return responseRepository.findAll();
    }

    @Override
    public Response saveResponse(Response response) {
        Optional<Response> foundResponseOptional =
                responseRepository.findById(response.getId());
        return foundResponseOptional.orElseGet(() -> responseRepository.save(response));
    }

    @Override
    public Response updateResponse(Response response) {
        Optional<Response> foundResponseOptional = responseRepository.findById(response.getId());
        if (foundResponseOptional.isPresent()) {
            foundResponseOptional.get().setCustomer(response.getCustomer());
            foundResponseOptional.get().setResponseEntryList(response.getResponseEntryList());
            responseRepository.save(foundResponseOptional.get());
            return responseRepository.findById(response.getId()).orElseThrow(() ->
                    new NotFoundException("No such response"));
        } else {
            throw new NotFoundException("No such response");
        }
    }
}
