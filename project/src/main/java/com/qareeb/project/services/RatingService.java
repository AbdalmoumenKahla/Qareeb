package com.qareeb.project.services;

import com.qareeb.project.models.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RatingService {

    /**
     * Create or update a rating from raterId to rateeId.
     * Validates rater != ratee and score range.
     */
    Rating rateUser(Long raterId, Long rateeId, int score, String comment);

    /**
     * Update an existing rating by ratingId (only rater can update; service should enforce).
     */
    Rating updateRating(Long ratingId, Long raterId, int score, String comment);

    /**
     * Get average score for a user (ratee).
     */
    double getAverageScore(Long userId);

    /**
     * Get total number of ratings for a user (ratee).
     */
    long getRatingCount(Long userId);

    /**
     * Get page of ratings for a user (ratee).
     */
    Page<Rating> getRatingsForUser(Long userId, Pageable pageable);
}
