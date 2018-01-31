/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4586.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

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
	public static Solenoid pushCube;
	public static WPI_TalonSRX elevatorsMotor;
	public static AnalogGyro gyro;
	public static Encoder drivingEncoder ;
	public static DigitalInput scaleSensor;
	public static DigitalInput switchSensor;
	public static DigitalInput floorSensor;
	public static Compressor compressor;
	public static Solenoid solenoidCube1;
	public static Solenoid solenoidCube2;

	
	public static void Init()
	{
		//TODO Check connections
		leftFrontMotor = new WPI_TalonSRX(2);
		leftBackMotor = new WPI_TalonSRX(4);
		rightFrontMotor = new WPI_TalonSRX(8);
		rightBackMotor = new WPI_TalonSRX(1);
		climbMotor1 = new WPI_TalonSRX(9);
		climbMotor2 = new WPI_TalonSRX(5);
		openLeftPlatfrom =  new Solenoid(9);
		openRightPlatfrom =  new Solenoid(5);
		elevatorsMotor = new WPI_TalonSRX(2);
		gyro = new AnalogGyro(0);
		drivingEncoder = new Encoder(0,0);
		scaleSensor = new DigitalInput(0);
		switchSensor= new DigitalInput(0);
		floorSensor= new DigitalInput(0);
		compressor = new  Compressor();
		solenoidCube1 = new Solenoid(0);
		solenoidCube2 = new Solenoid(0);
		pushCube =new Solenoid(0);
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
