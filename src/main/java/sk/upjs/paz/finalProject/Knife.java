package sk.upjs.paz.finalProject;

import sk.upjs.jpaz2.ImageTurtleShape;
import sk.upjs.jpaz2.Pane;
import sk.upjs.jpaz2.Turtle;

public class Knife extends Turtle {

	/**
	 * Construct knife
	 * 
	 * @param scene pane where knife will be added
	 * @param x     starting X coordinate of a knife
	 * @param y     starting Y coordinate of a knife
	 */
	public Knife(Pane scene, int x, int y) {
		scene.add(this);
		setPosition(x, y);
		setShape(new ImageTurtleShape("/images/knife32x16.png"));
		penUp();
	}

	/**
	 * Movement of a knife.
	 * 
	 * @param jumper jumper
	 */
	public void fly(Jumper jumper) {
		setDirectionTowards(jumper.getX(), jumper.getY());
		step(0.5);
	}

	/**
	 * Checks if jumper is close to knife for interaction
	 * 
	 * @param jumper jumper
	 * 
	 * @return true, if jumper is close enough to interact with kiss
	 */
	public boolean closeToJumper(Jumper jumper) {
		if (distanceTo(jumper.getX(), jumper.getY()) < 20) {
			return true;
		}
		return false;
	}

}
