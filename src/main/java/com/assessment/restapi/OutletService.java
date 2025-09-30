package com.assessment.restapi;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class OutletService {

    private final OutletRepository repo;

    public OutletService(OutletRepository repo) {
        this.repo = repo;
    }

    /** Stable ordering: rating desc, then name asc, then id asc. Page is 0-based. */
    public List<Outlet> getTopRated(int page, int size, Double minRating) {
        if (size <= 0) return List.of();
        if (page < 0) page = 0;
        double threshold = (minRating == null) ? Double.NEGATIVE_INFINITY : minRating;

        List<Outlet> sorted = repo.findAll().stream()
                .filter(o -> o.rating() >= threshold)
                .sorted(Comparator
                        .comparingDouble(Outlet::rating).reversed()
                        .thenComparing(Outlet::name)
                        .thenComparingLong(Outlet::id))
                .toList();

        int from = Math.min(page * size, sorted.size());
        int to   = Math.min(from + size, sorted.size());
        return sorted.subList(from, to);
    }
}
