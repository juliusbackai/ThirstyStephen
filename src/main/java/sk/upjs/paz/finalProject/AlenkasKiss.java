package sk.upjs.paz.finalProject;

import sk.upjs.jpaz2.ImageTurtleShape;
import sk.upjs.jpaz2.Pane;
import sk.upjs.jpaz2.Turtle;

public class AlenkasKiss extends Turtle {

	/**
	 * Construct alenkas kiss.
	 * 
	 * @param scene pane where kiss will be added
	 * @param x     starting X coordinate of a kiss
	 * @param y     starting Y coordinate of a kiss
	 */
	public AlenkasKiss(Pane scene, int x, int y) {
		scene.add(this);
		setPosition(x, y);
		setShape(new ImageTurtleShape("/images/alenkasKiss16x16.png"));
		penUp();
	}

	/**
	 * Movement of a kiss.
	 */
	public void fly() {
		// angle from interval 80-110
		setDirection(80 + (Math.random() * 110));
		step(10);
	}

	/**
	 * Checks if jumper is close to kiss for interaction
	 * 
	 * @param jumper jumper
	 * 
	 * @return true, if jumper is close enough to interact with kiss
	 */
	public boolean closeToJumper(Jumper jumper) {
		if (distanceTo(jumper.getX(), jumper.getY()) < 20) {
			// kiss disappear out of pane
			step(500);
			return true;
		}
		return false;
	}

}
