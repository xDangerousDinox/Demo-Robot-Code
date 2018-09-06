package com.team949.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.team949.Robot;
import com.team949.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Hand extends Subsystem {
	private double gearReduction = 15. / 30;
	private WPI_TalonSRX rightPickupMotor;
	private WPI_TalonSRX leftPickupMotor;
	private WPI_TalonSRX wristMotor;

	private Compressor compressor;
	private DoubleSolenoid handRotator;

	public static final double startingAngle = -Arm.startingAngle + 93;//

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
	}

	public Hand() {
		this.rightPickupMotor = new WPI_TalonSRX(RobotMap.rightPickupMotor);
		this.leftPickupMotor = new WPI_TalonSRX(RobotMap.leftPickupMotor);
		this.wristMotor = new WPI_TalonSRX(RobotMap.wristMotor);

		compressor = new Compressor(RobotMap.pcmID);
		compressor.start();
		handRotator = new DoubleSolenoid(RobotMap.pcmID, RobotMap.handRotatorSolenoidChannelIn,
				RobotMap.handRotatorSolenoidChannelOut);

		rightPickupMotor.setInverted(false);
		leftPickupMotor.setInverted(false);
		compressor.setClosedLoopControl(true);

		wristMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		wristMotor.setSensorPhase(true);
		wristMotor.setSelectedSensorPosition(0, 0, 0);
	}
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public double getEncoderPosition() {
		return wristMotor.getSelectedSensorPosition(0);
	}

	public double getEncoderVelocity() {
		return wristMotor.getSelectedSensorVelocity(0);
	}

	public void open() {
		handRotator.set(DoubleSolenoid.Value.kReverse);
	}

	public void close() {
		handRotator.set(DoubleSolenoid.Value.kForward);
	}

	public void die() {
		handRotator.set(DoubleSolenoid.Value.kOff);
	}

	/**
	 * Set both motors to 0.0
	 */
	public void stop() {
		setIntakeSeparate(0, 0);
	}

	/**
	 * Calls the .set() method with wheels rotating same direction.
	 * 
	 * @param rate
	 *            the double that goes in someMotor.set(rate); as parameter.
	 */
	public void setIntake(double rate) {
		setIntakeSeparate(rate, -rate);
	}

	public void setIntakeSeparate(double leftRate, double rightRate) {
		this.rightPickupMotor.set(leftRate);
		this.leftPickupMotor.set(rightRate);
	}

	/**
	 * Calls the .set() method with wristMotor.
	 * 
	 * @param rate
	 *            the double that goes in someMotor.set(rate); as parameter.
	 */
	public void setWrist(double rate) {
		this.wristMotor.set(rate);
	}

	/**
	 * 
	 * @return hand angle relative to ground in radians
	 */
	public double getAngle() {
		return Robot.arm.getAngle() + Math.toRadians(getEncoderPosition() * 360 / 4096 * gearReduction + startingAngle);
	}

}
