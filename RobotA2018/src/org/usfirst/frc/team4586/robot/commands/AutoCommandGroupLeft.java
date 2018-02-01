package org.usfirst.frc.team4586.robot.commands;

import java.sql.Driver;

import org.usfirst.frc.team4586.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoCommandGroupLeft extends CommandGroup {
	public AutoCommandGroupLeft() {
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		System.out.println();
		if (gameData.charAt(0) == 'L' && gameData.charAt(1) != 'L') {
			addSequential(new AutoDrive(420));
			addSequential(new AutoTurn(90));
			addSequential(new AutoDrive(70));
			// Remember: add elevator
		} else if (gameData.charAt(0) != 'L' && gameData.charAt(1) == 'L') {
			addSequential(new AutoDrive(820));
			addSequential(new AutoTurn(90));
			addSequential(new AutoDrive(76));
			// Remember: add elevator
		} else if (gameData.charAt(0) == 'R' && gameData.charAt(1) == 'R') {
			addSequential(new AutoDrive(300));
		}
	}
}
