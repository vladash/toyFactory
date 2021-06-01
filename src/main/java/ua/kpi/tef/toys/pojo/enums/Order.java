package ua.kpi.tef.toys.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "customer_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Toy toy;

    private int quantity;

    @ManyToOne
    private User user;

    private LocalDate orderDate;

    private LocalDate deliveryDate;

    private BigDecimal totalOrderPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public Order saveOrder(User user){
        this.setUser(user);
        user.getOrders().add(this);
        return this;
    }

    public Order saveOrder(Toy toy){
        this.setToy(toy);
        toy.getOrders().add(this);
        return this;
    }

    public BigDecimal getPrice() {
        if (this.toy != null) {
            return this.toy.getPrice().multiply(new BigDecimal(this.quantity));
        }
        return BigDecimal.ZERO;
    }
}
