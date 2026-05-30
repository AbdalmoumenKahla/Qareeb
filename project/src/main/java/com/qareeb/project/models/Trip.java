package com.qareeb.project.models;

import com.qareeb.project.enums.TripStatus;
import com.qareeb.project.enums.TripType;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "trips")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TripType type;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String pickupLocation;

    @Column(nullable = false)
    private String destinationLocation;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private TripStatus status = TripStatus.AVAILABLE;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}