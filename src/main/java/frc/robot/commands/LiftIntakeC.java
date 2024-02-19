package frc.robot.commands;

import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LiftIntakeS;

/** An example command that uses an example subsystem. */

public class LiftIntakeC extends Command {
    private final LiftIntakeS liftIntakeS;
    
    public LiftIntakeC(LiftIntakeS subsystem) {
        liftIntakeS = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {

    }

    @Override

    public void execute() {
        
        if(RobotContainer.m_driverController.getRightY() >= 0.02) {
            liftIntakeS.spinLiftIntake(RobotContainer.m_driverController.getRightY());
        } else if (RobotContainer.m_driverController.getRightY() <= -0.02) {
            liftIntakeS.spinLiftIntake(RobotContainer.m_driverController.getRightY());
        } else {
            liftIntakeS.spinLiftIntake(0);
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