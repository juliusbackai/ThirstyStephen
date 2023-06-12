package sk.upjs.paz.finalProject;

import java.util.ArrayList;
import java.util.List;

import sk.upjs.jpaz2.ImageTurtleShape;
import sk.upjs.jpaz2.Pane;
import sk.upjs.jpaz2.Turtle;

public class Alenka extends Turtle {

	/**
	 * X position of Alenkas middle.
	 */
	private int x;

	/**
	 * Y position of Alenkas middle.
	 */
	private int y;

	/**
	 * Elapsed time from last kiss.
	 */
	private double lastKissTime = 0;

	/**
	 * List of all Alenkas kisses.
	 */
	private static List<AlenkasKiss> listOfKisses = new ArrayList<>();

	/**
	 * Construct alenka.
	 * 
	 * @param scene pane where alenka will be added
	 * @param x     X position of alenkas middle
	 * @param y     Y position of alenkas middle
	 */
	public Alenka(Pane scene, int x, int y) {
		this.x = x;
		this.y = y;
		scene.add(this);
		setPosition(x, y);
		setShape(new ImageTurtleShape("/images/alenka32x64.png"));
	}

	/**
	 * Alenka send kiss every timeBetweenKisses
	 * 
	 * @param scene             pane where kisses will be added
	 * @param timeBetweenKisses time between two kisses
	 */
	public void sendKisses(Pane scene, int timeBetweenKisses) {
		double currentTime = System.currentTimeMillis();
		if (lastKissTime + timeBetweenKisses > currentTime) {
			return;
		}
		AlenkasKiss alenkasKiss = new AlenkasKiss(scene, x, y);
		listOfKisses.add(alenkasKiss);
		lastKissTime = currentTime;
	}

	public static List<AlenkasKiss> getListOfKisses() {
		return listOfKisses;
	}

	/**
	 * Movement of a kiss. If kiss is close enough to jumper it steps back and
	 * starts falling.
	 * 
	 * @param jumper jumper
	 */
	public void kissFly(Jumper jumper) {
		for (AlenkasKiss kiss : listOfKisses) {
			kiss.fly();
			if (kiss.closeToJumper(jumper)) {
				jumper.setPosition(jumper.getX() + 20, jumper.getY() + 20);
				jumper.setJumping(false);
			}
		}
	}
}
