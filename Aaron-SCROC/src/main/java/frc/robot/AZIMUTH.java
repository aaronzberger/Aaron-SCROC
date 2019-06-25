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
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class AZIMUTH extends Subsystem {
  

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private TalonSRX johnny = new TalonSRX(1);

  public AZIMUTH() {
    johnny.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    johnny.setSensorPhase(true);
    johnny.config_kP(0, 1);
    johnny.config_kI(0, 0);
    johnny.config_kD(0, 6);
    johnny.config_kF(0, 0);
  }

  public void setWheelsToDegree(double degree) {
    double setTicks = convertDegreeToTicks(degree);
    johnny.set(ControlMode.Position, setTicks);
  }

  private double convertDegreeToTicks(double degreeValue) {
    return (degreeValue * (1024/90));
  }

  @Override
  public void initDefaultCommand() {

    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
