package com.team949.auto;

import com.team949.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class HardTurn extends TimedCommand {

	private double targetAngle;
	
	public HardTurn(double timeout, double targetAngle) {
		super(timeout);
		requires(Robot.driveTrain);
		
		this.targetAngle = targetAngle;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println(targetAngle);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveTrain.arcadeNoConstants(0.0, Robot.driveTrain.gyroPReturn(targetAngle));
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (super.isTimedOut()) || (Robot.driveTrain.angleWithinTolerance(targetAngle));
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrain.stop();
		DriverStation.reportError("Hard Turn Done", false);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
