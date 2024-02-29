// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final int FRONT_LEFT_MOTOR = 10;
    public static final int FRONT_RIGHT_MOTOR = 11;
    public static final int BACK_RIGHT_MOTOR = 12;
    public static final int BACK_LEFT_MOTOR = 13;
    public static final double DEADBAND = 0.05;
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kOperatorController = 1;

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
  public static int intakeMotorID2 = 21;

    public static final int Hang_ID = 40;
  public static final int Hang_ID2 = 41;
//placeholder values for autos
  public static int kAutoDriveSpeed = 3;
  public static int kAutoDriveDistanceInches = 3;

  // Robot Max Speeds
  public static final double MAX_SPEED_METERS_PER_SECOND = 3;
  public static final double MAX_SPEED_HORIZONTAL_METERS_PER_SECOND = 3;
  public static final double MAX_SPEED_ROTATION_METERS_PER_SECOND = 3;
  // encoders 
  public static class AutoConstants{
    public static final double kP = 0.02;
    public static final double WHEEL_CIRCUMFERENCE = 0.1524;
    public static final double ENCODER_COUNTS_PER_REV = 42;  //was 2048, changed 2/29
    public static final double TARGET_HEADING = 0.0;
    public static final double kPRotate = 0.05;
    public static final double kIRotate = 0.0;
    public static final double kDRotate = 0.0;
    public static final double kPForward = .03;
    public static final double kIForward = .015;
    public static final double kDForward = 0;
    public static final double kPStrafe = .2;
    public static final double kIStrafe = .025;
    public static final double kDStrafe = 0;
}   
public static class TeleConstants{
    public static final double liftConversionFactor = 14112;
}
}
