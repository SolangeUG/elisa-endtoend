package elisa.devtest.endtoend.resource;

import elisa.devtest.endtoend.exception.OrderProcessingException;
import elisa.devtest.endtoend.model.Order;
import elisa.devtest.endtoend.service.OrderService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * Orders REST API controller
 */
@Path("/orders")
public class OrderResource {

    private OrderService orderService = new OrderService();

    /**
     * Return available orders
     * @return list of orders
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Order> getOrders() {
        //return new OrderDao().findOrders();
        return orderService.getOrders();
    }

    /**
     * Create a new order
     * @param order order to be created
     * @return newly created resource
     * @throws ServerErrorException in case of an error processing the request
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Order createOrder(Order order) throws ServerErrorException  {
        try {
            return orderService.saveOrder(order);
        } catch (OrderProcessingException exception) {
            throw new ServerErrorException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
