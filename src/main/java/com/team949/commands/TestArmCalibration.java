package com.team949.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.team949.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TestArmCalibration extends Command {

	private File csvArm;
	private OutputStream os;
	private int timeIterations;

	public TestArmCalibration() {
		csvArm = new File(""); // TODO: Probably not the correct file location
								// on RIO
		csvArm.setWritable(true);
		try {
			os = new FileOutputStream("");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// Use requires() here to declare subsystem dependencies
		requires(Robot.arm);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double offset = Timer.getFPGATimestamp();
		while (true) {
			double time = Timer.getFPGATimestamp() - offset;
			Robot.arm.move(time / 100.);
			writeToCSV();
		}
	}

	private void writeToCSV() {
		// TODO: Temporary
		System.out.println(Robot.arm.getEncoderPosition());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
