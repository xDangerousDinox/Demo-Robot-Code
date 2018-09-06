package com.team949.commands;

import com.team949.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class ShitForward extends TimedCommand {

	public ShitForward(double timeout) {
		super(timeout);
		// Use requires() here to declare subsystem dependencies
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveTrain.arcade(0.5, 0);
	}

	// Called once after timeout
	protected void end() {
		Robot.driveTrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
