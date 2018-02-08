package org.usfirst.frc.team4586.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSide extends CommandGroup {

	public AutoRightSide() {
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.charAt(0) == 'R' && gameData.charAt(1) != 'R') {
			addSequential(new AutoDrive(450));
			addSequential(new AutoTurn(-90));
			addSequential(new AutoDrive(175));
			// Remember: add elevator
		}
		else if (gameData.charAt(0) != 'R' && gameData.charAt(1) == 'R')
		{
			addSequential(new AutoDrive(820));
			addSequential(new AutoTurn(-90));
			addSequential(new AutoDrive(76));
			// Remember: add elevator
		}
		else if (gameData.charAt(0) == 'L' && gameData.charAt(1) == 'L')
		{
			addSequential(new AutoDrive(300));
		}
	}
}
