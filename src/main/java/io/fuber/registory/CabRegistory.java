package io.fuber.registory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.fuber.controller.Cabs;
import io.fuber.model.Book;

@Repository
public interface CabRegistory extends JpaRepository<Cabs, Integer> {

	List<Cabs> findAllByLatitudeLessThanEqualAndLatitudeGreaterThanEqualAndLongitudeLessThanEqualAndLongitudeGreaterThanEqualAndTypeAndisAllocated(
			float lat1, float lat2, float lng1, float lng2, String type, boolean isAllocated);
	@Modifying
	@Query("update Cabs set isAllocated = 1 where cab_id = ?1")
	int cabAllocated(int cabId);
}
