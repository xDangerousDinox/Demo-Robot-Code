package com.team949.auto;

import com.team949.Robot;
import com.team949.commands.ArmLower;
import com.team949.commands.ArmRaise;
import com.team949.commands.HandIntake;
import com.team949.commands.HandRaise;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestAutoCubePlace extends CommandGroup {

    public TestAutoCubePlace() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	requires(Robot.arm);
    	requires(Robot.hand);
    	
    	addSequential(new HandRaise());
    	addSequential(new ArmRaise());
    	addSequential(new AutoWristScalePlace());
    	addSequential(new ArmLower());
    	
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
