package com.team949.auto;

import com.team949.Robot;
import com.team949.commands.ArmLower;
import com.team949.commands.ArmRaise;
import com.team949.commands.HandRaise;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftSideLeftScale extends CommandGroup {

    public LeftSideLeftScale() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	requires(Robot.driveTrain);
    	requires(Robot.arm);
    	requires(Robot.hand);
    	
    	// Start backwards, get to breakpoint
    	addSequential(new GyroDirectionMoveReverse(2.3, 0.0));
    	addSequential(new Wait(0.75));
    	
//    	addSequential(new GyroDirectionMoveReverse(3.0, 0.0)); // Drive in one go
    	
    	// Scale Align
    	addSequential(new HardTurn(2.0, 30.0));
    	addSequential(new GyroDirectionMoveReverse(0.60, 30.0)); // 0.7
    	
    	// Place Cube
    	addSequential(new HandRaise());
    	addSequential(new ArmRaise());
    	addSequential(new AutoWristScalePlace());
    	addSequential(new ArmLower());
    	
    	
        // To run multiple commands at the same time,
        // use addParallel(
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
