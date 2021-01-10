package by.shulga.softarex.questionnaireportal.service;

import by.shulga.softarex.questionnaireportal.entity.ResponseEntry;
import by.shulga.softarex.questionnaireportal.exception.NotFoundException;
import by.shulga.softarex.questionnaireportal.repository.ResponseEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponseEntryServiceImpl implements ResponseEntryService {

    private final ResponseEntryRepository responseEntryRepository;

    @Autowired
    public ResponseEntryServiceImpl(ResponseEntryRepository responseEntryRepository) {
        this.responseEntryRepository = responseEntryRepository;
    }

    @Override
    public ResponseEntry getResponseEntryById(long id) {
        Optional<ResponseEntry> foundResponseEntryOptional = responseEntryRepository.findById(id);
        return foundResponseEntryOptional.orElseThrow(() -> new NotFoundException("No responseEntry with id" + id));
    }

    @Override
    public void deleteResponseEntry(long id) {
        this.getResponseEntryById(id);
        responseEntryRepository.deleteById(id);
    }

    @Override
    public List<ResponseEntry> getAllResponseEntries() {
        return responseEntryRepository.findAll();
    }

    @Override
    public ResponseEntry saveResponseEntry(ResponseEntry responseEntry) {
        Optional<ResponseEntry> foundResponseEntryOptional = responseEntryRepository
                .findById(responseEntry.getId());
        return foundResponseEntryOptional.orElseGet(() -> responseEntryRepository.save(responseEntry));
    }

    @Override
    public ResponseEntry updateResponseEntry(ResponseEntry responseEntry) {
        Optional<ResponseEntry> foundResponseEntryOptional = responseEntryRepository
                .findById(responseEntry.getId());
        if (foundResponseEntryOptional.isPresent()) {
            foundResponseEntryOptional.get().setResponseEntryValue(responseEntry.getResponseEntryValue());
            foundResponseEntryOptional.get().setResponse(responseEntry.getResponse());
            foundResponseEntryOptional.get().setField(responseEntry.getField());
            responseEntryRepository.save(foundResponseEntryOptional.get());
            return responseEntryRepository.findById(foundResponseEntryOptional.get().getId()).orElseThrow(() ->
                    new NotFoundException("No such responseEntry"));
        } else {
            throw new NotFoundException("No such responseEntry");
        }
    }
}
