package com.personal.apexrng.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.personal.apexrng.models.ApexMap;
import com.personal.apexrng.models.DropLocation;
import com.personal.apexrng.models.Legend;
import com.personal.apexrng.models.Loadout;
import com.personal.apexrng.models.Team;
import com.personal.apexrng.models.Weapon;
import com.personal.apexrng.models.dtos.WeaponDTO;

@Service
public class LoadoutService {
	private LegendService legServ;
	private WeaponService weapServ;
	private DropLocationService dropServ;

	/**
	 * Constructor for Spring Boot dependency injection
	 * 
	 * @param weaponRepo
	 */
	public LoadoutService(LegendService legServ, WeaponService weapServ, DropLocationService dropServ) {
		this.legServ = legServ;
		this.weapServ = weapServ;
		this.dropServ = dropServ;
	}

	/**
	 * Returns a set of loadouts and drop locations. Number of loadouts returned is
	 * dependent on given number of players. Available legends are filtered by given list
	 * 
	 * @param players
	 * @param usedLegends
	 * @return
	 */
	public Team getLoadouts(int players, List<Legend> usedLegends) {
		/* Local Variables */
		Team team = new Team();
		List<Loadout> loadouts = new ArrayList<Loadout>();
		List<DropLocation> dropLocations;

		/* Function */
		dropLocations = generateDropLocations();
		loadouts = generateLoadouts(players, usedLegends);

		team.setDropLocations(dropLocations);
		team.setLoadouts(loadouts);

		return team;
	}
	/**
	 * Returns a set of loadouts and drop locations. Number of loadouts returned is
	 * dependent on given number of players
	 * 
	 * @param players
	 * @return
	 */
	public Team getUnfilteredLoadouts(int players) {
		/* Local Variables */
		Team team = new Team();
		List<Loadout> loadouts = new ArrayList<Loadout>();
		List<DropLocation> dropLocations;

		/* Function */
		dropLocations = generateDropLocations();
		loadouts = generateLoadouts(players, new ArrayList<Legend>());

		team.setDropLocations(dropLocations);
		team.setLoadouts(loadouts);

		return team;
	}

	/**
	 * Returns a set of loadouts based on given number of players. Returned legends
	 * within each loadout can be filtered out with the usedLegends parameter
	 * 
	 * @param players
	 * @param usedLegends
	 * @return
	 */
	private List<Loadout> generateLoadouts(int players, List<Legend> usedLegends) {
		/* Local Variables */
		List<Loadout> teamLoads = new ArrayList<Loadout>();
		List<WeaponDTO> weapons = new ArrayList<WeaponDTO>();
		Loadout loadout = null;
		Legend legend = null;
		Weapon weap1 = null, weap2 = null, weapSling = null;
		boolean legendChosen, weaponsChosen;

		/* Function */
		if (usedLegends == null) {
			usedLegends = new ArrayList<Legend>();
		}

		for (int i = 0; i < players; i++) {
			loadout = new Loadout();
			weapons = new ArrayList<WeaponDTO>();
			legendChosen = false;
			weaponsChosen = false;

			// Legend Loop
			while (!legendChosen) {
				legend = legServ.getLegend();
				if (!usedLegends.contains(legend)) { // Given legend is not on filter list
					legendChosen = true;
					usedLegends.add(legend);
					loadout.setLegend(legend);
				} else {
					legendChosen = false;
				}
			}

			//Weapon Loop
			while (!weaponsChosen) {
				weap1 = new Weapon();
				weap2 = new Weapon();
				weapSling = new Weapon();
				weap1 = weapServ.getWeapon();
				weap2 = weapServ.getWeapon();
				if (legend.getName().equals("Ballistic")) { //Ballistic is the only character that can carry 3 weapons
					weapSling = weapServ.getWeaponSling();
				}

				if (weap1.getId() != weap2.getId()) { //Main weapons are different
					if (!weap1.getAmmoType().equals(weap2.getAmmoType())) { //Main weapon ammo types are different
						if (!(weap1.getEffectiveRange().contains("Long")
								&& weap2.getEffectiveRange().contains("Long"))) { //At least one of the main weapons are not long range only
							if (legend.getName().equals("Ballistic") && (!(weapSling.getId() == weap1.getId())
									&& !(weapSling.getId() == weap2.getId()))) { //(Ballistic Only) Sling weapon is different from main weapons
								weaponsChosen = true;
							} else if (!legend.getName().equals("Ballistic")) { //Given legend is not Ballistic
								weaponsChosen = true;
							}
						}
					}
				}
			}
			
			//Convert given weapons to DTOs and add to list
			weapons.add( new WeaponDTO(weap1));
			weapons.add( new WeaponDTO(weap2));
			if (legend.getName().equals("Ballistic")) { //Only add 3rd weapon if the legend is Ballistic
				weapons.add( new WeaponDTO(weapSling));
			}
			
			//Add weapons to loadout
			loadout.setWeapons(weapons);
			
			//Add generated loadout to team's list
			teamLoads.add(loadout);
		}

		return teamLoads;
	}

	private List<DropLocation> generateDropLocations() {
		List<DropLocation> dropLocations = new ArrayList<DropLocation>();
		List<ApexMap> maps = new ArrayList<ApexMap>();
		Random rng = new Random();
		ApexMap misc = new ApexMap(1, "Miscellaneous");
		ApexMap kingsCanyon = new ApexMap(2, "Kings Canyon");
		ApexMap stormpoint = new ApexMap(3, "Stormpoint");
		ApexMap worldsEdge = new ApexMap(4, "Worlds Edge");
		ApexMap brokenMoon = new ApexMap(5, "Broken Moon");
		ApexMap olympus = new ApexMap(6, "Olympus");
		DropLocation tempDL, miscDL;
		
		//Adding necessary maps to map list
		maps.add(kingsCanyon);
		maps.add(stormpoint);
		maps.add(worldsEdge);
		maps.add(brokenMoon);
		maps.add(olympus);
		
		// Generate a drop location for the team
		// 75% chance of named location
		if (rng.nextInt(0, 101) < 75) {
			for (ApexMap m : maps) {
				dropLocations.add(dropServ.getDropLocationByMap(m));
			}
		} else { // 25% chance of universal location
			miscDL = dropServ.getDropLocationByMap(misc);
			for (ApexMap m : maps) { //Loop converts displayed Apex Map to one that is in the list as opposed to Miscellaneous
				tempDL = new DropLocation(miscDL);
				tempDL.setMap(m);
				dropLocations.add(tempDL);
			}
		}
		return dropLocations;
	}

}
