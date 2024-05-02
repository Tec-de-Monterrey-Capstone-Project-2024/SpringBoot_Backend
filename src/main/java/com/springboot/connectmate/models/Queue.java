package com.springboot.connectmate.models;

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
        name = "queues"
)
@NamedStoredProcedureQuery(
        name = "sp_get_queue_agents",
        procedureName = "sp_get_queue_agents",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "queueId", type = Long.class)
        }
)
public class Queue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    // Link to Users(Agents) Table
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "queues")
    private Set<User> users;

    // Link to Metrics Table
    // Parent side
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "queue", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Metric> metrics;
}
