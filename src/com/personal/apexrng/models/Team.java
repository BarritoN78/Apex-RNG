package com.personal.apexrng.models;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Team {
	private List<Loadout> loadouts;
	private List<DropLocation> dropLocations;
}
