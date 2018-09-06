package com.team949.commands;

import com.team949.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class Test extends TimedCommand {
	private final double power;

	public Test(double time, double power) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		super(time);
		requires(Robot.driveTrain);
		this.power = power;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveTrain.tank(power, power);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrain.tank(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {

		Robot.driveTrain.tank(0, 0);
	}

}
