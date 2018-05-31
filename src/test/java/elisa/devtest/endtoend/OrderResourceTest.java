package elisa.devtest.endtoend;

import elisa.devtest.endtoend.model.Order;
import elisa.devtest.endtoend.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * OrderResource class unit tests
 * @author Solange U. Gasengayire
 */
@DisplayName("OrderResource should")
class OrderResourceTest {

    @Mock
    private OrderService mockOrderService;

    @InjectMocks
    private OrderResource resource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("invoke OrderService::getOrders method")
    void shouldInvokeOrderServiceGetOrdersMethod() {
        assertNotNull(mockOrderService);
        assertNotNull(resource);

        Order order = mock(Order.class);
        when(mockOrderService.saveOrder(order)).thenReturn(order);
        assertEquals(order, mockOrderService.saveOrder(order));

        resource.getOrders();

        // verify OrderService::getOrders method was invoked
        verify(mockOrderService).getOrders();
    }

    @Test
    @DisplayName("invoke OrderService::saveOrder method")
    void shouldInvokeOrderServiceSaveOrderMethod() {
        List<Order> orders = Arrays.asList(mock(Order.class), mock(Order.class));

        when(mockOrderService.getOrders()).thenReturn(orders);
        assertEquals(orders, mockOrderService.getOrders());

        Order order = mock(Order.class);
        resource.createOrder(order);

        // verify OrderService::saveOrder method was invoked
        verify(mockOrderService).saveOrder(any(Order.class));
    }
}
