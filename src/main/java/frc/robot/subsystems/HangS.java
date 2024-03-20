package frc.robot.subsystems;


import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.DoubleSolenoid;
//API is like a book. we IMPORT "chapters" of this book, to then use their knowledge.
//Imagine our .command.CommandBase saying "we're in this chapter! use this data."
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase; //duh
import frc.robot.Constants;

public class HangS extends SubsystemBase { //create a subsystem
    public static CANSparkMax hangMotor1 = new CANSparkMax(Constants.hangMotorID1,MotorType.kBrushless);
    public static CANSparkMax hangMotor2 = new CANSparkMax(Constants.hangMotorID2,MotorType.kBrushless);
    public static RelativeEncoder hangEncoder = hangMotor1.getEncoder();
    public static RelativeEncoder hangEncoder2 = hangMotor2.getEncoder();
    public static CANSparkMax tensionMotor1 = new CANSparkMax(Constants.tensionMotorID1,MotorType.kBrushless);
    public static CANSparkMax tensionMotor2 = new CANSparkMax(Constants.tensionMotorID2,MotorType.kBrushless);
    public static RelativeEncoder tensionEncoder = tensionMotor1.getEncoder();
    public static RelativeEncoder tensionEncoder2 = tensionMotor2.getEncoder();
    public HangS(){
      hangMotor1.enableVoltageCompensation(12);
      hangMotor2.setIdleMode(IdleMode.kBrake);
      hangMotor2.enableVoltageCompensation(12);
      hangMotor1.setIdleMode(IdleMode.kBrake);
      tensionMotor1.enableVoltageCompensation(12);
      tensionMotor2.enableVoltageCompensation(12);
      tensionMotor1.setIdleMode(IdleMode.kBrake);
      tensionMotor2.setIdleMode(IdleMode.kBrake);
      hangMotor1.burnFlash();
      hangMotor2.burnFlash();
      tensionMotor1.burnFlash();
      tensionMotor2.burnFlash();
    }
    public void spinHang(double speed) {
      hangMotor1.set(speed);
      hangMotor2.set(speed);
      }
      public void spinTension(double speed) {
      tensionMotor1.set(speed);
      tensionMotor2.set(speed);
      }
      public static void resetEncoders() {
        //Sets position of the encoder
        hangEncoder.setPosition(0);
        hangEncoder2.setPosition(0);
        tensionEncoder.setPosition(0);
        tensionEncoder2.setPosition(0);
      }
    }