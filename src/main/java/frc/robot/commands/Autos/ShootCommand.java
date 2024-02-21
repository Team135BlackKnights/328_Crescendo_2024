package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeS;

public class ShootCommand extends Command{
    boolean isFinished = false;
    public final IntakeS intake;
    Timer timer = new Timer();
    public ShootCommand(IntakeS intakeS){
        this.intake = intakeS;
        addRequirements(intakeS);
    }
    @Override
    public void initialize(){
        isFinished = false;
        timer.start();
        SmartDashboard.putString("Current Action", getName());
    }
    @Override
    public void execute(){
        intake.spinOuttake(1);
        if (timer.get() < .5){
            intake.spinIntake(0);
        }else{
            intake.spinIntake(-1);
        }
        if (timer.get() > .75){
            isFinished = true;
        }
    }
    @Override
    public void end(boolean interrupted){
        intake.spinIntake(0);
        intake.spinOuttake(0);
    }
    @Override
    public boolean isFinished(){
        return isFinished;
    }
}