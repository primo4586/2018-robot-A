package org.usfirst.frc.team4586.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLowerCloseUp extends CommandGroup {

    public AutoLowerCloseUp() {
    	addParallel(new LiftByTime(1, false)); //Lifts down the ele
    	addSequential(new CatchCube()); //Closes the claws
    }
}
