/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Add your docs here.
 */
public class AZIMUTH extends Subsystem {

  private TalonSRX frontLeft = new TalonSRX(RobotMap.AZ_FRONT_LEFT);
  private TalonSRX frontRight = new TalonSRX(RobotMap.AZ_FRONT_RIGHT);
  private TalonSRX backLeft = new TalonSRX(RobotMap.AZ_BACK_LEFT);
  private TalonSRX backRight = new TalonSRX(RobotMap.AZ_BACK_RIGHT);

  public AZIMUTH() {
    setupTalon(frontLeft);
    setupTalon(frontRight);
    setupTalon(backLeft);
    setupTalon(backRight);
  }

  public void setupOffsets() {
    setupOffset(frontLeft);
    setupOffset(frontRight);
    setupOffset(backLeft);
    setupOffset(backRight);
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
    if(goalMove < -180) {
      goalMove += 360;
    }
    if(goalMove > 180) {
      goalMove -= 360;
    }
    motor.set(ControlMode.Position, convertDegreesToTicks(goalMove) + currentRelativePosition);
  }

  private double convertDegreesToTicks(double degreeValue) {
    return (degreeValue * (1024.0/90.0));
  }
  private double convertTicksToDegrees(double ticks) {
    return (ticks / (1024.0/90.0));
  }

  private void setupTalon(TalonSRX talon) {
    talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
    talon.setSensorPhase(false);
    talon.config_kP(0, 3);
    talon.config_kI(0, 0);
    talon.config_kD(0, 30);
    talon.config_kF(0, 0);
    talon.setInverted(true);
    talon.setNeutralMode(NeutralMode.Brake);
    System.out.println("in setupTalon method");
  }

  private void setupOffset(TalonSRX talon) {
    switch(talon.getDeviceID()) {
      case RobotMap.AZ_FRONT_LEFT:
        frontLeft.set(ControlMode.Position, RobotMap.AZ_FRONT_LEFT_OFFSET);
      case RobotMap.AZ_FRONT_RIGHT:
        frontRight.set(ControlMode.Position, RobotMap.AZ_FRONT_RIGHT_OFFSET);
      case RobotMap.AZ_BACK_LEFT:
        backLeft.set(ControlMode.Position, RobotMap.AZ_BACK_LEFT_OFFSET);
      case RobotMap.AZ_BACK_RIGHT:
        backRight.set(ControlMode.Position, RobotMap.AZ_BACK_RIGHT_OFFSET);
    }
    System.out.println("before timer delay in setupOffset");
    /**
     * method needs to pause to let the motor reach the zero position before setting the current encoder value to 0.
     * We could use:
     * Timer.delay(5)
     * Scheduler.getInstance().add(new WaitCommand(10));
     */
    System.out.println("after timer delay in setupOffset");
    talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    talon.setSelectedSensorPosition(0);
  }

  public void setSpeed(int CANID, double speed) {
    switch(CANID) {
      case RobotMap.AZ_FRONT_LEFT:
        frontLeft.set(ControlMode.PercentOutput, speed);
      case RobotMap.AZ_FRONT_RIGHT:
        frontRight.set(ControlMode.PercentOutput, speed);
      case RobotMap.AZ_BACK_LEFT:
        backLeft.set(ControlMode.PercentOutput, speed);
      case RobotMap.AZ_BACK_RIGHT:
        backRight.set(ControlMode.PercentOutput, speed);
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
