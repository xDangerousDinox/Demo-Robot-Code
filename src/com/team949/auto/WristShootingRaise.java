package com.team949.auto;

import com.team949.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class WristShootingRaise extends TimedCommand {

    private static final double RAISE_RATE = -0.3;
	public WristShootingRaise(double timeout) {
        super(timeout);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.hand);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.hand.setIntake(0.3);
    	Robot.hand.setWrist(RAISE_RATE);
    }

    // Called once after timeout
    protected void end() {
    	Robot.hand.setIntake(0);
    	Robot.hand.setWrist(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
