package com.qareeb.project.services.servicesImpl;

import com.qareeb.project.models.Rating;
import com.qareeb.project.models.User;
import com.qareeb.project.repositories.RatingRepository;
import com.qareeb.project.repositories.UserRepository;
import com.qareeb.project.services.RatingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepo;
    private final UserRepository userRepo;

    @Override
    @Transactional
    public Rating rateUser(Long raterId, Long rateeId, int score, String comment) {

        if (raterId.equals(rateeId)) {
            throw new IllegalArgumentException("Cannot rate yourself");
        }

        if (score < 1 || score > 5) {
            throw new IllegalArgumentException("Score must be between 1 and 5");
        }

        User rater = userRepo.findById(raterId)
                .orElseThrow(() -> new RuntimeException("Rater not found"));

        User ratee = userRepo.findById(rateeId)
                .orElseThrow(() -> new RuntimeException("Ratee not found"));

        Optional<Rating> existing =
                ratingRepo.findByRaterIdAndRateeId(raterId, rateeId);

        Rating rating = existing.orElseGet(() ->
                Rating.builder()
                        .rater(rater)
                        .ratee(ratee)
                        .createdAt(Instant.now())
                        .build()
        );

        rating.setScore(score);
        rating.setComment(comment);

        return ratingRepo.save(rating);
    }

    @Override
    @Transactional
    public Rating updateRating(Long ratingId,
                               Long raterId,
                               int score,
                               String comment) {

        Rating rating = ratingRepo.findById(ratingId)
                .orElseThrow(() -> new RuntimeException("Rating not found"));

        // only rating owner can edit
        if (!rating.getRater().getId().equals(raterId)) {
            throw new RuntimeException("Unauthorized to update this rating");
        }

        if (score < 1 || score > 5) {
            throw new IllegalArgumentException("Score must be between 1 and 5");
        }

        rating.setScore(score);
        rating.setComment(comment);

        return ratingRepo.save(rating);
    }

    @Override
    @Transactional
    public double getAverageScore(Long userId) {

        Double avg = ratingRepo.findAverageScoreByRateeId(userId);

        return avg == null ? 0.0 : avg;
    }

    @Override
    @Transactional
    public long getRatingCount(Long userId) {

        return ratingRepo.countByRateeId(userId);
    }

    @Override
    @Transactional
    public Page<Rating> getRatingsForUser(Long userId, Pageable pageable) {

        return ratingRepo.findByRateeId(userId, pageable);
    }
}