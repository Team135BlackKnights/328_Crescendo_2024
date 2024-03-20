package frc.robot.commands;

import java.util.List;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Constants.TeleConstants;
import frc.robot.LimelightHelpers;
import frc.robot.RobotContainer;
import frc.robot.subsystems.LiftIntakeS;
import frc.robot.subsystems.MecanumSub;

public class AdjustCommand extends Command{
    private final MecanumSub driveSubsystem;
    private final double[] targetDistance;
    private final PIDController pidController;
    boolean isFinished = false;
    boolean isFinishedDriving = false;
    boolean isFinishedMovingLift = false;

    public AdjustCommand(MecanumSub subsystem, double[] targetDistance) {
        this.driveSubsystem = subsystem;
        this.targetDistance = targetDistance;
         pidController = new PIDController(Constants.AutoConstants.kPForward, Constants.AutoConstants.kIForward, Constants.AutoConstants.kDForward);
        targetDistance = LimelightHelpers.getTargetPose_CameraSpace(TeleConstants.limelightName);
    }
    @Override
    public void initialize() {
        // Reset encoders or any necessary setup when the command starts
        driveSubsystem.resetEncoders(); // (Assuming you have a reset method in DriveSubsystem)
        isFinished = false;
      //  timer.start();
        SmartDashboard.putString("Current Action", getName());
    }
    @Override
  public void execute() {
    List<Double> distanceTraveled = driveSubsystem.calculateDistance(); 
    SmartDashboard.putBoolean("MovingDONE", isFinishedDriving);

    
      /*   if(Math.abs(distanceTraveled.get(2)) < targetDistance){
        driveSubsystem.drive.driveCartesian(flip*pidController.calculate(Math.abs(distanceTraveled.get(2)),targetDistance), 0, flip*-.08);
        }else{
        isFinishedDriving = true;
      }*/
      
    if(isFinishedDriving){
      isFinished = true;
    }
  
  }

  @Override
  public void end(boolean interrupted) {
    driveSubsystem.drive.driveCartesian(0, 0, 0); // Stop the robot
  }

  @Override
  public boolean isFinished() {
    return isFinished;
  }
 
}