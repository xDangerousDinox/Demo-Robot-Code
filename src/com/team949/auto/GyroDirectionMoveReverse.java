package com.team949.auto;

import com.team949.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class GyroDirectionMoveReverse extends TimedCommand {

	private static final double MOVE_SPEED_PERCENT = -0.75; // TODO: Make it faster!
	private double direction;
	
    public GyroDirectionMoveReverse(double timeout, double direction) {
        super(timeout);
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
        
        this.direction = direction;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.arcade(MOVE_SPEED_PERCENT, Robot.driveTrain.gyroPReturn(direction));
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.arcadeNoConstants(MOVE_SPEED_PERCENT, Robot.driveTrain.gyroPReturn(direction));
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
