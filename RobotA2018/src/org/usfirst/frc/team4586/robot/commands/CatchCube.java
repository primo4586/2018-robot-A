package org.usfirst.frc.team4586.robot.commands;

import org.usfirst.frc.team4586.robot.Robot;
import org.usfirst.frc.team4586.robot.subsystems.CubeSystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CatchCube extends Command {

	CubeSystem cubeSystem;
	boolean isOpenedBothPistons;
	boolean isToOpen;
    public CatchCube() {
   	 this.cubeSystem=Robot.cubeSystem;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isToOpen = cubeSystem.isOpened();
		cubeSystem.setPiston1(isToOpen);
		setTimeout(1);
	    this.isOpenedBothPistons = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(isTimedOut())
    	{
    		cubeSystem.setPiston2(isToOpen);
    		this.isOpenedBothPistons = true;	
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isOpenedBothPistons;
    }

    // Called once after isFinished returns true
    protected void end() {
   
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
