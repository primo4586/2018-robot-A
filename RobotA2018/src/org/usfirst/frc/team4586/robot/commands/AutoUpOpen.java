package org.usfirst.frc.team4586.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 *
 */
public class AutoUpOpen extends CommandGroup {

    public AutoUpOpen() {
    	addParallel(new LiftToSwitch()); //Lifts to switch at begin
    	addSequential(new CatchCube()); //Opens the claws
    }
}
