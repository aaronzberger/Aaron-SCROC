/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Add your docs here.
 */
public class OI {
    public OI() {
        Button rightStickButton = new JoystickButton(Robot.joystick, RobotMap.kButtonRightStick);
        rightStickButton.whenPressed(new SwitchToArcadeMode());
        
        Button leftStickButton = new JoystickButton(Robot.joystick, RobotMap.kButtonLeftStick);
        leftStickButton.whenPressed(new SwitchToSwerveMode());
    }
}
