package com.personal.apexrng.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.apexrng.models.Weapon;

@Repository
public interface WeaponRepository extends JpaRepository<Weapon,Integer>{

}
