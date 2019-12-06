/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class TeleopDrive extends Command {

  private AZIMUTH azimuth;
  private DriveTrain driveTrain;
  private Joystick joystick;
  public static boolean swerveMode = false;

  public TeleopDrive() {
    this.driveTrain = Robot.driveTrain;
    this.azimuth = Robot.azimuth;
    this.joystick = Robot.joystick;
    requires(azimuth);
    requires(driveTrain);
    // Use requires() here to declare subsystem dependencies
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    driveTrain.stop();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    /* 
    *  The driving is split up between swerve mode and normal arcade drive
    *  In swerve mode, when the driver pushes the left stick to any angle, the azimuth motors turn to that angle and drive forward
    *  In normal mode, the driverrain operates as a normal arcade drive
    *  One can switch between the modes using buttons A(swerve) and B(normal)
    */
    double speed = RobotMap.DRIVE_THROTTLE_SCALE;
    double rotation = RobotMap.DRIVE_STEERING_SCALE;
    if(swerveMode) {
      //For swerve mode driving
      double xValue = joystick.getRawAxis(RobotMap.kLeftStickX);
      double yValue = joystick.getRawAxis(RobotMap.kLeftStickY);
      double degreeValue = convertXYtoDegree(xValue, yValue);
      if(xValue < -0.1 || xValue > 0.1 || yValue < -0.1 || yValue > 0.1) {
        azimuth.setWheelsToDegree(degreeValue);
        System.out.println("Set all wheels to " + degreeValue);
        arcadeDrive(RobotMap.DRIVE_THROTTLE_SCALE, 0.0);
      }

      speed *= joystick.getRawAxis(RobotMap.kRightStickY);
      rotation *= joystick.getRawAxis(RobotMap.kRightStickX);

      if(speed > RobotMap.DRIVE_MAX_SPEED) { speed = RobotMap.DRIVE_MAX_SPEED; }
      if(rotation > RobotMap.DRIVE_MAX_SPEED) { rotation = RobotMap.DRIVE_MAX_SPEED; }

      arcadeDrive(speed, rotation);
    }
    else {
    //For normal arcade driving
    speed *= joystick.getRawAxis(RobotMap.kRightStickY);
    rotation *= joystick.getRawAxis(RobotMap.kRightStickX);

    //or, for more curved control:
    // speed *= Math.pow(joystick.getRawAxis(RobotMap.kRightStickY), 3);
    // rotation *= Math.pow(joystick.getRawAxis(RobotMap.kRightStickX), 3);

    if(speed > RobotMap.DRIVE_MAX_SPEED) { speed = RobotMap.DRIVE_MAX_SPEED; }
    if(rotation > RobotMap.DRIVE_MAX_SPEED) { rotation = RobotMap.DRIVE_MAX_SPEED; }

    arcadeDrive(speed, rotation);
    }
    if(Robot.joystick.getRawButton(RobotMap.kButtonA)) {
      swerveMode = false;
    }
    if(Robot.joystick.getRawButton(RobotMap.kButtonB)) {
      swerveMode = true;
    }
  }

  //To ensure there is only one reference to arcadeDrive at a time:
  private static void arcadeDrive(double speed, double rotation) {
    Robot.driveTrain.arcadeDrive(speed, rotation);
  }

    /* 
    *  The joystick outputs an X and Y value. We need an angle value, however, so we can point our azimuth motors to the correct position
    *  This function converts an X and Y value to a degree value using inverse tangeant and a addition, depending on the quadrant (1-4)
    */
  private double convertXYtoDegree(double xValue, double yValue) {
    double returnValue = 0;
    if(xValue >= 0 && yValue >= 0) {
      returnValue = (90 + Math.toDegrees(Math.atan(yValue/xValue)));
    }
    else if(xValue > 0 && yValue < 0) {
      returnValue = (90 - Math.toDegrees(Math.atan(Math.abs(yValue)/(Math.abs(xValue)))));
    }
    else if (xValue <= 0 && yValue <= 0) {
      returnValue = (270 + Math.toDegrees(Math.atan(Math.abs(yValue)/(Math.abs(xValue)))));
    }
    else if (xValue < 0 && yValue > 0) {
      returnValue = (270 - Math.toDegrees(Math.atan(Math.abs(yValue)/(Math.abs(xValue)))));
    }
    return returnValue;
  }
  
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
