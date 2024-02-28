package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LiftIntakeS;

public class Manipulator extends Command { //adding more commands to the command file during automonous
boolean m_goDown;
boolean isFinished;

    public final LiftIntakeS lift;
    
    public Manipulator(LiftIntakeS liftS,boolean goDown){
        //when refering to desired time you can also say "seconds"
        //when saying intake you are refering to the subsystem intakeS
        lift = liftS;
        m_goDown = goDown;
        //when saying the word lift you are refering  to the subsystem liftS
        //when saying the word "autoSpeed" you are refering to the motor Speed
        addRequirements(liftS);
        
    }
    @Override


    public void initialize(){//intializes is when the robot starts
    lift.resetEncoders();//resets encoders back to 0, takes encoders from liftintakeS
    isFinished = false; // we arent done yet with game   
        SmartDashboard.putString("Current Action", getName());

    }
    @Override
    public void execute() { //public void execute starts the code, starts it and "executes"
        if(m_goDown){//if this is being done than the other if will be into play and if the the lifter isn't going then the ele statement basically makes it finished.
            if(Math.abs(lift.getLiftIntakeEncoderAverage()) < .1){ //will need to be changed, but I aint risking the motors eating themselves.
                lift.spinLiftIntake(0.05);
            }else{
                isFinished = true;//done, finished
            }
        } else {if(Math.abs(lift.getLiftIntakeEncoderAverage()) < .1){ //will need to be changed, but I aint risking the motors eating themselves.
                lift.spinLiftIntake(-0.05);
            }else{
                isFinished = true;
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
        return isFinished;
    }
}