package com.personal.apexrng.services;

import org.springframework.stereotype.Service;

import com.personal.apexrng.models.Legend;
import com.personal.apexrng.repositories.LegendRepository;

@Service
public class LegendService {
	private final LegendRepository legendRepo;
	
	public LegendService(LegendRepository legendRepo) {
		this.legendRepo = legendRepo;		
	}
	
	public Legend getLegend(int id) {
		return legendRepo.findById(id).get();
	}
}
