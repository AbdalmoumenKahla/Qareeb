package com.qareeb.project.controllers;

import com.qareeb.project.dto.RatingDto;
import com.qareeb.project.mappers.RatingMapper;
import com.qareeb.project.models.Rating;
import com.qareeb.project.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RatingController {

    private final RatingService ratingService;

    /**
     * Create or update rating
     */
    @PostMapping
    public ResponseEntity<RatingDto> rateUser(
            @RequestParam Long raterId,
            @RequestParam Long rateeId,
            @RequestParam int score,
            @RequestParam(required = false) String comment
    ) {
        Rating rating = ratingService.rateUser(raterId, rateeId, score, comment);
        return ResponseEntity.ok(RatingMapper.toDto(rating));
    }

    /**
     * Update existing rating
     */
    @PutMapping("/{ratingId}")
    public ResponseEntity<RatingDto> updateRating(
            @PathVariable Long ratingId,
            @RequestParam Long raterId,
            @RequestParam int score,
            @RequestParam(required = false) String comment
    ) {
        Rating updated = ratingService.updateRating(ratingId, raterId, score, comment);
        return ResponseEntity.ok(RatingMapper.toDto(updated));
    }

    /**
     * Get average score for user
     */
    @GetMapping("/average/{userId}")
    public ResponseEntity<Double> getAverageScore(@PathVariable Long userId) {
        return ResponseEntity.ok(ratingService.getAverageScore(userId));
    }

    /**
     * Get rating count
     */
    @GetMapping("/count/{userId}")
    public ResponseEntity<Long> getRatingCount(@PathVariable Long userId) {
        return ResponseEntity.ok(ratingService.getRatingCount(userId));
    }

    /**
     * Get ratings for user
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<RatingDto>> getRatingsForUser(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Rating> ratings = ratingService.getRatingsForUser(userId, PageRequest.of(page, size));
        return ResponseEntity.ok(ratings.map(RatingMapper::toDto));
    }
}