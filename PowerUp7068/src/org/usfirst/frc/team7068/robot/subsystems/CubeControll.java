package org.usfirst.frc.team7068.robot.subsystems;

import org.usfirst.frc.team7068.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CubeControll extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private final SpeedController motorLeft = RobotMap.leftIntakeMotor;
	private final SpeedController motorRight = RobotMap.rightIntakeMotor;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
    public void grabCube() {
    	motorLeft.set(1.0);
    	motorRight.set(1.0);
    }
    
    public void RejectCube() {
    	motorLeft.set(-1.0);
    	motorRight.set(-1.0);
    }
    
    public void StopIntake() {
    	motorLeft.stopMotor();
    	motorRight.stopMotor();
    }

}

