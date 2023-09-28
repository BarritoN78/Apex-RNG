package com.personal.apexrng.models;

import java.util.List;

import com.personal.apexrng.models.dtos.WeaponDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Loadout {
	private int id;
	private Legend legend;
	private List<WeaponDTO> weapons;
}
