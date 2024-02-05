// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands.Auto;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.DrivetrainSubsystem;

public class TankDriveRaw extends Command {
  private DrivetrainSubsystem driveSub;
  private DoubleSupplier leftInput, rightInput;
  /** Creates a new TankDriveCmd. */
  public TankDriveRaw(
    DrivetrainSubsystem driveSub,
    DoubleSupplier leftInput, DoubleSupplier rightInput,
    DoubleSupplier deadband,
    DoubleSupplier speedMultiplier
  ) {
    this.driveSub = driveSub;
    this.leftInput = leftInput;
    this.rightInput = rightInput;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveSub.tankDrive(leftInput.getAsDouble(), rightInput.getAsDouble());
    SmartDashboard.putNumber("Drivetrain Left Speed Input", leftInput.getAsDouble());
    SmartDashboard.putNumber("Drivetrain Right Speed Input", rightInput.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
