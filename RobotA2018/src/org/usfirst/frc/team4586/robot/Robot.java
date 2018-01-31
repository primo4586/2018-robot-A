/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4586.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4586.robot.commands.ArcadeDrive;
import org.usfirst.frc.team4586.robot.commands.ExampleCommand;
import org.usfirst.frc.team4586.robot.commands.LiftCubeByJoystick;
import org.usfirst.frc.team4586.robot.subsystems.Climber;
import org.usfirst.frc.team4586.robot.subsystems.CubeSystem;
import org.usfirst.frc.team4586.robot.subsystems.Driver;
import org.usfirst.frc.team4586.robot.subsystems.ExampleSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static final ExampleSubsystem kExampleSubsystem
	= new ExampleSubsystem();
public static OI m_oi;
public static Climber climber;
public static CubeSystem cubeSystem;
public static Driver driver;
Command m_autonomousCommand;
SendableChooser<Command> m_chooser = new SendableChooser<>();

/**
* This function is run when the robot is first started up and should be
* used for any initialization code.
*/
//@Override
public void robotInit() {
RobotMap.Init();
climber = new Climber(RobotMap.climbMotor1, RobotMap.climbMotor2, RobotMap.compressor,
		RobotMap.openLeftPlatfrom, RobotMap.openRightPlatfrom);

cubeSystem = new CubeSystem(RobotMap.solenoidCube2, RobotMap.solenoidCube1,RobotMap.pushCube,
		RobotMap.compressor, RobotMap.elevatorsMotor, RobotMap.scaleSensor, RobotMap.switchSensor, RobotMap.floorSensor);
driver = new Driver(RobotMap.leftFrontMotor,RobotMap. leftBackMotor,RobotMap. rightFrontMotor,RobotMap. rightBackMotor , RobotMap.gyro,RobotMap.drivingEncoder);
m_oi = new OI();
m_chooser.addDefault("Default Auto", new ExampleCommand());
// chooser.addObject("My Auto", new MyAutoCommand());
SmartDashBoardInit();
SmartDashboard.putData("Auto mode", m_chooser);
}

/**
* This function is called once each time the robot enters Disabled mode.
* You can use it to reset any subsystem information you want to clear when
* the robot is disabled.
*/
@Override
public void disabledInit() {

}

@Override
public void disabledPeriodic() {
Scheduler.getInstance().run();
}

/**
* This autonomous (along with the chooser code above) shows how to select
* between different autonomous modes using the dashboard. The sendable
* chooser code works with the Java SmartDashboard. If you prefer the
* LabVIEW Dashboard, remove all of the chooser code and uncomment the
* getString code to get the auto name from the text box below the Gyro
*
* <p>You can add additional auto modes by adding additional commands to the
* chooser code above (like the commented example) or additional comparisons
* to the switch structure below with additional strings & commands.
*/
@Override
public void autonomousInit() {
m_autonomousCommand = m_chooser.getSelected();


/*
 * String autoSelected = SmartDashboard.getString("Auto Selector",
 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
 * = new MyAutoCommand(); break; case "Default Auto": default:
 * autonomousCommand = new ExampleCommand(); break; }
 */

// schedule the autonomous command (example)
if (m_autonomousCommand != null) {
	m_autonomousCommand.start();
}
}

/**
* This function is called periodically during autonomous.
*/
@Override
public void autonomousPeriodic() {
Scheduler.getInstance().run();
SmartDashBoardPereodic();
}

@Override
public void teleopInit() {
// This makes sure that the autonomous stops running when
// teleop starts running. If you want the autonomous to
// continue until interrupted by another command, remove
// this line or comment it out.
if (m_autonomousCommand != null) {
	m_autonomousCommand.cancel();
}

Scheduler.getInstance().add(new ArcadeDrive());
Scheduler.getInstance().add(new LiftCubeByJoystick());

}

/**
* This function is called periodically during operator control.
*/
@Override
public void teleopPeriodic() {
Scheduler.getInstance().run();
SmartDashBoardPereodic();
}

/**
* This function is called periodically during test mode.
*/
@Override
public void testPeriodic() {
}

public void SmartDashBoardInit() {
SmartDashboard.putNumber("Elavator Speed", 0.7);
SmartDashboard.putNumber("Driving direction", 1);
	SmartDashboard.putNumber("Max speed",0.7);
	SmartDashboard.putNumber("Speed climb back", 0);
	SmartDashboard.putNumber("Speed climb foreward", 0);
	
	//sensors
	SmartDashboard.putNumber("Gyro angle", driver.getGyroAngle());
	//TODO: check if the values are corrected
	SmartDashboard.putNumber("Encoder value " ,driver.getSpeedEncoder());
SmartDashboard.putNumber("Encoder rate ", driver.getDistenceEncoder());

	SmartDashboard.putBoolean("in scale",  cubeSystem.getScaleSensor());
	SmartDashboard.putBoolean("in floor", cubeSystem.getFloorSensor());
	SmartDashboard.putBoolean("in switch",  cubeSystem.getSwitchSensor());
	
	SmartDashboard.putData("Teleop Gyro PID", driver.getGyroController());
	SmartDashboard.putBoolean("use gyro", false);
	SmartDashboard.putBoolean("allow pre end game platforms", true);
}


public void SmartDashBoardPereodic() {
SmartDashboard.putNumber("Gyro angle", driver.getGyroAngle());
	//TODO: check if the values are corrected
	SmartDashboard.putNumber("Encoder value " ,driver.getSpeedEncoder());
SmartDashboard.putNumber("Encoder rate ", driver.getDistenceEncoder());

	SmartDashboard.putBoolean("in scale",  cubeSystem.getScaleSensor());
	SmartDashboard.putBoolean("in floor", cubeSystem.getFloorSensor());
	SmartDashboard.putBoolean("in switch",  cubeSystem.getSwitchSensor());
}
}
