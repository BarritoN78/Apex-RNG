package com.personal.apexrng.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.apexrng.models.ApexMap;
import com.personal.apexrng.models.DropLocation;

@Repository
public interface DropLocationRepository extends JpaRepository<DropLocation,Integer>{
	List<DropLocation> findByMap(ApexMap map);
}
