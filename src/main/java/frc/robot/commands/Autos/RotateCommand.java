package frc.robot.commands.Autos;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.MecanumControllerCommand;
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
        this.angleTolerance = 1.0; // Adjust tolerance if necessary
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
        double angleError = targetAngle - (currentHeading - initialHeading); // Account for wrap-around 
        if (Math.abs(targetAngle - (currentHeading - initialHeading)) <= angleTolerance){
            isFinished = true;
        }
        if (timer.get() >= .25) {
            isFinished = true;
        }

        driveSubsystem.drive.driveCartesian(0, 0, pidController.calculate(currentHeading,targetAngle)); 

        SmartDashboard.putNumber("TIMER3", timer.get());

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