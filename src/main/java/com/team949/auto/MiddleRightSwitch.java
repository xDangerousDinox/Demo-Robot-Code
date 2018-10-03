package com.team949.auto;

import com.team949.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MiddleRightSwitch extends CommandGroup {

    public MiddleRightSwitch() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	requires(Robot.driveTrain);
    	requires(Robot.arm);
    	requires(Robot.hand);
    	
    	// Start FORWARDS, get to breakpoint
    	addSequential(new GyroDirectionMoveReverse(0.5, 0.0));
    	
    	addSequential(new HardTurn(2.0, 90.0));
    	addSequential(new GyroDirectionMoveReverse(0.75, 0.0));
    	addSequential(new Wait(0.2));
    	addSequential(new SwitchLaunch());
    	
//    	addSequential(new GyroDirectionMoveReverse(0.5, 45.0));
//    	addSequential(new GyroDirectionMoveReverse(0.75, 0.0));
        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
