package by.shulga.softarex.questionnaireportal.service;

import by.shulga.softarex.questionnaireportal.entity.Response;

import java.util.List;

public interface ResponseService {

    Response getResponseById(long id);

    void deleteResponse(long id);

    List<Response> getAllResponses();

    List<Response> getAllResponsesByCustomerId(long id);

    Response saveResponse(Response response);

    Response updateResponse(long id, Response response);
}
