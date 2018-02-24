package org.usfirst.frc.team7068.robot.subsystems;

import org.usfirst.frc.team7068.robot.RobotMap;

import edu.wpi.first.wpilibj.Preferences;
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
	
//  SmartDashBoard , SuffleBoard 
	final String IntakeSpeed ="IntakeSpeed";
	final String OutTakeSpeed = "OutTakeSpeed";
	final String HoldSpeed = "HoldSpeed";
	
	final double SpeedIn = 1.0;
	final double SpeedOut = -0.5;
	final double SpeedHold = 0.2;
	private  double setSpeed;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
    public void grabCube() {
    	double backup = SpeedIn;
    	setSpeed = getPreferencesDouble(IntakeSpeed ,backup); 
    	
    	motorLeft.set(setSpeed);
    	motorRight.set(setSpeed);
    }
    
    public void RejectCube() {
    	double backup = SpeedIn;
    	setSpeed = getPreferencesDouble(OutTakeSpeed,backup); 
    	
    	motorLeft.set(setSpeed);
    	motorRight.set(setSpeed);
    }
    
    public void holdCube() {
 	   double backup = SpeedHold;
 	   setSpeed = getPreferencesDouble(HoldSpeed ,backup);
 	   
 	   motorLeft.set(0.25);
 	   motorRight.set(0.25);
    }
    
    public void StopIntake() {
    	motorLeft.stopMotor();
    	motorRight.stopMotor();
    }
    
    
    /**
   	 * Retrieve numbers from the preferences table. If the specified key is in
   	 * the preferences table, then the preference value is returned. Otherwise,
   	 * return the backup value, and also start a new entry in the preferences
   	 * table. */
    private static double getPreferencesDouble(String key, double backup) {
    	Preferences preferences = Preferences.getInstance();
    	if(!preferences.containsKey(key)) {
    		preferences.putDouble(key, backup);
    	}
    	return preferences.getDouble(key, backup);
    		
    	}

}

