package com.personal.apexrng.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.personal.apexrng.models.Legend;
import com.personal.apexrng.models.Team;
import com.personal.apexrng.services.LoadoutService;

@RestController
public class MainController{
	private final LoadoutService loadServ;
	
	public MainController(LoadoutService loadServ) {
		this.loadServ = loadServ;
		
	}
	
	@GetMapping("/{players}")
	public ResponseEntity<Team> getUnfilteredLoadouts(@PathVariable("players") int players){
		Team team = loadServ.getUnfilteredLoadouts(players);
		return ResponseEntity.ok(team);
	}
	
	@PostMapping("/{players}")
	public ResponseEntity<Team> getLoadouts(@PathVariable("players") int players, @RequestBody List<Legend> usedLegends){
		Team team = loadServ.getLoadouts(players, usedLegends);
		return ResponseEntity.ok(team);
	}
}