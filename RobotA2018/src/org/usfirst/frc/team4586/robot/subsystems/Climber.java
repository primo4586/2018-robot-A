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
	Solenoid openPlatformRight;
	Solenoid closePlatformRight;
	Solenoid openPlatformLeft;
	Solenoid closePlatformLeft;
	boolean isOpen;

	public Climber(WPI_TalonSRX climbMotor1, WPI_TalonSRX climbMotor2, Compressor compressor, Solenoid openPlatformLeft, Solenoid closePlatformLeft,
			Solenoid openPlatformRight, Solenoid closePlatformRight) {
		this.isOpen = false;
		this.climbMotor1 = climbMotor1;
		this.climbMotor2 = climbMotor2;
		this.compressor = compressor;
		this.openPlatformRight = openPlatformRight;
		this.closePlatformRight = closePlatformRight;
		this.openPlatformLeft = openPlatformLeft;
		this.closePlatformLeft = closePlatformLeft;
	}

    // checks if the platforms' pistons are opened
    public boolean isOpened() {
	return openPlatformRight.get();
    }

    // set the pistons state
    public void setPiston(boolean isOpened) {

	openPlatformRight.set(isOpened);
	closePlatformRight.set(!isOpened);
	
	openPlatformLeft.set(isOpened);
	closePlatformLeft.set(!isOpened);
    }

    public void setSpeedClimb(double speed) {
	this.climbMotor1.set(speed);
	this.climbMotor2.set(speed);
    }

    public void setPlatforms(boolean open) {
    	openPlatformRight.set(open);
    	closePlatformRight.set(!open);
    	
    	openPlatformLeft.set(open);
    	closePlatformLeft.set(!open);
    }

    public void setPlatformLeft(boolean open) {
	this.closePlatformLeft.set(!open);
	this.openPlatformLeft.set(open);

    }

    public void setPlatformRight(boolean open) {
	this.openPlatformRight.set(open);
	this.closePlatformRight.set(!open);
    }

    public void stopAllClimberMotors() {
	this.climbMotor1.set(0);
	this.climbMotor2.set(0);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
	// Set the default command for a subsystem here.
	// setDefaultCommand(new MySpecialCommand());
    }
}
