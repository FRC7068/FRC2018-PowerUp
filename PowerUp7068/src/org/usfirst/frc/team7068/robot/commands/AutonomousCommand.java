package org.usfirst.frc.team7068.robot.commands;


import org.usfirst.frc.team7068.robot.AutoDistances;
import org.usfirst.frc.team7068.robot.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *  Autonomous Dive Straight
 */
public class AutonomousCommand extends CommandGroup {
	
	 public AutonomousCommand() {
		// A command group will require all of the subsystems that each member would require.
			requires(Robot.driveTrain);
			addParallel(new autoDriveStraight(AutoDistances.driveToSwitch));
			addParallel(new LiftToTravel());
	 }
}
