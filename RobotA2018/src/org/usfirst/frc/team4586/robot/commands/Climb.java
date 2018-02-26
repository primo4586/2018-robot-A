package org.usfirst.frc.team4586.robot.commands;
import org.usfirst.frc.team4586.robot.Robot;
import org.usfirst.frc.team4586.robot.subsystems.Climber;
import org.usfirst.frc.team4586.robot.subsystems.CubeSystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Climb extends Command {

	private Climber climber;
	private CubeSystem cubeSystem;

	public Climb() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		climber = Robot.climber;
		cubeSystem = Robot.cubeSystem;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		climber.setSpeedClimb(SmartDashboard.getNumber("Speed climb back", 0));
		cubeSystem.setSpeedElevators(SmartDashboard.getNumber("Speed climb foreward", 0));
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return cubeSystem.getFloorSensor();
	}

	// Called once after isFinished returns true
	protected void end() {
		climber.setSpeedClimb(0);
		cubeSystem.setSpeedElevators(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		climber.setSpeedClimb(0);
		cubeSystem.setSpeedElevators(0);
	}
}
