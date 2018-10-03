package com.team949.auto;

import com.team949.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class HardHandRelease extends TimedCommand {

    public HardHandRelease(double timeout) {
        super(timeout);
        // Use requires() here to declare subsystem dependencies
        requires(Robot.hand);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.hand.setIntake(-0.7);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Called once after timeout
    protected void end() {
    	Robot.hand.setIntake(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
