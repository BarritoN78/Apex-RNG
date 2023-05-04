package com.personal.apexrng.services;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.personal.apexrng.models.ApexMap;
import com.personal.apexrng.models.DropLocation;
import com.personal.apexrng.repositories.DropLocationRepository;

@Service
public class DropLocationService {
	private final DropLocationRepository dropRepo;
	private Random rng;
	
	public DropLocationService(DropLocationRepository dropRepo) {
		this.dropRepo = dropRepo;	
		this.rng = new Random();
	}

	public DropLocation getDropLocationByMap(ApexMap map) {
		List<DropLocation> mapDropList = dropRepo.findByMap(map);
		return mapDropList.get(rng.nextInt(0, mapDropList.size()));
	}
}
