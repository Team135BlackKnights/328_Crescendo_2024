package main.java.frc.robot.commands;

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
        /*
    if(RobotContainer.m_driverController.getXButton() == true) {// when the x button is pressed it squeezes the game object
        liftIntakeS.squeezeMotor.set(0.05);
    } else if (RobotContainer.m_driverController.getYButton() == true) {// when the buton y is pressed it releases the game object
        liftIntakeS.squeezeMotor.set(-0.05);
    } else {//                                                       when no buttons are pressed it stops
        liftIntakeS.squeezeMotor.set(0);
    } 

*/

    if(RobotContainer.m_driverController.getRightBumper() == true) {
        liftIntakeS.liftIntakeMotor.set(0.05);
        liftIntakeS.liftIntakeMotor2.set(0.05);
    } else if (RobotContainer.m_driverController.getLeftBumper() == true) {
        liftIntakeS.liftIntakeMotor.set(-0.05);
        liftIntakeS.liftIntakeMotor2.set(-0.05);
    } else {
        liftIntakeS.liftIntakeMotor.set(0);
        liftIntakeS.liftIntakeMotor2.set(0);
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
