package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
public class DriveSubsystem extends SubsystemBase{
    WPI_TalonFX frontLeftMotor  = new WPI_TalonFX(Constants.FRONT_LEFT_MOTOR);
    WPI_TalonFX rearLeftMotor   = new WPI_TalonFX(Constants.BACK_LEFT_MOTOR);
    WPI_TalonFX frontRightMotor = new WPI_TalonFX(Constants.FRONT_RIGHT_MOTOR);
    WPI_TalonFX rearRightMotor  = new WPI_TalonFX(Constants.BACK_RIGHT_MOTOR);
    AHRS navX;
    MecanumDrive mecanumDrive;
    public DriveSubsystem() {
        navX = new AHRS(Port.kUSB1);
        rearRightMotor.setInverted(true);
        frontRightMotor.setInverted(true);
        mecanumDrive = new MecanumDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
      }
    
      // Add methods for drive control: 
      public void driveCartesian(double x, double y, double rotation) {
        mecanumDrive.driveCartesian(x, y, rotation);
        SmartDashboard.putNumber("FRONT LEFT", frontLeftMotor.get());
        SmartDashboard.putNumber("FRONT RIGHT", frontRightMotor.get());
        SmartDashboard.putNumber("BACK LEFT", rearLeftMotor.get());
        SmartDashboard.putNumber("BACK RIGHT", rearRightMotor.get());
      }
      public double calculateDistance() {

        // Get average encoder counts from all wheels
        double averageEncoderCounts = (frontLeftMotor.getSelectedSensorPosition() + 
                                       rearLeftMotor.getSelectedSensorPosition() + 
                                       frontRightMotor.getSelectedSensorPosition() + 
                                       rearRightMotor.getSelectedSensorPosition()) / 4.0;
    
        // Convert encoder counts to wheel rotations
        double rotations = averageEncoderCounts / Constants.AutoConstants.ENCODER_COUNTS_PER_REV;
    
        // Calculate distance traveled
        double distanceTraveled = rotations * Constants.AutoConstants.WHEEL_CIRCUMFERENCE;
    
        return distanceTraveled;
    }
    public void reset() {
        navX.reset();
      }
      public void resetEncoders() {
        frontLeftMotor.setSelectedSensorPosition(0);
        rearLeftMotor.setSelectedSensorPosition(0);
        frontRightMotor.setSelectedSensorPosition(0);
        rearRightMotor.setSelectedSensorPosition(0);
    }
    
    
      public double getYaw() {
        return navX.getYaw();
      }
}
