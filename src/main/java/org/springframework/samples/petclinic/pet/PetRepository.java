package org.springframework.samples.petclinic.pet;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.samples.petclinic.owner.Pet;

public interface PetRepository extends JpaRepository<Pet, Integer> {


	@Query("SELECT p FROM Pet p " +
		"JOIN FETCH p.type " +
		"LEFT JOIN FETCH p.visits v " +
		"WHERE (:petName IS NULL OR p.name LIKE %:petName%)")
	Page<Pet> searchPets(String petName, Pageable pageable);
}
