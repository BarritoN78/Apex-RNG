package com.personal.apexrng.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.personal.apexrng.models.Weapon;
import com.personal.apexrng.repositories.WeaponRepository;

@Service
public class WeaponService {
	private final WeaponRepository weaponRepo;
	private List<Weapon> allWeapons;
	private Random rng;
	
	public WeaponService(WeaponRepository weaponRepo) {
		this.weaponRepo = weaponRepo;
		this.allWeapons = new ArrayList<Weapon>();
		this.rng = new Random();
	}
	
	public Weapon getWeaponByID(int id) {
		return weaponRepo.findById(id).get();
	}
	
	public Weapon getWeapon() {
		//Retrieve weapons from database if needed
		if(allWeapons.isEmpty()) {
			allWeapons = weaponRepo.findAll();
		}
		
		Weapon weaponResult;
		List<Weapon> groundWeapons;
		
		//Filtering out drop weapons
		groundWeapons = allWeapons.stream().filter(w -> w.isDropWeapon() == false).toList();
		
		//Choosing weapon from filtered list
		int weaponId = rng.nextInt(0 , groundWeapons.size());
		weaponResult = new Weapon(groundWeapons.get(weaponId));
		
		//Adding the 10% chance for no attachments
		if(rng.nextInt(0, 10) == 9) {
			weaponResult.setName(weaponResult.getName() + "(No Attachments)");
		}
		
		//Adding the 10% chance for a drop weapon
		if(rng.nextInt(0, 10) == 9) {
			weaponResult.setName(weaponResult.getName() + " / Drop Weapon");
		}
		
		return weaponResult;
	}

}
