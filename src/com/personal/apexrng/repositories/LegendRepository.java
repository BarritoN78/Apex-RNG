package com.personal.apexrng.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.apexrng.models.Legend;

@Repository
public interface LegendRepository extends JpaRepository<Legend,Integer>{

}
