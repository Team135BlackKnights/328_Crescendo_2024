package frc.robot.commands.Autos.AutoCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeS;

public class StrafeCommand extends Command{
    private final DriveSubsystem driveSubsystem;
    private final IntakeS intake;
    private final double targetDistance; // e.g., in meters 
    private final double headingCorrectionGain; // For gyro 
    private final PIDController pidController;
    boolean isFinished = false;
    private final double flip;
    private final boolean shouldIntake;
    private double initialHeading; 
Timer timer = new Timer();

    public StrafeCommand(DriveSubsystem driveSubsystem,  IntakeS intakeS, double targetDistance, boolean intakeWhileMoving) {
        this.driveSubsystem = driveSubsystem;
        this.intake = intakeS;
        if (targetDistance < 0){
            this.flip = -1;
        }else{
            this.flip = 1;
        }
        shouldIntake = intakeWhileMoving;
        this.targetDistance = Math.abs(targetDistance);
        pidController = new PIDController(Constants.AutoConstants.kPStrafe, Constants.AutoConstants.kIStrafe, Constants.AutoConstants.kDStrafe);
        this.headingCorrectionGain = 0.03; // Adjust for gyro correction

        addRequirements(driveSubsystem,intakeS); 
    }

    @Override
    public void initialize() {
        driveSubsystem.resetEncoders(); 
        timer.start();
        initialHeading = driveSubsystem.getYaw(); // Capture starting heading
        isFinished = false;
        SmartDashboard.putString("Current Action", getName());
    }

    @Override
    public void execute() {
        double distanceTraveled = driveSubsystem.calculateDistance(); // Make sure this factors in sideways movement
        double currentHeading = driveSubsystem.getYaw();
        double headingError = initialHeading - currentHeading;
        double turnAdjustment = headingError * headingCorrectionGain;

        if (distanceTraveled < targetDistance) {
            if (shouldIntake){
                intake.spinIntake(.75);
              }
            driveSubsystem.driveCartesian(0, flip*pidController.calculate(distanceTraveled,targetDistance), turnAdjustment); 
        } else{
            isFinished = true;
        }
        if (timer.get() >= 2.0) {
            isFinished = true;
            timer.reset();
        }
        SmartDashboard.putNumber("Strafe Distance:", distanceTraveled);
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.driveCartesian(0, 0, 0); 
        intake.spinIntake(0);
        timer.stop();
    }

    @Override
    public boolean isFinished() {
        return isFinished; 
    }

}