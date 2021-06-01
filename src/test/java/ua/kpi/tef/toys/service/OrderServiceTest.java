package ua.kpi.tef.toys.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.kpi.tef.toys.exception.ToyNotFoundException;
import ua.kpi.tef.toys.exception.OrderBookingException;
import ua.kpi.tef.toys.exception.UserNotFoundException;
import ua.kpi.tef.toys.pojo.Toy;
import ua.kpi.tef.toys.pojo.Order;
import ua.kpi.tef.toys.pojo.User;
import ua.kpi.tef.toys.pojo.enums.*;
import ua.kpi.tef.toys.repository.ToysRepository;
import ua.kpi.tef.toys.repository.OrderRepository;
import ua.kpi.tef.toys.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @MockBean
    ToysRepository toyRepository;

    @MockBean
    UserRepository userRepository;

    @MockBean
    OrderRepository orderRepository;


    @Test
    public void whenMakeOrderThenReturnOrder() throws UserNotFoundException, ToyNotFoundException, OrderBookingException {
        User user = new User(1L, "Vlada", "Shestobanskaya",
                "vlada.lada.17@gmail.com", "qwerty", "qwerty", RoleType.ROLE_USER);

        Toy toy = new Toy(1L, "piano", Kind.KEYBOARDS, "key",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN);
        Mockito.doReturn(Optional.of(toy))
                .when(toyRepository)
                .findToyById(1L);
        Order order = new Order(1L, toy, 1, new User(), LocalDate.EPOCH, LocalDate.EPOCH, BigDecimal.TEN, OrderStatus.RESERVED);
        Mockito.doReturn(Optional.of(user))
                .when(userRepository)
                .findUserById(1L);
        orderService.makeOrder(order, toy, user);
        Mockito.verify(userRepository, Mockito.times(1)).findUserById(1L);
        Mockito.verify(toyRepository, Mockito.times(1)).findToyById(1L);
        Mockito.verify(orderRepository, Mockito.times(1)).save(order);
    }

    @Test
    public void makeOrderFail(){

    }

    @Test
    public void whenFindAllUserOrdersThenReturnAllUserOrders(){
        Toy toy1 = new Toy(1L, "car", Kind.KEYBOARDS, "key",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN);
        Toy toy2 = new Toy(3L, "ball", Kind.STRINGS, "string",
                true, Motion.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, ToySize.MEDIUM,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN);

        List<Order> userOrdersList1 = new ArrayList<>();
        userOrdersList1.add(new Order(1L, toy1, 1, new User(), LocalDate.EPOCH, LocalDate.EPOCH, BigDecimal.TEN, OrderStatus.RESERVED));
        userOrdersList1.add(new Order(2L, toy2, 2, new User(), LocalDate.EPOCH, LocalDate.EPOCH, BigDecimal.TEN, OrderStatus.RESERVED));
        userOrdersList1.add(new Order(3L, toy2, 3, new User(), LocalDate.EPOCH, LocalDate.EPOCH, BigDecimal.TEN, OrderStatus.RESERVED));
        List<Order> userOrdersList2= new ArrayList<>();
        userOrdersList2.add(new Order(4L, toy2, 3, new User(), LocalDate.EPOCH, LocalDate.EPOCH, BigDecimal.TEN, OrderStatus.RESERVED));
        userOrdersList2.add(new Order(5L, toy2, 2, new User(), LocalDate.EPOCH, LocalDate.EPOCH, BigDecimal.TEN, OrderStatus.RESERVED));
        User user1 = new User(1L, "Vlada", "Shestobanskaya",
                "vlada.lada.17@gmail.com", "qwerty", "qwerty", RoleType.ROLE_USER, userOrdersList1);
        User user2 = new User(1L, "Denis", "Lila",
                "den__@gmail.com", "qwerty", "qwerty", RoleType.ROLE_USER, userOrdersList2);

        Mockito.doReturn(userOrdersList1).when(orderRepository).findOrderByUser(user1);
        List<Order> getOrderList1 = orderService.findAllUserOrders(user1);
        assertEquals(3, getOrderList1.size());
        assertEquals(1L, getOrderList1.get(0).getId());
        assertEquals(2L, getOrderList1.get(1).getId());
        assertEquals(3L, getOrderList1.get(2).getId());

        Mockito.doReturn(userOrdersList2).when(orderRepository).findOrderByUser(user2);
        List<Order> getOrderList2 = orderService.findAllUserOrders(user2);
        assertEquals(2, getOrderList2.size());
        assertEquals(4L, getOrderList2.get(0).getId());
        assertEquals(5L, getOrderList2.get(1).getId());
    }

}
