package com.team949.auto;

import com.team949.Robot;
import com.team949.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class HardMoveForward extends Command {

	private double distance;
	private final DriveTrain drive = Robot.driveTrain;

	public HardMoveForward(double distance) {
		this.distance = distance;
		requires(drive);
	}

	private double initL, initR;

	// Called just before this Command runs the first time
	protected void initialize() {
		initL = drive.getLeftPosition();
		initR = drive.getRightPosition();
		Robot.driveTrain.startPID();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double l = getLeftTarget(drive.getLeftPosition() - initL), r = getRightTarget(drive.getRightPosition() - initR);
		if (!Double.isNaN(l + r))
			drive.setVelocity(getLeftTarget(l), getRightTarget(r));
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return drive.getLeftPosition() > initL + distance && drive.getRightPosition() > initR + distance;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

	private final double acc = 40 * 1, T = 0.02, dec = 40 * 0.5, v = 40 * 0.25;// in
																				// inches

	private double getLeftTarget(double x) {
		return Math.min(Math.min(acc * T + Robot.driveTrain.getLeftVelocity(), Math.sqrt(2 * dec * (distance - x))), v);
	}

	private double getRightTarget(double x) {
		return Math.min(Math.min(acc * T + Robot.driveTrain.getRightVelocity(), Math.sqrt(2 * dec * (distance - x))),
				v);
	}
}
