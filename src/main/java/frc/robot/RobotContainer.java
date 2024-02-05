// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Commands.Teleop.TankDriveCmd;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.DriverConstants;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.Constants.IntakeShooterConstants;
import frc.robot.Subsystems.DrivetrainSubsystem;

public class RobotContainer {
  private final XboxController controller = new XboxController(DriverConstants.port);
  private final CommandXboxController commandController = new CommandXboxController(DriverConstants.port);

  private final DrivetrainSubsystem driveSub = new DrivetrainSubsystem();


  public RobotContainer() {
    driveSub.setDefaultCommand(
      new TankDriveCmd(
        driveSub,
        () -> controller.getRawAxis(DriverConstants.leftJoystickAxis),
        () -> controller.getRawAxis(DriverConstants.rightJoystickAxis),
        () -> DriverConstants.joystickDeadband,
        () -> DrivetrainConstants.speed
      )
    );

    configureBindings();
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}