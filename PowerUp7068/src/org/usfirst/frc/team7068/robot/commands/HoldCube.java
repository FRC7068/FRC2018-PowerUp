package org.usfirst.frc.team7068.robot.commands;

import org.usfirst.frc.team7068.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HoldCube extends Command {

    public HoldCube() {
        // Use requires() here to declare subsystem dependencies
       requires(Robot.cubeControll);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.cubeControll.holdCube();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.cubeControll.StopIntake();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
