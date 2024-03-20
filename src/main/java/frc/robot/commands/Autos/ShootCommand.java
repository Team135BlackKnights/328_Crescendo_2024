package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeS;

public class ShootCommand extends Command{
    boolean isFinished = false;
    private final boolean isBlue;
    public final IntakeS intake;
    Timer timer = new Timer();
    public ShootCommand(IntakeS intakeS,boolean isBlue){
        this.intake = intakeS;
        this.isBlue = isBlue;
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
        if (isBlue){
            if (timer.get() > .25){
                intake.spinOuttake(-1);
                intake.spinIntake(1);
                if (timer.get() > 1){
                    intake.spinIntake(0);
                    intake.spinOuttake(0);
                    isFinished = true;
                }
            }
            
        }else{
            intake.spinOuttake(-1);
            intake.spinIntake(1);
            if (timer.get() > .75){
                intake.spinIntake(0);
                intake.spinOuttake(0);
                isFinished = true;
            }
        }
        
    }
    @Override
    public void end(boolean interrupted){
        intake.spinIntake(0);
        intake.spinOuttake(0);
        timer.stop();
        timer.reset();
    }
    @Override
    public boolean isFinished(){
        return isFinished;
    }
}