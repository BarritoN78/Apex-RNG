package com.personal.apexrng.services;

import org.springframework.stereotype.Service;

import com.personal.apexrng.models.Weapon;
import com.personal.apexrng.repositories.WeaponRepository;

@Service
public class WeaponService {
	private final WeaponRepository weaponRepo;
	
	public WeaponService(WeaponRepository weaponRepo) {
		this.weaponRepo = weaponRepo;		
	}
	
	public Weapon getWeapon(int id) {
		return weaponRepo.findById(id).get();
	}

}
