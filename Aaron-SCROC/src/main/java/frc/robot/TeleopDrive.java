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

  public TeleopDrive() {
    requires(azimuth);
    requires(driveTrain);
    this.driveTrain = Robot.driveTrain;
    this.azimuth = Robot.azimuth;
    this.joystick = Robot.joystick;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    driveTrain.stop();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //FOR AZIMUTH DRIVING (talons, left stick)
    double xValue = joystick.getRawAxis(RobotMap.kLeftStickX);
    double yValue = joystick.getRawAxis(RobotMap.kLeftStickY);
    double degreeValue = convertXYtoDegree(xValue, yValue);
    if(xValue != 0.0 && yValue != 0.0) {
      azimuth.setWheelsToDegree(degreeValue);
      arcadeDrive(RobotMap.THROTTLE_SCALE, 0.0);
      System.out.println("Set all wheels to " + degreeValue);
    }

    //FOR SIMPLE DRIVING (neos)
    double speed = RobotMap.THROTTLE_SCALE;
    double rotation = RobotMap.STEERING_SCALE;

    speed *= joystick.getRawAxis(RobotMap.kRightStickY);
    rotation *= joystick.getRawAxis(RobotMap.kRightStickX);

    //or, for more curved control:
    // speed *= Math.pow(joystick.getRawAxis(RobotMap.kRightStickY), 3);
    // rotation *= Math.pow(joystick.getRawAxis(RobotMap.kRightStickX), 3);

    if(speed > RobotMap.MAX_SPEEDS) { speed = RobotMap.MAX_SPEEDS; }
    if(rotation > RobotMap.MAX_SPEEDS) { rotation = RobotMap.MAX_SPEEDS; }

    arcadeDrive(speed, rotation);
  }

  //to ensure there is only one reference to arcadeDrive at one time.
  private static void arcadeDrive(double speed, double rotation) {
    Robot.driveTrain.arcadeDrive(speed, rotation);
  }

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
    //should never run because it checks to make sure they are both not zero. (atan is only zero @ x=0)  }

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
