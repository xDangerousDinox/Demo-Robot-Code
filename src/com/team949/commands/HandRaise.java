package com.team949.commands;

import com.team949.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class HandRaise extends Command {

	public static final double kHandUpMax = 0.85;
	public static final double kHandUpStall = 0.00;
	public static double startTime;
	
    public HandRaise() {
    	requires(Robot.hand);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double angle = Robot.hand.getAngle();
		double out = 0;
		// if (angle < 0)
		// out = Math.cos(angle) * kArmRaiseMax;
		// else if (angle > 0 && angle < Math.toRadians(60))
		// out = 0;
		// else if (angle > Math.toRadians(80))
		// out = kArmUpStall;
		out = kHandUpMax * Math.cos(Robot.hand.getAngle());
		if (Robot.hand.getAngle() > Math.toRadians(45)) {
			out *= 0.6;
			out += 0.1;
		}
		Robot.hand.setWrist(out);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.hand.getAngle() > Math.toRadians(89);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.hand.setWrist(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
