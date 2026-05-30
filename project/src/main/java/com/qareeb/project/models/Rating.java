package com.qareeb.project.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "ratings", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"rater_id", "ratee_id"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rater_id", nullable = false)
    private User rater;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ratee_id", nullable = false)
    private User ratee;

    // choose integer scale (1..5) or double
    @Column(nullable = false)
    private Integer score;

    private String comment;

    private Instant createdAt;
}
