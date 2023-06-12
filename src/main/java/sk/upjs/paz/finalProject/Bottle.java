package sk.upjs.paz.finalProject;

import sk.upjs.jpaz2.ImageTurtleShape;
import sk.upjs.jpaz2.Pane;
import sk.upjs.jpaz2.Turtle;

public class Bottle extends Turtle {

	/**
	 * Construct a bottle.
	 */
	public Bottle(Pane scene) {
		scene.add(this);
		setPosition(50, 180);
		setShape(new ImageTurtleShape("/images/bottle32x64.png"));
	}

	/**
	 * Indicates that jumper is close enough to bottle. Player wins game ends.
	 */
	public boolean closeToJumper(Jumper jumper) {
		if (distanceTo(jumper.getX(), jumper.getY()) < 40) {
			return true;
		}
		return false;
	}
}
