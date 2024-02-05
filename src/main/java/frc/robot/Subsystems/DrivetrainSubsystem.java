// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;

public class DrivetrainSubsystem extends SubsystemBase {
  private final WPI_VictorSPX frontLeft = new WPI_VictorSPX(DrivetrainConstants.frontLeftID);
  private final WPI_VictorSPX frontRight = new WPI_VictorSPX(DrivetrainConstants.frontRightID);
  private final WPI_VictorSPX backLeft = new WPI_VictorSPX(DrivetrainConstants.backLeftID);
  private final WPI_VictorSPX backRight = new WPI_VictorSPX(DrivetrainConstants.backRightID);
  
  private final DifferentialDrive drive = new DifferentialDrive(frontLeft, frontRight);

  private double left, right;

  /** Creates a new DrivetrainSubsystem. */
  public DrivetrainSubsystem() {
    frontLeft.setInverted(true);
    backLeft.setInverted(true);

    backLeft.follow(frontLeft);
    backRight.follow(frontRight);

    frontLeft.setNeutralMode(NeutralMode.Brake);
    frontRight.setNeutralMode(NeutralMode.Brake);
    backLeft.setNeutralMode(NeutralMode.Brake);
    frontRight.setNeutralMode(NeutralMode.Brake);

    frontLeft.setSelectedSensorPosition(0);
    frontRight.setSelectedSensorPosition(0);
    backLeft.setSelectedSensorPosition(0);
    backRight.setSelectedSensorPosition(0);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Front Left Speed", frontLeft.get());
    SmartDashboard.putNumber("Front Right Speed", frontRight.get());
    SmartDashboard.putNumber("Back Left Speed", backLeft.get());
    SmartDashboard.putNumber("Back Right Speed", backRight.get());

    SmartDashboard.putNumber("Front Left Pos", frontLeft.getSelectedSensorPosition());
    SmartDashboard.putNumber("Front Right Pos", frontRight.getSelectedSensorPosition());
    SmartDashboard.putNumber("Back Left Pos", backLeft.getSelectedSensorPosition());
    SmartDashboard.putNumber("Back Right Pos", backRight.getSelectedSensorPosition());

    SmartDashboard.putNumber("Left Speed", left);
    SmartDashboard.putNumber("Right Speed", right);
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    drive.tankDrive(leftSpeed, rightSpeed);
    left = leftSpeed;
    right = rightSpeed;
  }
}