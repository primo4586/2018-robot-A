package org.usfirst.frc.team4586.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 *
 */
public class AutoUpOpen extends CommandGroup {

    public AutoUpOpen() {
    	addParallel(new LiftByTime(1.5, true)); //Lifts down the ele
    	addSequential(new CatchCube()); //Closes the claws
    }
}
