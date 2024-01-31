// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.math.kinematics.MecanumDriveOdometry;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.math.kinematics.MecanumDriveWheelSpeeds;

import com.ctre.phoenix6.hardware.TalonFX;
//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkLowLevel.MotorType;



  //its the motors gang
  public class MecanumSub extends SubsystemBase {
  public TalonFX frontLeft = new TalonFX(Constants.FRONT_LEFT_MOTOR);
  public TalonFX frontRight = new TalonFX(Constants.FRONT_RIGHT_MOTOR);
  public TalonFX backRight = new TalonFX(Constants.BACK_RIGHT_MOTOR);
  public TalonFX backLeft = new TalonFX(Constants.BACK_LEFT_MOTOR); 

  public MecanumDrive drive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
  
  Translation2d m_frontLeftLocation = new Translation2d(Units.inchesToMeters(15.4), Units.inchesToMeters(15.4)); // check to see if 19.1 is the correct length in inches, but we're pretty sure it is from doing math stuff
  Translation2d m_frontRightLocation = new Translation2d(Units.inchesToMeters(15.4), -Units.inchesToMeters(15.4));
  Translation2d m_backLeftLocation = new Translation2d(-Units.inchesToMeters(15.4), Units.inchesToMeters(15.4));
  Translation2d m_backRightLocation = new Translation2d(-Units.inchesToMeters(15.4), -Units.inchesToMeters(15.4));

  private final AnalogGyro m_gyro = new AnalogGyro(0);
  // creation of kinematics with utilization of wheel locations
  MecanumDriveKinematics m_kinematics = new MecanumDriveKinematics
    (m_frontRightLocation, m_frontLeftLocation, m_backRightLocation, m_backLeftLocation);
  MecanumDriveWheelSpeeds m_speeds = new MecanumDriveWheelSpeeds(0,0,0,0);

 /* MecanumDriveOdometry m_odometry = new MecanumDriveOdometry(m_kinematics, m_gyro.getRotation2d(), getCurrentDistances());*/
  

  /*public MecanumSub() {
    // Sets the distance per pulse for the encoders
    m_leftEncoder.setDistancePerPulse(Constants.FRONT_LEFT_DRIVE_ENCODER);
    m_leftEncoder.setDistancePerPulse(Constants.kEncoderDistancePerPulse);
    m_leftEncoder.setDistancePerPulse(Constants.kEncoderDistancePerPulse);
    m_rightEncoder.setDistancePerPulse(Constants.kEncoderDistancePerPulse);

    resetEncoders();
    m_odometry =
        new MecanumDriveOdometry(
            m_gyro.getRotation2d(), m_leftEncoder.getDistance(), m_rightEncoder.getDistance());
  }*/

 /* public DrivefromChassisSpeeds(ChassisSpeeds speeds) {
    m_speeds = m_kinematics.toWheelSpeeds(speeds);
    frontLeft.set();
  }*/
}
