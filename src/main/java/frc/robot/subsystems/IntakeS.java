package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

//API is like a book. we IMPORT "chapters" of this book, to then use their knowledge.
//Imagine our .command.CommandBase saying "we're in this chapter! use this data."

import edu.wpi.first.wpilibj2.command.SubsystemBase; //duh
import frc.robot.Constants;

public class IntakeS extends SubsystemBase { //create a subsystem

    public static CANSparkMax intakeMotor = new CANSparkMax(Constants.intakeMotorID,MotorType.kBrushless);
    public static RelativeEncoder intakeEncoder = intakeMotor.getEncoder();

    public IntakeS(){
      intakeMotor.enableVoltageCompensation(12);
      intakeMotor.setIdleMode(IdleMode.kBrake);
    }

    public void spinIntake(double speed) {
    intakeMotor.set(speed);
    }



    public static void resetEncoders() {
      //Sets position of the encoder
           intakeEncoder.setPosition(0);
      }


  }