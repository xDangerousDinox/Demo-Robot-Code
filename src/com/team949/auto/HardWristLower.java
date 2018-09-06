package com.team949.auto;

import com.team949.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class HardWristLower extends TimedCommand {

	private static final double WRIST_POWER = -0.4;
	
	public HardWristLower(double timeout) {
		super(timeout);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void execute() 
	{
		Robot.hand.setWrist(WRIST_POWER);
	}

	@Override
	protected void end() 
	{
		Robot.hand.setWrist(0.0);
	}
}
