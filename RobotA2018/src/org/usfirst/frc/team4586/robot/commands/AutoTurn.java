package org.usfirst.frc.team4586.robot.commands;

import org.usfirst.frc.team4586.robot.Robot;
import org.usfirst.frc.team4586.robot.subsystems.Driver;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoTurn extends Command {
	public Driver driver;
	double setPoint;

	public AutoTurn(double setPoint) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		driver = Robot.driver;
		this.setPoint = setPoint;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// setPoint = SmartDashboard.getNumber("GYRO Setpoint",0);
		setTimeout(1.15);
		driver.setGyroControllerSetPoint(setPoint);
		driver.gyroController.enable();
		driver.resetGyro();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		driver.gyroController.setPID(0.08, 0, 0.3);
		if (Math.abs(driver.getGyro() - setPoint) >= 2)
			driver.arcadeDrive(-driver.getPIDRotationInPlace() * 0.95, 0);
		else
			driver.arcadeDrive(0, 0);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("Turned: " + SmartDashboard.getNumber("Gyro Value", 0));
		driver.gyroController.disable();
		driver.arcadeDrive(0, 0);
		System.out.println(setPoint);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		System.out.println("Turned: " + SmartDashboard.getNumber("Gyro Value", 0));
		driver.gyroController.disable();
		driver.arcadeDrive(0, 0);
	}
}
