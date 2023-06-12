package sk.upjs.paz.finalProject;

import java.util.ArrayList;
import java.util.List;

import sk.upjs.jpaz2.ImageTurtleShape;
import sk.upjs.jpaz2.Pane;
import sk.upjs.jpaz2.Turtle;

public class Vilko extends Turtle {

	/**
	 * X position of Vilkos middle.
	 */
	private int x;

	/**
	 * Y position of Vilkos middle.
	 */
	private int y;

	/**
	 * Elapsed time from last knife.
	 */
	private double lastKnifeTime = 0;

	/**
	 * List of all Vilkos knives.
	 */
	private static List<Knife> listOfKnives = new ArrayList<>();

	/**
	 * Construct Vilko.
	 * 
	 * @param scene pane where vilko will be added
	 * @param x     X position of vilkos middle
	 * @param y     Y position of vilkos middle
	 */
	public Vilko(Pane scene, int x, int y) {
		this.x = x;
		this.y = y;
		scene.add(this);
		setPosition(x, y);
		setShape(new ImageTurtleShape("/images/vilko32x32.png"));
	}

	/**
	 * Vilko send knife every timeBetweenKnives
	 * 
	 * @param scene             pane where kisses will be added
	 * @param timeBetweenKnives time between two kisses
	 */
	public void sendKnives(Pane scene, int timeBetweenKnives) {
		double currentTime = System.currentTimeMillis();
		if (lastKnifeTime + timeBetweenKnives > currentTime) {
			return;
		}
		Knife knife = new Knife(scene, x, y);
		listOfKnives.add(knife);
		lastKnifeTime = currentTime;
	}

	/**
	 * List of Vilkos knives.
	 * 
	 * @return list of knives on scene
	 */
	public static List<Knife> getListOfKnives() {
		return listOfKnives;
	}

	/**
	 * Movement of a knife. If knife is close enough to jumper it returns jumper to
	 * level 1.
	 * 
	 * @param jumper jumper
	 */
	public void knifeFly(Jumper jumper) {
		for (Knife knife : listOfKnives) {
			knife.fly(jumper);
			if (knife.closeToJumper(jumper)) {
				LevelScene.setLevelOne(true);
			}
		}
	}
}
