package frc.robot.commands;

import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
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
        /*
       if(RobotContainer.m_driverController.getAButton() == true) {//     When the a button is pressed it spins the wheeles on the intake
        intakeS.inOutMotor1.set(-1);
        intakeS.inOutMotor2.set(1);
    } else if (RobotContainer.m_driverController.getBButton() == true) {//     When the b button is pressed it spins the wheeles on the intake the other way
        intakeS.inOutMotor1.set(1);
        intakeS.inOutMotor2.set(-1);
    } else {//     when neither the a or b are pressed it turns off
        intakeS.inOutMotor1.set(0); 
        intakeS.inOutMotor2.set(0);
    } 


    if(RobotContainer.m_driverController.getXButton() == true) {// when the x button is pressed it squeezes the game object
        intakeS.squeezeMotor.set(0.05);
    } else if (RobotContainer.m_driverController.getYButton() == true) {// when the buton y is pressed it releases the game object
        intakeS.squeezeMotor.set(-0.05);
    } else {//                                                       when no buttons are pressed it stops
        intakeS.squeezeMotor.set(0);
    } 

*/

    if(RobotContainer.m_driverController.getRightTriggerAxis() >= 0.1) {
        intakeS.intakeMotor.set(RobotContainer.m_driverController.getRightTriggerAxis());
    } else if (RobotContainer.m_driverController.getLeftTriggerAxis() >= 0.1) {
        intakeS.intakeMotor.set(-RobotContainer.m_driverController.getLeftTriggerAxis());
    } else {
        intakeS.intakeMotor.set(0);
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
