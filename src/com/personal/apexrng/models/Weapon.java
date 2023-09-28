package com.personal.apexrng.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Weapon {
	@Id
	@Column(columnDefinition = "serial")
	private int id;
	private String name;
	private String imgUrl;
	private String category;
	private String ammoType;
	private String effectiveRange;
	private boolean isDropWeapon;
	@Transient
	private String modifiers;
	@Transient
	private boolean isSling;
	
	/*Constructor for cloning*/
	public Weapon(Weapon original) {
		this.id = original.id;
		this.name = original.name;
		this.imgUrl = original.imgUrl;
		this.category = original.category;
		this.ammoType = original.ammoType;
		this.effectiveRange = original.effectiveRange;
		this.isDropWeapon = original.isDropWeapon;
	}
}
