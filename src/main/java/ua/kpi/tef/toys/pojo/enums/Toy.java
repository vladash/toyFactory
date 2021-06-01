package ua.kpi.tef.toys.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Toy {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Kind kind;

    private String soundSource;

    private boolean motionPresence;

    @Enumerated(EnumType.STRING)
    private Motion motion;

    @Enumerated(EnumType.STRING)
    private Material toyMaterial;

    @Enumerated(EnumType.STRING)
    private Coating coating;

    @Enumerated(EnumType.STRING)
    private ToySize toySize;

    @Enumerated(EnumType.STRING)
    private AvailableStatus availableStatus;

    private long availableAmount;

    private BigDecimal price;

    @OneToMany(mappedBy = "toy")
    private List<Order> orders = new ArrayList<>();

    public Toy(Long id, String name, Kind kind, String soundSource, boolean motionPresence, Motion motion, Material toyMaterial, Coating coating, ToySize toySize, AvailableStatus availableStatus, long availableAmount, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.kind = kind;
        this.soundSource = soundSource;
        this.motionPresence = motionPresence;
        this.motion = motion;
        this.toyMaterial = toyMaterial;
        this.coating = coating;
        this.toySize = toySize;
        this.availableStatus = availableStatus;
        this.availableAmount = availableAmount;
        this.price = price;
    }
}
