package org.usfirst.frc.team4586.robot.commands;

import org.usfirst.frc.team4586.robot.Robot;
import org.usfirst.frc.team4586.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class OpenPlatforms extends Command {

	private Climber climber;
	boolean toOpen;
	public OpenPlatforms() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this.climber = Robot.climber;
		this.toOpen=this.climber.isOpened();
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		toOpen = !toOpen;
		System.out.println("loy");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println("about to "+toOpen);
		if (Timer.getMatchTime() < 32 || SmartDashboard.getBoolean("Allow Pre End Game Platforms", false)) {
			climber.setPlatforms(toOpen);
			System.out.println("Just opened "+toOpen);
		}
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
