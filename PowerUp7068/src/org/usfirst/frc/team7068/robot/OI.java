/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team7068.robot;

import org.usfirst.frc.team7068.robot.commands.AutonomousCommand;
import org.usfirst.frc.team7068.robot.commands.DriveRobot;
import org.usfirst.frc.team7068.robot.commands.GrabCube;
import org.usfirst.frc.team7068.robot.commands.HoldCube;
import org.usfirst.frc.team7068.robot.commands.LiftToPickUp;
import org.usfirst.frc.team7068.robot.commands.LiftToScale;
import org.usfirst.frc.team7068.robot.commands.LiftToSwitch;
import org.usfirst.frc.team7068.robot.commands.LiftToTravel;
import org.usfirst.frc.team7068.robot.commands.RejectCube;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	// Public Declares JoySticks
    public static XboxController driveJoyStick;
    public static XboxController actuatorJoyStick;
    //Joystick buttons
    public static JoystickButton IntakeOut;
    public static JoystickButton IntakeIn;
    public static JoystickButton  holdCube;
    public JoystickButton buttonLiftToPickup;
    public JoystickButton butttonLiftSwitch;
    public JoystickButton butttonLiftScale;
    public JoystickButton butttonLiftTravel;

    public OI() {
    	//set constructor for controller
        driveJoyStick = new XboxController(0);
        actuatorJoyStick = new XboxController(1);
        
        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("DriveRobot", new DriveRobot());
        //Intake / OutTake Motors
        IntakeIn = new JoystickButton(actuatorJoyStick, 5);
        IntakeIn.whileHeld(new GrabCube());
        IntakeOut = new JoystickButton(actuatorJoyStick, 6);
        IntakeOut.whileHeld(new RejectCube());
      //Button hold cube
	    Button holdCube = new JoystickButton(actuatorJoyStick, 7);
	   	holdCube.whileHeld(new HoldCube());
        
      //---------------------------------------------
        //button declares for commands to subsystems
        //---------------------------------------------
        // Button #1
        buttonLiftToPickup = new JoystickButton(actuatorJoyStick, 1);
        buttonLiftToPickup.whenPressed(new LiftToPickUp());
        // Button #2
        butttonLiftTravel = new JoystickButton(actuatorJoyStick, 2);
        butttonLiftTravel.whenPressed(new LiftToTravel());
        // Button #3
        butttonLiftSwitch = new JoystickButton(actuatorJoyStick, 3);
        butttonLiftSwitch.whenPressed(new LiftToSwitch());
        // Button #4
        butttonLiftScale = new JoystickButton(actuatorJoyStick, 4);
        butttonLiftScale.whenPressed(new LiftToScale());
        
        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("LiftToTravel", new LiftToTravel());
        SmartDashboard.putData("LiftToSwitch", new LiftToSwitch());
        SmartDashboard.putData("LiftToScale", new LiftToScale());
        SmartDashboard.putData("LiftToPickup", new LiftToPickUp());
        
    }

    
    public XboxController getDriveJoyStick() {
        return driveJoyStick;
    }
    
    public XboxController getactuatorJoyStick() {
        return driveJoyStick;
    }

}
