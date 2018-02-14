package org.usfirst.frc.team4586.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPickCubeMid extends CommandGroup {

	public AutoPickCubeMid() {
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.charAt(0) == 'L') {
			addSequential(new AutoDrive(50)); // 50
			addSequential(new AutoTurn(-60)); // -60
			addSequential(new AutoDrive(235)); // 235
			addSequential(new AutoTurn(60)); // 60
			addSequential(new AutoDrive(110)); // 110
			addSequential(new AutoTurn(90)); // 90
			addSequential(new AutoDrive(150)); // 150
			// Remember: add elevator
		} else {
			addSequential(new AutoLowerCloseUp());
	    	addSequential(new LiftByTime(0.5, true)); //Lifts up a little the ele
			addSequential(new AutoDrive(61)); // 50
			addSequential(new CatchCube()); //Closes the claws
			addSequential(new LiftToSwitch()); //Lifts down the ele
			addSequential(new AutoTurn(34)); // -60
			addSequential(new AutoDrive(180)); // 235
			addSequential(new AutoTurn(-34)); // 60
			addSequential(new AutoDrive(60)); // 110
			addParallel(new CatchCube()); //Closes the claws
			//addSequential(new AutoTurn(-90)); // 90
			//addSequential(new AutoDrive(150)); // 150
		}
	}
}
