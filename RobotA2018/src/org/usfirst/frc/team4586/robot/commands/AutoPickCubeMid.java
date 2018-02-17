package org.usfirst.frc.team4586.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPickCubeMid extends CommandGroup {

	public AutoPickCubeMid() {
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		addSequential(new AutoToSwitch()); //Lifts to switch at begin
    	addSequential(new CatchCube()); //Opens the claws
		addParallel(new AutoDrive(60));
		addSequential(new Wait(0.1));
		addSequential(new LiftToFloor());
		//addSequential(new Wait(0.1));
		//addSequential(new AutoDrive(25));
		addSequential(new AutoDrive(40));
		addSequential(new CatchCube());
		addSequential(new Wait(0.15));
		addSequential(new LiftToSwitch()); //Lifts down the ele
		addSequential(new Wait(0.3));
		if (gameData.charAt(0) == 'L') {
			addSequential(new AutoTurn(-55)); // -60
			addSequential(new AutoDrive(170)); // 235
			addSequential(new AutoTurn(55)); // 60
			addSequential(new AutoDrive(85)); // 110
			//addSequential(new Wait(0.5));
			addSequential(new CatchCube()); //Closes the claws

		} else {
			addSequential(new AutoTurn(48)); // -60
			addSequential(new AutoDrive(200)); // 235
			addSequential(new AutoTurn(-48)); // 60
			addSequential(new AutoDrive(60)); // 110
			addSequential(new CatchCube()); //Closes the claws
			//addSequential(new AutoTurn(-90)); // 90
			//addSequential(new AutoDrive(150)); // 150
		}
	}
}
