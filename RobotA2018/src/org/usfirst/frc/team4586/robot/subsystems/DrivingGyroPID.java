package org.usfirst.frc.team4586.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class DrivingGyroPID implements PIDSource {

	AnalogGyro gyro;

	public DrivingGyroPID(AnalogGyro _gyro) {
		this.gyro = _gyro;
	}

	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub

	}

	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return PIDSourceType.kDisplacement;
	}

	public double pidGet() {
		// TODO Auto-generated method stub
		return this.gyro.getAngle() % 360;
	}

	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}

	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub

	}

	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
}
