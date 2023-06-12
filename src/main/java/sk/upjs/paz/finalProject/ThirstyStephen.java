package sk.upjs.paz.finalProject;

import sk.upjs.jpaz2.AudioClip;
import sk.upjs.jpaz2.ImageShape;
import sk.upjs.jpaz2.theater.*;

/**
 * The demo stage.
 */
public class ThirstyStephen extends Stage {

	/**
	 * Constructs the stage.
	 */
	public ThirstyStephen() {
		super("Thirsty Stephen", 600, 800, new ImageShape("images", "logo32x32.png"));
	}

	@Override
	protected void initialize() {
		// initialize stage and add scenes
		AudioClip titanic = new AudioClip("/audio/titanic.wav", true);
		setBackgroundMusic(titanic);
		titanic.setVolume(0);
		
		addScene(IntroScene.NAME, new IntroScene(this));
		addScene(LevelScene.NAME, new LevelScene(this));
		addScene(TransitionScene.NAME, new TransitionScene(this));
		addScene(WinScene.NAME, new WinScene(this));
	}

	public static void main(String[] args) {
		// create demo stage and start a show
		ThirstyStephen show = new ThirstyStephen();
		show.run(IntroScene.NAME);
	}
}