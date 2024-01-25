// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.math.geometry.Translation2d;
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
      public static final int FRONT_LEFT_MOTOR = 10;
    public static final int FRONT_RIGHT_MOTOR = 11;
    public static final int BACK_RIGHT_MOTOR = 12;
    public static final int BACK_LEFT_MOTOR = 13;
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }


// lift and intake motors
  public static final int Lift_ID = 20;
  public static final int Lift_ID2 = 21;
  public static int intakeMotorID = 30;

  // encoders 
  public static Encoder FRONT_LEFT_DRIVE_ENCODER;
  public static Encoder FRONT_RIGHT_DRIVE_ENCODER;
  public static Encoder BACK_LEFT_DRIVE_ENCODER;
  public static Encoder BACK_RIGHT_DRIVE_ENCODER;
}
