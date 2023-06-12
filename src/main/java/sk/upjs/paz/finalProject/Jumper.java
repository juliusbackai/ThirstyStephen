package sk.upjs.paz.finalProject;

import sk.upjs.jpaz2.ImageTurtleShape;
import sk.upjs.jpaz2.Pane;
import sk.upjs.jpaz2.theater.Actor;

public class Jumper extends Actor {

	/**
	 * Indicates that left arrow is pressed.
	 */
	private boolean left;

	/**
	 * Indicates that right arrow is pressed.
	 */
	private boolean right;

	/**
	 * Indicates that up arrow is released (jump starts).
	 */
	private boolean upReleased;

	/**
	 * Indicates that up arrow is pressed.
	 */
	private boolean up;

	/**
	 * Indicates that jumper is falling.
	 */
	private boolean falling;

	/**
	 * Indicates that jumper is jumping.
	 */
	private boolean jumping;

	/**
	 * Time when up arrow is pressed.
	 */
	private double jumpStartTime;

	/**
	 * Time when up arrow is released.
	 */
	private double jumpEndTime;

	/**
	 * Size of step to left or right.
	 */
	private int step;

	/**
	 * Size of step during falling.
	 */
	private double fallStep = 1;

	/**
	 * Size of step during jumping.
	 */
	private double jumpStep = 1;

	/**
	 * Jumper position right before jump starts.
	 */
	private int positionBeforeJump;

	/**
	 * Construct a jumper.
	 * 
	 * @param level a pane where jumper is added
	 */
	public Jumper(Pane level) {
		level.add(this);
		setShape(new ImageTurtleShape("/images/Jumper32x32.png"));
		penUp();
	}

	/**
	 * Set an image for jumper while moving.
	 */
	public void setImage() {
		if (left) {
			setShape(new ImageTurtleShape("/images/Jumper32x32_lookLeft.png"));
		} else if (right) {
			setShape(new ImageTurtleShape("/images/Jumper32x32_lookRight.png"));
		} else if (up && !falling) {
			setShape(new ImageTurtleShape("/images/Jumper32x32_jump.png"));
		} else {
			setShape(new ImageTurtleShape("/images/Jumper32x32.png"));
		}

	}

	// **************************** LEFT ***********************************//
	/**
	 * Set left.
	 * 
	 * @param left left arrow
	 */
	public void setLeft(boolean left) {
		this.left = left;
	}

	/**
	 * Step to the left.
	 */
	public void goLeft() {
		setPosition(getX() - step, getY());
	}

	/**
	 * Indicates if left movement is active.
	 * 
	 * @return true, if a left arrow is pressed
	 */
	public boolean isLeft() {
		return left;
	}

	// **************************** RIGHT ***********************************//
	/**
	 * Set right.
	 * 
	 * @param right right arrow
	 */
	public void setRight(boolean right) {
		this.right = right;
	}

	/**
	 * Step to the right.
	 */
	public void goRight() {
		setPosition(getX() + step, getY());
	}

	/**
	 * Indicates if right movement is active.
	 * 
	 * @return true, if a right arrow is pressed
	 */
	public boolean isRight() {
		return right;
	}

	// **************************** JUMPING ***********************************//
	public void setUP(boolean up) {
		this.up = up;
	}

	/**
	 * After up arrow is released jump starts. If jumper is falling no other jump
	 * can starts.
	 * 
	 * @param upReleased is true, if an up arrow is released
	 */
	public void jumpStart(boolean upReleased) {
		if (falling) {
			return;
		}
		this.upReleased = upReleased;
	}

	/**
	 * Set the start time of a jump.
	 * 
	 * @param startTime time when up arrow is pressed
	 */
	public void setJumpStartTime(double startTime) {
		this.jumpStartTime = startTime;
	}

	/**
	 * Set the end time of a jump.
	 * 
	 * @param endTime time when up arrow is released
	 */
	public void setJumpEndTime(double endTime) {
		this.jumpEndTime = endTime;
	}

	/**
	 * Jump duration or strength calculation.
	 * 
	 * @return jumpDuration size of a jump
	 */
	public int jumpDuration() {
		int jumpDuration = (int) ((jumpEndTime - jumpStartTime) / 2);
		if (jumpDuration >= 300) {
			jumpDuration = 300;
		}
		return jumpDuration;
	}

	/**
	 * Set the initial size of step during jumping.
	 * 
	 * @param jumpStep size of step during jumping.
	 */
	public void setJumpStep(int jumpStep) {
		this.jumpStep = jumpStep;
	}

	/**
	 * Jump movement. Maximum of jump size is jumpDuration/
	 */
	public void jump() {
		setPosition(getX(), getY() - jumpStep);
		if (getY() < positionBeforeJump - jumpDuration()) {
			jumping = false;
		}
	}

	/**
	 * Set jump.
	 * 
	 * @param jumping jumping
	 */
	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	/**
	 * Indicates if jumper is jumping.
	 * 
	 * @return true, if jumper is jumping.
	 */
	public boolean isJumping() {
		return jumping;
	}

	// **************************** FALLING ***********************************//
	/**
	 * Set falling.
	 * 
	 * @param falling falling
	 */
	public void setFall(boolean falling) {
		this.falling = falling;
	}

	/**
	 * Very simple simulation of gravitation during falling. Each step is larger
	 * than step before. Max step is 16.
	 */
	public void fallingGravity() {
		fallStep += 0.6;
		if (fallStep > 16) {
			fallStep = 16;
		}
	}

	/**
	 * Very simple simulation of gravitation during jumping. Each step is smaller
	 * then step before. Minimum step size is 1.
	 */
	public void jumpingGravity() {
		jumpStep -= 0.5;
		if (jumpStep < 1) {
			setFallStep(1);
			jumping = false;
		}
	}

	/**
	 * Step down (falling).
	 */
	public void fall() {
		setPosition(getX(), getY() + fallStep);
	}

	/**
	 * Indicates if jumper is falling.
	 * 
	 * @return true if jumper is falling
	 */
	public boolean isFalling() {
		return falling;
	}

	/**
	 * Set the initial size of step during falling.
	 * 
	 * @param fallStep size of step during falling.
	 */
	public void setFallStep(int fallStep) {
		this.fallStep = fallStep;
	}

	/**
	 * Jumper movement.
	 */
	public void move() {
		// during falling or jumping is step bigger due to larger jumps
		if (falling || jumping) {
			step = 5;
		} else {
			step = 3;
		}
		// move to right
		if (right) {
			goRight();
		}
		// move to left
		if (left) {
			goLeft();
		}
		// jump
		if (upReleased && !falling) {
			positionBeforeJump = (int) getY();
			jumping = true;
			// reset value
			upReleased = false;
		}
		// jumping
		if (jumping) {
			jumpingGravity();
			jump();
		}
		// falling
		if (falling) {
			fallingGravity();
			fall();
		}
		setImage();
	}

}
