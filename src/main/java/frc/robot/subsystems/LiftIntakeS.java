package main.java.frc.robot.subsystems;

import com.revrobots.CANSparkMax;
import com.revrobtoics.RelativeEncoder;
import com.revrobotics.CANParkMaxLowLever.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
//API is like a book. we IMPORT "chapters" of this book, to then use their knowledge.
//Imagine our .command.CommandBase saying "we're in this chapter! use this data."
import edu.wpi.first.wpilibj2.command.CommandBase; //we are a commandbase, using methods
import edu.wpi.first.wpilibj2.command.SubsystemBase; //duh
import frc.robot.Constants;

public class intakeS extends SubsystemBase { //create a subsystem

    public CANSparkMax liftIntakeMotor = new CANSparkMax(Constants.IntakeConstants.Lift_ID,MotorType.kBrushless);
    public RelativeEncoder liftIntakeEncoder = liftIntakeMotor.getEncoder();
    public CANSparkMax liftIntakeMotor2 = new CANSparkMax(Constants.IntakeConstants.Lift_ID,MotorType.kBrushless);
    public RelativeEncoder liftIntakeEncoder2 = liftIntakeMotor.getEncoder();

    public liftIntakeS(){
      liftIntakeMotor.enableVoltageCompensation(12);
      liftIntakeMotor.setIdleMode(IdleMode.kBrake);
      liftIntakeMotor2.enableVoltageCompensation(12);
      liftIntakeMotor2.setIdleMode(IdleMode.kBrake);
    }

    public void spinLiftIntake(double speed) {
      spinLiftIntake.set(speed);
    }



    public void resetEncoders() {
      //Sets position of the encoder
           liftIntakeEncoder.setPosition(0);
           liftIntakeEncoder2.setPosition(0);
      }


  }