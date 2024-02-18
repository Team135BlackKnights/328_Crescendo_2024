package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;

public class RotateCommand extends Command{
    private final DriveSubsystem driveSubsystem;
    private final double targetAngle; // Degrees
    private final double angleTolerance; // Degrees
    boolean isFinished = false;
    private double initialHeading;
    private final PIDController pidController;
    Timer timer = new Timer();

    public RotateCommand(DriveSubsystem driveSubsystem, double targetAngle) {
        this.driveSubsystem = driveSubsystem;
        this.targetAngle = targetAngle;
        this.angleTolerance = 1.0; // Adjust tolerance if necessary
        pidController = new PIDController(Constants.AutoConstants.kPRotate, Constants.AutoConstants.kIRotate, Constants.AutoConstants.kDRotate); // Adjust constants as needed
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        initialHeading = driveSubsystem.getYaw(); 
        isFinished = false;
        timer.start();

    }

    @Override
    public void execute() {
        double currentHeading = driveSubsystem.getYaw();
        double angleError = targetAngle - (currentHeading - initialHeading); // Account for wrap-around 
        if (Math.abs(targetAngle - (driveSubsystem.getYaw() - initialHeading)) <= angleTolerance){
            isFinished = true;
        }
        if (timer.get() >= 5.0) {
            isFinished = true;
        }
        double rotationOutput = pidController.calculate(currentHeading, targetAngle);
        rotationOutput = Math.min(rotationOutput, pidController.calculate(angleError,targetAngle)); // Cap the output  
        rotationOutput = Math.max(-1.0, Math.min(rotationOutput, 1.0)); 

        driveSubsystem.driveCartesian(0, 0, rotationOutput); 

        SmartDashboard.putNumber("Current Heading", currentHeading);
        SmartDashboard.putNumber("TIMER3", timer.get());

    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.driveCartesian(0, 0, 0); 
        timer.stop();

    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
