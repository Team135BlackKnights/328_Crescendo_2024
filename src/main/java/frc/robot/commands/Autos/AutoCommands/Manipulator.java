package frc.robot.commands.Autos.AutoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LiftIntakeS;
import com.revrobotics.CANSparkBase.IdleMode;

public class Manipulator extends Command {
double autoSpeed;
boolean move;
boolean isFinished;

    public final LiftIntakeS lift;
    
    public Manipulator(LiftIntakeS liftS,LiftIntakeS subsystem, double MotorSpeed ){
        //when refering to desired time you can also say "seconds"
        //when saying intake you are refering to the subsystem intakeS
        lift = liftS;
        //when saying the word lift you are refering  to the subsystem liftS
        autoSpeed = MotorSpeed;
        //when saying the word "autoSpeed" you are refering to the motor Speed
        addRequirements(subsystem);
        
    }
    @Override


    public void initialize(){
    lift.resetEncoders();
    //resets the encoders so that they can be ready when the match starts
    //starts a timer
    if(autoSpeed<0){
        move=true;
        //if the motor speed is less than 0 the manipulator will stay up
    }else{
        move=false;
        //if the motor speed is more than 0 the manipulator will be down
    }
    isFinished = false;    
    }
    @Override
    public void execute() {
        if (move==true){
            while (LiftIntakeS.liftIntakeEncoder.getPosition()<0) { 
            
                lift.spinLiftIntake(autoSpeed);
                 
             }
        }else{
            while (LiftIntakeS.liftIntakeEncoder.getPosition()<27) { 
            
                lift.spinLiftIntake(-autoSpeed);
                 
             }
        }
        
        LiftIntakeS.liftIntakeMotor.setIdleMode(IdleMode.kCoast);
        LiftIntakeS.liftIntakeMotor2.setIdleMode(IdleMode.kCoast);
         isFinished = true;
         
        
        
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
