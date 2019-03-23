package io.fuber.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.fuber.model.Book;
import io.fuber.model.Trip;
import io.fuber.service.BookingService;

@RestController
public class Booking {

	@Autowired
	BookingService bookingService;

	@PostMapping("/book")
	public ResponseEntity<String> cabBooking(@RequestBody Book book) {

		List<Cabs> availableCabList = bookingService.getListOfCab(book);
		if (availableCabList.isEmpty()) {
			return new ResponseEntity<>("No Cab Found", HttpStatus.NO_CONTENT);
		}
		Trip trip = bookingService.bookACab(availableCabList, book);
		bookingService.addTrip(trip);
		bookingService.updateCab(trip.getCabId());
		return new ResponseEntity<>(
				"Booking confirmed with cabId- " + trip.getCabId() + " & userId - " + book.getUserId(), HttpStatus.OK);
	}
}
