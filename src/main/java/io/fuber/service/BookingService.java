package io.fuber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.fuber.controller.Cabs;
import io.fuber.model.Book;
import io.fuber.model.Trip;
import io.fuber.registory.CabRegistory;
import io.fuber.registory.TripRegistory;

@Service
public class BookingService {

	@Autowired
	CabRegistory cabRegistory;

	@Autowired
	TripRegistory tripRegistory;

	public List<Cabs> getListOfCab(Book book) {
		float lat = book.getLatitude();
		float lng = book.getLongitude();
		String type = book.getType();

		return cabRegistory
				.findAllByLatitudeLessThanEqualAndLatitudeGreaterThanEqualAndLongitudeLessThanEqualAndLongitudeGreaterThanEqualAndTypeAndisAllocated(
						lat + 2, lat - 2, lng + 2, lng - 2, type, false);
	}

	public Trip bookACab(List<Cabs> availableCabList, Book book) {
		double minDist = Double.MAX_VALUE;
		int cabId = 0;

		for (int i = 0; i < availableCabList.size(); i++) {
			float cabLat = availableCabList.get(i).getLatitude();
			float cabLng = availableCabList.get(i).getLongitude();

			double distance = Math.sqrt(Math.sqrt(Math.abs(book.getLatitude() - cabLat))
					+ Math.sqrt(Math.abs(book.getLongitude() - cabLng)));
			if (minDist > distance) {
				minDist = distance;
				cabId = availableCabList.get(i).getCabId();
			}
		}
		Trip trip = new Trip();
		trip.setCabId(cabId);
		trip.setUserId(book.getUserId());
		return trip;
	}

	public void addTrip(Trip trip) {
		tripRegistory.save(trip);
	}

	public void updateCab(int cabId) {
		cabRegistory.cabAllocated(cabId);
	}
}
