package org.usfirst.frc.team4586.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CubeSystem extends Subsystem {
	Compressor compressor;
	Solenoid solenoid1;
	Solenoid solenoid2;
	Solenoid pushCubeOpen;
	Solenoid pushCubeClose;
	
	WPI_TalonSRX elevatorsMotor;
	DigitalInput scaleSensor;
	DigitalInput switchSensor;
	DigitalInput floorSensor;
    boolean isCubeCatcherOpen;
    
	public CubeSystem(Solenoid solenoid2, Solenoid solenoid1, Solenoid pushCubeOpen, Solenoid pushCubeClose, Compressor compressor,
			WPI_TalonSRX elevatorsMotor, DigitalInput scaleSensor, DigitalInput switchSensor,
			DigitalInput floorSensor) {
		this.compressor = compressor;
		this.solenoid1 = solenoid1;
		this.solenoid2 = solenoid2;
		this.pushCubeOpen = pushCubeOpen;
		this.pushCubeClose = pushCubeClose;
		this.elevatorsMotor = elevatorsMotor;
		this.scaleSensor = scaleSensor;
		this.switchSensor = switchSensor;
		this.floorSensor = floorSensor;
		this.isCubeCatcherOpen = solenoid1.get();
	}

    // checks if the pusher piston is opened
    public boolean isOpenedPusher() {
    	System.out.println("push " + pushCubeOpen.get());
    	return pushCubeOpen.get();
    }

    public void setCubePusher(boolean isOpenedPusher) {
		System.out.println("pusher turn to " + isOpenedPusher);
    	pushCubeOpen.set(isOpenedPusher);
    	pushCubeClose.set(!isOpenedPusher);
    	System.out.println("pusher is " + pushCubeOpen.get());
    }

    // checks if the platforms' pistons are opened
    public boolean isOpened() {
	return (this.isCubeCatcherOpen);
    }
    
    public void setIsCubeCatcherOpen(boolean value)
    {
	this.isCubeCatcherOpen = value;
    }
//
//    // set the pistons state
//    public void setPiston1(boolean isOpened) {
//	if (isOpened) {
//	    solenoid2.set(false);
//	} else {
//	    solenoid1.set(true);
//	}
//    }
//
//    public void setPiston2(boolean isOpened) {
//	if (isOpened) {
//	    solenoid1.set(false);
//	} else {
//	    solenoid2.set(true);
//	}
//    }

    public void setPistonR(boolean toOpen) {
	solenoid1.set(toOpen);
	System.out.println("set 1 to " + toOpen);
	System.out.println("1 " + this.solenoid1.get());
    }

    public void setPistonL(boolean toOpen) {
	solenoid2.set(toOpen);

	System.out.println("set 2 to " + toOpen);
	System.out.println("2 " + this.solenoid2.get());
    }

    // elevator's speed
    public void setSpeedElevators(double speed) {
	this.elevatorsMotor.set(speed);
    }

    public double getSpeedElevators() {
	return this.elevatorsMotor.get();
    }

    // sensors
    public boolean getFloorSensor() {
	return !floorSensor.get();
    }

    public boolean getSwitchSensor() {
    if (!switchSensor.get())
    	System.out.println("Switch Sensor");
	return !switchSensor.get();
    }

    public boolean getScaleSensor() {
	return !scaleSensor.get();
    }

    public void stopElevators() {
	this.elevatorsMotor.set(0);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
	// Set the default command for a subsystem here.
	// setDefaultCommand(new MySpecialCommand());
    }
}
