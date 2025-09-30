package com.assessment.restapi;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OutletServiceTest {

    @Test
    void returnsTopRated_sortedAndPaginated() {
        // real in-memory repo (uses static DATA)
        OutletRepository repo = new OutletRepository();
        OutletService service = new OutletService(repo);

        // page 0, size 3, minRating 4.7
        List<Outlet> p0 = service.getTopRated(0, 3, 4.7);
        assertEquals(3, p0.size());
        assertEquals(new Outlet(7, "Dosa Corner", 4.9),      p0.get(0));
        assertEquals(new Outlet(1, "Saravana Bhavan", 4.9),  p0.get(1));
        // 4.8s ordered by name asc
        assertEquals(new Outlet(3, "Annapurna", 4.8),        p0.get(2));

        // page 1, size 3, minRating 4.7 → the next slice
        List<Outlet> p1 = service.getTopRated(1, 3, 4.7);
        assertEquals(List.of(
                new Outlet(5, "Curry Leaf", 4.8),
                new Outlet(2, "Udipi Palace", 4.8),
                new Outlet(9, "Madras Cafe", 4.7)
        ), p1);
    }

    @Test
    void guardClauses_andBoundaryPaging() {
        OutletService service = new OutletService(new OutletRepository());

        // size <= 0 returns empty
        assertTrue(service.getTopRated(0, 0, null).isEmpty());

        // page beyond range returns empty slice
        assertTrue(service.getTopRated(999, 10, null).isEmpty());

        // negative page treated as 0
        assertFalse(service.getTopRated(-1, 2, null).isEmpty());
    }
}
