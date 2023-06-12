package sk.upjs.paz.finalProject;

import java.awt.event.KeyEvent;

import sk.upjs.jpaz2.ImageShape;
import sk.upjs.jpaz2.TransitionEffect;
import sk.upjs.jpaz2.Turtle;
import sk.upjs.jpaz2.theater.Scene;
import sk.upjs.jpaz2.theater.Stage;

/**
 * Transition scene when jumper jumps out of border or gets hit by knife.
 */
public class TransitionScene extends Scene {

	/**
	 * Name of scene.
	 */
	public static final String NAME = "Transition";

	/**
	 * Construction of scene.
	 */
	public TransitionScene(Stage stage) {
		super(stage);
	}

	/**
	 * Showing pictures for player to calm down.
	 */
	public void drawScene() {
		Turtle painter = new Turtle();
		painter.setShape(new ImageShape("images/transition", constructName()));
		add(painter);
		painter.center();
		painter.setTransparency(0);
		painter.stamp();
		remove(painter);
	}

	/**
	 * Construction of Stefans photo name. It randomly chooses between 4 photos.
	 */
	public String constructName() {
		int number = (int) (1 + Math.random() * 4);
		StringBuilder sb = new StringBuilder();
		sb.append("stefan");
		sb.append(number);
		sb.append(".png");
		return sb.toString();
	}

	@Override
	protected void onKeyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_LEFT
				|| e.getKeyCode() == KeyEvent.VK_RIGHT) {
			getStage().changeScene(LevelScene.NAME, TransitionEffect.FADE_OUT_FADE_IN, 1000);
		}
	}

	@Override
	public void start() {
		constructName();
		drawScene();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

}
