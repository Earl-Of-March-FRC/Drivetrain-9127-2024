// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private final WPI_VictorSPX frontLeft = new WPI_VictorSPX(2);
  private final WPI_VictorSPX frontRight = new WPI_VictorSPX(0);
  private final WPI_VictorSPX backLeft = new WPI_VictorSPX(3);
  private final WPI_VictorSPX backRight = new WPI_VictorSPX(1);

  private final DifferentialDrive drive = new DifferentialDrive(frontLeft, frontRight);

  XboxController controller = new XboxController(0);

  // make speed negative
  private final double speed = 1;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    SmartDashboard.putData("Auto choices", m_chooser);

    frontLeft.setInverted(true);
    backLeft.setInverted(true);

    backLeft.follow(frontLeft);
    backRight.follow(frontRight);

    // frontLeft.setNeutralMode(NeutralMode.Brake);
    // frontRight.setNeutralMode(NeutralMode.Brake);
    // backLeft.setNeutralMode(NeutralMode.Brake);
    // frontRight.setNeutralMode(NeutralMode.Brake);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    double leftJoystickValue = controller.getRawAxis(1);
    double rightJoystickValue = controller.getRawAxis(5);

    // Relation between joystick value and motor speed is a square root function
    double lSpeed = Math.signum(leftJoystickValue)*(Math.sqrt(Math.abs(leftJoystickValue))) * speed;
    double rSpeed = Math.signum(rightJoystickValue)*(Math.sqrt(Math.abs(rightJoystickValue))) * speed;

    //double lSpeed = controller.getRawAxis(1) * controller.getRawAxis(1) * (controller.getRawAxis(1) > 0 ? 1 :-1);
    //double rSpeed = controller.getRawAxis(5) * controller.getRawAxis(5) * (controller.getRawAxis(5) > 0 ? 1 : -1);

    drive.tankDrive(lSpeed, rSpeed);
    // drive.arcadeDrive(controller.getRawAxis(1), controller.getRawAxis(4));
  } 

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
