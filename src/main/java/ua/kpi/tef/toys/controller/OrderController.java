package ua.kpi.tef.toys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ua.kpi.tef.toys.exception.ToyNotFoundException;
import ua.kpi.tef.toys.exception.OrderBookingException;
import ua.kpi.tef.toys.exception.UserNotFoundException;
import ua.kpi.tef.toys.pojo.Toy;
import ua.kpi.tef.toys.pojo.Order;
import ua.kpi.tef.toys.pojo.User;
import ua.kpi.tef.toys.service.OrderService;

import java.util.List;


@Controller
public class OrderController {
    private final OrderService orderService;

     @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public Order createOrder(Order newOrder, Toy toy, User user) throws UserNotFoundException, ToyNotFoundException, OrderBookingException {
            return orderService.makeOrder(newOrder, toy, user);
    }

    public List<Order> findAllUserOrders(User user){
         return orderService.findAllUserOrders(user);
    }
}
