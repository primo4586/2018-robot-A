package org.usfirst.frc.team4586.robot.commands;

import org.usfirst.frc.team4586.robot.Robot;
import org.usfirst.frc.team4586.robot.subsystems.Climber;
import org.usfirst.frc.team4586.robot.subsystems.CubeSystem;
import org.usfirst.frc.team4586.robot.subsystems.Driver;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopAllMotors extends Command {

	private Driver driver;
	private CubeSystem cubeSystem;
	private Climber climber;

	public StopAllMotors() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		driver = Robot.driver;
		cubeSystem = Robot.cubeSystem;
		climber = Robot.climber;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		driver.stopAllWheels();
		cubeSystem.stopElevators();
		climber.stopAllClimberMotors();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
