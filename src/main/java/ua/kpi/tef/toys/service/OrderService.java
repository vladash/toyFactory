package ua.kpi.tef.toys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.kpi.tef.toys.exception.ToyNotFoundException;
import ua.kpi.tef.toys.exception.OrderBookingException;
import ua.kpi.tef.toys.exception.UserNotFoundException;
import ua.kpi.tef.toys.pojo.Toy;
import ua.kpi.tef.toys.pojo.Order;
import ua.kpi.tef.toys.pojo.User;
import ua.kpi.tef.toys.pojo.enums.OrderStatus;
import ua.kpi.tef.toys.repository.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ToysRepository toyRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ToysRepository toyRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.toyRepository = toyRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,
            rollbackFor = OrderBookingException.class)
    public Order makeOrder(Order order, Toy toy, User user) throws OrderBookingException,
            UserNotFoundException, ToyNotFoundException {
        User userToSave = userRepository.findById(user.getId())
                .orElseThrow(()->new UserNotFoundException("no user with id=" + user.getId()));
        Toy toyToSave = toyRepository.findById(toy.getId())
                .orElseThrow(()->new ToyNotFoundException("no toy with id=" + toy.getId()));
        order.saveOrder(toyToSave);
        if(userToSave != null) {
            order.saveOrder(userToSave);
        }
        order.setTotalOrderPrice(order.getPrice());
        order.setOrderStatus(OrderStatus.RESERVED);
        orderRepository.save(order);
        return order;
    }

    public List<Order> findAllUserOrders(User user) {
        return orderRepository.findOrderByUser(user).stream()
                .collect(Collectors.toList());
    }
}
