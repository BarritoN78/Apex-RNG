package com.personal.apexrng.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Weapon {
	@Id
	@Column(columnDefinition = "serial")
	private int id;
	private String name;
	private String category;
	private String ammoType;
	private String effectiveRange;
	private boolean isDropWeapon;
}
