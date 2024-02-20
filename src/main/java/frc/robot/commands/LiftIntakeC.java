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
        /*
    if(RobotContainer.m_driverController.getXButton() == true) {// when the x button is pressed it squeezes the game object
        liftIntakeS.squeezeMotor.set(0.05);
    } else if (RobotContainer.m_driverController.getYButton() == true) {// when the buton y is pressed it releases the game object
        liftIntakeS.squeezeMotor.set(-0.05);
    } else {//                                                       when no buttons are pressed it stops
        liftIntakeS.squeezeMotor.set(0);
    } 

*/

if(RobotContainer.m_operatorController.getRightY() >= 0.05) {
    liftIntakeS.spinLiftIntake(RobotContainer.m_operatorController.getRightY()/2);
} else if (RobotContainer.m_operatorController.getRightY() <= -0.05) {
    liftIntakeS.spinLiftIntake(RobotContainer.m_operatorController.getRightY()/2);
} else {
    liftIntakeS.spinLiftIntake(0);
}
/*if(RobotContainer.m_driverController.getPOV() == 0) {
    liftIntakeS.spinLiftIntake(-.25);
} else if (RobotContainer.m_driverController.getPOV() == 180) {
    liftIntakeS.spinLiftIntake(.25);
} else {
    liftIntakeS.spinLiftIntake(0);
}*/

}


    @Override
    public void end(boolean interrupted) {
    
    }

    @Override
    public boolean isFinished() {
        return false;
    }
    
}