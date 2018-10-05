package com.team949.commands;

import com.team949.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class DelayedShoot extends TimedCommand {
	private double start, wait, power;

	public DelayedShoot(double wait, double power, double timeout) {
		super(timeout);
		this.wait = wait;
		this.power = power;
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		start = Timer.getFPGATimestamp();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (wait < Timer.getFPGATimestamp() - start)
			Robot.hand.setIntake(power);
	}

	// Called once after timeout
	protected void end() {
		Robot.hand.setIntake(0);
		Robot.hand.close();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.hand.setIntake(0);
	}
}
