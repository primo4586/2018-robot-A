/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4586.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GamepadBase;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4586.robot.commands.ArcadeDrive;
import org.usfirst.frc.team4586.robot.commands.AutoDrive;
import org.usfirst.frc.team4586.robot.commands.AutoMid;
import org.usfirst.frc.team4586.robot.commands.AutoOnlyDriveByTime;
import org.usfirst.frc.team4586.robot.commands.AutoSideSwitchLeft;
import org.usfirst.frc.team4586.robot.commands.AutoSideSwitchRight;
import org.usfirst.frc.team4586.robot.commands.LiftCubeByJoystick;
import org.usfirst.frc.team4586.robot.subsystems.Climber;
import org.usfirst.frc.team4586.robot.subsystems.CubeSystem;
import org.usfirst.frc.team4586.robot.subsystems.Driver;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static OI m_oi;
	public static Climber climber;
	public static CubeSystem cubeSystem;
	public static Driver driver;
	Command m_autonomousCommand;
	SendableChooser<Integer> m_chooser = new SendableChooser<>();

	// int i = 0;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	// @Override
	public void robotInit() {
		RobotMap.Init();
		RobotMap.gyro.calibrate();
		climber = new Climber(RobotMap.climbMotor1, RobotMap.climbMotor2, RobotMap.compressor, RobotMap.openPlatfrom,
				RobotMap.closePlatfrom , RobotMap.openShloplopSolenoid , RobotMap.closeShloplopSolenoid);

		cubeSystem = new CubeSystem(RobotMap.solenoidCube2, RobotMap.solenoidCube1, RobotMap.pushCubeOpen,
				RobotMap.pushCubeClose, RobotMap.compressor, RobotMap.elevatorsMotor, RobotMap.scaleSensor,
				RobotMap.switchSensor, RobotMap.floorSensor);
		driver = new Driver(RobotMap.leftFrontMotor, RobotMap.leftBackMotor, RobotMap.rightFrontMotor,
				RobotMap.rightBackMotor, RobotMap.gyro, RobotMap.drivingEncoder);
		m_oi = new OI();
		this.cubeSystem.setCanUseElevator(true);
		this.cubeSystem.setCubePusher(true);
		this.cubeSystem.setPistonL(false);
		this.cubeSystem.setPistonR(false);
		this.climber.setShloplop(false);
		this.climber.setPlatform(true);
		m_chooser.addDefault("Auto Drive Only Straight", 0);
		m_chooser.addObject("Auto Middle Pickup", 1);
		m_chooser.addObject("Auto Left Switch", 2);
		m_chooser.addObject("Auto Right Switch", 3);
		SmartDashBoardRobotInit();
		SmartDashboard.putData("Auto mode", m_chooser);
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(480, 360);
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
	
	@Override
	public void autonomousInit() {
		this.cubeSystem.setCanUseElevator(true);
		this.cubeSystem.setCubePusher(true);
		this.cubeSystem.setPistonL(false);
		this.cubeSystem.setPistonR(false);
		this.climber.setShloplop(false);
		this.climber.setPlatform(true);
		if (m_chooser.getSelected() == 0) {
			m_autonomousCommand = new AutoOnlyDriveByTime();
		} else if (m_chooser.getSelected() == 1) {
			m_autonomousCommand = new AutoMid();
		} else if (m_chooser.getSelected() == 2) {
			m_autonomousCommand = new AutoSideSwitchLeft();
		} else if (m_chooser.getSelected() == 3) {
			m_autonomousCommand = new AutoSideSwitchRight();
		}
		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			System.out.println("game data: " + DriverStation.getInstance().getGameSpecificMessage());
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
		System.out.println("init");
		this.cubeSystem.setCanUseElevator(true);
		this.cubeSystem.setCubePusher(true);
		this.cubeSystem.setPistonL(false);
		this.cubeSystem.setPistonR(false);
		this.climber.setShloplop(false);
		this.climber.setPlatform(true);
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
		// SmartDashboard.putNumber("periodic", ++i);
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}

	public void SmartDashBoardRobotInit() {

		SmartDashboard.putNumber("Elevator Speed", 0.7);
		SmartDashboard.putBoolean("Climbing Motor", false);
		//SmartDashboard.putNumber("Delay Hands", 0.1);
		//SmartDashboard.putNumber("Driving Direction", -1);
		SmartDashboard.putNumber("Max Speed", 0.7);
		//SmartDashboard.putNumber("kP", 0.11); // 0.11
		//SmartDashboard.putNumber("kPD", 0.15);
//		SmartDashboard.putNumber("Auto Direction", 1);
		SmartDashboard.putNumber("Auto Time Straight", 4);
//		SmartDashboard.getNumber("Max Rotation Speed", 0.8);
		// sensors
		SmartDashboard.putNumber("Gyro Angle", driver.getGyroAngle());
		// TODO: check if the values are corrected
		SmartDashboard.putNumber("Encoder Distance", driver.getSpeedEncoder());
		//SmartDashboard.putNumber("Encoder Value", driver.getEncoderValue());
		//SmartDashboard.putNumber("Encoder Rate", driver.getSpeedEncoder());
		//SmartDashboard.putNumber("kD", 0.13); // 0.13
		//SmartDashboard.putBoolean("In Scale", cubeSystem.getScaleSensor());
		SmartDashboard.putBoolean("In Floor", cubeSystem.getFloorSensor());
		SmartDashboard.putBoolean("In Switch", cubeSystem.getSwitchSensor());
		//SmartDashboard.putBoolean("Use Gyro", false);
		SmartDashboard.putBoolean("Allow Pre End Game Platforms", false);
//		SmartDashboard.putBoolean("sol 1", false);
//		SmartDashboard.putBoolean("sol 2", false);
//		SmartDashboard.putBoolean("sol 3", false);
//		SmartDashboard.putBoolean("sol 4", false);
		//SmartDashboard.putData("Encoder PID", driver.getEncoderController());
		// SmartDashboard.putData("Gyro PID", driver.getGyroController());
		//SmartDashboard.putNumber("Speed climb left", 1);
		//SmartDashboard.putNumber("Speed climb right", 1);
		//SmartDashboard.putNumber("Ultrasonic value", RobotMap.ultrasonic.getValue());
	}

	public void SmartDashBoardPereodic() {
//		SmartDashboard.putNumber("elevator power", RobotMap.elevatorsMotor.get());
		SmartDashboard.putBoolean("Compressor on",RobotMap.compressor.enabled());
		//SmartDashboard.putString("game data", DriverStation.getInstance().getGameSpecificMessage());
		SmartDashboard.putNumber("Gyro Angle", driver.getGyro());
		// TODO: check if the values are cosrrected
		SmartDashboard.putNumber("Encoder Distance", driver.getEncoderDistance());
		//SmartDashboard.putNumber("Encoder Value", driver.getEncoderValue());
		//SmartDashboard.putNumber("Encoder Rate", driver.getSpeedEncoder());
		SmartDashboard.putNumber("elevator current", RobotMap.elevatorsMotor.getOutputCurrent());
		SmartDashboard.putNumber("Left front current", RobotMap.leftFrontMotor.getOutputCurrent());
		SmartDashboard.putNumber("Left back current", RobotMap.leftBackMotor.getOutputCurrent());
		SmartDashboard.putNumber("Right front current", RobotMap.rightFrontMotor.getOutputCurrent());
		SmartDashboard.putNumber("Right back current", RobotMap.rightBackMotor.getOutputCurrent());
		//SmartDashboard.putNumber("climb 1 current", RobotMap.climbMotor1.getOutputCurrent());
		//SmartDashboard.putNumber("climb 2 current", RobotMap.climbMotor2.getOutputCurrent());
		//SmartDashboard.putNumber("Ultrasonic value", RobotMap.ultrasonic.getValue());

		//SmartDashboard.putBoolean("In Scale", cubeSystem.getScaleSensor());
		SmartDashboard.putBoolean("In Floor", cubeSystem.getFloorSensor());
		SmartDashboard.putBoolean("In Switch", cubeSystem.getSwitchSensor());

		SmartDashboard.putBoolean("Compressor Pressure Switch", RobotMap.compressor.getPressureSwitchValue());
		
//		SmartDashboard.putNumber("elevator speed", RobotMap.elevatorsMotor.get());
	}
}
