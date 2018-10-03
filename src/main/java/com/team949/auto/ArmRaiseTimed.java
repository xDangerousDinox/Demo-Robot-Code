package com.team949.auto;

import com.team949.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class ArmRaiseTimed extends TimedCommand {

	// TODO: Tune
	private final double kArmRaiseMax = 0.5;
	private final double kArmUpStall = 0.05;
	private double startTime;

	public ArmRaiseTimed(double timeout) {
		super(timeout);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.arm);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.arm.servoOut(179);
		startTime = Timer.getFPGATimestamp();
	}

	// Called repeatedly when thish Command is scheduled to run
	protected void execute() {
		double angle = Robot.arm.getAngle();
		double out = 0;
		// if (angle < 0)
		// out = Math.cos(angle) * kArmRaiseMax;
		// else if (angle > 0 && angle < Math.toRadians(60))
		// out = 0;
		// else if (angle > Math.toRadians(80))
		// out = kArmUpStall;
		out = Math.cos(Robot.arm.getAngle());
		if (Robot.arm.getAngle() > Math.toRadians(45)) {
			out *= 0.6;
			out += 0.1;
		}
		if (Robot.arm.getAngle() > Math.toRadians(85))
			out = kArmUpStall;
		if (Timer.getFPGATimestamp() < startTime + 0.5)
			out = 0;
		Robot.arm.move(out);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return DriverStation.getInstance().isAutonomous() && Robot.arm.getAngle() > Math.toRadians(84);
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.arm.move(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
