/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4586.robot;

import org.usfirst.frc.team4586.robot.commands.AutoDrive;
import org.usfirst.frc.team4586.robot.commands.AutoStart;
import org.usfirst.frc.team4586.robot.commands.AutoTurn;
import org.usfirst.frc.team4586.robot.commands.CalibrateGyro;
import org.usfirst.frc.team4586.robot.commands.CatchCube;
import org.usfirst.frc.team4586.robot.commands.Climb;
import org.usfirst.frc.team4586.robot.commands.ClimbByTime;
import org.usfirst.frc.team4586.robot.commands.ClimbRight;
import org.usfirst.frc.team4586.robot.commands.CubeCatcherNoDelay;
import org.usfirst.frc.team4586.robot.commands.Invert;
import org.usfirst.frc.team4586.robot.commands.LiftToFloor;
import org.usfirst.frc.team4586.robot.commands.LiftToSwitch;
import org.usfirst.frc.team4586.robot.commands.OpenPlatforms;
import org.usfirst.frc.team4586.robot.commands.OpenZikpaAndHands;
import org.usfirst.frc.team4586.robot.commands.OpendShloplop;
import org.usfirst.frc.team4586.robot.commands.ResetEncoder;
import org.usfirst.frc.team4586.robot.commands.StopAllMotors;
import org.usfirst.frc.team4586.robot.commands.SwitchCompressor;
import org.usfirst.frc.team4586.robot.commands.UnlockElevator;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team4586.robot.commands.CubePusher;
import org.usfirst.frc.team4586.robot.commands.DownRight;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	//driver
	public Joystick joystickDriver;
	public JoystickButton stopAllMotors;
	public JoystickButton catchCube;
	//public JoystickButton invert;
	public JoystickButton calibrateGyro;
	public JoystickButton resetEncoder;
	public JoystickButton cubePusher;
	public JoystickButton noDelayCatch;
	public JoystickButton gyroTurn;
	public JoystickButton openBothThings;
	
	//operator
	public Joystick joystickOpertor;
	public JoystickButton climbL;
	public JoystickButton climbR;
	public JoystickButton liftToScale;
	public JoystickButton liftToFloor;
	public JoystickButton liftToSwitch;
	public JoystickButton openPlatform;
	public JoystickButton switchCompressor;
	public JoystickButton downRight;
	public JoystickButton toggleShloplop;
	public JoystickButton unlockElevator;
	
	
	public OI()
	{
		//buttons left -> 2,7
				joystickDriver = new Joystick(0);
				stopAllMotors = new JoystickButton(joystickDriver , 9);
				//invert = new JoystickButton(joystickDriver , 4);
				resetEncoder = new JoystickButton(joystickDriver , 8);
				cubePusher = new JoystickButton(joystickDriver , 3);
				catchCube = new JoystickButton(joystickDriver , 5);
				noDelayCatch = new JoystickButton(joystickDriver, 6);
				openBothThings = new JoystickButton(joystickDriver, 1);
				
				joystickOpertor = new Joystick(1);
				liftToFloor = new JoystickButton(joystickOpertor , 8);
				//liftToSwitch = new JoystickButton(joystickOpertor ,7);
				climbR = new JoystickButton(joystickOpertor , 6);
				openPlatform = new JoystickButton(joystickOpertor , 2);
				switchCompressor = new JoystickButton(joystickOpertor, 5);
				calibrateGyro = new JoystickButton(joystickOpertor , 3);
				downRight = new JoystickButton(joystickOpertor, 4);
				toggleShloplop = new JoystickButton(joystickOpertor, 1);
				unlockElevator = new JoystickButton(joystickOpertor, 10);
				gyroTurn = new JoystickButton(joystickOpertor, 7);
				
				//driver commands
				stopAllMotors.whenPressed(new StopAllMotors());
				//invert.whenPressed(new Invert());
				calibrateGyro.whenPressed(new CalibrateGyro());
				resetEncoder.whenPressed(new ResetEncoder());
				cubePusher.whenPressed(new CubePusher());
				catchCube.whenPressed(new CatchCube());
				noDelayCatch.whenPressed(new CubeCatcherNoDelay());
				openBothThings.whenPressed(new OpenZikpaAndHands());
				
				//operator commands
				liftToFloor.toggleWhenPressed(new ClimbByTime());
				gyroTurn.toggleWhenPressed(new ClimbByTime());
				//liftToSwitch.toggleWhenPressed(new LiftToSwitch());
				climbR.whileHeld(new ClimbRight());
				openPlatform.whenPressed(new OpenPlatforms());
				switchCompressor.whenPressed(new SwitchCompressor());
				downRight.whileHeld(new DownRight());
				toggleShloplop.whenPressed(new OpendShloplop());
//				unlockElevator.whenPressed(new AutoStart());
		
	}
}