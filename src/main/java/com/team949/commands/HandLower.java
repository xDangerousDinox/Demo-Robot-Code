package com.team949.commands;

import com.team949.Robot;
import com.team949.subsystems.Arm;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HandLower extends Command {

	// TODO: Tune
	private final double kHandLowerLinear = 0.4;
	private final double kHandLowerDamp = 0.0;
	private final double switchAngle = Math.toRadians(75);

	public HandLower() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.hand);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double angle = Robot.hand.getAngle();
		double out = 0;
		if (angle > Math.toRadians(80)) {
			Robot.arm.move(0);
			out = -kHandLowerLinear;
		}
		if (angle <= Math.toRadians(80)) {
			Robot.arm.move(-0.08);
			out = Math.cos(angle) * kHandLowerDamp;
			if (angle < Math.toRadians(-10)) {
				out = Math.cos(Robot.hand.getAngle());
			}
		}

		Robot.hand.setWrist(out);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.hand.getAngle() < switchAngle;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.arm.move(0);
		Robot.hand.setWrist(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.arm.move(0);
		Robot.hand.setWrist(0);
	}
}
