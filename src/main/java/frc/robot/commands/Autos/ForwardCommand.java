package frc.robot.commands.Autos;

import java.util.List;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.LiftIntakeS;
import frc.robot.subsystems.MecanumSub;

public class ForwardCommand extends Command{
    private final MecanumSub driveSubsystem;
    private final LiftIntakeS lift;
    private final double targetDistance;
    private final PIDController pidController;
    private final PIDController pidControllerRotate;
    private final double flip;
    private final boolean isBlue;
    private final boolean m_goDown;
    private double initalHeading;
    boolean isFinished = false;
    boolean isFinishedDriving = false;
    boolean isFinishedMovingLift = false;

    public ForwardCommand(MecanumSub subsystem, double targetDistance, LiftIntakeS liftS,boolean goDown,boolean ignoreManip,boolean isBlue) {
            this.driveSubsystem = subsystem;
            this.lift = liftS;
            if (targetDistance < 0){
                this.flip = -1;
            }else{
                this.flip = 1;
            }
            m_goDown = goDown;
            this.isBlue = isBlue;
            isFinishedMovingLift = ignoreManip;
            initalHeading = driveSubsystem.getYaw();
              driveSubsystem.resetEncoders(); // (Assuming you have a reset method in DriveSubsystem)
            this.targetDistance = Math.abs(targetDistance)*2.425;
            pidControllerRotate = new PIDController(Constants.AutoConstants.kPStrafeRotate, Constants.AutoConstants.kIStrafeRotate, Constants.AutoConstants.kDStrafeRotate);
             pidController = new PIDController(Constants.AutoConstants.kPForward, Constants.AutoConstants.kIForward, Constants.AutoConstants.kDForward);
            addRequirements(subsystem);
    }
    @Override
    public void initialize() {
        // Reset encoders or any necessary setup when the command starts
        driveSubsystem.resetEncoders(); // (Assuming you have a reset method in DriveSubsystem)
        isFinished = false;
        initalHeading = driveSubsystem.getYaw();
      //  timer.start();
        SmartDashboard.putString("Current Action", getName());
    }
    @Override
  public void execute() {
    List<Double> distanceTraveled = driveSubsystem.calculateDistance(); 
    SmartDashboard.putBoolean("MovingDONE", isFinishedDriving);
    SmartDashboard.putBoolean("MovingLIFTDONE", isFinishedMovingLift);
    double currentHeading = driveSubsystem.getYaw();
    if (Math.abs(distanceTraveled.get(2)) < targetDistance) {
      if (isBlue){
      driveSubsystem.drive.driveCartesian(flip*pidController.calculate(Math.abs(distanceTraveled.get(2)),targetDistance), 0,  -flip*pidControllerRotate.calculate(currentHeading,initalHeading)); // Drive forward
      }else{
      driveSubsystem.drive.driveCartesian(flip*pidController.calculate(Math.abs(distanceTraveled.get(2)),targetDistance), 0,  -flip*pidControllerRotate.calculate(currentHeading,initalHeading)); // Drive forward
      }
    }else{
      isFinishedDriving = true;
    }

    if(isFinishedDriving){
      if(!m_goDown )
      {
        if(!isFinishedMovingLift)
        {
          if(lift.getLiftIntakeEncoderAverage() > -.001){ //will need to be changed, but I aint risking the motors eating themselves.
            lift.spinLiftIntake(-0.25);
          } else {
              lift.spinLiftIntake(0);
              isFinishedMovingLift = true;
          }
        }  
      } else {
        if (!isFinishedMovingLift){
          if(Math.abs(lift.getLiftIntakeEncoderAverage()) < .0059){ //will need to be changed, but I aint risking the motors eating themselves.
              lift.spinLiftIntake(0.25);
          }else{
              lift.spinLiftIntake(0);
              isFinishedMovingLift = true;
          }
        }
      }
    }
      
    if(isFinishedDriving && isFinishedMovingLift){
      isFinished = true;
    }
              SmartDashboard.putNumber("Forward Distance:", distanceTraveled.get(2));
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