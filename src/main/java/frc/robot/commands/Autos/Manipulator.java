package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LiftIntakeS;

public class Manipulator extends Command {
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


    public void initialize(){
    lift.resetEncoders();
    isFinished = false;    
        SmartDashboard.putString("Current Action", getName());

    }
    @Override
    public void execute() {
        if(m_goDown){
            if(Math.abs(lift.getLiftIntakeEncoderAverage()) < .1){ //will need to be changed, but I aint risking the motors eating themselves.
                lift.spinLiftIntake(0.05);
            }else{
                isFinished = true;
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