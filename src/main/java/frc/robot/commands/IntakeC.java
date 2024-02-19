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

    if(RobotContainer.m_driverController.getRightTriggerAxis() >= 0.1) {
        intakeS.spinIntake(RobotContainer.m_driverController.getRightTriggerAxis());
    } else if (RobotContainer.m_driverController.getLeftTriggerAxis() >= 0.1) {
        intakeS.spinIntake(-RobotContainer.m_driverController.getLeftTriggerAxis());
    } else {
        intakeS.spinIntake(0);
    }
    //outtake
    if(RobotContainer.m_driverController.getLeftY() >= 0.1) {
        intakeS.spinOuttake(RobotContainer.m_driverController.getLeftY());
    } else if (RobotContainer.m_driverController.getLeftY() <= -0.1) {
        intakeS.spinOuttake(RobotContainer.m_driverController.getLeftY());
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
