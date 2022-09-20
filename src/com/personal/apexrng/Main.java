package com.personal.apexrng;

import java.util.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.personal.apexrng.models.Legend;
import com.personal.apexrng.models.Weapon;

@SpringBootApplication
public class Main {
	private static Random rng = new Random();;
	private static Scanner input = new Scanner(System.in);
	private static boolean nextGame = true, legendChosen = false, weaponsChosen = false;
	private static int teammates = 0, legendNum = 0, weaponNum1 = 0, weaponNum2 = 0, matchesPlayed = 0;
	private static String loadout = "";
	private static String[] playerNames;
	List<Integer> usedLegends = new ArrayList<Integer>();

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
		teamSelection();

		while (nextGame) {
			nextGame = loadoutGenerator();
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
	}

	private static boolean loadoutGenerator() {
		/*Local Variables*/
		String userInp = "";
		
		/*Function*/
		//Generate a loadout for each team member
		System.out.println("Enter any value to exit or nothing to continue");
		userInp = input.nextLine();
		if (userInp.equals("")) {
			return true;
		} else {
			return false;
		}
	}
}
