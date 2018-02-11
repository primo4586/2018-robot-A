package org.usfirst.frc.team4586.robot.commands;

import org.usfirst.frc.team4586.robot.Robot;
import org.usfirst.frc.team4586.robot.subsystems.Driver;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDrive extends Command {

	private Driver driver;
	private double setPoint;

	public AutoDrive(double setPoint) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis)
		driver = Robot.driver;
		this.setPoint = setPoint;

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		driver.resetEncoder();
		driver.resetGyro();
		// setPoint = SmartDashboard.getNumber("Encoder Setpoint",0);
		driver.setEncoderControllerSetPoint(setPoint);
		driver.enableEncoder();
		driver.setSetPointGyro(0);
		driver.enableGyro();
		setTimeout(1);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// System.out.println("Drive: " + SmartDashboard.getNumber("Encoder
		// Distance",0) + ", Stiya: "
		// + SmartDashboard.getNumber("Gyro Value",0));
//		driver.getGyroController().setPID(0.08, 0, 0.295);
//		driver.getEncoderController().setPID(0.013, 0, 0.2);
		driver.arcadeDrive(driver.getSpeed() * 0.5, -driver.getRotation());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut() || driver.getEncoderController().onTarget(); // &&
																			// driver.getBLSpeed()
																			// ==
																			// 0
																			// &&
																			// driver.getBRSpeed()
																			// ==
																			// 0;
	}

	// Called once after isFinished returns true
	protected void end() {
    	driver.getGyroController().disable();
    	driver.getEncoderController().disable();
    	driver.setLeft(0);
    	driver.setRight(0);
		// System.out.println("Drive: " + SmartDashboard.getNumber("Encoder
		// Distance",0) + ", Stiya: "
		// + SmartDashboard.getNumber("Gyro Value",0));
		System.out.println(setPoint);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
    	driver.getGyroController().disable();
    	driver.getEncoderController().disable();
    	driver.setLeft(0);
    	driver.setRight(0);
		// System.out.println("Drive: " + SmartDashboard.getNumber("Encoder
		// Distance",0) + ", Stiya: "
		// + SmartDashboard.getNumber("Gyro Value",0));
		System.out.println(setPoint);
	}
}
