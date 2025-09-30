package com.assessment.restapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/outlets")
public class OutletController {

    private final OutletService service;

    public OutletController(OutletService service) {
        this.service = service;
    }

    // Example: GET /outlets/top-rated?page=0&size=3&minRating=4.7
    @GetMapping("/top-rated")
    public ResponseEntity<List<Outlet>> topRated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) Double minRating
    ) {
        return ResponseEntity.ok(service.getTopRated(page, size, minRating));
    }
}
