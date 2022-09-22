package com.personal.apexrng;

import java.util.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.personal.apexrng.models.ApexMap;
import com.personal.apexrng.models.Legend;
import com.personal.apexrng.models.Weapon;
import com.personal.apexrng.services.DropLocationService;
import com.personal.apexrng.services.LegendService;
import com.personal.apexrng.services.WeaponService;

@SpringBootApplication
public class Main {
	private static Random rng = new Random();
	private static Scanner input = new Scanner(System.in);
	private static LegendService legendServ;
	private static WeaponService weaponServ;
	private static DropLocationService dropServ;
	private static boolean teamChosen = false, nextGame = true, legendChosen = false,
			weaponsChosen = false;
	private static int teammates = 0, matchesPlayed = 0;
	private static String loadout = "";
	private static String[] playerNames;
	private static List<Legend> usedLegends = new ArrayList<Legend>();
	
	public Main(LegendService legendServ, WeaponService weaponServ, DropLocationService dropServ) {
		Main.dropServ = dropServ;
		Main.legendServ = legendServ;
		Main.weaponServ = weaponServ;
	}

	public static void main(String[] args){
		SpringApplication.run(Main.class, args);
		while (!teamChosen) {
			try {
				teamSelection();
			} catch (Exception e) {
				System.out.println("Your input was invalid. Please try again.");
			}
		}

		while (nextGame) {
			loadout = "Match #" + matchesPlayed + ":\n";
			nextGame = loadoutGenerator();
			matchesPlayed++;
		}
	}

	private static void teamSelection() {
		// Determining team size and member names
		System.out.println("How many teammates?");
		teammates = Integer.valueOf(input.nextLine());
		playerNames = new String[teammates];
		for (int i = 0; i < teammates; i++) {
			System.out.println("Enter Player " + (i + 1) + "'s Name: ");
			playerNames[i] = input.nextLine();
		}
		teamChosen = true;
	}

	private static boolean loadoutGenerator() {
		/* Local Variables */
		Legend chosenLegend = null;
		Weapon weapon1 = null, weapon2 = null;
		ApexMap misc = new ApexMap(1, "Miscellaneous");
		ApexMap kingsCanyon = new ApexMap(2, "Kings Canyon");
		ApexMap stormpoint = new ApexMap(3, "Stormpoint");
		ApexMap worldsEdge = new ApexMap(4, "Worlds Edge");
		String userInp = "", dropKC = "", dropSP = "", dropWE = "";

		/* Function */
		// Adjust list of used legends for every match after the first three
		if (matchesPlayed > 3) {
			for (int i = 0; i < teammates; i++) {
				usedLegends.remove(0);
			}
		}

		// Generate a drop location for the team
		// 75% chance of named location
		if (rng.nextInt(0, 101) < 75) {
			dropKC = dropServ.getDropLocationByMap(kingsCanyon, rng.nextInt(0, 22)).getName();
			dropSP = dropServ.getDropLocationByMap(stormpoint, rng.nextInt(0, 19)).getName();
			dropWE = dropServ.getDropLocationByMap(worldsEdge, rng.nextInt(0, 22)).getName();
		} else { // 25% chance of universal location
			dropKC = dropServ.getDropLocationByMap(misc, rng.nextInt(0, 4)).getName();
			dropSP = dropKC;
			dropWE = dropKC;
		}
		loadout += "\nDrop Locations:\n";
		loadout += "King's Canyon: " + dropKC + "\n";
		loadout += "Stormpoint: " + dropSP + "\n";
		loadout += "World's Edge: " + dropWE + "\n";

		// Generate a loadout for each team member
		for (int i = 0; i < teammates; i++) {
			legendChosen = false;
			weaponsChosen = false;
			
			loadout += "\n" + playerNames[i] + "'s Loadout:\n";
			
			while (!legendChosen) {
				chosenLegend = legendServ.getLegend(rng.nextInt(1, 23));
				if(!usedLegends.contains(chosenLegend)) {
					legendChosen = true;
					usedLegends.add(chosenLegend);
				} else {
					legendChosen = false;
				}
			}
			
			loadout += "Legend: " + chosenLegend.getName() + "\n";
			
			while(!weaponsChosen) {
				weapon1 = weaponServ.getWeapon(rng.nextInt(1, 29));
				weapon2 = weaponServ.getWeapon(rng.nextInt(1, 29));
				
				if(!weapon1.equals(weapon2)) {
					if(!weapon1.getAmmoType().equals(weapon2.getAmmoType())) {
						if(!(weapon1.getEffectiveRange().contains("Long") && weapon2.getEffectiveRange().contains("Long"))) {
							weaponsChosen = true;
						}
					}
				}
			}
			
			if (weapon1.isDropWeapon()) {
				loadout += "Weapon 1: Drop Weapon\n";
			} else {
				loadout += "Weapon 1: " + weapon1.getName() + "\n";
			}
			
			if (weapon2.isDropWeapon()) {
				loadout += "Weapon 2: Drop Weapon\n";
			} else {
				loadout += "Weapon 2: " + weapon2.getName() + "\n";
			}
		}
		
		System.out.println(loadout);

		// Prompt to generate a new loadout
		System.out.println("Enter any value to exit or nothing to continue");
		userInp = input.nextLine();
		if (userInp.equals("")) {
			return true;
		} else {
			System.out.println("Adios. Hasta Luego. You can close the console now");
			return false;
		}
	}
}
