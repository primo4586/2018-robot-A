package org.usfirst.frc.team4586.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLowerCloseUp extends CommandGroup {

    public AutoLowerCloseUp() {
    	addSequential(new LiftByTime(1, false)); //Lifts down the ele
    	addSequential(new CatchCube()); //Closes the claws
    	addSequential(new LiftByTime(0.75, true)); //Lifts up a little the ele
    }
}
