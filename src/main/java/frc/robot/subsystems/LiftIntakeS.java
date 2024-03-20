
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

//API is like a book. we IMPORT "chapters" of this book, to then use their knowledge.
//Imagine our .command.CommandBase saying "we're in this chapter! use this data."
 //we are a commandbase, using methods
import edu.wpi.first.wpilibj2.command.SubsystemBase; //duh
import frc.robot.Constants;

public class LiftIntakeS extends SubsystemBase { //create a subsystem

  //motor declarations if you couldn't tell
    public static CANSparkMax liftIntakeMotor = new CANSparkMax(Constants.Lift_ID,MotorType.kBrushless);
    public static final RelativeEncoder liftIntakeEncoder = liftIntakeMotor.getEncoder();//the encoder calculate the amount of rotations that the motor has done in order to calculate how much the lift(or anything in the robot that has a motor) has moved forward or backward.
    public static CANSparkMax liftIntakeMotor2 = new CANSparkMax(Constants.Lift_ID2,MotorType.kBrushless);
    public static final RelativeEncoder liftIntakeEncoder2 = liftIntakeMotor.getEncoder();
    public LiftIntakeS(){
      liftIntakeMotor.enableVoltageCompensation(12); //limits the lift intake motors power to 12 only
      liftIntakeMotor.setIdleMode(IdleMode.kBrake);
      liftIntakeMotor2.enableVoltageCompensation(12);
      liftIntakeMotor2.setIdleMode(IdleMode.kBrake);
      liftIntakeEncoder.setPositionConversionFactor(1/Constants.TeleConstants.liftConversionFactor);
      liftIntakeEncoder2.setPositionConversionFactor(1/Constants.TeleConstants.liftConversionFactor);
      liftIntakeMotor.burnFlash();
      liftIntakeMotor2.burnFlash();
    
    }

    public void spinLiftIntake(double speed) {
      liftIntakeMotor.set(speed);
      liftIntakeMotor2.set(-speed);
    }

    public void stopLiftIntake(){ //all of this code in these 3 lines will result in the lift intake motors stopping.;
      liftIntakeMotor.set(0); //this sets the lieft intake motor #1 to 0 power, meaning that it will stop.
      liftIntakeMotor2.set(0); //this sets the lift intake motor #2 to 0 power, meaning that this will defenitely stop.

     }

     public double getLiftIntakeEncoderAverage(){
      return (liftIntakeEncoder.getPosition() + liftIntakeEncoder2.getPosition()) / 2; //this helps get the position of the first encoder and the seoncd encoder position(how far the robot has moved 
    }
    public void resetEncoders() {
      //Sets position of the encoder
           liftIntakeEncoder.setPosition(0); //resets the econders(showing where they lift is, how high up or how low it is) to avoid the build up of rotations on the motor(Example: if the motor was 12 rotations, resetting it back to 0 would avoid it from going to higher rotations. If your robit is already at rotation 12 and you coded it to be at 13, then the lift motor would only rotate once, resulting in the lift not reaching the destination it was supposed to reach. This can be a very big problem that we hope to eliminate through the use of these encoders which calculate the amount of rotations any given motor has done in order to find out how much the robot has moved forward or backward.) 
           liftIntakeEncoder2.setPosition(0);
      }



  }