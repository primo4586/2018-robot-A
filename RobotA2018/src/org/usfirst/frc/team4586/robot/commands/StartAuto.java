package org.usfirst.frc.team4586.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StartAuto extends CommandGroup {

    public StartAuto() {
    	addSequential(new LiftByTime(1, true)); //Lifts up the ele
    	addParallel(new CatchCube()); //Open the claws
    	addSequential(new AutoDriveTime(4)); //Drive a desired distance/time
    	addParallel(new AutoLowerCloseUp()); //Lowers the ele, closes the claws and lifts it up a little
    }
}
