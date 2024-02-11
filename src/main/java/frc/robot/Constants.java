// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Encoder;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final int FRONT_LEFT_MOTOR = 12;
    public static final int FRONT_RIGHT_MOTOR = 10;
    public static final int BACK_RIGHT_MOTOR = 13;
    public static final int BACK_LEFT_MOTOR = 11;
    public static class DriveConstants{
      public static final double MAX_SPEED_METERS_PER_SECOND = 3;
      public static final double MAX_SPEED_HORIZONTAL_METERS_PER_SECOND = 3;
      public static final double MAX_SPEED_ROTATION_METERS_PER_SECOND = 3;
      public static final double DRIVE_BASE_RADIUS = Units.inchesToMeters(27.01);
      public static double Deadband = .01;
      public static double kTeleDriveMaxSpeed = 6.96;
      public static double kTeleDriveMaxAcceleration = 6.71;
      public static double kTeleTurningMaxAcceleration = 3; //guess
      public static double kMaxTurningSpeedRadPerSec = 2.285; //guess, maybe 45.67
    }
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;

    public static final double ksVolts = 0.22;
    public static final double kvVoltSecondsPerMeter = 1.98;
    public static final double kaVoltSecondsSquaredPerMeter = 0.2;

    // Example value only - as above, this must be tuned for your drive!

    public static final double kPDriveVel = 8.5;
        public static final double kMaxSpeedMetersPerSecond = 3;
        public static final double kMaxAccelerationMetersPerSecondSquared = 1;

        public static final double kRamseteB = 2.0;
        public static final double kRamseteZeta = 0.7;

  }


// lift and intake motor
  public static final int Lift_ID = 30;
  public static final int Lift_ID2 = 31;
  public static int intakeMotorID = 20;

  // Robot Max Speeds
  
  // encoders 
}
