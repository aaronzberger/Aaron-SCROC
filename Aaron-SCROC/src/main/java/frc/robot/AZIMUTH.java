/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class AZIMUTH extends Subsystem {

  private TalonSRX frontLeft = new TalonSRX(RobotMap.AZ_FRONT_LEFT);
  private TalonSRX frontRight = new TalonSRX(RobotMap.AZ_BACK_RIGHT);
  private TalonSRX backLeft = new TalonSRX(RobotMap.AZ_BACK_LEFT);
  private TalonSRX backRight = new TalonSRX(RobotMap.AZ_BACK_RIGHT);

  public AZIMUTH() {
    setupTalon(frontLeft);
    setupTalon(frontRight);
    setupTalon(backLeft);
    setupTalon(backRight);
  }

  public void setWheelsToDegree(double goal) {
    setWheelToDegree(frontLeft, goal);
    setWheelToDegree(frontRight, goal);
    setWheelToDegree(backLeft, goal);
    setWheelToDegree(backRight, goal);
  }

  private void setWheelToDegree(TalonSRX motor, double goalPosition) {
    double currentRelativePosition = convertTicksToDegrees(motor.getSelectedSensorPosition());
    double currentAbsolutePosition = ((currentRelativePosition % 360.0) + 360.0) % 360.0;
    double goalMove = goalPosition - currentAbsolutePosition;
    if(goalMove < -180.0) {
      goalMove += 360.0;
    }
    if(goalMove > 180.0) {
      goalMove -= 360.0;
    }
    motor.set(ControlMode.Position, convertDegreesToTicks(goalMove) + currentRelativePosition);
  }

  private void setWheelToFieldDegree(TalonSRX motor, double goalPosition) {
    double motorRelativePosition = convertTicksToDegrees(motor.getSelectedSensorPosition());
    double motorAbsolutePosition = ((motorRelativePosition % 360.0) + 360.0) % 360.0;
    double robotFieldPosition = gyro.getReading();
    double motorFieldPosition;
    if(robotFieldPosition < 180.0) {
      motorFieldPosition = motorAbsolutePosition + robotFieldPosition;
    }
    if(motorFieldPosition > 360.0) {
      motorFieldPosition -= 360;
    }
    double goalMove = goalPosition - motorFieldPosition;
    if(goalMove < -180.0) {
      goalMove += 360.0;
    }
    if(goalMove > 180.0) {
      goalMove -= 360.0;
    }
    motor.set(ControlMode.Position, convertDegreesToTicks(goalMove) + motorRelativePosition);
  }

  private double convertDegreesToTicks(double degreeValue) {
    return (degreeValue * (1024.0/90.0));
  }
  private double convertTicksToDegrees(double ticks) {
    return (ticks / (1024.0/90.0));
  }

  private void setupTalon(TalonSRX talon) {
    talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    talon.setSensorPhase(true);
    talon.config_kP(0, 1);
    talon.config_kI(0, 0);
    talon.config_kD(0, 6);
    talon.config_kF(0, 0);
    talon.setInverted(true);
  }

  @Override
  public void initDefaultCommand() {

    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
