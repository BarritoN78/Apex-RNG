import java.util.*;

public class Main {
	private static Random rng;
	private static Scanner input = new Scanner(System.in);
	private static int matchesPlayed = 0;

	public static void main(String[] args) {
		/* Local Variables */
		int teammates, legendNum = -1, weaponNum1 = -1, weaponNum2 = -1;
		String[] playerNames;
		String loadout = "";
		List<Integer> usedLegends = new ArrayList<Integer>();
		boolean nextGame = true, legendChosen = false, weaponsChosen = false;
		String[] legends = { "Bloodhound", "Gibraltar", "Lifeline", "Pathfinder", "Wraith", "Bangalore", "Caustic",
				"Mirage", "Octane", "Wattson", "Crytpo", "Revenant", "Loba", "Rampart", "Horizon", "Fuse", "Valkyre",
				"Seer", "Ash", "Mad Maggie", "Newcastle", "Vantage" };
		String[] weapons = { "Havoc", "Flatline", "Hemlok", "R-301", "Alternator", "Prowler", "R-99", "Volt", "CAR",
				"Devotion", "L-Star", "Spitfire", "G7 Scout", "Triple Take", "30-30 Repeater", "Charge Rifle",
				"Longbow", "Sentinel", "Eva-8", "Mozambique", "Peacekeeper", "RE-45", "P2020", "Wingman",
				"Drop Weapon" };
		String[] kingsCanyon = {"Crash Site", "Artillery", "Basin", "The Rig", "Spotted Lake",
				"Containment", "Capacitor", "Runoff", "The Pit", "Bunker",
				"The Cage", "Labs", "Swamps", "Airbase", "Gauntlet",
				"Relic", "Market", "Hydro Dam", "Repulsor", "Caustic Treatment", 
				"Map Room",	"Supply Ship", "Blue Zone", "Straight Down", "Last to Drop",
				"Four Corners"};
		String[] stormpoint = {"North Pad", "The Mill", "Highpoint", "Lightning Rod", "Downed Beast",
				"Checkpoint", "Cascade Falls", "Command Center", "Thunder Watch", "The Wall",
				"Prowler Den", "Launch Pad", "Cenote Cave", "Barometer", "Antenna",
				"Ship Fall", "Gale Station", "Fish Farms", "Nearest IMC Armory", "Blue Zone",
				"Straight Down", "Last to Drop", "Four Corners"};
		String[] worldsEdge = {"Trails", "Skyhook", "Survey Camp", "Climatizer", "Lava Fissure",
				"Landslide", "Countdown", "Fragment East", "Fragment West", "The Epicenter",
				"Overlook", "Staging", "Harvester", "The Geyser", "Thermal Station", 
				"The Tree", "Lava Siphon", "Launch Site", "Lava City", "The Dome",
				"Geyser Vault", "East of Fragment East", "Blue Zone", "Straight Down", "Last to Drop",
				"Four Corners"};

		/* Function */
		rng = new Random();
		
		//Determining team size and member names
		System.out.println("How many teammates?");
		teammates = Integer.valueOf(input.nextLine());
		playerNames = new String[teammates];
		for (int i = 0; i < teammates; i++) {
			System.out.println("Enter Player " + (i + 1) + "'s Name: ");
			playerNames[i] = input.nextLine();
		}

		//Loadout RNG Loop
		while (nextGame) {
			//Determining drop style
			System.out.println("Match #" + matchesPlayed + ":\nDrop Locations:");
			System.out.println("King's Canyon: " + kingsCanyon[rng.nextInt(0, 26)]);
			System.out.println("Stormpoint: " + stormpoint[rng.nextInt(0, 23)]);
			System.out.println("World's Edge: " + worldsEdge[rng.nextInt(0, 26)] + "\n");
			
			//A list is maintained to keep from getting the same legends too often
			//Essentially, this offers a two match padding before your team is able to get the same legend again
			//For context, my group played with a given loadout for two games before changing the loadout
			//This would make it to where we could play four games before seeing the same legend on the team
			if (matchesPlayed >= 2) {
				for (int r = 0; r < teammates; r++) {
					usedLegends.remove(0);
				}
			}
			
			//Generating a new loadout for each member of the team
			for (int p = 0; p < teammates; p++) {
				legendChosen = false;
				weaponsChosen = false;
				while (!legendChosen) {
					legendNum = rng.nextInt(0, 22);
					
					//If the current a legend has already been in the team within the last two matches, it will be rerolled
					if (!usedLegends.contains(legendNum)) {
						loadout = playerNames[p] + "'s Loadout:\n";
						loadout += "Legend: " + legends[legendNum] + "\n";
						usedLegends.add(legendNum);
						legendChosen = true;

						while (!weaponsChosen) {
							weaponNum1 = rng.nextInt(0, 25);
							weaponNum2 = rng.nextInt(0, 25);

							//This is here so that a player does not ever have to run two of the same weapon
							while (weaponNum1 == weaponNum2) {
								weaponNum2 = rng.nextInt(0, 25);
							}

							loadout += "Weapon 1: " + weapons[weaponNum1] + "\n";
							loadout += "Weapon 2: " + weapons[weaponNum2] + "\n";
							weaponsChosen = true;
						}
					}
				}

				System.out.println(loadout);
			}
			System.out.println("Enter nothing to continue or N to stop");
			if (input.nextLine().toUpperCase().equals("N")) {
				nextGame = false;
			}

			matchesPlayed += 1;
		}

	}
}
