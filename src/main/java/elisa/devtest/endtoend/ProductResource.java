package elisa.devtest.endtoend;

import elisa.devtest.endtoend.model.Product;
import elisa.devtest.endtoend.service.ProductService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/products")
public class ProductResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Product> getProducts() {
        return new ProductService().findProducts();
    }
}
