// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.math.kinematics.MecanumDriveOdometry;
import edu.wpi.first.math.kinematics.MecanumDriveWheelPositions;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.kinematics.MecanumDriveWheelSpeeds;
import com.revrobotics.RelativeEncoder;

import com.ctre.phoenix6.hardware.TalonFX;
//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkLowLevel.MotorType;


/*public class MecanumSub extends SubsystemBase {
  public CANSparkMax frontLeft = new CANSparkMax(Constants.FRONT_LEFT_MOTOR, MotorType.kBrushless);
  public CANSparkMax frontRight = new CANSparkMax(Constants.FRONT_RIGHT_MOTOR, MotorType.kBrushless);
  public CANSparkMax backRight = new CANSparkMax(Constants.BACK_RIGHT_MOTOR, MotorType.kBrushless);
  public CANSparkMax backLeft = new CANSparkMax(Constants.BACK_LEFT_MOTOR, MotorType.kBrushless); */


  //its the motors gang
  public class MecanumSub extends SubsystemBase {
  public TalonFX frontLeft = new TalonFX(Constants.FRONT_LEFT_MOTOR);
  public TalonFX frontRight = new TalonFX(Constants.FRONT_RIGHT_MOTOR);
  public TalonFX backRight = new TalonFX(Constants.BACK_RIGHT_MOTOR);
  public TalonFX backLeft = new TalonFX(Constants.BACK_LEFT_MOTOR); 
  //
  public double distance = Units.inchesToMeters(15.5724115024); // distance from center to each wheel, stored in a variable. :)
  public MecanumDrive drive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
  
  Translation2d m_frontLeftLocation = new Translation2d(distance, distance); // check to see if 19.1 is the correct length in inches, but we're pretty sure it is from doing math stuff
  Translation2d m_frontRightLocation = new Translation2d(distance, -distance);
  Translation2d m_backLeftLocation = new Translation2d(-distance, distance);
  Translation2d m_backRightLocation = new Translation2d(-distance, -distance);

  // creation of kinematics with utilization of wheel locations
  MecanumDriveKinematics m_kinematics = new MecanumDriveKinematics
    (m_frontRightLocation, m_frontLeftLocation, m_backRightLocation, m_backLeftLocation);
  MecanumDriveWheelSpeeds m_speeds = new MecanumDriveWheelSpeeds(0,0,0,0);


//wheel speeds
  public MecanumSub(ChassisSpeeds speeds) {
       m_speeds = m_kinematics.toWheelSpeeds(speeds);
    frontLeft.set(0.5);
    frontRight.set(0.5);
    backLeft.set(0.5);
    backRight.set(0.5);
  }
  //odometry setup object
MecanumDriveOdometry m_odometry = new MecanumDriveOdometry(
  m_kinematics,
  m_gyro.getRotation2d(), // not sure what gyro is being used here so we can't get the right import 
  new MecanumDriveWheelPositions(distance, distance, distance, distance) //all of these distances might be wrong
  ,
  new Pose2d()
);

@Override
public void periodic() {
  // put any stats we need here once we've got it programmed-- smartdashboard stuff basically
}

}
