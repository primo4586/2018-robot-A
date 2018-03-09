package org.usfirst.frc.team4586.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoMid extends CommandGroup {

	public AutoMid() {
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		while (gameData.isEmpty()) {
			gameData = DriverStation.getInstance().getGameSpecificMessage();
		}
		addSequential(new AutoStart());
		if (gameData.charAt(0) == 'L') {
			addSequential(new AutoTurn(-55)); // -60
			addSequential(new AutoDrive(170)); // 235
			addSequential(new AutoTurn(55)); // 60
			addSequential(new AutoDrive(80)); // 110
			addSequential(new CatchCube()); // Closes the claws
		} else {
			addSequential(new AutoTurn(48)); // -60
			addSequential(new AutoDrive(190)); // 235
			addSequential(new AutoTurn(-48)); // 60
			addSequential(new AutoDrive(90)); // 110
			addSequential(new Wait(0.1));
			addSequential(new CatchCube()); // Closes the claws
			// addSequential(new AutoTurn(-90)); // 90
			// addSequential(new AutoDrive(150)); // 150
		}
	}
}
