package elisa.devtest.endtoend.resource;

import elisa.devtest.endtoend.model.Product;
import elisa.devtest.endtoend.service.ProductService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Products REST API controller
 */
@Path("/products")
public class ProductResource {

    /**
     * Return all available products
     * @return collection of products
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Product> getProducts() {
        return new ProductService().findProducts();
    }
}
