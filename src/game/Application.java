package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.World;
import game.actions.TeleportAction;
import game.actors.*;
import game.enemies.Bowser;
import game.grounds.*;
import game.items.PowerStar;
import game.items.SuperMushroom;

/**
 * The main class for the Mario World game.
 *
 */
public class Application {

	public static void main(String[] args) {

			World world = new World(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout(), new Lava(), new WarpPipe(), new BlinkingTower());

			List<String> map = Arrays.asList(
				"..........................................##..........+.........................",
				"............+............+..................#...................................",
				"............................................#...................................",
				".............................................##......................+..........",
				"...............................................#................................",
				"................................................#...............................",
				".................+................................#.............................",
				".................................................##.............................",
				"................................................##..............................",
				".........+..............................+#____####.................+............",
				".......................................+#_____###++.............................",
				".......................................+#______###..............................",
				"........................................+#_____###..............................",
				"........................+........................##.............+...............",
				"...................................................#............................",
				"....................................................#...........................",
				"...................+.................................#..........................",
				"......................................................#.........................",
				".......................................................##.......................");

			List<String> lavaMap = Arrays.asList(
					"C___#....+.........#",
					"___...............#.",
					"__#..........+...#..",
					"_#..............#...",
					"#..............#....",
					".......+......#.....",
					".............#......",
					"............#...+...",
					"...+.......#........",
					"..........#.........");

			// Randomly populate map with grounds
			generateRandomGrounds(map, new Sprout(), 0.01);
			generateRandomGrounds(map, new WarpPipe(), 0.01);
			generateRandomGrounds(map, new BlinkingTower(), 0.01);
			generateRandomGrounds(lavaMap, new Sprout(), 0.01);
			generateRandomGrounds(lavaMap, new Lava(), 0.1);

			// Create game maps and add them to the world
			GameMap gameMap = new GameMap(groundFactory, map);
			GameMap lavaZone = new GameMap(groundFactory, lavaMap);
			world.addGameMap(gameMap);
			world.addGameMap(lavaZone);

			// Create player and place him in the world
			Actor mario = new Player("Player", 'm', 100);
			world.addPlayer(mario, gameMap.at(42, 10));

			// Assign initial destination of TeleportAction
			TeleportAction.setDestination(lavaZone.at(0,0));

			// Add actor and items in map
			gameMap.at(42,11).addActor(new Toad());
			gameMap.at(43,10).addItem(new SuperMushroom());
			gameMap.at(41,10).addItem(new PowerStar());
			lavaZone.at(10, 2).addActor(new Bowser());
			lavaZone.at(11, 2).addActor(new PrincessPeach());

			// Add health and power fountain in map
			gameMap.at(42, 8).setGround(new HealthFountain());
			gameMap.at(43, 8).setGround(new PowerFountain());

			// Add Yoshi
			gameMap.at(43,10).addActor(new Yoshi());

			// Add Luigi
			gameMap.at(0, 16).addActor(new Luigi());
			world.run();
	}

	/**
	 * Randomly populate map with selected ground
	 * @param map The string representation of the map
	 * @param ground The type of ground that will be randomly placed on the map
	 * @param odds The odds of a successful placement
	 */
	public static void generateRandomGrounds(List<String> map, Ground ground, double odds) {
		for (int i = 0; i <  map.size(); i++) {
			char[] newString = map.get(i).toCharArray();
			for (int j = 0; j < map.get(i).length(); j++) {
				if (Math.random() < odds && map.get(i).charAt(j) == '.') {
					newString[j] = ground.getDisplayChar();
				}
			}
			map.set(i, String.valueOf(newString));
		}
	}
}
