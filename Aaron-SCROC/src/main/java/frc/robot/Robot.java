/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.TestDriveCommands.BackLeftDriveBackward;
import frc.robot.TestDriveCommands.BackLeftDriveForward;
import frc.robot.TestDriveCommands.BackRightDriveBackward;
import frc.robot.TestDriveCommands.BackRightDriveForward;
import frc.robot.TestDriveCommands.FrontLeftDriveBackward;
import frc.robot.TestDriveCommands.FrontLeftDriveForward;
import frc.robot.TestDriveCommands.FrontRightDriveBackward;
import frc.robot.TestDriveCommands.FrontRightDriveForward;
import frc.robot.TestSwerveCommands.BackLeftSwerveBackward;
import frc.robot.TestSwerveCommands.BackLeftSwerveForward;
import frc.robot.TestSwerveCommands.BackRightSwerveBackward;
import frc.robot.TestSwerveCommands.BackRightSwerveForward;
import frc.robot.TestSwerveCommands.FrontLeftSwerveBackward;
import frc.robot.TestSwerveCommands.FrontLeftSwerveForward;
import frc.robot.TestSwerveCommands.FrontRightSwerveBackward;
import frc.robot.TestSwerveCommands.FrontRightSwerveForward;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static DriveTrain driveTrain;
  public static AZIMUTH azimuth;
  public static Joystick joystick;
  public static OI oi;
  private static boolean isAzimuthOffsetSetup;

  // private static final String kDefaultAuto = "Default";
  // private static final String kCustomAuto = "My Auto";
  // private String m_autoSelected;

  // private final SendableChooser<String> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    driveTrain = new DriveTrain();
    azimuth = new AZIMUTH();
    joystick = new Joystick(0);
    oi = new OI();
    isAzimuthOffsetSetup = false;

    //Put commands to the Smart Dashboard for controlling each swerve motor (forward and backward) using the TestSwerveCommands
    SmartDashboard.putData("Back Left Swerve Forward", new BackLeftSwerveForward());
    SmartDashboard.putData("Back Left Swerve Backward", new BackLeftSwerveBackward());
    SmartDashboard.putData("Back Right Swerve Forward", new BackRightSwerveForward());
    SmartDashboard.putData("Back Right Swerve Backward", new BackRightSwerveBackward());
    SmartDashboard.putData("Front Left Swerve Forward", new FrontLeftSwerveForward());
    SmartDashboard.putData("Front Left Swerve Backward", new FrontLeftSwerveBackward());
    SmartDashboard.putData("Front Right Swerve Forward", new FrontRightSwerveForward());
    SmartDashboard.putData("Front Right Swerve Backward", new FrontRightSwerveBackward());

    //Put commands to the Smart Dashboard for controlling each drive motor (forward and backward) using the TestDriveCommands
    SmartDashboard.putData("Back Left Drive Forward", new BackLeftDriveForward());
    SmartDashboard.putData("Back Left Drive Backward", new BackLeftDriveBackward());
    SmartDashboard.putData("Back Right Drive Forward", new BackRightDriveForward());
    SmartDashboard.putData("Back Right Drive Backward", new BackRightDriveBackward());
    SmartDashboard.putData("Front Left Drive Forward", new FrontLeftDriveForward());
    SmartDashboard.putData("Front Left Drive Backward", new FrontLeftDriveBackward());
    SmartDashboard.putData("Front Right Drive Forward", new FrontRightDriveForward());
    SmartDashboard.putData("Front Right Drive Backward", new FrontRightDriveBackward());
    
    // m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    // m_chooser.addOption("My Auto", kCustomAuto);
    // SmartDashboard.putData("Auto choices", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
  //Sets up the Azimuth motors if they have not already been set up
    if(!isAzimuthOffsetSetup) {
      Robot.azimuth.setupOffsets();
      isAzimuthOffsetSetup = true;
    }
    // m_autoSelected = m_chooser.getSelected();
    // // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    // System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    // switch (m_autoSelected) {
    //   case kCustomAuto:
    //     // Put custom auto code here
    //     break;
    //   case kDefaultAuto:
    //   default:
    //     // Put default auto code here
    //     break;
    // }
  }

  @Override
  public void teleopInit() {
    //Sets up the Azimuth motors if they have not already been set up
     if(!isAzimuthOffsetSetup) {
       Robot.azimuth.setupOffsets();
       isAzimuthOffsetSetup = true;
     }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
