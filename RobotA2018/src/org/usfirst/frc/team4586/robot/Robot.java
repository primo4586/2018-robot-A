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
import org.usfirst.frc.team4586.robot.commands.AutoCommandGroupLeft;
import org.usfirst.frc.team4586.robot.commands.AutoPickCubeMid;
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
	public static final ExampleSubsystem kExampleSubsystem = new ExampleSubsystem();
	public static OI m_oi;
	public static Climber climber;
	public static CubeSystem cubeSystem;
	public static Driver driver;
	Command m_autonomousCommand;
	SendableChooser<Integer> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	// @Override
	public void robotInit() {
		RobotMap.Init();
		climber = new Climber(RobotMap.climbMotor1, RobotMap.climbMotor2, RobotMap.compressor,
				RobotMap.openLeftPlatfrom, RobotMap.openRightPlatfrom);

		cubeSystem = new CubeSystem(RobotMap.solenoidCube2, RobotMap.solenoidCube1, RobotMap.pushCube,
				RobotMap.compressor, RobotMap.elevatorsMotor, RobotMap.scaleSensor, RobotMap.switchSensor,
				RobotMap.floorSensor);
		driver = new Driver(RobotMap.leftFrontMotor, RobotMap.leftBackMotor, RobotMap.rightFrontMotor,
				RobotMap.rightBackMotor, RobotMap.gyro, RobotMap.drivingEncoder);
		m_oi = new OI();
		m_chooser.addDefault("Auto Left Side", 0);
		// chooser.addObject("Auto Right Side", new AutoCommandGroupRight());
		// chooser.addObject("AutoMiddle", new AutoCommandGroupMiddle());
		m_chooser.addObject("Auto Middle Pickup", 1);
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
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		if (m_chooser.getSelected() == 0) {
			m_autonomousCommand = new AutoCommandGroupLeft();
		} else if (m_chooser.getSelected() == 1) {
			m_autonomousCommand = new AutoPickCubeMid();
		}
		
		
		
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
		SmartDashboard.putNumber("Elevator Speed", 0.7);
		SmartDashboard.putNumber("Driving Direction", 1);
		SmartDashboard.putNumber("Max Speed", 0.7);
		SmartDashboard.putNumber("Speed Climb Back", 0);
		SmartDashboard.putNumber("Speed Climb Forward", 0);

		// sensors
		SmartDashboard.putNumber("Gyro Angle", driver.getGyroAngle());
		// TODO: check if the values are corrected
		SmartDashboard.putNumber("Encoder Value ", driver.getSpeedEncoder());
		SmartDashboard.putNumber("Encoder Rate ", driver.getDistenceEncoder());

		SmartDashboard.putBoolean("In scale", cubeSystem.getScaleSensor());
		SmartDashboard.putBoolean("In floor", cubeSystem.getFloorSensor());
		SmartDashboard.putBoolean("In switch", cubeSystem.getSwitchSensor());

		SmartDashboard.putData("Teleop Gyro PID", driver.getGyroController());
		SmartDashboard.putBoolean("Use Gyro", false);
		SmartDashboard.putBoolean("Allow Pre End Game Platforms", true);

		SmartDashboard.putData("Encoder PID", driver.getEncoderController());
		SmartDashboard.putData("Gyro PID", driver.getGyroController());
	}

	public void SmartDashBoardPereodic() {
		SmartDashboard.putNumber("Gyro Angle", driver.getGyroAngle());
		// TODO: check if the values are corrected
		SmartDashboard.putNumber("Encoder Value ", driver.getSpeedEncoder());
		SmartDashboard.putNumber("Encoder Rate ", driver.getDistenceEncoder());

		SmartDashboard.putBoolean("In Scale", cubeSystem.getScaleSensor());
		SmartDashboard.putBoolean("In Floor", cubeSystem.getFloorSensor());
		SmartDashboard.putBoolean("In Switch", cubeSystem.getSwitchSensor());
	}
}
