package com.team949.auto;

import com.team949.Robot;
import com.team949.commands.DelayedShoot;
import com.team949.commands.HandLower;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SwitchLaunch extends CommandGroup {

    public SwitchLaunch() {
        requires(Robot.hand);
        
        addParallel(new HardWristLower(0.4));
        addSequential(new DelayedShoot(0.0, -1.0, 0.4));
    }
}
