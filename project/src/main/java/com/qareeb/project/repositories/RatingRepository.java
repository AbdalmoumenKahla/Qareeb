package com.qareeb.project.repositories;
import com.qareeb.project.models.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    Optional<Rating> findByRaterIdAndRateeId(Long raterId, Long rateeId);

    long countByRateeId(Long rateeId);

    Page<Rating> findByRateeId(Long rateeId, Pageable pageable);

    @Query("""
        SELECT AVG(r.score)
        FROM Rating r
        WHERE r.ratee.id = :rateeId
    """)
    Double findAverageScoreByRateeId(Long rateeId);
}