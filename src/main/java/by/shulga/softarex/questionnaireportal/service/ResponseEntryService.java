package by.shulga.softarex.questionnaireportal.service;

import by.shulga.softarex.questionnaireportal.entity.ResponseEntry;

import java.util.List;

public interface ResponseEntryService {

    ResponseEntry getResponseEntryById(long id);

    void deleteResponseEntry(long id);

    List<ResponseEntry> getAllResponseEntries();

    ResponseEntry saveResponseEntry(ResponseEntry responseEntry);

    ResponseEntry updateResponseEntry(ResponseEntry responseEntry);
}
