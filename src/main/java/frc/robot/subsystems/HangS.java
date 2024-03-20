package frc.robot.subsystems;


import edu.wpi.first.wpilibj.DoubleSolenoid;
//API is like a book. we IMPORT "chapters" of this book, to then use their knowledge.
//Imagine our .command.CommandBase saying "we're in this chapter! use this data."
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase; //duh
import frc.robot.Constants;

public class HangS extends SubsystemBase { //create a subsystem
public static DoubleSolenoid hang = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.Hang_ID, Constants.Hang_ID2);

    public HangS(){
      
    }

    public void pneumaticOut() {
     hang.set(DoubleSolenoid.Value.kForward);
      }

    public void pneumaticIn() {
     hang.set(DoubleSolenoid.Value.kReverse);
      }

    }