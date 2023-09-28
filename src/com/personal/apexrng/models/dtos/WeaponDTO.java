package com.personal.apexrng.models.dtos;

import com.personal.apexrng.models.Weapon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeaponDTO {
	private String name;
	private String imgUrl;
	private String modifiers;
	private boolean isSling;

	/**
	 * Constructor to convert Weapon to WeaponDTO
	 * @param weapon
	 */
	public WeaponDTO(Weapon weapon) {
		this.name = weapon.getName();
		this.imgUrl = weapon.getImgUrl();
		this.modifiers = weapon.getModifiers();
		this.isSling = weapon.isSling();
	}
}
