package com.qareeb.project.dto;

import java.time.Instant;

public record RatingDto(
        Long id,
        Long raterId,
        Long rateeId,
        Integer score,
        String comment,
        Instant createdAt
) {}