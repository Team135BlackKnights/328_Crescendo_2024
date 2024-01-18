// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;


/*public class MecanumSub extends SubsystemBase {
  public CANSparkMax frontLeft = new CANSparkMax(Constants.FRONT_LEFT_MOTOR, MotorType.kBrushless);
  public CANSparkMax frontRight = new CANSparkMax(Constants.FRONT_RIGHT_MOTOR, MotorType.kBrushless);
  public CANSparkMax backRight = new CANSparkMax(Constants.BACK_RIGHT_MOTOR, MotorType.kBrushless);
  public CANSparkMax backLeft = new CANSparkMax(Constants.BACK_LEFT_MOTOR, MotorType.kBrushless); */

  public class MecanumSub extends SubsystemBase {
  public TalonSRX frontLeft = new TalonFX(Constants.FRONT_LEFT_MOTOR, MotorType.kBrushless);
  public TalonSRX frontRight = new TalonSRX(Constants.FRONT_RIGHT_MOTOR, MotorType.kBrushless);
  public TalonSRX backRight = new TalonSRX(Constants.BACK_RIGHT_MOTOR, MotorType.kBrushless);
  public TalonSRX backLeft = new TalonSRX(Constants.BACK_LEFT_MOTOR, MotorType.kBrushless); 

  public MecanumDrive drive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
  



  public MecanumSub() {
    
  }



  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }



  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
