/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * For variables
 */
public class RobotMap {
    //CAN IDs
	public static final int AZ_FRONT_LEFT = 1;
	public static final int AZ_FRONT_RIGHT = 8;
	public static final int AZ_BACK_LEFT = 4;
    public static final int AZ_BACK_RIGHT = 5;
    
    public static final int DRIVE_FRONT_LEFT = 2;
	public static final int DRIVE_FRONT_RIGHT = 7;
	public static final int DRIVE_BACK_LEFT = 3;
    public static final int DRIVE_BACK_RIGHT = 6;

	public static final double THROTTLE_SCALE = 0.75;
	public static final double STEERING_SCALE = 0.6;
	public static final double MAX_SPEEDS = 0.95;

	public static final double TEST_AZ_SPEED = 0.15;
	public static final double TEST_DRIVE_SPEED = 0.15;

	//Logitech f310 Mappings
	/* axis mappings */
	public static final int kLeftStickX = 0;
	public static final int kLeftStickY = 1;
	public static final int kLeftTrigger = 2;
	public static final int kRightTrigger = 3;
	public static final int kRightStickX = 4;
	public static final int kRightStickY = 5;
	
	/* button mappings */
	public static final int kButtonA = 1;
	public static final int kButtonB = 2;
	public static final int kButtonX = 3;
	public static final int kButtonY = 4;
	public static final int kButtonLeftBumper = 5;
	public static final int kButtonRightBumper = 6;
	public static final int kButtonBack = 7;
	public static final int kButtonStart = 8;
	public static final int kButtonLeftStick = 9;
	public static final int kButtonRightStick = 10;
	public static final int kPOVDPad = 0;
}
