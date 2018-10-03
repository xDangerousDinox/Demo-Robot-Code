package com.team949.commands;

import com.team949.Robot;
import com.team949.subsystems.Arm;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmLower extends Command {

	// TODO: Tune

	private final double kArmLowerMax = 0.05;
	private final double kArmLowStall = 0.00;

	public ArmLower() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.arm);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double angle = Robot.arm.getAngle();
		double out = 0;
		if (angle > Math.toRadians(5 + Arm.startingAngle)) {
			out = Math.cos(angle) * kArmLowerMax;
		}
		if (angle < Math.toRadians(-30))
			out *= 3;
		else if (angle > Math.toRadians(80))
			out -= 0.2;
		else
			out -= 0.1;
		Robot.arm.move(out);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.arm.getAngle() < Math.toRadians(5 + Arm.startingAngle);
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.arm.servoOut(90);
		Robot.arm.move(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.arm.servoOut(130);
	}
}
