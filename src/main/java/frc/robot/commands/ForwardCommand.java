package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;

public class ForwardCommand extends Command{
    private final DriveSubsystem driveSubsystem;
    private final double distance;
    private final PIDController pidController;
    boolean isFinished = false;
    Timer timer = new Timer();

    public ForwardCommand(DriveSubsystem subsystem, double distance) {
            this.driveSubsystem = subsystem;
            this.distance = distance;
            pidController = new PIDController(Constants.AutoConstants.kPForward, Constants.AutoConstants.kIForward, Constants.AutoConstants.kDForward);
            addRequirements(subsystem);
    }
    @Override
    public void initialize() {
        // Reset encoders or any necessary setup when the command starts
        driveSubsystem.resetEncoders(); // (Assuming you have a reset method in DriveSubsystem)
        isFinished = false;
        timer.start();
    }
    @Override
  public void execute() {
    double distanceTraveled = driveSubsystem.calculateDistance(); 

    if (distanceTraveled < distance) {
      driveSubsystem.driveCartesian(pidController.calculate(distanceTraveled,distance), 0, 0); // Drive forward
    }  else{
        isFinished = true;
    }
    if (timer.get() >= 5.0) {
        isFinished = true;
    }
            SmartDashboard.putNumber("Forward Distance:", distanceTraveled);
            SmartDashboard.putNumber("TIMER 1", timer.get());
  }

  @Override
  public void end(boolean interrupted) {
    driveSubsystem.driveCartesian(0, 0, 0); // Stop the robot
    timer.stop();
  }

  @Override
  public boolean isFinished() {
    return isFinished;
  }
}
