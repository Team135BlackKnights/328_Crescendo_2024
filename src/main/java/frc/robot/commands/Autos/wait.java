package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeS;

public class wait extends Command{
    boolean isFinished = false;
    double waitTime;
    Timer timer = new Timer();
    public wait(double time){
        this.waitTime = time;
    }
    @Override
    public void initialize(){
        isFinished = false;
        timer.start();
        SmartDashboard.putString("Current Action", getName());
    }
    @Override
    public void execute(){
        if (timer.get() > waitTime){
            isFinished =true;
        }
        
    }
    @Override
    public void end(boolean interrupted){
        timer.stop();
        timer.reset();
    }
    @Override
    public boolean isFinished(){
        return isFinished;
    }
}