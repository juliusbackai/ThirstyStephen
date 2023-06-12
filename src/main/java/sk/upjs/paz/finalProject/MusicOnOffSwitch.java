package sk.upjs.paz.finalProject;

import java.awt.event.MouseEvent;

import sk.upjs.jpaz2.ImageShape;
import sk.upjs.jpaz2.Pane;
import sk.upjs.jpaz2.Turtle;
import sk.upjs.jpaz2.theater.Stage;

/**
 * On/off switch with icon for controlling the music.
 */
public class MusicOnOffSwitch extends Pane {

	/**
	 * The turtle used to display the icon of this switch.
	 */
	private Turtle icon;

	/**
	 * The stage controlled by the switch.
	 */
	private Stage stage;

	/**
	 * Constructs a new music on/off switch
	 * 
	 * @param stage the stage.
	 */
	public MusicOnOffSwitch(Stage stage) {
		this.stage = stage;

		ImageShape.Builder imageBuilder = new ImageShape.Builder("/images/musicOnOff32x64.png");
		imageBuilder.setViewCount(2);

		ImageShape iconShape = imageBuilder.createShape();
		resize(iconShape.getWidth(), iconShape.getHeight());

		icon = new Turtle();
		icon.setShape(iconShape);
		add(icon);
		icon.center();
	}

	/**
	 * Updates the icon displayed in the switch in order to reflect current status
	 * of playing the music
	 */
	public void updateView() {
		if (isMusicOn()) {
			icon.setViewIndex(0);
		} else {
			icon.setViewIndex(1);
		}
	}

	/**
	 * Returns whether the music is on.
	 * 
	 * @return true, if the music is on.
	 */
	public boolean isMusicOn() {
		return stage.getBackgroundMusic().isPlaying();

	}

	/**
	 * Activates/deactivates the background music.
	 * 
	 * @param musicOn a boolean, set true to play the background music or false to
	 *                stop the background music.
	 */
	public void setMusicOn(boolean musicOn) {
		stage.setMutedMusic(!musicOn);
		updateView();
	}

	@Override
	protected void onMouseReleased(int x, int y, MouseEvent detail) {
		if (detail.getButton() == MouseEvent.BUTTON1) {
			setMusicOn(!isMusicOn());
		}
	}
}
