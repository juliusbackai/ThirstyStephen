package sk.upjs.paz.finalProject;

import java.awt.event.MouseEvent;

import sk.upjs.jpaz2.ImageShape;
import sk.upjs.jpaz2.Pane;
import sk.upjs.jpaz2.TransitionEffect;
import sk.upjs.jpaz2.Turtle;
import sk.upjs.jpaz2.theater.Stage;

/**
 * A back button displayed on the level scene.
 */
public class BackButton extends Pane {

	/**
	 * stage
	 */
	private Stage stage;

	/**
	 * Construction of a backbutton.
	 * 
	 * @param stage stage
	 */
	public BackButton(Stage stage) {
		super(32, 32);
		this.stage = stage;

		setTransparentBackground(true);

		Turtle arrow = new Turtle();
		arrow.setShape(new ImageShape("/images/back_button32x32.png"));
		add(arrow);
		arrow.center();
		arrow.stamp();
		remove(arrow);
	}

	@Override
	protected boolean onCanClick(int x, int y) {
		return true;
	}

	@Override
	protected void onMouseClicked(int x, int y, MouseEvent detail) {
		if (detail.getButton() == MouseEvent.BUTTON1) {
			stage.changeScene(IntroScene.NAME, TransitionEffect.MOVE_LEFT, 1000);
		}
	}

}
