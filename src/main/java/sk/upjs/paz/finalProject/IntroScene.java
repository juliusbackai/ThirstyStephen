package sk.upjs.paz.finalProject;

import java.awt.Color;
import java.awt.event.MouseEvent;

import sk.upjs.jpaz2.*;
import sk.upjs.jpaz2.theater.*;

/**
 * The scene with introduction.
 */
public class IntroScene extends Scene {

	/**
	 * Identifier (name) of this scene.
	 */
	public static final String NAME = "Intro";

	/**
	 * Start button.
	 */
	private Turtle startButton;

	/**
	 * Quit button.
	 */
	private Turtle quitButton;

	/**
	 * Constructs the scene.
	 * 
	 * @param stage the stage where the scene will be shown.
	 */
	public IntroScene(Stage stage) {
		super(stage);
		setBackgroundColor(Color.white);
		drawScene();
	}

	/**
	 * Drawing intro scene. Adding buttons and title.
	 */
	public void drawScene() {
		startButton = new Turtle();
		startButton.setShape(new ImageTurtleShape("/images/play_button.png"));
		add(startButton);
		startButton.setPosition(300, 400);

		quitButton = new Turtle();
		quitButton.setShape(new ImageTurtleShape("/images/quit_button.png"));
		add(quitButton);
		quitButton.setPosition(300, 550);

		Turtle painter = new Turtle();
		add(painter);
		painter.setPosition(300, 200);
		painter.setDirection(90);
		painter.setFont(painter.getFont().deriveFont(25.0f));
		painter.printCenter("Can you help Stephen find something to drink ?");
		painter.setPosition(300, 650);
		painter.printCenter("INSTRUCTIONS:");
		painter.setPosition(300, 680);
		painter.printCenter("Press arrow keys to move.");
		remove(painter);
	}

	/**
	 * Indicates if x and y are in start button borders.
	 * 
	 * @param x x coordinate of button middle
	 * @param y y coordinate of button middle
	 * 
	 * @return returns true, if x and y are in button borders
	 */
	private boolean isOverStart(int x, int y) {
		return (x > startButton.getX() - 100) && (x < startButton.getX() + 100) && (y > startButton.getY() - 50)
				&& (y < startButton.getY() + 50);
	}

	/**
	 * Indicates if x and y are in quit button borders.
	 * 
	 * @param x x coordinate of button middle
	 * @param y y coordinate of button middle
	 * 
	 * @return returns true, if x and y are in button borders
	 */
	private boolean isOverQuit(int x, int y) {
		return (x > quitButton.getX() - 100) && (x < quitButton.getX() + 100) && (y > quitButton.getY() - 50)
				&& (y < quitButton.getY() + 50);
	}
	
	@Override
	public void start() {
		getStage().getBackgroundMusic().setVolume(0);
		LevelScene.resetLvlOneCount();
	}

	@Override
	public void stop() {

	}

	@Override
	protected void onMousePressed(int x, int y, MouseEvent detail) {
		if (detail.getButton() == MouseEvent.BUTTON1) {
			if (isOverStart(x, y)) {
				getStage().changeScene(LevelScene.NAME, TransitionEffect.MOVE_RIGHT, 1000);
				return;
			}
			if (isOverQuit(x, y)) {
				System.exit(0);
			}
		}
	}

	@Override
	protected boolean onCanClick(int x, int y) {
		return isOverStart(x, y) || isOverQuit(x, y);
	}
}
