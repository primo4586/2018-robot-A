package org.usfirst.frc.team4586.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPickUp extends CommandGroup {

	public AutoPickUp() {
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.charAt(0) == 'L') {
			addSequential(new AutoDrive(50)); //50
			addSequential(new AutoTurn(-60)); //-60
			addSequential(new AutoDrive(235)); //235
			addSequential(new AutoTurn(60)); //60
			addSequential(new AutoDrive(110)); //110
			addSequential(new AutoTurn(90)); //90
			addSequential(new AutoDrive(150)); //150
			// Remember: add elevator
		}
		else
		{
			addSequential(new AutoDrive(50)); //50
			addSequential(new AutoTurn(60)); //-60
			addSequential(new AutoDrive(235)); //235
			addSequential(new AutoTurn(-60)); //60
			addSequential(new AutoDrive(110)); //110
			addSequential(new AutoTurn(-90)); //90
			addSequential(new AutoDrive(150)); //150
		}
	}
}
