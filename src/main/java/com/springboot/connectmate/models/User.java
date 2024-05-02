package com.springboot.connectmate.models;

import com.springboot.connectmate.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data // Lombok annotation to create all the getters, setters, equals, hash, and toString methods for us
@AllArgsConstructor // Lombok annotation to create a constructor with all the arguments
@NoArgsConstructor // Lombok annotation to create a constructor with no arguments

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint( columnNames = { "username", "email"} )
        },
        indexes = {
                @Index(columnList = "username", name = "idx_user_username"),
                @Index(columnList = "email", name = "idx_user_email")
        }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, columnDefinition = "ENUM('SUPERVISOR', 'AGENT') DEFAULT 'AGENT'")
    private UserRole role;

    // Link to Queues Table
    // Declares the Many to Many relationship between User and Queue
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "queue_agents",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "queue_id", referencedColumnName = "id")
    )
    private Set<Queue> queues;

    // Link to Metrics Table
    // Parent side
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Metric> metrics;

}

