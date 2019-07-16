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

  public TeleopDrive(DriveTrain driveTrain, AZIMUTH azimuth) {
    requires(azimuth);
    requires(driveTrain);
    this.driveTrain = driveTrain;
    this.azimuth = azimuth;
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
    double xValue = joystick.getRawAxis(0);
    double yValue = joystick.getRawAxis(1);
    double degreeValue = convertXYtoDegree(xValue, yValue);
    if(xValue != 0.0 && yValue != 0.0) {
      azimuth.setWheelsToDegree(degreeValue);
      System.out.println("Set all wheels to " + degreeValue);
    }

    //FOR SIMPLE DRIVING (neos)
    double speed = 0.75;
    double rotation = 0.6;

    speed *= joystick.getRawAxis(5);
    rotation *= joystick.getRawAxis(4);

    //or, for more curved control:
    // speed *= Math.pow(joystick.getRawAxis(5), 3);
    // rotation *= Math.pow(joystick.getRawAxis(4), 3);

    if(speed > 0.9) { speed = 0.9; }
    if(rotation > 0.9) { rotation = 0.9; }

    driveTrain.arcadeDrive(speed, rotation);
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
