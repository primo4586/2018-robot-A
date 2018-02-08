package org.usfirst.frc.team4586.robot.commands;

import org.usfirst.frc.team4586.robot.Robot;
import org.usfirst.frc.team4586.robot.subsystems.Driver;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoDriveTime extends Command {

	private Driver driver;
	private double time;
	
    public AutoDriveTime(double _time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.driver = Robot.driver;
		this.time = _time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		driver.resetGyro();
		driver.setSetPointGyro(0);
		driver.enableGyro();
		setTimeout(this.time);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		driver.getGyroController().setPID(0, 0, 0);
		driver.arcadeDrive(-SmartDashboard.getNumber("Max speed", 0.7), 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
