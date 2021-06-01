package ua.kpi.tef.toys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kpi.tef.toys.pojo.Order;
import ua.kpi.tef.toys.pojo.User;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrderByUser(User user);
}
