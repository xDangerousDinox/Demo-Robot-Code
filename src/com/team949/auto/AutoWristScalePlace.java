package com.team949.auto;

import com.team949.Robot;
import com.team949.commands.DelayedShoot;
import com.team949.commands.HandIntake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoWristScalePlace extends CommandGroup {

    public AutoWristScalePlace() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	requires(Robot.hand);
    	
    	addParallel(new WristShootingRaise(0.7));
    	addSequential(new DelayedShoot(0.5, -0.5, 2.0)); // Use Heesoo and David's thing.
        // To run multiple commands at the same time,
        // use addParallel()S
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
