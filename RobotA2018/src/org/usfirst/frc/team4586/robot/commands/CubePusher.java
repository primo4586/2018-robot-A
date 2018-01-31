package org.usfirst.frc.team4586.robot.commands;

import org.usfirst.frc.team4586.robot.Robot;
import org.usfirst.frc.team4586.robot.subsystems.CubeSystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CubePusher extends Command {
	CubeSystem cubeSystem;
	boolean isOpened;
	

    public CubePusher() {
    	this.cubeSystem=Robot.cubeSystem;
     	 this.isOpened = false;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isOpened =cubeSystem.isOpenedPusher();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	cubeSystem.setCubePusher(!isOpened);
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
