package elisa.devtest.endtoend.exception;


import elisa.devtest.endtoend.util.Messages;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class to represent REST exceptions
 */
@Provider
public class RestExceptionMapper implements ExceptionMapper<Throwable> {
    private static final Logger log = Logger.getLogger(RestExceptionMapper.class.getName());

    /**
     * Return exception as a REST response
     * @param exception exception that occurred
     * @return response corresponding to exception
     */
    @Override
    public Response toResponse(Throwable exception) {
        log.log(Level.SEVERE, exception.toString(), exception);

        return Response.status(getStatusCode(exception))
                .entity(Messages.REST_PROCESSING_EXCEPTION)
                .build();
    }

    /**
     * Return status code for given exception
     * @param exception exception that occurred
     * @return corresponding status code
     */
    private int getStatusCode(Throwable exception) {
        if (exception instanceof WebApplicationException) {
            return ((WebApplicationException) exception).getResponse().getStatus();
        }

        return Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
    }
}
