package sk.upjs.paz.finalProject;

import java.awt.Color;

import sk.upjs.jpaz2.Turtle;
import sk.upjs.jpaz2.theater.Scene;
import sk.upjs.jpaz2.theater.Stage;

/**
 * The final (winning) scene.
 */
public class WinScene extends Scene {

	/**
	 * Identifier (name) of this scene.
	 */
	public static final String NAME = "WinScene";

	/**
	 * Constructs the scene.
	 * 
	 * @param stage the stage where the scene will be shown.
	 */
	public WinScene(Stage stage) {
		super(stage);
		buildScene();
	}

	/**
	 * Drawing intro scene. Adding a title.
	 */
	public void buildScene() {
		Turtle painter = new Turtle();
		int r = (int) (Math.random() * 256);
		int g = (int) (Math.random() * 256);
		int b = (int) (Math.random() * 256);
		painter.setPenColor(new Color(r, g, b));

		add(painter);
		painter.center();
		painter.setDirection(90);
		painter.setFont(painter.getFont().deriveFont(30.0f));
		painter.printCenter("Congrats you did it!");
		remove(painter);
	}

	@Override
	public void start() {

	}

	@Override
	public void stop() {

	}
}
