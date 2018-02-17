package org.usfirst.frc.team4586.robot.commands;

import org.usfirst.frc.team4586.robot.Robot;
import org.usfirst.frc.team4586.robot.subsystems.Driver;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoDrive extends Command {
	private Driver driver;
	private double setPoint;
	double kP;
	
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
    	driver.setEncoderControllerSetPoint(setPoint);
    	driver.encoderController.enable();
    	driver.setGyroControllerSetPoint(0);
    	driver.gyroController.enable();
    	setTimeout(2);
    	kP = SmartDashboard.getNumber("kPD", 0);
    	System.out.println(kP);
    }

    
    protected void execute() {
    	double error = 0 - driver.getGyro();
    	double prcw = kP * error; //aka loyshamen
    	driver.arcadeDrive(driver.getPIDspeed() * 0.75, prcw);
    }


    protected boolean isFinished() {
    	return isTimedOut() || (driver.encoderController.onTarget()); 
	}


    protected void end() {
    	driver.gyroController.disable();
    	driver.encoderController.disable();
    	driver.stopAllWheels();
    }


    protected void interrupted() {
    	driver.gyroController.disable();
    	driver.encoderController.disable();
    	driver.stopAllWheels();
    }
}
