package com.personal.apexrng.services;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.personal.apexrng.models.Legend;
import com.personal.apexrng.repositories.LegendRepository;

@Service
public class LegendService {
	private final LegendRepository legendRepo;
	private List<Legend> allLegends;
	private Random rng;
	
	public LegendService(LegendRepository legendRepo) {
		this.legendRepo = legendRepo;	
		this.allLegends = legendRepo.findAll();
		this.rng = new Random();		
	}
	
	public Legend getLegendById(int id) {
		return legendRepo.findById(id).get();
	}
	
	public Legend getLegend() {
		return allLegends.get(rng.nextInt(0, allLegends.size()));
	}
}
