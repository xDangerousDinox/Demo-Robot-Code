package com.team949.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.team949.Robot;
import com.team949.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {
	private double offset = 135. / 131;
	private double gearReduction = 30. / 54 * 1 / 2;
	// Initialize your subsystem here
	private WPI_TalonSRX armMotor;
	private Servo servo = new Servo(0);
	public static final double startingAngle = -50;

	public double servoStart;

	public Arm() {
		armMotor = new WPI_TalonSRX(RobotMap.armMotor);

		armMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		armMotor.setSensorPhase(false);
		armMotor.setSelectedSensorPosition(0, 0, 0);
		servoStart = servoAngle();
	}

	public double servoAngle() {
		return servo.getAngle();
	}

	public void servoOut(double deg) {
		servo.setAngle(deg);
	}

	public void initDefaultCommand() {
		setDefaultCommand(null);
	}

	public double getEncoderPosition() {
		return armMotor.getSelectedSensorPosition(0);
	}

	public double getEncoderVelocity() {
		return armMotor.getSelectedSensorVelocity(0);
	}

	/**
	 * 
	 * @return angle of arm, 0 is parallel to ground, in radians
	 */
	public double getAngle() {
		return Math.toRadians(getEncoderPosition() * gearReduction * offset / 4096 * 360 + startingAngle);
	}

	/**
	 * Default .set() method
	 * 
	 * @param moveValue
	 *            -1.0 to 1.0
	 */
	public void move(double moveValue) {
		armMotor.set(-moveValue);
	}

	public boolean raised() {
		return getAngle() > Math.toRadians(175); // 130 deg elevation * 4096
													// units/rev
	}

	public boolean lowered() {
		return getAngle() < Math.toRadians(5);
	}

}
