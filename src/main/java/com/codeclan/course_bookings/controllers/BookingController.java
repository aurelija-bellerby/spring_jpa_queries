package com.codeclan.course_bookings.controllers;

import com.codeclan.course_bookings.models.Booking;
import com.codeclan.course_bookings.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class BookingController {
    @Autowired
    BookingRepository bookingRepository;

    @GetMapping("/bookings")
    ResponseEntity<List<Booking>> index(
            @RequestParam(required = false, name = "date") String date
    ) {
        List<Booking> bookings;
        if (date != null) {
            bookings = bookingRepository.findByDate(date);
        } else {
            bookings = bookingRepository.findAll();
        }
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/bookings/{id}")
    ResponseEntity<Optional<Booking>> show(@PathVariable Long id) {
        return new ResponseEntity<>(bookingRepository.findById(id), HttpStatus.OK);
    }
}
