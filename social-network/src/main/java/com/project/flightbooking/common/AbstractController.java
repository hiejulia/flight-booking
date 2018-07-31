package com.project.flightbooking;

import com.tenx.ms.commons.rest.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 * Abstract Controller 
 */
public abstract class AbstractAPIController extends AbstractController {

    @Autowired
    protected HttpServletResponse response;

    /**
     *  Occurs when a constraint is violated inserting entities in the db.
     *  Returns a PRECONDITION_FAILED code indicating that the existing condition in the
     *  Database does not allow the operation.
     *
     * @param ex The DataIntegrity exception
     * @param request The API request.
     * @param response The API response.
     * @throws IOException
     */
    @ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected void handleDataIntegrityException(DataIntegrityViolationException ex,
                                                HttpServletRequest request,
                                                HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.PRECONDITION_FAILED.value(), ex.getMessage());
    }
}