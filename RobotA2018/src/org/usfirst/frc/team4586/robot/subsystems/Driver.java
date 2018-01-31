package org.usfirst.frc.team4586.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class Driver extends Subsystem {

	WPI_TalonSRX leftFrontMotor;
	WPI_TalonSRX leftBackMotor;
	WPI_TalonSRX rightFrontMotor;
	WPI_TalonSRX rightBackMotor;
	AnalogGyro gyro;
	Encoder encoder ;
	SpeedControllerGroup rightController, leftController;
	DifferentialDrive diffDrive;
	
	private DrivingGyroPID gyroSource;
	private DrivingRotationPID rotationPID;
	public PIDController gyroController;
	public PIDController encoderController;
	public DrivingSpeedPID speed;
	//public DrivingRotationPID rotation;
	public DrivingRotationPID rotation;
	
	
	
	public Driver(WPI_TalonSRX leftFrontMotor,WPI_TalonSRX leftBackMotor ,WPI_TalonSRX rightFrontMotor,WPI_TalonSRX rightBackMotor ,AnalogGyro gyro,Encoder drivingEncoder) 
	{
		this.leftFrontMotor = leftFrontMotor;
		this.leftBackMotor = leftBackMotor;
		this.rightFrontMotor = rightFrontMotor;
		this.rightBackMotor =rightBackMotor;
		this.gyro=gyro;
		this.encoder=drivingEncoder;
		this.encoder.setDistancePerPulse(0.47877872);
		this.rightController = new SpeedControllerGroup(this.rightBackMotor, this.rightFrontMotor);
		this.leftController = new SpeedControllerGroup(this.leftBackMotor, this.leftFrontMotor);
		this.diffDrive = new DifferentialDrive(this.leftController, this.rightController);
		
		speed = new DrivingSpeedPID();
		//rotation = new DrivingRotationPID();
		rotation = new DrivingRotationPID();
		
		
		this.gyroSource = new DrivingGyroPID(this.gyro);
		this.rotationPID = new DrivingRotationPID();
		
		encoderController = new PIDController(0.1, 0.0, 0.395, encoder, speed);
		encoderController.setAbsoluteTolerance(0.7);
		
		gyroController = new PIDController(0.13, 0.005, 0.023, gyroSource, rotation); //Driving forward PID 0.13, 0.005, 0.023
		gyroController.setAbsoluteTolerance(1);
	}
	
	public PIDController getGyroController(){
		return this.gyroController;
	}
	
	public void setSetPoint(double setpoint) {
		this.gyroController.setSetpoint(setpoint);
	}
	
	public void enable() {
		this.gyroController.enable();
	}
	
	public void disable() {
		this.gyroController.disable();
	}
	
	public double getRotation() {
		return this.rotationPID.getRotation();
	}
	
	//wheels
	  public double getWheelSpeedLeftFront() 
	  {
		  return leftFrontMotor.get();
	  }
	  public double getWheelSpeedLeftBack() 
	  {
		  return leftBackMotor.get();
	  }
	  public double getWheelSpeedRightBack() 
	  {
		  return rightBackMotor.get();
	  }
	  public double getWheelSpeedRightFront() 
	  {
		  return rightFrontMotor.get();
	  }
	  //stops the wheels
	  public void stopAllWheels()
		{
			this.leftBackMotor.set(0);
			this.leftFrontMotor.set(0);
			this.rightFrontMotor.set(0);
			this.rightBackMotor.set(0);
		}
	  
	  //gyro
		public void resetGyro()
		{
			this.gyro.reset();
		}
		public double getGyroAngle()
		{
			return this.gyro.getAngle();
		}
		//calibrates the gyro
		public void calibrateGyro()
		{
			this.gyro.calibrate();
		}
		

		//encoder
		public double getSpeedEncoder()
		{
			return encoderController.get();
		}
		public double getDistenceEncoder()
		{
			return getEncoderDistance();
		}
		public double getEncoderDistance()
		{
			return -encoder.getDistance();
		}
		public void resetEncoder()
		{
			this.encoderController.reset();
		}
		
		//drive
		public void arcadeDrive(double speed, double rotation)
		{
			this.diffDrive.arcadeDrive(speed, rotation);
		}
		
		public void setGyroControllerSetPoint(double setPoint)
		{
			gyroController.setSetpoint(setPoint);
		}
		
		public void setEncoderControllerSetPoint(double setPoint)
		{
			encoderController.setSetpoint(setPoint);
		}
		
		
		public void setLeft(double speed)
		{
			this.leftFrontMotor.set(speed);
			this.leftBackMotor.set(speed);
		}
		
		public void setRight(double speed)
		{
			this.rightFrontMotor.set(speed);
			this.rightBackMotor.set(speed);
		}
		
		public double getPIDRotationInPlace() {
			return rotation.getRotation();
		}
		
		public double getPIDspeed() {
			return speed.getSpeed();
		}
		
		public double getGyro()
		{
			return gyro.getAngle() % 360;
		}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

