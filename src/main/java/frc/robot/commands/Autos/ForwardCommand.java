package frc.robot.commands.Autos;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.LiftIntakeS;
import frc.robot.subsystems.MecanumSub;

public class ForwardCommand extends Command{
    private final MecanumSub driveSubsystem;
    private final LiftIntakeS lift;
    private final double distance;
    private final PIDController pidController;
    private final double flip;
    private final boolean m_goDown;
    boolean isFinished = false;
    boolean isFinishedDriving = false;
    boolean isFinishedMovingLift = false;
    Timer timer = new Timer();

    public ForwardCommand(MecanumSub subsystem, double distance, LiftIntakeS liftS,boolean goDown,boolean ignoreManip) {
            this.driveSubsystem = subsystem;
            this.lift = liftS;
            if (distance < 0){
                this.flip = -1;
            }else{
                this.flip = 1;
            }
            m_goDown = goDown;
            this.distance = Math.abs(distance);
            pidController = new PIDController(Constants.AutoConstants.kPForward, Constants.AutoConstants.kIForward, Constants.AutoConstants.kDForward);
            addRequirements(subsystem);
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
    double[] distanceTraveled = driveSubsystem.calculateDistance(false); 

    if (Math.abs(distanceTraveled[0]) < distance) {
      
      driveSubsystem.drive.driveCartesian(flip*pidController.calculate(Math.abs(distanceTraveled[0]),distance), 0, 0); // Drive forward
    }else{
      isFinishedDriving = true;
    }
    if(m_goDown){
      if(Math.abs(lift.getLiftIntakeEncoderAverage()) < .1){ //will need to be changed, but I aint risking the motors eating themselves.
          lift.spinLiftIntake(0.15);
      } else {
          isFinishedMovingLift = true;
      }
    } else {
      if(Math.abs(lift.getLiftIntakeEncoderAverage()) < .1){ //will need to be changed, but I aint risking the motors eating themselves.
          lift.spinLiftIntake(-0.15);
      }else{
          isFinishedMovingLift = true;
      }
    }
    if(isFinishedDriving && isFinishedMovingLift){
      isFinished = true;
    }
    if (timer.get() >= 2.0) {
        isFinished = true;
        timer.reset();
    }
            SmartDashboard.putNumber("Forward Distance:", distanceTraveled[0]);
            SmartDashboard.putNumber("TIMER 1", timer.get());
  }

  @Override
  public void end(boolean interrupted) {
    driveSubsystem.drive.driveCartesian(0, 0, 0); // Stop the robot
    timer.stop();
  }

  @Override
  public boolean isFinished() {
    return isFinished;
  }
}