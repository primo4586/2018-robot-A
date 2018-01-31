/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4586.robot;

import org.usfirst.frc.team4586.robot.commands.CalibrateGyro;
import org.usfirst.frc.team4586.robot.commands.CatchCube;
import org.usfirst.frc.team4586.robot.commands.Climb;
import org.usfirst.frc.team4586.robot.commands.CubePusher;
import org.usfirst.frc.team4586.robot.commands.Invert;
import org.usfirst.frc.team4586.robot.commands.LiftToFloor;
import org.usfirst.frc.team4586.robot.commands.LiftToScale;
import org.usfirst.frc.team4586.robot.commands.LiftToSwitch;
import org.usfirst.frc.team4586.robot.commands.OpenPlatforms;
import org.usfirst.frc.team4586.robot.commands.ResetEncoder;
import org.usfirst.frc.team4586.robot.commands.StopAllMotors;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//driver
	//driver
		public Joystick joystickDriver;
		public JoystickButton stopAllMotors;
		public JoystickButton invert;
		public JoystickButton calibrateGyro;
		public JoystickButton resetEncoder;
		public JoystickButton cubePusher;
		
		//operator
		public Joystick joystickOpertor; 
		public JoystickButton liftToScale;
		public JoystickButton liftToFloor;
		public JoystickButton liftToSwitch;
		public JoystickButton climb;
		public JoystickButton catchCube;
		public JoystickButton openPlatform;

		
		public OI()
		{
		joystickDriver = new Joystick(0);
		stopAllMotors = new JoystickButton(joystickDriver , 1);
		invert = new JoystickButton(joystickDriver , 3);
		calibrateGyro = new JoystickButton(joystickDriver , 6);
		resetEncoder = new JoystickButton(joystickDriver , 2);
		cubePusher = new JoystickButton(joystickDriver , 7);
		
		joystickOpertor = new Joystick(1);
		liftToScale = new JoystickButton(joystickOpertor , 5);
		liftToFloor = new JoystickButton(joystickOpertor , 4);
		liftToSwitch = new JoystickButton(joystickOpertor ,3);
		climb = new JoystickButton(joystickOpertor , 6);
		catchCube = new JoystickButton(joystickOpertor , 1);
		openPlatform = new JoystickButton(joystickOpertor , 2);
		
		//driver commands
		stopAllMotors.whenPressed(new StopAllMotors());
		invert.whenPressed(new Invert());
		calibrateGyro.whenPressed(new CalibrateGyro());
		resetEncoder.whenPressed(new ResetEncoder());
		cubePusher.whenPressed(new CubePusher());
		
		//operator commands
		liftToScale.toggleWhenPressed(new LiftToScale());
		liftToFloor.toggleWhenPressed(new LiftToFloor());
		liftToSwitch.toggleWhenPressed(new LiftToSwitch());
		climb.whileHeld(new Climb());
		catchCube.whenPressed(new CatchCube());
		openPlatform.whenPressed(new OpenPlatforms());
		
		}
	
	}
	

	
