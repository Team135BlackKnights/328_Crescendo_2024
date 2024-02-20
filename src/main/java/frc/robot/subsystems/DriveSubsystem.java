package frc.robot.subsystems;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
public class DriveSubsystem extends SubsystemBase{
  public TalonFX frontLeft = new TalonFX(Constants.FRONT_LEFT_MOTOR);
  public TalonFX frontRight = new TalonFX(Constants.FRONT_RIGHT_MOTOR);
  public TalonFX backRight = new TalonFX(Constants.BACK_RIGHT_MOTOR);
  public TalonFX backLeft = new TalonFX(Constants.BACK_LEFT_MOTOR); 

  public MecanumDrive drive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
    AHRS navX;
    MecanumDrive mecanumDrive;
    public DriveSubsystem() {
        navX = new AHRS(Port.kUSB1);
        frontLeft.setNeutralMode(NeutralModeValue.Brake);
        frontRight.setNeutralMode(NeutralModeValue.Brake);
        backLeft.setNeutralMode(NeutralModeValue.Brake);
        backRight.setNeutralMode(NeutralModeValue.Brake); 
      }
    
      // Add methods for drive control: 
      public void driveCartesian(double x, double y, double rotation) {
        mecanumDrive.driveCartesian(x, y, rotation);
        SmartDashboard.putNumber("FRONT LEFT", frontLeft.get());
        SmartDashboard.putNumber("FRONT RIGHT", frontRight.get());
        SmartDashboard.putNumber("BACK LEFT", backLeft.get());
        SmartDashboard.putNumber("BACK RIGHT", backRight.get());
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
        double averageEncoderCounts = (frontLeft.getPosition().getValueAsDouble() + 
                                       frontRight.getPosition().getValueAsDouble() + 
                                       backLeft.getPosition().getValueAsDouble() + 
                                       backRight.getPosition().getValueAsDouble()) / 4.0;
    
        // Convert encoder counts to wheel rotations
        double rotations = averageEncoderCounts / Constants.AutoConstants.ENCODER_COUNTS_PER_REV;
    
        // Calculate distance traveled
        double[] distanceTraveled = new double[1];
        distanceTraveled[0] = rotations * Constants.AutoConstants.WHEEL_CIRCUMFERENCE;
        return distanceTraveled;
        }
    }
    public void reset() {
        navX.reset();
      }
      public void resetEncoders() {
        frontLeft.setPosition(0);
        frontRight.setPosition(0);
        backLeft.setPosition(0);
        backRight.setPosition(0);
    }
    
    
      public double getYaw() {
        return navX.getYaw();
      }
}
