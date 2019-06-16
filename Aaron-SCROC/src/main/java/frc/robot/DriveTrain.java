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
  // private CANSparkMax frontLeft;
  // private CANSparkMax frontRight;
  // private CANSparkMax backRight;
  // private CANSparkMax backLeft;

  // private SpeedControllerGroup leftMotors;
  // private SpeedControllerGroup rightMotors;

  // private DifferentialDrive differentialDrive;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public DriveTrain() {
    // frontLeft = new CANSparkMax(0, MotorType.kBrushless);
    // frontRight = new CANSparkMax(1, MotorType.kBrushless);
    // backRight = new CANSparkMax(2, MotorType.kBrushless);
    // backLeft = new CANSparkMax(3, MotorType.kBrushless);

    // leftMotors = new SpeedControllerGroup(frontLeft, backLeft);
    // rightMotors = new SpeedControllerGroup(frontRight, backRight);

    // differentialDrive = new DifferentialDrive(leftMotors, rightMotors);
  }
  
  // public void arcadeDrive(double speed, double rotation) {
  //   differentialDrive.arcadeDrive(speed, rotation);
  // }

  // public void tankDrive(double leftSpeed, double rightSpeed) {
  //   differentialDrive.tankDrive(leftSpeed, rightSpeed);
  // }

  // public void stop() {
  //   tankDrive(0.0, 0.0);
  // }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
