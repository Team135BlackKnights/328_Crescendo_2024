package frc.robot;

public class Constants {
    public static final int FRONT_LEFT_MOTOR = 12;
    public static final int FRONT_RIGHT_MOTOR = 10;
    public static final int BACK_RIGHT_MOTOR = 13;
    public static final int BACK_LEFT_MOTOR = 11;
    public static final int Lift_ID = 30;
    public static final int Lift2_ID = 31;
    public static final int Intake_ID = 20;
    public static final int Intake2_ID = 21;
    public static final int DRIVER_CONTROLLER = 0;
    public static class DriveConstants{
        public static final double DEADBAND = 0.1;}   
    public static class AutoConstants{
        public static final double kP = 0.02;
        public static final double WHEEL_CIRCUMFERENCE = 0.1524;
        public static final double ENCODER_COUNTS_PER_REV = 2048.0;
        public static final double TARGET_HEADING = 0.0;
        public static final double kPRotate = 0.5;
        public static final double kIRotate = 0.0;
        public static final double kDRotate = 0.0;
        public static final double kPForward = 1;
        public static final double kIForward = 0;
        public static final double kDForward = 0;
        public static final double kPStrafe = 1;
        public static final double kIStrafe = 0;
        public static final double kDStrafe = 0;
    }   
}
