package com.assessment.restapi;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OutletRepository {

    private static final List<Outlet> DATA = List.of(
            new Outlet(1, "Saravana Bhavan", 4.9),
            new Outlet(7, "Dosa Corner",      4.9),
            new Outlet(2, "Udipi Palace",     4.8),
            new Outlet(3, "Annapurna",        4.8),
            new Outlet(5, "Curry Leaf",       4.8),
            new Outlet(4, "The Spice House",  4.7),
            new Outlet(9, "Madras Cafe",      4.7),
            new Outlet(6, "Masala Magic",     4.6),
            new Outlet(8, "Spice Route",      4.5),
            new Outlet(10,"Tiffin Express",   4.4)
    );

    public List<Outlet> findAll() {
        return DATA;
    }
}
