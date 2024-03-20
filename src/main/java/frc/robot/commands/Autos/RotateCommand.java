package frc.robot.commands.Autos;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.MecanumSub;

public class RotateCommand extends Command{
    private final MecanumSub driveSubsystem;
    private final double targetAngle; // Degrees
    private final double angleTolerance; // Degrees
    boolean isFinished = false;
    private double initialHeading;
    private final PIDController pidController;
    Timer timer = new Timer();

    public RotateCommand(MecanumSub driveSubsystem, double targetAngle) {
        this.driveSubsystem = driveSubsystem;
        this.targetAngle = targetAngle;
        this.angleTolerance = 5; // Adjust tolerance if necessary
        pidController = new PIDController(Constants.AutoConstants.kPRotate, Constants.AutoConstants.kIRotate, Constants.AutoConstants.kDRotate); // Adjust constants as needed
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        initialHeading = driveSubsystem.getYaw(); 
        isFinished = false;
        timer.start();
        SmartDashboard.putString("Current Action", getName());
    }

    @Override
    public void execute() {
         double currentHeading = driveSubsystem.getYaw();
       //  double error = 
       double angleError = targetAngle - currentHeading;
       angleError = (angleError + 180.0) % 360.0 - 180.0;
        SmartDashboard.putNumber("angleerroR", Math.abs(angleError));
// Check if the absolute angle error is within tolerance
        if (Math.abs(angleError) <= angleTolerance) {
            isFinished = true;
        }     
        if (timer.get() > 1){
            isFinished = true;
        }
        driveSubsystem.drive.driveCartesian(0, 0, pidController.calculate(currentHeading,targetAngle)); 


    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.drive.driveCartesian(0, 0, 0); 
        timer.stop();
        timer.reset();


    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}