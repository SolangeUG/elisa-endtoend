package elisa.devtest.endtoend.resource;

import elisa.devtest.endtoend.util.Messages;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Class to represent "echo" REST resource
 */
@Path("echo")
public class EchoResource {

    /**
     * Process a GET request to this resource
     * @return echo resource
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return Messages.REST_ECHO_RESOURCE_GET_MESSAGE;
    }
}
