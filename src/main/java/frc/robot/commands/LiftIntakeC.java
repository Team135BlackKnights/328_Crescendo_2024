package frc.robot.commands;

import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LiftIntakeS;

/** An example command that uses an example subsystem. */

public class LiftIntakeC extends Command {
    private final LiftIntakeS liftIntakeS;
    float multiplier;
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
if (RobotContainer.m_operatorController.getLeftBumper()){//this sets the A button to making how fast the lift will move.
    multiplier = 2f;
}else{
    multiplier = 1.5f;
}
if (RobotContainer.m_operatorController.getBButton()){
    liftIntakeS.resetEncoders();
}
if(RobotContainer.m_operatorController.getRightY() >= 0.05) {
    liftIntakeS.spinLiftIntake(RobotContainer.m_operatorController.getRightY()/multiplier);
} else if (RobotContainer.m_operatorController.getRightY() <= -0.05) {
    liftIntakeS.spinLiftIntake(RobotContainer.m_operatorController.getRightY()/multiplier);
} else {
    if (DriverStation.isTeleop()){
    liftIntakeS.spinLiftIntake(0); //callin the liftintakeS to tel it to stop moving, happens when u aren't bullying the controller.
    }
}
SmartDashboard.putNumber("ARM HEIGHT", liftIntakeS.getLiftIntakeEncoderAverage());
// Assuming you want to stop when the position is within a range of 4.5 to 5.5
/*if (LiftIntakeS.liftIntakeEncoder2.getPosition() < 0 || LiftIntakeS.liftIntakeEncoder2.getPosition() > 5) {
    liftIntakeS.stopLiftIntake();
}

// Adjust the conditions for the other encoder as well
if (LiftIntakeS.liftIntakeEncoder.getPosition() < 0 || LiftIntakeS.liftIntakeEncoder.getPosition() > 5) {
    liftIntakeS.stopLiftIntake();
}
*/
/*if(RobotContainer.m_driverController.getPOV() == 0) {
    liftIntakeS.spinLiftIntake(-.25);
} else if (RobotContainer.m_driverController.getPOV() == 180) {
    liftIntakeS.spinLiftIntake(.25);
} else {
    liftIntakeS.spinLiftIntake(0);
}*/

}


    @Override
    public void end(boolean interrupted) { //stopping all things(including booleans) which will ultimately stop the entire robot at the end of the match.
    
    }

    @Override
    public boolean isFinished() {
        return false;//stops everything
    }
    
}