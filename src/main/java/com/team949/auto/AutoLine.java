package com.team949.auto;

import com.team949.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *	Align this robot backwards!
 */
public class AutoLine extends GyroDirectionMoveReverse {

	private static final double TIMEOUT = 1.2;
	private static final double DIRECTION = 0.0; // Robot.driveTrain.getGyroAngle()  ??
    public AutoLine() {
        super(TIMEOUT, DIRECTION);
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
    }
}
