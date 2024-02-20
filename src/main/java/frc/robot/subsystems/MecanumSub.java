// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.math.kinematics.MecanumDriveOdometry;
import edu.wpi.first.math.kinematics.MecanumDriveWheelPositions;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.math.kinematics.MecanumDriveWheelSpeeds;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkBase.IdleMode;



  //its the motors gang
  public class MecanumSub extends SubsystemBase {
  public TalonFX frontLeft = new TalonFX(Constants.FRONT_LEFT_MOTOR);
  public TalonFX frontRight = new TalonFX(Constants.FRONT_RIGHT_MOTOR);
  public TalonFX backRight = new TalonFX(Constants.BACK_RIGHT_MOTOR);
  public TalonFX backLeft = new TalonFX(Constants.BACK_LEFT_MOTOR); 

  public MecanumDrive drive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
  
  Translation2d m_frontLeftLocation = new Translation2d(Units.inchesToMeters(19.1), Units.inchesToMeters(19.1)); // check to see if 19.1 is the correct length in inches, but we're pretty sure it is from doing math stuff
  Translation2d m_frontRightLocation = new Translation2d(Units.inchesToMeters(19.1), -Units.inchesToMeters(19.1));
  Translation2d m_backLeftLocation = new Translation2d(-Units.inchesToMeters(19.1), Units.inchesToMeters(19.1));
  Translation2d m_backRightLocation = new Translation2d(-Units.inchesToMeters(19.1), -Units.inchesToMeters(19.1));

  private final Encoder m_frontLeftEncoder = new Encoder(0,1);
  private final Encoder m_frontRightEncoder = new Encoder(2,3);
  private final Encoder m_backLeftEncoder = new Encoder(4,5);
  private final Encoder m_backRightEncoder = new Encoder(6,7);


private final AnalogGyro m_gyro = new AnalogGyro(0);
  // creation of kinematics with utilization of wheel locations
 MecanumDriveKinematics m_kinematics = new MecanumDriveKinematics
    (m_frontRightLocation, m_frontLeftLocation, m_backRightLocation, m_backLeftLocation);
 MecanumDriveWheelSpeeds m_speeds = new MecanumDriveWheelSpeeds(0,0,0,0);

  Pose2d m_pose = new Pose2d();

  // kinematics and odometry
  MecanumDriveOdometry m_odometry = new MecanumDriveOdometry(
  m_kinematics,
  m_gyro.getRotation2d(),
  new MecanumDriveWheelPositions(
    m_frontLeftEncoder.getDistance(), m_frontRightEncoder.getDistance(),
    m_backLeftEncoder.getDistance(), m_backRightEncoder.getDistance()
  ),
  new Pose2d(5.0, 13.5, new Rotation2d())
);

public void periodic() {
  // Get my wheel positions
  var wheelPositions = new MecanumDriveWheelPositions(
    m_frontLeftEncoder.getDistance(), m_frontRightEncoder.getDistance(),
    m_backLeftEncoder.getDistance(), m_backRightEncoder.getDistance());

  // Get the rotation of the robot from the gyro.
  var gyroAngle = m_gyro.getRotation2d();

  // Update the pose
  m_pose = m_odometry.update(gyroAngle, wheelPositions);
}

    public void runStop() {
    //Sets motors to stop when not in use
        frontLeft.setNeutralMode(NeutralModeValue.Brake);
        frontRight.setNeutralMode(NeutralModeValue.Brake);
        backLeft.setNeutralMode(NeutralModeValue.Brake);
        backRight.setNeutralMode(NeutralModeValue.Brake); 
    }

  public MecanumSub() {

  }
  
  
  // sets wheel speeds
   public MecanumSub(ChassisSpeeds speeds) {
    m_speeds = m_kinematics.toWheelSpeeds(speeds);
    //frontLeft.set(0.5);
    }

  public void MecanumDrive(double frontLeft,double backLeft,double frontRight,double backRight) {
      MecanumDrive(frontLeft, backLeft, frontRight, backRight);
  }



  }
