// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
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
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.math.kinematics.MecanumDriveWheelSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.kinematics.WheelPositions;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.kauailabs.navx.frc.AHRS;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.util.HolonomicPathFollowerConfig;
import com.pathplanner.lib.util.PIDConstants;
import com.pathplanner.lib.util.ReplanningConfig;
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

    private AHRS gyro = new AHRS(Port.kUSB1);
    NetworkTableEntry pipeline;
    

    public NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight-mechanum");
    NetworkTableEntry tx = limelight.getEntry("tx");
    double xError = tx.getDouble(0.0);

    Pose2d robotPosition = new Pose2d(0,0, getRotation2d());
    MecanumDriveKinematics m_kinematics = new MecanumDriveKinematics
    (m_frontRightLocation, m_frontLeftLocation, m_backRightLocation, m_backLeftLocation);
    MecanumDriveWheelSpeeds m_speeds = new MecanumDriveWheelSpeeds(0,0,0,0);
    MecanumDriveWheelPositions wheelPositions;
    MecanumDriveOdometry m_odometry = new MecanumDriveOdometry(
      m_kinematics,
      gyro.getRotation2d(),
      new MecanumDriveWheelPositions(
        m_frontLeftEncoder.getDistance(),
        m_frontRightEncoder.getDistance(),
        m_backLeftEncoder.getDistance(),
        m_backRightEncoder.getDistance()
      ),
      new Pose2d(5, 13.5, getRotation2d())
    );
    public MecanumSub(){
      // Waits for the RIO to finishing booting
      new Thread(() -> {
        try {
            Thread.sleep(1000);
            zeroHeading();
            m_frontLeftEncoder.reset();
            m_frontRightEncoder.reset();
            m_backLeftEncoder.reset();
            m_backRightEncoder.reset();
        } catch (Exception e) {
          //die lol
        }
    }).start();
    AutoBuilder.configureHolonomic(
      this::getPose,
      this::resetPose,
      this::getChassisSpeeds,
      this::setChassisSpeeds,
      new HolonomicPathFollowerConfig(
        new PIDConstants(0,0,0),
        new PIDConstants(0,0,0),
        Constants.DriveConstants.MAX_SPEED_METERS_PER_SECOND,
        Constants.DriveConstants.DRIVE_BASE_RADIUS,
        new ReplanningConfig()
      ),this::getAlliance
      ,
      this
    );
    }
    @Override
    public void periodic() {
      xError = tx.getDouble(0.0);
      wheelPositions = new MecanumDriveWheelPositions(
      m_frontLeftEncoder.getDistance(), m_frontRightEncoder.getDistance(),
      m_backLeftEncoder.getDistance(), m_backRightEncoder.getDistance());

    robotPosition = m_odometry.update(getRotation2d(), wheelPositions);
    }
    public void zeroHeading() {
      gyro.reset();
  }
    public double getHeading() {
      return -1*Math.IEEEremainder(gyro.getAngle(),360); //modulus
  }
  public Rotation2d getRotation2d() {
          return Rotation2d.fromDegrees(getHeading());
      }
  public boolean getAlliance(){
        // Boolean supplier that controls when the path will be mirrored for the red alliance
            // This will flip the path being followed to the red side of the field.
            // THE ORIGIN WILL REMAIN ON THE BLUE SIDE
        var alliance = DriverStation.getAlliance();
            if (alliance.isPresent()) {
                return alliance.get() == DriverStation.Alliance.Red;
            }
            return false;
    }
  
  public void resetPose(Pose2d pose) {
        // LIST MODULES IN THE SAME EXACT ORDER USED WHEN DECLARING SwerveDriveKinematics
        m_odometry.resetPosition(getRotation2d(), wheelPositions, pose);
    }
  public void setChassisSpeeds(ChassisSpeeds speed) {
        m_speeds = m_kinematics.toWheelSpeeds(speed);
        frontLeft.set(m_speeds.frontLeftMetersPerSecond);
        frontRight.set(m_speeds.frontRightMetersPerSecond);
        backLeft.set(m_speeds.rearLeftMetersPerSecond);
        backRight.set(m_speeds.rearRightMetersPerSecond);
    }
  public void stop(){
    frontLeft.set(0);
    frontRight.set(0);
    backLeft.set(0);
    backRight.set(0);
  
  }
  public ChassisSpeeds getChassisSpeeds() {
      return m_kinematics.toChassisSpeeds(m_speeds);
  }
  public Pose2d getPose() {
    return robotPosition;
}
  }
