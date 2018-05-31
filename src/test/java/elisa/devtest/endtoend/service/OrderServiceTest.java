package elisa.devtest.endtoend.service;

import elisa.devtest.endtoend.dao.OrderDao;
import elisa.devtest.endtoend.model.Order;
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
import static org.mockito.Mockito.*;

/**
 * OrderService class unit tests
 * @author Solange U. Gasengayire
 */
@DisplayName("OrderService should")
class OrderServiceTest {

    @Mock
    private OrderDao mockOrderDao;

    @InjectMocks
    private OrderService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("invoke OrderDAO::save method")
    void shouldInvokeOrderDaoSaveMethod() {
        assertNotNull(mockOrderDao);
        assertNotNull(service);

        List<Order> orders = Arrays.asList(
                mock(Order.class), mock(Order.class));

        when(mockOrderDao.findOrders()).thenReturn(orders);
        assertEquals(orders, mockOrderDao.findOrders());

        service.saveOrder(new Order());

        // verify that OrderDao::save method was invoked
        verify(mockOrderDao).save(any(Order.class));
    }

    @Test
    @DisplayName("invoke OrderDAO::find method")
    void shouldInvokeOrderDaoFindMethod() {
        Order order = mock(Order.class);

        when(mockOrderDao.save(order)).thenReturn(4L);
        assertEquals(4L, mockOrderDao.save(order));

        service.getOrder(4L);

        // verify that OrderDao::find method was invoked
        verify(mockOrderDao).find(any(Long.class));
    }

    @Test
    @DisplayName("invoke OrderDao::findOrders method")
    void shouldInvokeOrderDaoFindOrdersMethod() {
        Order order = mock(Order.class);

        when(mockOrderDao.find(any(Long.class))).thenReturn(order);
        assertEquals(order, mockOrderDao.find(any(Long.class)));

        service.getOrders();

        // verify that OrderDao::findOrders method was invoked
        verify(mockOrderDao).findOrders();
    }
}
