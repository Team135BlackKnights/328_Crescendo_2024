package frc.robot.commands.Autos.AutoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeS;
import frc.robot.subsystems.MecanumSub;

public class Intake extends Command {
    long timeStart;
double seconds;
double autoSpeed;
boolean isFinished;
    public final MecanumSub drive;
    Timer timeElapsed= new Timer();
    public Intake(MecanumSub subsystem, double desiredTime, double MotorSpeed ){
        seconds = desiredTime;
        //when using the word "seconds" you are really refering to the desired time
        drive = subsystem;
        //when you use the word "drive" you are refering to the subsystem tankDriveS
        autoSpeed = MotorSpeed;
        //when using the word "autoSpeed" you are refering to the motor speed
        addRequirements(subsystem);
    }
    @Override 
    public void initialize(){
    IntakeS.resetEncoders();
    //this resets the encoder(a thing used to show how much a motor has moved)
    timeElapsed.start();
    //starts a timer 
    isFinished = false;
    }
    @Override
    public void execute() {
        if (timeElapsed.get() <= seconds) {
            //if the time elapsed is less than the time desired the robot shoots a cube if not the robot doesn't shoot anything
            IntakeS.intakeMotor.set(autoSpeed);
        } else {
            isFinished = true;
        }

    }
    @Override
    public void end(boolean interrupted) {
        IntakeS.intakeMotor.set(0);
        //stops the robot from moving
        timeElapsed.stop();
        //stops timer
        timeElapsed.reset();
        //resets the timer
    }
    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
