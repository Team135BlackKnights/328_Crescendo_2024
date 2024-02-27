// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort.Port;
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
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkBase.IdleMode;



  //its the motors gang
  public class MecanumSub extends SubsystemBase {
  public CANSparkMax frontLeft = new CANSparkMax(Constants.FRONT_LEFT_MOTOR, MotorType.kBrushless);
  public CANSparkMax frontRight = new CANSparkMax(Constants.FRONT_RIGHT_MOTOR, MotorType.kBrushless);
  public CANSparkMax backRight = new CANSparkMax(Constants.BACK_RIGHT_MOTOR, MotorType.kBrushless);
  public CANSparkMax backLeft = new CANSparkMax(Constants.BACK_LEFT_MOTOR, MotorType.kBrushless); 

  public MecanumDrive drive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
  
  Translation2d m_frontLeftLocation = new Translation2d(Units.inchesToMeters(19.1), Units.inchesToMeters(19.1)); // check to see if 19.1 is the correct length in inches, but we're pretty sure it is from doing math stuff
  Translation2d m_frontRightLocation = new Translation2d(Units.inchesToMeters(19.1), -Units.inchesToMeters(19.1));
  Translation2d m_backLeftLocation = new Translation2d(-Units.inchesToMeters(19.1), Units.inchesToMeters(19.1));
  Translation2d m_backRightLocation = new Translation2d(-Units.inchesToMeters(19.1), -Units.inchesToMeters(19.1));


private final AHRS m_gyro = new AHRS(Port.kUSB1);
  // creation of kinematics with utilization of wheel locations
 MecanumDriveKinematics m_kinematics = new MecanumDriveKinematics
    (m_frontRightLocation, m_frontLeftLocation, m_backRightLocation, m_backLeftLocation);
 MecanumDriveWheelSpeeds m_speeds = new MecanumDriveWheelSpeeds(0,0,0,0);

  Pose2d m_pose = new Pose2d();

  // kinematics and odometry
 

public void periodic() {
  // Get my wheel positions
 
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
    public double[] calculateDistance(boolean isStrafe) {
      if (isStrafe){
          double averageEncoderCountsFLBR = (frontLeft.getPosition().getValueAsDouble() + backRight.getPosition().getValueAsDouble()) / 2.0;
          double averageEncoderCountsFRBL = (frontRight.getPosition().getValueAsDouble() + backLeft.getPosition().getValueAsDouble()) / 2.0;
          double averageEncoderCountsOverall = (Math.abs(averageEncoderCountsFLBR) + Math.abs(averageEncoderCountsFRBL)) / 2.0;
          // Convert encoder counts to wheel rotations
          double rotationsFLBR = averageEncoderCountsFLBR / Constants.AutoConstants.ENCODER_COUNTS_PER_REV;
          double rotationsFRBL = averageEncoderCountsFRBL / Constants.AutoConstants.ENCODER_COUNTS_PER_REV;
          double rotationsOverall = averageEncoderCountsOverall / Constants.AutoConstants.ENCODER_COUNTS_PER_REV;
          double[] distanceTraveled = new double[2];
          distanceTraveled[0] = rotationsFLBR * Constants.AutoConstants.WHEEL_CIRCUMFERENCE;
          distanceTraveled[1] = rotationsFRBL * Constants.AutoConstants.WHEEL_CIRCUMFERENCE;
          distanceTraveled[2] = rotationsOverall * Constants.AutoConstants.WHEEL_CIRCUMFERENCE;
          // Calculate distance traveled
          return distanceTraveled;
      }else{
      double averageEncoderCountsFLBR = (frontLeft.getPosition().getValueAsDouble() + backRight.getPosition().getValueAsDouble()) / 2.0;
          double averageEncoderCountsFRBL = (frontRight.getPosition().getValueAsDouble() + backLeft.getPosition().getValueAsDouble()) / 2.0;
          double averageEncoderCountsOverall = (Math.abs(averageEncoderCountsFLBR) + Math.abs(averageEncoderCountsFRBL)) / 2.0;
  
      // Convert encoder counts to wheel rotations
      double rotations = averageEncoderCountsOverall;
  
      // Calculate distance traveled
      double[] distanceTraveled = new double[1];
      distanceTraveled[0] = rotations * Constants.AutoConstants.WHEEL_CIRCUMFERENCE;
      return distanceTraveled;}
      }
      public double getYaw() {
        return m_gyro.getYaw();
      }
      public void resetEncoders() {
        frontLeft.setPosition(0);
        frontRight.setPosition(0);
        backLeft.setPosition(0);
        backRight.setPosition(0);
    }
  public void MecanumDrive(double frontLeft,double backLeft,double frontRight,double backRight) {
      MecanumDrive(frontLeft, backLeft, frontRight, backRight);
  }



  }
