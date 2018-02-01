package org.usfirst.frc.team4586.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
	Compressor compressor;
	WPI_TalonSRX climbMotor1;
	WPI_TalonSRX climbMotor2;
	Solenoid openRightPlatfrom;
	Solenoid openLeftPlatfrom;
	boolean isOpen;

	public Climber(WPI_TalonSRX climbMotor1, WPI_TalonSRX climbMotor2, Compressor compressor, Solenoid openLeftPlatfrom,
			Solenoid openRightPlatfrom) {
		this.isOpen = false;
		this.climbMotor1 = climbMotor1;
		this.climbMotor2 = climbMotor2;
		this.compressor = compressor;
		this.openRightPlatfrom = openRightPlatfrom;
		this.openLeftPlatfrom = openLeftPlatfrom;
	}

	public boolean isOpened() {
		return (openRightPlatfrom.get() && openLeftPlatfrom.get());
	}

	// set the pistons state
	public void setPiston(boolean isOpened) {

		openRightPlatfrom.set(!isOpened);
		openLeftPlatfrom.set(!isOpened);
	}

	public void setSpeedClimb(double speed) {
		this.climbMotor1.set(speed);
		this.climbMotor2.set(speed);
	}

	public void setPlatforms(boolean open) {
		this.openLeftPlatfrom.set(open);
		this.openRightPlatfrom.set(open);
	}

	public void setPlatformLeft(boolean open) {
		this.openLeftPlatfrom.set(open);

	}

	public void setPlatformRight(boolean open) {
		this.openRightPlatfrom.set(open);
	}

	public void stopAllClimberMotors() {
		this.climbMotor1.set(0);
		this.climbMotor2.set(0);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
}
