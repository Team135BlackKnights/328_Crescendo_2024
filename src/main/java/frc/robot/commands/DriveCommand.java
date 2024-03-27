// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.RobotContainer;
import frc.robot.subsystems.MecanumSub;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;

/** An example command that uses an example subsystem.*/
public class DriveCommand extends Command {
  private final SlewRateLimiter xLimiter, yLimiter, turningLimiter;


  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final MecanumSub m_subsystem;
  


  public DriveCommand(MecanumSub subsystem) {
    m_subsystem = subsystem;
    this.xLimiter = new SlewRateLimiter(8);
    this.yLimiter = new SlewRateLimiter(8);
    this.turningLimiter = new SlewRateLimiter(8);
    // Use addRequirements() here to declare subsystem dependencies
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_subsystem.backLeft.setInverted(false);
    m_subsystem.backRight.setInverted(true);
    m_subsystem.frontLeft.setInverted(false);
    m_subsystem.frontRight.setInverted(true);

    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //m_subsystem.pCompress.enableDigital();
    double xSpeed = -RobotContainer.m_driverController.getLeftY();
   double ySpeed = RobotContainer.m_driverController.getLeftX();
   double turningSpeed = RobotContainer.m_driverController.getRightX();

    xSpeed = Math.pow(xSpeed, 2) * (xSpeed < 0 ? -1 : 1);
   ySpeed = Math.pow(ySpeed, 2) * (ySpeed < 0 ? -1 : 1);
   turningSpeed = Math.pow(turningSpeed, 2) * (turningSpeed < 0 ? -1 : 1);

   xSpeed = Math.abs(xSpeed) > Constants.DEADBAND ? xSpeed : 0.0;
   ySpeed = Math.abs(ySpeed) > Constants.DEADBAND ? ySpeed : 0.0;
   turningSpeed = Math.abs(turningSpeed) > Constants.DEADBAND ? turningSpeed : 0.0;
   xSpeed = xLimiter.calculate(xSpeed);
   ySpeed = yLimiter.calculate(ySpeed);
   turningSpeed = turningLimiter.calculate(turningSpeed);
   m_subsystem.drive.driveCartesian(xSpeed, ySpeed, turningSpeed);//,m_subsystem.getRotation());
    SmartDashboard.putNumber("HEADING", m_subsystem.getYaw());
    //new ChassisSpeeds(RobotContainer.m_driverController.getLeftX()*Constants.MAX_SPEED_HORIZONTAL_METERS_PER_SECOND, RobotContainer.m_driverController.getLeftY()*Constants.MAX_SPEED_METERS_PER_SECOND, RobotContainer.m_driverController.getRightX()*Constants.MAX_SPEED_ROTATION_METERS_PER_SECOND);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
  // A chooser for autonomous commands
  // SendableChooser<Command> m_chooser = new SendableChooser<>(); not sure why this is in drivecommand but ok
  
}