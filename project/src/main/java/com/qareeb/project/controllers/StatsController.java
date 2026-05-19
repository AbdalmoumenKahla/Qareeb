package com.qareeb.project.controllers;

import com.qareeb.project.services.TripService;
import com.qareeb.project.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
public class StatsController {

    private final TripService tripService;
    private final UserService userService;

    @GetMapping
    public Map<String, Long> getStats() {

        Map<String, Long> stats = new HashMap<>();

        stats.put("activeRequests", tripService.getRequestCount());
        stats.put("activeOffers", tripService.getOfferCount());
        stats.put("totalUsers", userService.getTotalUsers());

        return stats;
    }
}