package com.team949;

public class Constants {

	// Stored in inches
	public final static double wheelbase = 23.5;
	public final static int wheel_diameter = 6;
	
	public static final double hand_intakeSpeed = 1.0;
	public static final double hand_shootSpeed = 1.0;
	
	// TODO: Why is not static?
	public double in2cm(double in)
	{
		return in * 2.54;
	}
	// TODO: Why is not static?
	public double cm2in(double cm)
	{
		return cm / 2.54;
	}
	
	/**
	 * Arcade Drive taken directly from DifferentialDrive, created by WPI
	 * @param moveSpeed Move magnitude
	 * @param rotateValue Rotate magnitude
	 * @return Left and Right inputs
	 */
	public static double[] arcadeToTank(double moveSpeed, double rotateValue) 
    {
    	double leftMotorOutput;
        double rightMotorOutput;

        double maxInput = Math.copySign(Math.max(Math.abs(moveSpeed), Math.abs(rotateValue)), moveSpeed);
        
        if (moveSpeed >= 0.0) {
        	// First quadrant, else second quadrant
        	if (rotateValue >= 0.0) {
        		leftMotorOutput = maxInput;
        		rightMotorOutput = moveSpeed - rotateValue;
        	} else {
        		leftMotorOutput = moveSpeed + rotateValue;
        		rightMotorOutput = maxInput;
        	}
        } else {
        	// Third quadrant, else fourth quadrant
        	if (rotateValue >= 0.0) {
        		leftMotorOutput = moveSpeed + rotateValue;
        		rightMotorOutput = maxInput;
        	} else {
        		leftMotorOutput = maxInput;
        		rightMotorOutput = moveSpeed - rotateValue;
        	}
        }
        return new double[] { leftMotorOutput, rightMotorOutput };
    }
}
