package sk.upjs.paz.finalProject;

import java.util.ArrayList;
import java.util.List;

import sk.upjs.jpaz2.ImageTurtleShape;
import sk.upjs.jpaz2.Pane;
import sk.upjs.jpaz2.Turtle;

public class Platform extends Turtle {

	/**
	 * X position of platform middle.
	 */
	private int x;

	/**
	 * Y position of platform middle.
	 */
	private int y;

	/**
	 * Number of platforms in a row.
	 */
	private int numberOfPlatformsInRow;

	/**
	 * Number of platforms in a column.
	 */
	private int numberOfPlatformsInColumn;

	/**
	 * List of platforms in first level.
	 */
	private static List<Platform> platformsLvlOne;

	/**
	 * List of platforms in second level.
	 */
	private static List<Platform> platformsLvlTwo;

	/**
	 * List of platforms in third level.
	 */
	private static List<Platform> platformsLvlThree;

	/**
	 * List of platforms in fourth level.
	 */
	private static List<Platform> platformsLvlFour;

	/**
	 * List of platforms in fifth level.
	 */
	private static List<Platform> platformsLvlFive;

	/**
	 * Construction of a platforms. If there are more platforms first one will be at
	 * the x and y coordinates and next ones will be added on the left or bottom.
	 * Only the last platform is a turtle rest are stamps.
	 * 
	 * @param scene                     Scene where platform will be added
	 * @param x                         X position of platform middle
	 * @param y                         Y position of platform middle
	 * @param numberOfPlatformsInRow    how many platforms in a row will be added
	 * @param numberOfPlatformsInColumn how many platforms in a column will be added
	 */
	public Platform(Pane scene, int x, int y, int numberOfPlatformsInRow, int numberOfPlatformsInColumn) {
		this.x = x;
		this.y = y;
		this.numberOfPlatformsInRow = numberOfPlatformsInRow;
		this.numberOfPlatformsInColumn = numberOfPlatformsInColumn;
		for (int j = 0; j < numberOfPlatformsInColumn; j++) {
			for (int i = 0; i < numberOfPlatformsInRow; i++) {
				scene.add(this);
				setPosition(x + i * 32, y + j * 32);
				// shape of a platform
				setShape(new ImageTurtleShape("/images/Platform32x32.png"));
				stamp();
			}
		}
		scene.remove(this);
	}

	/**
	 * Checks if jumper is close enough to stay on platform.
	 * 
	 * @param jumper jumper
	 * 
	 * @return true, if jumper is close enough to stay on platform
	 */
	public boolean stayOnPlatformDistance(Jumper jumper) {
		int a = 33;
		int leftXBorder = x - a;
		int rightXBorder = x + (numberOfPlatformsInRow * a);
		int upBorder = y - a;
		int downBorder = y - 16;

		if (leftXBorder <= jumper.getX() && rightXBorder >= jumper.getX() && upBorder <= jumper.getY()
				&& downBorder >= jumper.getY()) {
			// set jumper position just above platform
			jumper.setPosition(jumper.getX(), upBorder + 1);
			// reset initial step for falling
			jumper.setFallStep(6);
			// reset initial step for jumping
			jumper.setJumpStep(16);
			return true;
		}
		return false;
	}

	/**
	 * Checks if jumper is close enough to bounce off platform's bottom.
	 * 
	 * @param jumper jumper
	 * 
	 * @return true, if jumper is close enough to bounce off platform bottom
	 */
	public boolean bounceOffPlatformBottom(Jumper jumper) {
		int a = 33;
		int leftXBorder = x - a;
		int rightXBorder = x + (numberOfPlatformsInRow * a);
		int upBorder = y + (numberOfPlatformsInColumn * a) - 16;
		int downBorder = y + (numberOfPlatformsInColumn * a);

		if (leftXBorder <= jumper.getX() && rightXBorder >= jumper.getX() && upBorder <= jumper.getY()
				&& downBorder >= jumper.getY()) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if jumper is close enough to bounce off platform's left side.
	 * 
	 * @param jumper jumper
	 * 
	 * @return true, if jumper is close enough to bounce off platform's left side
	 */
	public boolean bounceOffPlatformLeft(Jumper jumper) {
		int a = 33;
		int leftXBorder = x - a;
		int rightXBorder = x - 16;
		int upBorder = y - 16;
		int downBorder = y + (numberOfPlatformsInColumn * a) - 16;

		if (leftXBorder <= jumper.getX() && rightXBorder >= jumper.getX() && upBorder <= jumper.getY()
				&& downBorder >= jumper.getY()) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if jumper is close enough to bounce off platform's right side.
	 * 
	 * @param jumper jumper
	 * 
	 * @return true, if jumper is close enough to bounce off platform's right side
	 */
	public boolean bounceOffPlatformRight(Jumper jumper) {
		int a = 33;
		int leftXBorder = x + (numberOfPlatformsInRow * a) - 16;
		int rightXBorder = x + (numberOfPlatformsInRow * a);
		int upBorder = y - 16;
		int downBorder = y + (numberOfPlatformsInColumn * a) - 16;

		if (leftXBorder <= jumper.getX() && rightXBorder >= jumper.getX() && upBorder <= jumper.getY()
				&& downBorder >= jumper.getY()) {
			return true;
		}
		return false;
	}

	/**
	 * Construction of platforms in first level.
	 * 
	 * @param scene scene where platforms will be added
	 */
	public static void levelOnePlatforms(Pane scene) {
		platformsLvlOne = new ArrayList<>();
		Platform platform1 = new Platform(scene, 16, 784, 25, 1);
		platformsLvlOne.add(platform1);
		Platform platform2 = new Platform(scene, 100, 650, 3, 2);
		platformsLvlOne.add(platform2);
		Platform platform3 = new Platform(scene, 300, 150, 2, 1);
		platformsLvlOne.add(platform3);
		Platform platform4 = new Platform(scene, 500, 650, 2, 1);
		platformsLvlOne.add(platform4);
		Platform platform5 = new Platform(scene, 300, 550, 2, 1);
		platformsLvlOne.add(platform5);
		Platform platform6 = new Platform(scene, 500, 450, 2, 1);
		platformsLvlOne.add(platform6);
		Platform platform7 = new Platform(scene, 300, 350, 2, 1);
		platformsLvlOne.add(platform7);
		Platform platform8 = new Platform(scene, 100, 50, 1, 18);
		platformsLvlOne.add(platform8);
	}

	/**
	 * List of platforms in first level.
	 * 
	 * @return List of platforms in first level
	 */
	public static List<Platform> getLevelOnePlatforms() {
		return platformsLvlOne;
	}

	/**
	 * Construction of platforms in second level.
	 * 
	 * @param scene scene where platforms will be added
	 */
	public static void levelTwoPlatforms(Pane scene) {
		platformsLvlTwo = new ArrayList<>();
		Platform platform1 = new Platform(scene, 100, 200, 1, 15);
		platformsLvlTwo.add(platform1);
		Platform platform2 = new Platform(scene, 100, 740, 3, 2);
		platformsLvlTwo.add(platform2);
		Platform platform3 = new Platform(scene, 300, 150, 2, 1);
		platformsLvlTwo.add(platform3);
		Platform platform4 = new Platform(scene, 500, 650, 2, 1);
		platformsLvlTwo.add(platform4);
		Platform platform5 = new Platform(scene, 300, 550, 2, 1);
		platformsLvlTwo.add(platform5);
		Platform platform6 = new Platform(scene, 500, 450, 2, 1);
		platformsLvlTwo.add(platform6);
		Platform platform7 = new Platform(scene, 500, 450, 1, 4);
		platformsLvlTwo.add(platform7);
		Platform platform8 = new Platform(scene, 450, 300, 2, 1);
		platformsLvlTwo.add(platform8);
	}

	/**
	 * List of platforms in second level.
	 * 
	 * @return List of platforms in second level
	 */
	public static List<Platform> getLevelTwoPlatforms() {
		return platformsLvlTwo;
	}

	/**
	 * Construction of platforms in third level.
	 * 
	 * @param scene scene where platforms will be added
	 */
	public static void levelThreePlatforms(Pane scene) {
		platformsLvlThree = new ArrayList<>();
		Platform platform1 = new Platform(scene, 164, 770, 5, 1);
		platformsLvlThree.add(platform1);
		Platform platform2 = new Platform(scene, 370, 770, 2, 1);
		platformsLvlThree.add(platform2);
		Platform platform3 = new Platform(scene, 250, 100, 1, 1);
		platformsLvlThree.add(platform3);
		Platform platform4 = new Platform(scene, 0, 550, 1, 1);
		platformsLvlThree.add(platform4);
		Platform platform5 = new Platform(scene, 260, 640, 4, 1);
		platformsLvlThree.add(platform5);
		Platform platform6 = new Platform(scene, 400, 270, 1, 1);
		platformsLvlThree.add(platform6);
		Platform platform7 = new Platform(scene, 200, 350, 1, 1);
		platformsLvlThree.add(platform7);
	}

	/**
	 * List of platforms in third level.
	 * 
	 * @return List of platforms in third level
	 */
	public static List<Platform> getLevelThreePlatforms() {
		return platformsLvlThree;
	}

	/**
	 * Construction of platforms in fourth level.
	 * 
	 * @param scene scene where platforms will be added
	 */
	public static void levelFourPlatforms(Pane scene) {
		platformsLvlFour = new ArrayList<>();
		Platform platform1 = new Platform(scene, 64, 760, 2, 1);
		platformsLvlFour.add(platform1);
		Platform platform2 = new Platform(scene, 370, 760, 1, 1);
		platformsLvlFour.add(platform2);
		Platform platform3 = new Platform(scene, 300, 180, 1, 1);
		platformsLvlFour.add(platform3);
		Platform platform4 = new Platform(scene, 600, 380, 1, 1);
		platformsLvlFour.add(platform4);
		Platform platform5 = new Platform(scene, 260, 670, 4, 1);
		platformsLvlFour.add(platform5);
		Platform platform6 = new Platform(scene, 600, 500, 1, 1);
		platformsLvlFour.add(platform6);
		Platform platform7 = new Platform(scene, 400, 380, 1, 1);
		platformsLvlFour.add(platform7);
	}

	/**
	 * List of platforms in fourth level.
	 * 
	 * @return List of platforms in fourth level
	 */
	public static List<Platform> getLevelFourPlatforms() {
		return platformsLvlFour;
	}

	/**
	 * Construction of platforms in fifth level.
	 * 
	 * @param scene scene where platforms will be added
	 */
	public static void levelFivePlatforms(Pane scene) {
		platformsLvlFive = new ArrayList<>();
		Platform platform1 = new Platform(scene, 370, 770, 1, 1);
		platformsLvlFive.add(platform1);
		Platform platform2 = new Platform(scene, 270, 660, 1, 1);
		platformsLvlFive.add(platform2);
		Platform platform3 = new Platform(scene, 170, 560, 1, 1);
		platformsLvlFive.add(platform3);
		Platform platform4 = new Platform(scene, 70, 660, 1, 1);
		platformsLvlFive.add(platform4);
		Platform platform5 = new Platform(scene, 370, 350, 1, 9);
		platformsLvlFive.add(platform5);
		Platform platform6 = new Platform(scene, 270, 350, 1, 8);
		platformsLvlFive.add(platform6);
		Platform platform7 = new Platform(scene, 110, 470, 5, 1);
		platformsLvlFive.add(platform7);
		Platform platform8 = new Platform(scene, 0, 250, 16, 1);
		platformsLvlFive.add(platform8);
		Platform platform9 = new Platform(scene, 440, 410, 1, 1);
		platformsLvlFive.add(platform9);
		Platform platform10 = new Platform(scene, 580, 410, 1, 1);
		platformsLvlFive.add(platform10);
		Platform platform11 = new Platform(scene, 580, 250, 1, 1);
		platformsLvlFive.add(platform11);
		Platform platform12 = new Platform(scene, 496, 70, 1, 4);
		platformsLvlFive.add(platform12);
		Platform platform0 = new Platform(scene, 16, 16, 25, 1);
		platformsLvlFive.add(platform0);
	}

	/**
	 * List of platforms in fifth level.
	 * 
	 * @return List of platforms in fifth level
	 */
	public static List<Platform> getLevelFivePlatforms() {
		return platformsLvlFive;
	}

}
