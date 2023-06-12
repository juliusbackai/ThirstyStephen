package sk.upjs.paz.finalProject;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import sk.upjs.jpaz2.AudioClip;
import sk.upjs.jpaz2.TransitionEffect;
import sk.upjs.jpaz2.theater.Scene;
import sk.upjs.jpaz2.theater.Stage;

public class LevelScene extends Scene {

	/**
	 * Name of scene.
	 */
	public static final String NAME = "Level";

	/**
	 * Jumper
	 */
	protected Jumper jumper = new Jumper(this);

	/**
	 * Variable for jumping.
	 */
	private int temp = -1;

	/**
	 * Current level.
	 */
	private int levelNumber;

	/**
	 * List of platforms for each level.
	 */
	private List<Platform> platforms = new ArrayList<>();

	/**
	 * Alenka
	 */
	private Alenka alenka;

	/**
	 * List of Alenkas kisses.
	 */
	private List<AlenkasKiss> kisses = new ArrayList<>();

	/**
	 * Vilko
	 */
	private Vilko vilko;

	/**
	 * List of Vilkos knives.
	 */
	private List<Knife> knives = new ArrayList<>();

	/**
	 * Indicates collision with a knife.
	 */
	private static boolean pushLevelOne;

	/**
	 * Bottle.
	 */
	private Bottle bottle;

	/**
	 * Back button.
	 */
	private BackButton backButton;

	/**
	 * Count how many times jumper fall into first level, for playing right music.
	 */
	private static int levelOneCount;

	/**
	 * Music on/off switch.
	 */
	private MusicOnOffSwitch musicButton;

	/**
	 * titanic song
	 */
	AudioClip titanic = new AudioClip("/audio/titanic.wav", true);

	/**
	 * never ending story song
	 */
	AudioClip falko = new AudioClip("/audio/falko.wav", true);

	/**
	 * Construct a scene FirstLevel.
	 * 
	 * @param stage stage
	 */
	public LevelScene(Stage stage) {
		super(stage);
		backButton = new BackButton(getStage());
		backButton.setPosition(5, 762);
		backButton.setTransparency(0.5);
		add(backButton);
	}

	/**
	 * Clears platforms and features from lower or upper level and places new ones.
	 */
	public void buildLevel() {
		if (levelNumber == 1) {
			remove(alenka);
			remove(vilko);
			deleteKissesFromScene();
			deleteKnivesFromScene();
			clear();
			Platform.levelOnePlatforms(this);
			platforms = Platform.getLevelOnePlatforms();
			levelOneCount++;
			return;
		}
		if (levelNumber == 2) {
			clear();
			alenka = new Alenka(this, 150, 150);
			kisses = Alenka.getListOfKisses();
			Platform.levelTwoPlatforms(this);
			platforms = Platform.getLevelTwoPlatforms();
			return;
		}
		if (levelNumber == 3) {
			remove(alenka);
			remove(vilko);
			deleteKissesFromScene();
			deleteKnivesFromScene();
			clear();
			Platform.levelThreePlatforms(this);
			platforms = Platform.getLevelThreePlatforms();
			return;
		}
		if (levelNumber == 4) {
			remove(bottle);
			clear();
			vilko = new Vilko(this, 300, 300);
			knives = Vilko.getListOfKnives();
			Platform.levelFourPlatforms(this);
			platforms = Platform.getLevelFourPlatforms();
			return;
		}
		if (levelNumber == 5) {
			deleteKnivesFromScene();
			remove(vilko);
			clear();
			bottle = new Bottle(this);
			Platform.levelFivePlatforms(this);
			platforms = Platform.getLevelFivePlatforms();
			return;
		}
	}

	/**
	 * Indicates when collision with a knife is happening.
	 */
	public static void setLevelOne(boolean pushLevelOne) {
		LevelScene.pushLevelOne = pushLevelOne;
	}

	/**
	 * Sets level one after collision with a knife.
	 */
	public void knifeCollision() {
		if (pushLevelOne) {
			getStage().changeScene(TransitionScene.NAME, TransitionEffect.FADE_OUT_FADE_IN, 2000);
			jumper.setLeft(false);
			jumper.setRight(false);
		}
		LevelScene.pushLevelOne = false;
	}

	/**
	 * Remove kisses from scene.
	 */
	public void deleteKissesFromScene() {
		for (int i = 0; i < kisses.size(); i++) {
			remove(kisses.get(i));
		}
		kisses.clear();
	}

	/**
	 * Remove knives from scene.
	 */
	public void deleteKnivesFromScene() {
		for (int i = 0; i < knives.size(); i++) {
			remove(knives.get(i));
		}
		knives.clear();
	}

	/**
	 * Movement when arrows are pressed.
	 */
	@Override
	protected void onKeyPressed(KeyEvent e) {
		// left arrow
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			jumper.setLeft(true);
		}
		// right arrow
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			jumper.setRight(true);
		}
		// up arrow (jump)
		if (e.getKeyCode() != temp) {
			temp = e.getKeyCode();
			if (e.getKeyCode() == KeyEvent.VK_UP && !jumper.isJumping()) {
				// starts count time for computing jump duration
				jumper.setJumpStartTime(System.currentTimeMillis());
				jumper.setUP(true);
			}
		}
	}

	/**
	 * Movement stop when arrows are released.
	 */
	@Override
	protected void onKeyReleased(KeyEvent e) {
		// left arrow
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			jumper.setLeft(false);
		}
		// right arrow
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			jumper.setRight(false);
		}
		// up arrow (jump)
		if (e.getKeyCode() == KeyEvent.VK_UP && !jumper.isJumping()) {
			// end time for computing jump duration
			jumper.setJumpEndTime(System.currentTimeMillis());
			jumper.jumpStart(true);
			jumper.setUP(false);
			temp = -1;
		}
	}

	/**
	 * Checking if the jumper is close enough to top of platform to stay on it.
	 */
	public void stopOnPlatform() {
		for (Platform platform : platforms) {
			if (platform.stayOnPlatformDistance(jumper) || jumper.isJumping()) {
				jumper.setFall(false);
				return;
			} else {
				jumper.setFall(true);
			}
		}
	}

	/**
	 * Checking if the jumper is close enough to side or bottom of platform and
	 * bounce.
	 */
	public void bounceOffPlatform() {
		for (Platform platform : platforms) {
			// bounce from bottom
			if (platform.bounceOffPlatformBottom(jumper)) {
				jumper.setJumping(false);
				return;
			}
			// bounce from left
			if (platform.bounceOffPlatformLeft(jumper)) {
				jumper.goLeft();
				jumper.setPosition(jumper.getX() - 8, jumper.getY());
				jumper.setJumping(false);
				return;
			}
			// bounce from right
			if (platform.bounceOffPlatformRight(jumper)) {
				jumper.setPosition(jumper.getX() + 8, jumper.getY());
				jumper.setJumping(false);
				return;
			}
		}
	}

	/**
	 * Changes level when jumper cross top or bottom border.
	 */
	public void changeLevel() {
		// top border
		if (jumper.getY() < 0) {
			jumper.setPosition(jumper.getX(), 800);
			levelNumber += 1;
			buildLevel();
		}
		// bottom border
		if (jumper.getY() > 800) {
			jumper.setPosition(jumper.getX(), 0);
			levelNumber -= 1;
			buildLevel();
		}
	}

	/**
	 * Returns jumper on the start of level 1 if it jumps out of scene.
	 */
	public void outOfBorders() {
		if (jumper.getX() > 610 || jumper.getX() < -10) {
			levelNumber = 1;
			jumper.setLeft(false);
			jumper.setRight(false);
			getStage().changeScene(TransitionScene.NAME, TransitionEffect.FADE_OUT_FADE_IN, 1000);
		}
	}

	/**
	 * Plays music according to how many times jumper fall down to level one.
	 */
	public void playMusic() {
		if (levelOneCount == 8) {
			getStage().setBackgroundMusic(titanic);
			levelOneCount++;
			// mute button
			musicButton = new MusicOnOffSwitch(getStage());
			musicButton.setPosition(45, 762);
			musicButton.setTransparency(0.5);
			add(musicButton);
		}
		if (levelOneCount == 20) {
			getStage().setBackgroundMusic(falko);
			levelOneCount++;
		}
		// another songs
	}
	
	/**
	 * Reset counter for falls to level one.
	 */
	public static void resetLvlOneCount() {
		LevelScene.levelOneCount = 1;
	}

	@Override
	protected void onTick() {
		super.onTick();
		if (levelNumber == 2) {
			alenka.sendKisses(this, 300);
			alenka.kissFly(jumper);
		}
		if (levelNumber == 4) {
			vilko.sendKnives(this, 2500);
			vilko.knifeFly(jumper);
			knifeCollision();
		}
		if (levelNumber == 5 && bottle.closeToJumper(jumper)) {
			getStage().changeScene(WinScene.NAME, TransitionEffect.FADE_OUT_FADE_IN, 1000);
			stop();
		}
		playMusic();
		stopOnPlatform();
		bounceOffPlatform();
		changeLevel();
		outOfBorders();
		jumper.move();
	}

	@Override
	public void start() {
		levelNumber = 1;
		buildLevel();
		setTickPeriod(8);
		jumper.setPosition(300, 650);
	}

	@Override
	public void stop() {
		setTickPeriod(0);
	}

}
