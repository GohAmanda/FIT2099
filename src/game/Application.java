package game;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.*;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actors.enemies.GiantCrab;
import game.actors.traders.FingerReaderEnia;
import game.actors.Player;
import game.actors.traders.Trader;
import game.environments.*;
import game.items.GoldenRunes;
import game.items.Runes;
import game.roles.Bandit;
import game.roles.Samurai;
import game.roles.Wretch;
import game.roles.Astrologer;
import game.surfaces.*;
import game.tools.FancyMessage;

import javax.management.relation.Role;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Amanda Goh, Samantha, Yi Jin
 *
 */
public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());
		GoldenFogDoor goldenFogDoor = new GoldenFogDoor();

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(),
				new Graveyard(), new PuddleOfWater(),new GustOfWind(), new SummonSign(), new Cliff(),
				new Cage(), new Barrack(), new ToxicLandfill());

		List<String> limgrave = Arrays.asList(
				"......................#.............#..........................+++.........",
				"......................#.............#.......................+++++..........",
				"......................#..___....____#.........................+++++........",
				"......................#...........__#............................++........",
				"......................#_____........#.............................+++......",
				"......................#............_#..............................+++.....",
				"......................######...######......................................",
				"...........................................................................",
				"...........................=...............................................",
				"........++++..............############__###................................",
				"........+++++++.................._________#................................",
				"..........+++.............#########________................................",
				"............+++...................#_______#................................",
				".............+.............&......###___###................................",
				"............++......................#___#..................................",
				"..............+...................=........................................",
				"..............++.................................................=.........",
				"..............................................++...........................",
				"..................++++......................+++...............######..##...",
				"#####___######....++...........................+++............#....____....",
				"_____________#.....++++..........................+..............__.....#...",
				"_____________#.....+....++........................++.........._.....__.#...",
				"_____________#.........+..+.....................+++...........###..__###...",
				"_____________#.............++..............................................");

		List<String> stormveilCastle = Arrays.asList(
				"...........................................................................",
				"..................<...............<........................................",
				"...........................................................................",
				"##############################################...##########################",
				"............................#................#.......B..............B......",
				".....B...............B......#................#.............................",
				"...............................<.........<.................................",
				".....B...............B......#................#.......B..............B......",
				"............................#................#.............................",
				"#####################..#############...############.####..#########...#####",
				"...............#++++++++++++#................#++++++++++++#................",
				"...............#++++++++++++...<.........<...#++++++++++++#................",
				"...............#++++++++++++..................++++++++++++#................",
				"...............#++++++++++++#................#++++++++++++#................",
				"#####...##########.....#############...#############..#############...#####",
				".._______........................B......B........................B.....B...",
				"_____..._..____....&&.~~.....<..............<..............................",
				".........____......&&......................................................",
				"...._______..................<..............<....................<.....<...",
				"#####....##...###..#####...##########___###############......##.....####...",
				"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++....................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#....................+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++");

		List<String> roundtableHold = Arrays.asList(
				"##################",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"########___#######");

		List<String> bossRoom = Arrays.asList(
				"+++++++++++++++++++++++++",
				".........................",
				"..=......................",
				".........................",
				".........................",
				".........................",
				".........................",
				".........................",
				"+++++++++++++++++++++++++");

		List<String> poisonMaze = Arrays.asList(
				"##################",
				"##☢☢☢☢☢☢##☢☢☢☢☢☢☢#",
				"#☢###☢☢#☢☢☢☢☢☢☢☢☢#",
				"#☢☢☢☢####☢☢☢####☢☢",
				"#☢☢☢☢☢☢#☢☢☢###☢☢☢#",
				"######☢☢☢#☢☢☢☢☢☢☢#",
				"#☢☢#☢☢☢☢☢☢☢#######",
				"#☢☢☢☢☢#####☢☢☢☢☢☢#",
				"#####☢☢##☢☢☢☢☢#☢☢#",
				"#☢☢☢☢☢☢##☢☢☢#☢☢☢☢#",
				"#☢B☢☢☢☢#☢☢☢☢#☢☢☢☢#",
				"#########☢☢#######");

		GameMap limgraveMap = new GameMap(groundFactory, limgrave);
		GameMap stormveilCastleMap = new GameMap(groundFactory, stormveilCastle);
		GameMap roundtableHoldMap = new GameMap(groundFactory, roundtableHold);
		GameMap bossRoomMap = new GameMap(groundFactory, bossRoom);
		GameMap poisonMazeMap = new GameMap(groundFactory, poisonMaze);

		/**
		 * Add the map into world
		 */
		world.addGameMap(limgraveMap);
		world.addGameMap(stormveilCastleMap);
		world.addGameMap(roundtableHoldMap);
		world.addGameMap(bossRoomMap);
		world.addGameMap(poisonMazeMap);

		/**
		 * Setting the GoldFogDoor in the map
		 */
		goldenFogDoor.setGoldFogDoor(limgraveMap, 30,10,"\"Roundatable Hold\"", roundtableHoldMap, 10, 10);
		goldenFogDoor.setGoldFogDoor(limgraveMap, 33,10,"\"Stormveil Castle\"", stormveilCastleMap, 30,10);
		goldenFogDoor.setGoldFogDoor(limgraveMap, 35,10,"\"Poison Maze\"", poisonMazeMap, 16,1);

		goldenFogDoor.setGoldFogDoor(stormveilCastleMap, 30,10,"\"Limgrave\"", limgraveMap, 33,10);
		goldenFogDoor.setGoldFogDoor(stormveilCastleMap, 30,15,"\"Boss Room Map\"", bossRoomMap, 8,8);

		goldenFogDoor.setGoldFogDoor(roundtableHoldMap, 10,10,"\"Limgrave\"", limgraveMap, 30,10);

		goldenFogDoor.setGoldFogDoor(bossRoomMap, 8,8,"\"Stormveil Castle\"", stormveilCastleMap, 30,15);

		goldenFogDoor.setGoldFogDoor(poisonMazeMap, 16,1,"\"Limgrave\"", limgraveMap, 35,10);


		// BEHOLD, ELDEN RING
		for (String line : FancyMessage.ELDEN_RING.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		/**
		 * Setting the role that the player will start with
		 */
		int selection;

		Player player;
		int hittingPoint = 0;
		WeaponItem weaponItem = null;

		Scanner input = new Scanner(System.in);
		Display display = new Display();
		display.println("Which Class Do U Want To Start With: ");
		display.println("-------------------------\n");
		display.println("1 - Bandit");
		display.println("2 - Samurai");
		display.println("3 - Wretch");
		display.println("4 - Astrologer");
		selection = input.nextInt();

		switch (selection){
			case 1:
				hittingPoint = new Bandit().getHittingPoint();
				weaponItem = new Bandit().getWeaponItem();
				break;
			case 2:
				hittingPoint = new Samurai().getHittingPoint();
				weaponItem = new Samurai().getWeaponItem();
				break;
			case 3:
				hittingPoint = new Wretch().getHittingPoint();
				weaponItem = new Wretch().getWeaponItem();
				break;
			case 4:
				hittingPoint = new Astrologer().getHittingPoint();
				weaponItem = new Astrologer().getWeaponItem();
				break;
		}
		player = new Player ("Tarnished", '@', hittingPoint);
		player.addWeaponToInventory(weaponItem);

		/**
		 * Adding player, actor, item in the map
		 * Setting ground in the map
		 */
		world.addPlayer(player, limgraveMap.at(36, 10));
		limgraveMap.at(36, 11).addActor(new Trader());
		limgraveMap.at(36,12).addActor(new FingerReaderEnia());
		limgraveMap.at(36, 15).setGround(new SiteOfLostGrace(limgraveMap.at(36, 15)));
		limgraveMap.at(37, 10).addItem(new GoldenRunes());

		poisonMazeMap.at(4, 10).addItem(new Runes(player, 1000000000));


		world.run();
	}
}
