package com.booking.booking_jd.controller;

import com.booking.booking_jd.model.Booking;
import com.booking.booking_jd.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin("*")
public class BookingController {
	 @Autowired
	    private BookingRepository bookingRepository;

	    @GetMapping
	    public List<Booking> getAllBookings() {
	        return bookingRepository.findAll();
	    }

	    @PostMapping
	    public Booking createBooking(@RequestBody Booking booking) {
	        return bookingRepository.save(booking);
	    }
}