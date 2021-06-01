package ua.kpi.tef.toys.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.kpi.tef.toys.pojo.enums.RoleType;

import javax.persistence.*;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table( name="user",
        uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class User{
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    public User(Long id, String firstName, String lastName, String email, String username, String password, RoleType role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @OneToMany(mappedBy = "user")
    private List<Order> orders= new ArrayList<>();
}