/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  private CANSparkMax frontLeft;
  private CANSparkMax frontRight;
  private CANSparkMax backRight;
  private CANSparkMax backLeft;

  private SpeedControllerGroup leftMotors;
  private SpeedControllerGroup rightMotors;

  private DifferentialDrive differentialDrive;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public DriveTrain() {
    frontLeft = new CANSparkMax(RobotMap.DRIVE_FRONT_LEFT, MotorType.kBrushless);
    frontRight = new CANSparkMax(RobotMap.DRIVE_FRONT_RIGHT, MotorType.kBrushless);
    backLeft = new CANSparkMax(RobotMap.DRIVE_BACK_LEFT, MotorType.kBrushless);
    backRight = new CANSparkMax(RobotMap.DRIVE_BACK_RIGHT, MotorType.kBrushless);

    leftMotors = new SpeedControllerGroup(frontLeft, backLeft);
    rightMotors = new SpeedControllerGroup(frontRight, backRight);

    differentialDrive = new DifferentialDrive(leftMotors, rightMotors);
  }
  
  public void arcadeDrive(double speed, double rotation) {
    differentialDrive.arcadeDrive(speed, rotation);
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    differentialDrive.tankDrive(leftSpeed, rightSpeed);
  }

  public void setSpeed(int CANID, double speed) {
    switch(CANID) {
      case RobotMap.DRIVE_FRONT_LEFT:
        frontLeft.set(speed);
      case RobotMap.DRIVE_FRONT_RIGHT:
        frontRight.set(speed);
      case RobotMap.DRIVE_BACK_LEFT:
        backLeft.set(speed);
      case RobotMap.DRIVE_BACK_RIGHT:
        backRight.set(speed);
    }
  }

  public void stop() {
    tankDrive(0.0, 0.0);
  }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
