package com.team949.auto;

import com.team949.Robot;
import com.team949.commands.ArmLower;
import com.team949.commands.ArmRaise;
import com.team949.commands.HandRaise;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightSideLeftScale extends CommandGroup {

    public RightSideLeftScale() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	requires(Robot.driveTrain);
    	requires(Robot.arm);
    	requires(Robot.hand);
    	
    	// Start backwards, get to breakpoint
    	addSequential(new GyroDirectionMoveReverse(2.25, 0.0));
    	addSequential(new HandRaise());
    	addSequential(new Wait(0.5));
    	
    	addSequential(new HardTurn(1.5, -90.0));
    	addSequential(new GyroDirectionMoveReverse(2.3, -90.0));
    	addSequential(new HandRaise());
    	addSequential(new Wait(0.5));
 
    	addSequential(new HardTurn(1.5, 30.0));
    	addSequential(new GyroDirectionMoveReverse(0.6, 30.0));
//    	TODO: New cube logic
    	// Place the cube
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
