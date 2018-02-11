/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4586.robot;

import com.ctre.phoenix.CTREJNIWrapper;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static WPI_TalonSRX leftFrontMotor;
	public static WPI_TalonSRX leftBackMotor;
	public static WPI_TalonSRX rightFrontMotor;
	public static WPI_TalonSRX rightBackMotor;
	public static WPI_TalonSRX climbMotor1;
	public static WPI_TalonSRX climbMotor2;
	public static Solenoid openLeftPlatfrom;
	public static Solenoid openRightPlatfrom;
	public static Solenoid closeLeftPlatfrom;
	public static Solenoid closeRightPlatfrom;
	public static Solenoid pushCubeOpen;
	public static Solenoid pushCubeClose;
	public static WPI_TalonSRX elevatorsMotor;
	public static AnalogGyro gyro;
	public static ADXRS450_Gyro  gyroSpi;
	public static Encoder drivingEncoder;
	public static DigitalInput scaleSensor;
	public static DigitalInput switchSensor;
	public static DigitalInput floorSensor;
	public static Compressor compressor;
	public static Solenoid solenoidCube1;
	public static Solenoid solenoidCube2;

	public static void Init() {
		// TODO Check connections
		leftFrontMotor = new WPI_TalonSRX(1);
		leftFrontMotor.setSafetyEnabled(false);
		leftBackMotor = new WPI_TalonSRX(0);
		leftBackMotor.setSafetyEnabled(false);
		rightFrontMotor = new WPI_TalonSRX(7);
		rightFrontMotor.setSafetyEnabled(false);
		rightBackMotor = new WPI_TalonSRX(6);
		rightBackMotor.setSafetyEnabled(false);
		climbMotor1 = new WPI_TalonSRX(5);
		climbMotor1.setSafetyEnabled(false);
		climbMotor2 = new WPI_TalonSRX(2);
		climbMotor2.setSafetyEnabled(false);
		elevatorsMotor = new WPI_TalonSRX(3);
		elevatorsMotor.setSafetyEnabled(false);

		compressor = new Compressor();
		compressor.setClosedLoopControl(true);
		solenoidCube1 = new Solenoid(6);
		solenoidCube2 = new Solenoid(7);
		pushCubeOpen = new Solenoid(4); //ID'S ARE 4 AND 5  - DOUBLE SOLENOID
		pushCubeClose = new Solenoid(5);
		openLeftPlatfrom = new Solenoid(2); // ID'S ARE 2 AND 3  - DOUBLE SOLENOID
		closeLeftPlatfrom = new Solenoid(3);
		openRightPlatfrom = new Solenoid(0); // ID'S ARE 0 AND 1  - DOUBLE SOLENOID
		closeRightPlatfrom = new Solenoid(1);
		
		gyroSpi = new ADXRS450_Gyro();
		//gyro = new AnalogGyro(0);
		drivingEncoder = new Encoder(9, 8);
		scaleSensor = new DigitalInput(2);
		switchSensor = new DigitalInput(0);
		floorSensor = new DigitalInput(1);
	}

	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
