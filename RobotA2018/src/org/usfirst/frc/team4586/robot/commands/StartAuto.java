package org.usfirst.frc.team4586.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StartAuto extends CommandGroup {

    public StartAuto() {
    	addSequential(new LiftByTime(1, true)); //Lifts up the ele
    	addParallel(new CatchCube()); //Open the claws
    	addSequential(new AutoDriveTime(3)); //Drive a desired distance
    	addParallel(new LiftByTime(1, false)); //Lifts down the ele
    	addSequential(new CatchCube()); //Closes the claws
    	addSequential(new LiftByTime(0.75, true)); //Lifts a little the ele
    }
}
