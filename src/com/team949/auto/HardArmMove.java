package com.team949.auto;

import com.team949.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class HardArmMove extends TimedCommand {

	private double moveValue;
	
	public HardArmMove(double timeout, double moveValue) {
		super(timeout);
		// Use requires() here to declare subsystem dependencies
		requires(Robot.arm);
		this.moveValue = moveValue;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.arm.move(moveValue);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Called once after timeout
	protected void end() {
		Robot.arm.move(0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
