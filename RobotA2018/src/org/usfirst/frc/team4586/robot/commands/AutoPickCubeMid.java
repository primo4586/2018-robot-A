package org.usfirst.frc.team4586.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPickCubeMid extends CommandGroup {

	public AutoPickCubeMid() {
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		addSequential(new AutoUpOpen());
		addSequential(new LiftToFloor()); //Lifts up a little the ele
    	addSequential(new AutoDrive(60));
    	try {
			Thread.sleep((long) 0.25);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addSequential(new CatchCube()); //Closes the claws
		addSequential(new LiftToSwitch()); //Lifts down the ele
		if (gameData.charAt(0) == 'L') {
			addSequential(new AutoTurn(-55)); // -60
			addSequential(new AutoDrive(210)); // 235
			addSequential(new AutoTurn(55)); // 60
			addSequential(new AutoDrive(140)); // 110
			try {
				Thread.sleep((long) 0.25);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			addSequential(new CatchCube()); //Closes the claws

		} else {
			addSequential(new AutoTurn(40)); // -60
			addSequential(new AutoDrive(180)); // 235
			addSequential(new AutoTurn(-40)); // 60
			addSequential(new AutoDrive(100)); // 110
			try {
				Thread.sleep((long) 0.25);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			addParallel(new CatchCube()); //Closes the claws
			//addSequential(new AutoTurn(-90)); // 90
			//addSequential(new AutoDrive(150)); // 150
		}
	}
}
