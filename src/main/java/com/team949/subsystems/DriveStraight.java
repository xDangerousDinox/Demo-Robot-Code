/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team949.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.team949.RobotMap;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


/**
 * Add your docs here.
 */
public class DriveStraight extends PIDSubsystem {
  /**
   * Add your docs here.
   */

  private DifferentialDrive drive;

  private SpeedControllerGroup r;
  private SpeedControllerGroup l;
  
  public WPI_TalonSRX r0, r1, l0, l1;

  public DriveStraight() {
    // Intert a subsystem name and PID values here
    super("SubsystemName", 0.01, 0, 0);
    this.r0 = new WPI_TalonSRX(RobotMap.rightDriveMotor1);
		this.r1 = new WPI_TalonSRX(RobotMap.rightDriveMotor2);
		this.l0 = new WPI_TalonSRX(RobotMap.leftDriveMotor1);
    this.l1 = new WPI_TalonSRX(RobotMap.leftDriveMotor2);
    setUpEncoders();
    this.r = new SpeedControllerGroup(r0, r1);
		this.l = new SpeedControllerGroup(l0, l1);

		this.r.setInverted(false);
		this.l.setInverted(false);

		this.drive = new DifferentialDrive(l, r);

		this.drive.setSafetyEnabled(false);
    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.
  }

  private void setUpEncoders() {
		r0.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		r0.setSensorPhase(false);
		r0.setSelectedSensorPosition(0, 0, 0);
		l0.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		l0.setSensorPhase(true);
		l0.setSelectedSensorPosition(0, 0, 0);
  }
  
  public double getLeftVelocity() {
		return this.l0.getSelectedSensorVelocity(0) * 6 * Math.PI / 4096;
	}

	public double getRightVelocity() {
		return this.r0.getSelectedSensorVelocity(0) * 6 * Math.PI / 4096;
	}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    return Math.abs(getRightVelocity() - getLeftVelocity());
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
  }

  
}
