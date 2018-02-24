/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team7068.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//Drive train motors
    public static WPI_TalonSRX driveTrainTalonSRX1;
    public static WPI_TalonSRX driveTrainTalonSRX2;
    public static SpeedControllerGroup driveTrainRightDrive;
    public static WPI_TalonSRX driveTrainTalonSRX4;
    public static WPI_TalonSRX driveTrainTalonSRX3;
    public static SpeedControllerGroup driveTrainLeftDrive;
    public static DifferentialDrive driveTrainArcadeTrain;
    
    // Intake system motors
    public static SpeedController leftIntakeMotor;
    public static SpeedController rightIntakeMotor;
    //Lift Motors
    public static WPI_TalonSRX liftElevatorliftMotor5;
    public static WPI_TalonSRX liftElevatorliftMotor6;
    
    static double iaccum = 0;
  
      public static void init() {
    	// Constructors for the left drives
    	driveTrainTalonSRX1 = new WPI_TalonSRX (1);
    	driveTrainTalonSRX2 = new WPI_TalonSRX (2);
    	driveTrainTalonSRX2.follow(driveTrainTalonSRX1);
   
    	// Speed Controller Group Left Drive
        driveTrainLeftDrive = new SpeedControllerGroup(driveTrainTalonSRX1,driveTrainTalonSRX2 );
        LiveWindow.addActuator("DriveTrain", "LeftDrive", driveTrainLeftDrive);
        
        // Constructors for the right drives
        driveTrainTalonSRX3 = new WPI_TalonSRX (3);
    	driveTrainTalonSRX4 = new WPI_TalonSRX (4);
    	driveTrainTalonSRX4.follow(driveTrainTalonSRX3);
          
        // Speed Controller Group Right Drive
        driveTrainRightDrive = new SpeedControllerGroup(driveTrainTalonSRX3, driveTrainTalonSRX4 );
        LiveWindow.addActuator("DriveTrain", "RightDrive", driveTrainRightDrive);
        driveTrainRightDrive.setInverted(true);
     
        // Set the differential drive parameters
        driveTrainArcadeTrain = new DifferentialDrive(driveTrainLeftDrive, driveTrainRightDrive);
        LiveWindow.addActuator("DriveTrain", "ArcadeTrain", driveTrainArcadeTrain);
        driveTrainArcadeTrain.setSafetyEnabled(false);
        driveTrainArcadeTrain.setExpiration(0.1);
        driveTrainArcadeTrain.setMaxOutput(1.0);
        driveTrainArcadeTrain.setDeadband(0.0);
        
        leftIntakeMotor = new Spark(0);
        LiveWindow.addActuator("Intake","motorLeft", (Spark) leftIntakeMotor );
        rightIntakeMotor = new Spark(1);
        LiveWindow.addActuator("Intake", "motorRight", (Spark) rightIntakeMotor);
        
        /**
         * LIFT Constructors for encoders/speedcontrollers
         */
        //Lift motor/encoder sensor contructors
        liftElevatorliftMotor5 = new WPI_TalonSRX(5);
        LiveWindow.addActuator("Lift Motor", "Lift Elevator", (WPI_TalonSRX) liftElevatorliftMotor5);
        
        liftElevatorliftMotor6 = new WPI_TalonSRX(6);
        liftElevatorliftMotor6.follow(liftElevatorliftMotor5);
        
        
        //Setups the encoder position sensor
        liftElevatorliftMotor5.clearStickyFaults(20);
        liftElevatorliftMotor6.clearStickyFaults(20);

		/* first choose the sensor */
		liftElevatorliftMotor5.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		liftElevatorliftMotor5.setSensorPhase(true);
		liftElevatorliftMotor5.setInverted(true);
		liftElevatorliftMotor6.setInverted(true);
		
		liftElevatorliftMotor5.setIntegralAccumulator(iaccum, 0, 10);

		/* Set relevant frame periods to be at least as fast as periodic rate */
		liftElevatorliftMotor5.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
		liftElevatorliftMotor5.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);

		/* set the peak and nominal outputs */
		liftElevatorliftMotor5.configNominalOutputForward(0, Constants.kTimeoutMs);
		liftElevatorliftMotor5.configNominalOutputReverse(0, Constants.kTimeoutMs);
		liftElevatorliftMotor5.configPeakOutputForward(1, Constants.kTimeoutMs);
		liftElevatorliftMotor5.configPeakOutputReverse(-1, Constants.kTimeoutMs);

		/* set closed loop gains in slot0 - see documentation */
		liftElevatorliftMotor5.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
		liftElevatorliftMotor5.config_kF(0, 0.2481, Constants.kTimeoutMs);
		liftElevatorliftMotor5.config_kP(0, 2.7, Constants.kTimeoutMs);
		liftElevatorliftMotor5.config_kI(0, 0.001, Constants.kTimeoutMs);
		liftElevatorliftMotor5.config_kD(0, 0, Constants.kTimeoutMs);
		/* set acceleration and vcruise velocity - see documentation */
		liftElevatorliftMotor5.configMotionCruiseVelocity(1443, Constants.kTimeoutMs);
		liftElevatorliftMotor5.configMotionAcceleration(1443, Constants.kTimeoutMs);
		/* zero the sensor */
		liftElevatorliftMotor5.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
        
    }
}
