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
	Solenoid openPlatform;
	Solenoid closePlatform;
	boolean isOpen;

	public Climber(WPI_TalonSRX climbMotor1, WPI_TalonSRX climbMotor2, Compressor compressor, Solenoid openPlatform, Solenoid closePlatform) {
		this.isOpen = false;
		this.climbMotor1 = climbMotor1;
		this.climbMotor2 = climbMotor2;
		this.compressor = compressor;
		this.openPlatform = openPlatform;
		this.closePlatform = closePlatform;
	}

    // checks if the platforms' pistons are opened
    public boolean isOpened() {
	return openPlatform.get();
    }

    // set the pistons state
    public void setPiston(boolean isOpened) {
	openPlatform.set(isOpened);
	closePlatform.set(!isOpened);
    }

    public void setSpeedClimb(double speed) {
	this.climbMotor1.set(speed);
	this.climbMotor2.set(speed);
    }
    
    public void setSpeedClimbL(double speed) {
    	this.climbMotor1.set(speed);
    }
    
    public void setSpeedClimbR(double speed) {
    	this.climbMotor2.set(speed);
    }

    public void setPlatform(boolean open) {
    	openPlatform.set(open);
    	closePlatform.set(!open);
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
