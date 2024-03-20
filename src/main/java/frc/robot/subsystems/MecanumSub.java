// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.LimelightHelpers;
import frc.robot.Constants.TeleConstants;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import java.util.ArrayList;
import java.util.List;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkBase.IdleMode;


  //its the motors gang
  public class MecanumSub extends SubsystemBase {
  public CANSparkMax frontLeft = new CANSparkMax(Constants.FRONT_LEFT_MOTOR, MotorType.kBrushless);
  public CANSparkMax frontRight = new CANSparkMax(Constants.FRONT_RIGHT_MOTOR, MotorType.kBrushless);
  public CANSparkMax backRight = new CANSparkMax(Constants.BACK_RIGHT_MOTOR, MotorType.kBrushless);
  public CANSparkMax backLeft = new CANSparkMax(Constants.BACK_LEFT_MOTOR, MotorType.kBrushless); 
  public RelativeEncoder frontLeftEncoder = frontLeft.getEncoder();
  public RelativeEncoder frontRightEncoder = frontRight.getEncoder();
  public RelativeEncoder backLeftEncoder = backLeft.getEncoder();
  public RelativeEncoder backRightEncoder = backRight.getEncoder();
  public static Compressor pCompress = new Compressor(PneumaticsModuleType.CTREPCM);  //Digtial I/O,Relay
  public MecanumDrive drive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
  double distance = 0;
  
  //Translation2d m_frontLeftLocation = new Translation2d(Units.inchesToMeters(19.1), Units.inchesToMeters(19.1)); // check to see if 19.1 is the correct length in inches, but we're pretty sure it is from doing math stuff
  //Translation2d m_frontRightLocation = new Translation2d(Units.inchesToMeters(19.1), -Units.inchesToMeters(19.1));
  //Translation2d m_backLeftLocation = new Translation2d(-Units.inchesToMeters(19.1), Units.inchesToMeters(19.1));
  //Translation2d m_backRightLocation = new Translation2d(-Units.inchesToMeters(19.1), -Units.inchesToMeters(19.1));


private final AHRS m_gyro = new AHRS(Port.kUSB1);
  // creation of kinematics with utilization of wheel locations
/*  MecanumDriveKinematics m_kinematics = new MecanumDriveKinematics
    (m_frontRightLocation, m_frontLeftLocation, m_backRightLocation, m_backLeftLocation);
 MecanumDriveWheelSpeeds m_speeds = new MecanumDriveWheelSpeeds(0,0,0,0);

  Pose2d m_pose = new Pose2d();
*/
  // kinematics and odometry
 

public void periodic() {
  // Get my wheel positions
  distance = LimelightHelpers.getTX(TeleConstants.limelightName);
}


    public void runStop() {
    //Sets motors to stop when not in use

        

    }

  public MecanumSub() {
        pCompress.enableDigital();
        frontLeft.setIdleMode(IdleMode.kBrake);
        frontRight.setIdleMode(IdleMode.kBrake);
        backLeft.setIdleMode(IdleMode.kBrake);
        backRight.setIdleMode(IdleMode.kBrake);
        frontLeft.enableVoltageCompensation(12);
        frontRight.enableVoltageCompensation(12);
        backLeft.enableVoltageCompensation(12);
        backRight.enableVoltageCompensation(12);
        frontLeft.setSmartCurrentLimit(50);
        frontRight.setSmartCurrentLimit(50);
        backLeft.setSmartCurrentLimit(50);
        backRight.setSmartCurrentLimit(50);
        frontLeft.burnFlash();
        frontRight.burnFlash();
        backLeft.burnFlash();
        backRight.burnFlash();
        m_gyro.reset();
  }
  public Rotation2d getRotation(){
            return m_gyro.getRotation2d();
  }
  public void resetRotation(){
    m_gyro.reset();
  }
  // sets wheel speeds
   public MecanumSub(ChassisSpeeds speeds) {
   // m_speeds = m_kinematics.toWheelSpeeds(speeds);
    //frontLeft.set(0.5);
    }

    public List<Double> calculateDistance() {
          double averageEncoderCountsFLBR = (frontLeftEncoder.getPosition()  + backRightEncoder.getPosition() ) / 2.0;
          double averageEncoderCountsFRBL = (frontRightEncoder.getPosition() + backLeftEncoder.getPosition() ) / 2.0;
          double averageEncoderCountsOverall = (frontLeftEncoder.getPosition() + backRightEncoder.getPosition() + backLeftEncoder.getPosition() + frontRightEncoder.getPosition()) / 4.0;
          // Convert encoder counts to wheel rotations
          double rotationsFLBR = averageEncoderCountsFLBR / Constants.AutoConstants.ENCODER_COUNTS_PER_REV;
          double rotationsFRBL = averageEncoderCountsFRBL / Constants.AutoConstants.ENCODER_COUNTS_PER_REV;
          double rotationsOverall = averageEncoderCountsOverall; /// Constants.AutoConstants.ENCODER_COUNTS_PER_REV; /// Constants.AutoConstants.ENCODER_COUNTS_PER_REV;
          List<Double> distanceTraveled = new ArrayList<Double>();
          distanceTraveled.add(rotationsFLBR * Constants.AutoConstants.WHEEL_CIRCUMFERENCE);
          distanceTraveled.add(rotationsFRBL * Constants.AutoConstants.WHEEL_CIRCUMFERENCE);
          distanceTraveled.add(rotationsOverall * Constants.AutoConstants.WHEEL_CIRCUMFERENCE);
          // Calculate distance traveled
          return distanceTraveled;
      
      }
      public double getYaw() {
        return m_gyro.getYaw();
      }
      public void resetEncoders() {
        frontLeftEncoder.setPosition(0);
        frontRightEncoder.setPosition(0);
        backLeftEncoder.setPosition(0);
        backRightEncoder.setPosition(0);
    }
  public void MecanumDrive(double frontLeft,double backLeft,double frontRight,double backRight) {
      MecanumDrive(frontLeft, backLeft, frontRight, backRight);
  }
  


  }
