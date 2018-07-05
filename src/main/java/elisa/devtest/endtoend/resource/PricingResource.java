package elisa.devtest.endtoend.resource;

import elisa.devtest.endtoend.model.Price;
import elisa.devtest.endtoend.service.PricingService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Prices REST API controller
 */
@Path("/prices")
public class PricingResource {

    /**
     * Return all available prices
     * @return collection of prices
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Price> findPrices() {
        return new PricingService().findPrices();
    }
}
