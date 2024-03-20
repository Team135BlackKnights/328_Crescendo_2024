package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LiftIntakeS;

public class Manipulator extends Command { //adding more commands to the command file during automonous
int m_goDown;
boolean isFinishedMovingLift = false;
    public final LiftIntakeS lift;
    
    public Manipulator(LiftIntakeS liftS, int goDown){
        //when refering to desired time you can also say "seconds"
        //when saying intake you are refering to the subsystem intakeS
        lift = liftS;
        m_goDown = goDown;
        isFinishedMovingLift = false; // we arent done yet with game   
        //when saying the word lift you are refering  to the subsystem liftS
        //when saying the word "autoSpeed" you are refering to the motor Speed
        addRequirements(liftS);
        
    }
    @Override


    public void initialize(){//intializes is when the robot starts
        SmartDashboard.putString("Current Action", getName());
    isFinishedMovingLift = false; // we arent done yet with game   

    }
    @Override
    public void execute() { //public void execute starts the code, starts it and "executes"
    if(m_goDown == 0){
        if(!isFinishedMovingLift){
            if(lift.getLiftIntakeEncoderAverage() > -.001){ //will need to be changed, but I aint risking the motors eating themselves.
                lift.spinLiftIntake(-0.45);
            } else {
                lift.spinLiftIntake(0);
                isFinishedMovingLift = true;
            }
        }  
    } else if (m_goDown ==1){
        if (!isFinishedMovingLift){
            if(lift.getLiftIntakeEncoderAverage() < .0055){ //will need to be changed, but I aint risking the motors eating themselves.
                lift.spinLiftIntake(.45);
            }else{
                lift.spinLiftIntake(0);
                lift.stopLiftIntake();
                isFinishedMovingLift = true;
        }
        }
      }
      else if (m_goDown ==2){
        if (!isFinishedMovingLift){
            if(Math.abs(lift.getLiftIntakeEncoderAverage()) < -0.00){ //will need to be changed, but I aint risking the motors eating themselves.
                lift.spinLiftIntake(0.45);
            }else{
                lift.spinLiftIntake(0);
                isFinishedMovingLift = true;
        }
        }
      }
       // LiftIntakeS.liftIntakeMotor2.setIdleMode(IdleMode.kCoast);
    }
    @Override
    public void end(boolean interrupted) {
        lift.stopLiftIntake();
    }
    @Override
    public boolean isFinished() {
        return isFinishedMovingLift;
    }
}