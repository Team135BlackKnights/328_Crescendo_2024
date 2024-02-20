package frc.robot.commands;

import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeS;

/** An example command that uses an example subsystem. */

public class IntakeC extends Command {
    private final IntakeS intakeS;
    
    public IntakeC(IntakeS subsystem) {
        intakeS = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {

    if(RobotContainer.m_operatorController.getRightTriggerAxis() >= 0.1) {
        intakeS.spinIntake(RobotContainer.m_operatorController.getRightTriggerAxis());
    } else if (RobotContainer.m_operatorController.getLeftTriggerAxis() >= 0.1) {
        intakeS.spinIntake(-RobotContainer.m_operatorController.getLeftTriggerAxis());
    } else {
        intakeS.spinIntake(0);
    }
    //outtake
    if(RobotContainer.m_operatorController.getLeftY() >= 0.1) {
        intakeS.spinOuttake(RobotContainer.m_operatorController.getLeftY());
    } else if (RobotContainer.m_operatorController.getLeftY() <= -0.1) {
        intakeS.spinOuttake(RobotContainer.m_operatorController.getLeftY());
    } else {
        intakeS.spinOuttake(0);
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
