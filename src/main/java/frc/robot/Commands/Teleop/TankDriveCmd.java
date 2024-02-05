// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands.Teleop;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.DrivetrainSubsystem;

public class TankDriveCmd extends Command {
  private DrivetrainSubsystem driveSub;
  private DoubleSupplier leftInput, rightInput, deadband, speedMultiplier;
  /** Creates a new TankDriveCmd. */
  public TankDriveCmd(
    DrivetrainSubsystem driveSub,
    DoubleSupplier leftInput, DoubleSupplier rightInput,
    DoubleSupplier deadband,
    DoubleSupplier speedMultiplier
  ) {
    this.driveSub = driveSub;
    this.leftInput = leftInput;
    this.rightInput = rightInput;
    this.deadband = deadband;
    this.speedMultiplier = speedMultiplier;

    addRequirements(driveSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double left = MathUtil.applyDeadband(leftInput.getAsDouble(), deadband.getAsDouble());
    double right = MathUtil.applyDeadband(rightInput.getAsDouble(), deadband.getAsDouble());

    driveSub.tankDrive(
      Math.signum(left)*(Math.sqrt(Math.abs(left))) * speedMultiplier.getAsDouble(),
      Math.signum(right)*(Math.sqrt(Math.abs(right))) * speedMultiplier.getAsDouble()
    );
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
