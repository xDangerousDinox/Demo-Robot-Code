package com.team949.auto;

import com.team949.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class HardWristRaise extends TimedCommand {

	private static final double HAND_MOVE_SPEED = 0.40; //.20 too weak to stow
	
    public HardWristRaise(double timeout) {
        super(timeout);
        // Use requires() here to declare subsystem dependencies
        requires(Robot.hand);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.hand.setWrist(HAND_MOVE_SPEED);
    }

    // Called once after timeout
    protected void end() {
    	Robot.hand.setWrist(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
