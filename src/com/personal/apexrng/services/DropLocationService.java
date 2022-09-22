package com.personal.apexrng.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.personal.apexrng.models.ApexMap;
import com.personal.apexrng.models.DropLocation;
import com.personal.apexrng.repositories.DropLocationRepository;

@Service
public class DropLocationService {
	private final DropLocationRepository dropRepo;
	
	public DropLocationService(DropLocationRepository dropRepo) {
		this.dropRepo = dropRepo;		
	}

	public DropLocation getDropLocationByMap(ApexMap map, int index) {
		List<DropLocation> mapDropList = dropRepo.findByMap(map);
		return mapDropList.get(index);
	}
}
