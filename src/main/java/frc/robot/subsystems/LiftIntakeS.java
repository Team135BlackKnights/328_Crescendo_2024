package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
//API is like a book. we IMPORT "chapters" of this book, to then use their knowledge.
//Imagine our .command.CommandBase saying "we're in this chapter! use this data."
import edu.wpi.first.wpilibj2.command.CommandBase; //we are a commandbase, using methods
import edu.wpi.first.wpilibj2.command.SubsystemBase; //duh
import frc.robot.Constants;

public class LiftIntakeS extends SubsystemBase { //create a subsystem

    public CANSparkMax liftIntakeMotor = new CANSparkMax(Constants.Lift_ID,MotorType.kBrushless);
    public RelativeEncoder liftIntakeEncoder = liftIntakeMotor.getEncoder();
    public CANSparkMax liftIntakeMotor2 = new CANSparkMax(Constants.Lift_ID2,MotorType.kBrushless);
    public RelativeEncoder liftIntakeEncoder2 = liftIntakeMotor.getEncoder();

    public LiftIntakeS(){
      liftIntakeMotor.enableVoltageCompensation(12);
      liftIntakeMotor.setIdleMode(IdleMode.kBrake);
      liftIntakeMotor2.enableVoltageCompensation(12);
      liftIntakeMotor2.setIdleMode(IdleMode.kBrake);
    }

    public void spinLiftIntake(double speed) {
      liftIntakeMotor.set(speed);
       liftIntakeMotor2.set(speed);
    }



    public void resetEncoders() {
      //Sets position of the encoder
           liftIntakeEncoder.setPosition(0);
           liftIntakeEncoder2.setPosition(0);
      }


  }