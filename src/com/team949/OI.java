package com.team949;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

//i am the dude man!
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public final Joystick driveStick;
	public final Joystick operatorStick;

	private Button driveTrigger;
	private Button driveSideButton;

	public OI() {
		this.driveStick = new Joystick(0);
		this.operatorStick = new Joystick(1);

		this.driveTrigger = new JoystickButton(driveStick, 1);
		this.driveSideButton = new JoystickButton(driveStick, 2);

	}

	// Drive Stick
	public double getDriveX() {
		return this.driveStick.getX();
	}

	public double getDriveY() {
		return this.driveStick.getY();
	}

	public double getDriveZ() {
		return this.driveStick.getZ();
	}

	public double getDriveThrottle() {
		return this.driveStick.getThrottle();
	}

	public boolean isDriveButtonDown(int buttonNumber) {
		return this.driveStick.getRawButton(buttonNumber);
	}

	// Operator Stick
	public double getOperatorX() {
		return this.operatorStick.getX();
	}

	public double getOperatorY() {
		return this.operatorStick.getY();
	}

	public double getOperatorZ() {
		return this.operatorStick.getZ();
	}

	public double getOperatorThrottle() {
		return this.operatorStick.getThrottle();
	}

	public boolean isOperatorButtonDown(int buttonNumber) {
		return this.operatorStick.getRawButton(buttonNumber);
	}

	public int getOperatorPOV() {
		return this.operatorStick.getPOV();
	}

	//// CREATING BUTTONS hahahah
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
