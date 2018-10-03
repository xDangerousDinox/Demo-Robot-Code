package com.team949.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.team949.Constants;
import com.team949.Robot;
import com.team949.RobotMap;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 *
 */
public class DriveTrain extends Subsystem {

	private DifferentialDrive drive;

	private final ADXRS450_Gyro g;

	private SpeedControllerGroup r;
	private SpeedControllerGroup l;

	public WPI_TalonSRX r0, r1, l0, l1;

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.

	}

	public DriveTrain() {
		this.g = new ADXRS450_Gyro();
		gyroCalibrate();

		this.r0 = new WPI_TalonSRX(RobotMap.rightDriveMotor1);
		this.r1 = new WPI_TalonSRX(RobotMap.rightDriveMotor2);
		this.l0 = new WPI_TalonSRX(RobotMap.leftDriveMotor1);
		this.l1 = new WPI_TalonSRX(RobotMap.leftDriveMotor2);

		// Set slaves r1 and l1 to follow masters r0 and l0

		setUpEncoders();
		// //683 u/100ms, 745u/ 100ms 24% output
		// //0.358724, 0.328870
		// l0.config_kF(0, 0.328870, 0);
		// r0.config_kF(0, 0.358724, 0);
		// l0.config_kP(0, 10./4096, 0);
		// r0.config_kP(0, 10./4096, 0);
		// l0.config_kD(0, 100000./4096, 0);
		// r0.config_kD(0, 100000./4096, 0);

		this.r = new SpeedControllerGroup(r0, r1);
		this.l = new SpeedControllerGroup(l0, l1);

		this.r.setInverted(false);
		this.l.setInverted(false);

		this.drive = new DifferentialDrive(l, r);

		this.drive.setSafetyEnabled(false);
	}

	private void setUpEncoders() {
		r0.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		r0.setSensorPhase(false);
		r0.setSelectedSensorPosition(0, 0, 0);
		l0.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		l0.setSensorPhase(true);
		l0.setSelectedSensorPosition(0, 0, 0);
	}

	// Drive Methods
	public void arcade(double moveValue, double rotateValue) {
		tank(moveValue - rotateValue, moveValue + rotateValue);
	}

	// kd not implemented
	/* TODO: measure Kv */
	private final double kp = 10. / 40, ki = 0.3 / 50, kd = 0. / 40, kv = 1. / 30;
	private double accL = 0, accR = 0;// accumulation
	private double prevT = 0, prevErrL = 0, prevErrR = 0;

	public void startPID() {
		accL = 0.05;
		accR = 0.05;
		prevT = Timer.getFPGATimestamp();
	}

	public void setVelocity(double velL, double velR) {
		double errL = velL - getLeftVelocity();
		accL += errL * ki;
		double errR = velR - getRightVelocity();
		accR += errR * ki;
		double time = Timer.getFPGATimestamp();
		tank(errL * kp + velL * kv + accL + (prevErrL - errL) / (time - prevT) * kd,
				errR * kp + velR * kv + accR + (prevErrR - errR) / (time - prevT) * kd);
		prevT = time;
		prevErrL = errL;
		prevErrR = errR;
	}

	// Previously right offset was 0.04
	public void tank(double leftMoveValue, double rightMoveValue) {
		// this.drive.tankDrive(leftMoveValue, rightMoveValue * 0.97);
		this.drive.tankDrive(leftMoveValue, Math.abs(rightMoveValue) < 0.04 ? 0
				: ((Math.abs(rightMoveValue) - 0.04) * Math.signum(rightMoveValue)));
	}

	public void arcadeNoConstants(double moveValue, double rotateValue) 
	{
		drive.arcadeDrive(moveValue, rotateValue);
//		double[] k = Constants.arcadeToTank(moveValue, rotateValue);
//		tank(k[0], k[1]);
	}
	public void stop() {
		arcade(0.0, 0.0);
	}

	// ACCESSORS
	public double gyroRate() {
		return this.g.getRate();
	}

	public void gyroCalibrate() {
		this.g.calibrate();
	}
	public void gyroReset() 
	{
		this.g.reset();
	}

	public double getLeftVelocity() {
		return this.l0.getSelectedSensorVelocity(0) * 6 * Math.PI / 4096;
	}

	public double getRightVelocity() {
		return this.r0.getSelectedSensorVelocity(0) * 6 * Math.PI / 4096;
	}

	public double getLeftPosition() {
		return this.l0.getSelectedSensorPosition(0) * 6 * Math.PI / 4096;
	}

	public double getRightPosition() {
		return this.r0.getSelectedSensorPosition(0) * 6 * Math.PI / 4096;
	}
	
	public double getGyroAngle() 
	{
		return g.getAngle();
	}
	/**
	 * Same as gyroPTurn but doesn't drive robot, just return values.
	 * @param targetAngle
	 */
	public double gyroPReturn(double targetAngle) {
		double turn = targetAngle - g.getAngle();
		final double maximumTurnValue = 0.9; // 0.6
		final double kPTurn = 0.06;
		if ((turn * kPTurn) > maximumTurnValue) {
			return maximumTurnValue;
		} else if ((turn * kPTurn) < -maximumTurnValue) {
			return -maximumTurnValue;
		} else {
			return (turn * kPTurn);
		}
	}
	public double gyroDReturn(double targetAngle, double previousError) 
	{
		final double kDTurn = 0.5; // Tune it up
		
		double currentError = targetAngle - g.getAngle();
		double differenceInError = currentError - previousError;
		return differenceInError * kDTurn; // TODO: Not an actual d implementation.
	}
//	public double gyroPDReturn(double targetAngle) 
//	{
//		return gyroPReturn(targetAngle) - gyroDReturn(targetAngle, );
//	}
	
	public void gyroPTurn(double targetAngle) {
		double turn = targetAngle - g.getAngle();
		final double maximumTurnValue = 0.5;
		final double kPTurn = 0.06;
		if ((turn * kPTurn) > maximumTurnValue) {
			this.arcade(0.0, maximumTurnValue);
		} else if ((turn * kPTurn) < -maximumTurnValue) {
			this.arcade(0.0, -maximumTurnValue);
		} else {
			this.arcade(0.0, turn * kPTurn);
		}

	}
	public boolean angleWithinTolerance(double targetAngle) {
		final double tolerance = 2.0;
		DriverStation.reportWarning("" + g.getAngle(), false);
		return (Math.abs(targetAngle - g.getAngle()) <= tolerance) && (Math.abs(g.getRate()) < 1);
	}
}