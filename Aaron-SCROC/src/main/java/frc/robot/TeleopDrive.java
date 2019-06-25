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

  public TeleopDrive(DriveTrain driveTrain) {
    requires(Robot.azimuth);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double xValue = Robot.joystick.getRawAxis(0);
    double yValue = Robot.joystick.getRawAxis(1);
    double degreeValue = convertXYtoDegree(xValue, yValue);
    if(xValue != 0.0 && yValue != 0.0) {
      Robot.azimuth.setWheelsToDegree(degreeValue);
    }
  }

  private double convertXYtoDegree(double xValue, double yValue) {
    if(xValue > 0 && yValue > 0) {
      return (90 - Math.toDegrees(Math.atan(yValue/xValue)));
    }
    else if(xValue > 0 && yValue < 0) {
      return (90 + Math.toDegrees(Math.atan(Math.abs(yValue)/(Math.abs(xValue)))));
    }
    else if (xValue < 0 && yValue < 0) {
      return (180 + Math.toDegrees(Math.atan(Math.abs(yValue)/(Math.abs(xValue)))));
    }
    else if (xValue < 0 && yValue > 0) {
      return (270 + Math.toDegrees(Math.atan(Math.abs(yValue)/(Math.abs(xValue)))));
    }
    //should never run because it checks to make sure they are both not zero. (atan is only zero @ x=0)
    return 0.0;
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
