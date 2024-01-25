// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.RobotContainer;
import frc.robot.subsystems.MecanumSub;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;

/** An example command that uses an example subsystem. */
public class DriveCommand extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final MecanumSub m_subsystem;



  public DriveCommand(MecanumSub subsystem) {
    m_subsystem = subsystem;
    ChassisSpeeds speeds;
    // Use addRequirements() here to declare subsystem dependencies
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_subsystem.drive.driveCartesian(RobotContainer.m_driverController.getLeftX(), RobotContainer.m_driverController.getLeftY(), RobotContainer.m_driverController.getRightX());
    ChassisSpeeds speeds = new ChassisSpeeds(RobotContainer.m_driverController.getLeftX()*Constants.MAX_SPEED_HORIZONTAL_METERS_PER_SECOND, RobotContainer.m_driverController.getLeftY()*Constants.MAX_SPEED_METERS_PER_SECOND, RobotContainer.m_driverController.getRightX()*Constants.MAX_SPEED_ROTATION_METERS_PER_SECOND);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
  
}
