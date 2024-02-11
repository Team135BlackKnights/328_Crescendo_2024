package frc.robot.commands.Autos.AutoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MecanumSub;

public class Drive extends Command{
    long timeStart;
double seconds;
double autoSpeed;
    public final MecanumSub drive;
    Timer timeElapsed= new Timer();
    public Drive(MecanumSub subsystem, double desiredTime, double MotorSpeed ){
        seconds = desiredTime;
        //when using the word "seconds" you are really refering to the desired time
        drive = subsystem;
        //when you use the word "drive" you are refering to the subsystem tankDriveS
        autoSpeed = MotorSpeed;
        //when using the word "autoSpeed" you are refering to the motor speed
        addRequirements(subsystem);
        
    }

    public void initialize(){
        System.out.print("Running forwardDriveAuto...");
    timeElapsed.start();
    }
    public void execute() {

        //if (timeElapsed.get() <= seconds) {
            //if the time that has gone by is less than the desired time then make the robot move forward 
            
       //     drive.MecanumDrive (autoSpeed, autoSpeed, autoSpeed, autoSpeed);
            //FL, BL, FR, BR
      //  }
    }
    public void end() {
       // drive.MecanumDrive(0,0,0,0);
        //at the end of auto it makes the wheels stop moving and stops the robot from moving
     ///   drive.runStop();
      //  timeElapsed.stop();
        //stops the timer known as timeElapsed as it is not needed anymore 
       // timeElapsed.reset();
        //resets the timmer so that we can use it next match

    }
}
