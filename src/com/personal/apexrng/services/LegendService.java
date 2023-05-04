package com.personal.apexrng.services;

import java.util.ArrayList;
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
		this.allLegends = new ArrayList<Legend>();
		this.rng = new Random();		
	}
	
	public Legend getLegendById(int id) {
		return legendRepo.findById(id).get();
	}
	
	public Legend getLegend() {
		//Retrieves legends from database if needed
		if(allLegends.isEmpty()) {
			allLegends = legendRepo.findAll();
		}
		return allLegends.get(rng.nextInt(0, allLegends.size()));
	}
}
