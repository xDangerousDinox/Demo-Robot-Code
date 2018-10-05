package com.team949.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.team949.RobotMap;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

	private WPI_TalonSRX climberMotor1;

	public void initDefaultCommand() {

	}

	public Climber() {
		climberMotor1 = new WPI_TalonSRX(RobotMap.climberMotor1);

		// IMPORTANT TO RUN ONE BACKWARDS AND ONE FORWARDS (GEARBOX DESIGN)
		climberMotor1.setInverted(true);
	}

	/**
	 * Calls the .set() method on both pickup motors
	 * 
	 * @param rate
	 *            the double that goes in someMotor.set(rate); as parameter.
	 */
	public void setMotors(double rate) {
		climberMotor1.set(rate);
	}

}
