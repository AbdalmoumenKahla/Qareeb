package com.qareeb.project.mappers;

import com.qareeb.project.dto.RatingDto;
import com.qareeb.project.models.Rating;

public final class RatingMapper {
    private RatingMapper() {}

    public static RatingDto toDto(Rating rating) {
        if (rating == null) return null;

        // Accessing proxy ID is safe (usually doesn’t initialize the proxy)
        Long raterId = rating.getRater() != null ? rating.getRater().getId() : null;
        Long rateeId = rating.getRatee() != null ? rating.getRatee().getId() : null;

        return new RatingDto(
                rating.getId(),
                raterId,
                rateeId,
                rating.getScore(),
                rating.getComment(),
                rating.getCreatedAt()
        );
    }
}