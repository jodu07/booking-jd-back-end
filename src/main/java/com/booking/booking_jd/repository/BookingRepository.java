package com.booking.booking_jd.repository;
import com.booking.booking_jd.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}