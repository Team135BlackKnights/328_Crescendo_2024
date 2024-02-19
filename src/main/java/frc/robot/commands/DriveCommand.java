package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSubsystem;

public class DriveCommand extends Command{
    private final DriveSubsystem driveSubsystem;
    public DriveCommand(DriveSubsystem driveSubsystem) {
        this.driveSubsystem = driveSubsystem;
        addRequirements(driveSubsystem);
    }
    @Override
    public void initialize() {
        // Reset encoders or any necessary setup when the command starts
        driveSubsystem.resetEncoders(); // (Assuming you have a reset method in DriveSubsystem)
    }
    @Override
    public void execute() {
        // Use the subsystem to drive the robot
        double xSpeed = -RobotContainer.m_driverController.getLeftY();
   double ySpeed = RobotContainer.m_driverController.getLeftX();
   double turningSpeed = RobotContainer.m_driverController.getRightX();

   /*xSpeed = Math.pow(xSpeed, 2) * (xSpeed < 0 ? -1 : 1);
   ySpeed = Math.pow(ySpeed, 2) * (ySpeed < 0 ? -1 : 1);
   turningSpeed = Math.pow(turningSpeed, 2) * (turningSpeed < 0 ? -1 : 1);

   xSpeed = Math.abs(xSpeed) > Constants.DriveConstants.DEADBAND ? xSpeed : 0.1;
   ySpeed = Math.abs(ySpeed) > Constants.DriveConstants.DEADBAND ? ySpeed : 0.1;
   turningSpeed = Math.abs(turningSpeed) > Constants.DriveConstants.DEADBAND ? turningSpeed : 0.1;
   */
        driveSubsystem.driveCartesian(xSpeed, ySpeed, turningSpeed); // Drive forward
    
    }
    @Override
    public void end(boolean interrupted) {
        driveSubsystem.driveCartesian(0, 0, 0); // Stop the robot
    }
    @Override
    public boolean isFinished() {
        return false; // Ends when interrupted
    }
}
