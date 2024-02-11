// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.RobotContainer;
import frc.robot.subsystems.MecanumSub;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;

/** An example command that uses an example subsystem.*/
public class DriveCommand extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final MecanumSub m_subsystem;
  private final SlewRateLimiter xLimiter, yLimiter, turningLimiter;
  public ChassisSpeeds chassisSpeeds;
  private final boolean fieldOriented = true;

  public DriveCommand(MecanumSub subsystem) {
    m_subsystem = subsystem;
    this.xLimiter = new SlewRateLimiter(Constants.DriveConstants.kTeleDriveMaxAcceleration);
    this.yLimiter = new SlewRateLimiter(Constants.DriveConstants.kTeleDriveMaxAcceleration);
    this.turningLimiter = new SlewRateLimiter(Constants.DriveConstants.kTeleTurningMaxAcceleration);
    // Use addRequirements() here to declare subsystem dependencies
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   // m_subsystem.drive.driveCartesian(RobotContainer.m_driverController.getLeftY(), RobotContainer.m_driverController.getRightX(), RobotContainer.m_driverController.getLeftX());
   double xSpeed = -RobotContainer.m_driverController.getLeftX();
   double ySpeed = RobotContainer.m_driverController.getLeftY();
   double turningSpeed = RobotContainer.m_driverController.getRightX();

   xSpeed = Math.pow(xSpeed, 2) * (xSpeed < 0 ? -1 : 1);
   ySpeed = Math.pow(ySpeed, 2) * (ySpeed < 0 ? -1 : 1);
   turningSpeed = Math.pow(turningSpeed, 2) * (turningSpeed < 0 ? -1 : 1);

   xSpeed = Math.abs(xSpeed) > Constants.DriveConstants.Deadband ? xSpeed : 0.0001;
   ySpeed = Math.abs(ySpeed) > Constants.DriveConstants.Deadband ? ySpeed : 0.0001;
   turningSpeed = Math.abs(turningSpeed) > Constants.DriveConstants.Deadband ? turningSpeed : 0.0001;

   xSpeed = xLimiter.calculate(xSpeed) * Constants.DriveConstants.kTeleDriveMaxSpeed; 
    ySpeed = yLimiter.calculate(ySpeed) * Constants.DriveConstants.kTeleDriveMaxSpeed;
    turningSpeed = turningLimiter.calculate(turningSpeed) * Constants.DriveConstants.kMaxTurningSpeedRadPerSec;
    if (fieldOriented) {
      chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, ySpeed, turningSpeed, m_subsystem.getRotation2d());
    } else {
      chassisSpeeds = new ChassisSpeeds(xSpeed, ySpeed, turningSpeed);
    }
    m_subsystem.setChassisSpeeds(chassisSpeeds);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
  
}
