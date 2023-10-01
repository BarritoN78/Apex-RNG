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
	
	/**
	 * Constructor for Spring Boot dependency injection
	 * @param weaponRepo
	 */
	public WeaponService(WeaponRepository weaponRepo) {
		this.weaponRepo = weaponRepo;
		this.allWeapons = new ArrayList<Weapon>();
		this.rng = new Random();
	}
	
	/**
	 * Returns a weapon object with the given id
	 * @param id
	 * @return
	 */
	public Weapon getWeaponByID(int id) {
		return weaponRepo.findById(id).get();
	}
	
	/**
	 * Returns a random weapon with added modifiers
	 * @return
	 */
	public Weapon getWeapon() {
		Weapon weaponResult;
		
		weaponResult = addMods(weaponSelector());
		
		return weaponResult;
	}
	
	/**
	 * Returns a random weapon with no modifiers
	 * @return
	 */
	public Weapon getWeaponSling() {
		Weapon weapSling = weaponSelector();
		weapSling.setSling(true);
		return weapSling;
	}
	
	/**
	 * Selects a weapon from a filtered list using a random number generator
	 * @return
	 */
	public Weapon weaponSelector() {
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
		
		return weaponResult;
	}
	
	/**
	 * Adds modifications to the given weapon object
	 * @param weaponMod
	 * @return
	 */
	public Weapon addMods(Weapon weaponMod) {
		String modifier = "";
		
		//Adding the 10% chance for no attachments
		if(rng.nextInt(0, 10) == 9) {
			modifier += "(No Attachments)";
		}
		
		//Adding the 10% chance for a drop weapon
		if(rng.nextInt(0, 10) == 9) {
			modifier += "(Drop Weapon)";
		}
		
		weaponMod.setModifiers(modifier);
		
		return weaponMod;
	}

}
