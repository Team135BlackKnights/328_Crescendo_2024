package frc.robot.commands;

import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.HangS;

/** An example command that uses an example subsystem. */

public class HangC extends Command {
    private final HangS hangS;
    
    public HangC(HangS subsystem) {
        hangS = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {

        if (RobotContainer.m_operatorController.getYButtonPressed()) {
           hangS.pneumaticOut();
        }   else if (RobotContainer.m_operatorController.getXButtonPressed()) {
           hangS.pneumaticIn();
        }
    }


    @Override
    public void end(boolean interrupted) {
    
    }

    @Override
    public boolean isFinished() {
        return false;
    }
    
}
