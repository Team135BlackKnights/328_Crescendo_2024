// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.IntakeC;
import frc.robot.commands.LiftIntakeC;
import frc.robot.commands.Autos.Auto;
import frc.robot.commands.Autos.AutoCommands.Drive;
import frc.robot.subsystems.MecanumSub;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.IntakeS;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
import frc.robot.subsystems.LiftIntakeS;
public class RobotContainer {
  public static final String controller2 = null;
    public static MecanumSub _driveSub = new MecanumSub();
    DriveCommand _DriveCommand = new DriveCommand(_driveSub);
    IntakeS intakeS = new IntakeS();
    LiftIntakeS liftIntakeS = new LiftIntakeS();
    LiftIntakeC liftC = new LiftIntakeC(liftIntakeS);
    IntakeC intakeC = new IntakeC(intakeS);
// The robot's subsystems and commands are defined here...
  public static Auto driveout = new Auto(_driveSub);

  // Replace with CommandPS4Controller or CommandJoystick if needed
  public final static XboxController m_driverController =
      new XboxController(OperatorConstants.kDriverControllerPort);
public final static XboxController m_operatorController =
      new XboxController(OperatorConstants.kOperatorController);
      SendableChooser<Command> m_Chooser = new SendableChooser<>();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    intakeS.setDefaultCommand(intakeC);
    liftIntakeS.setDefaultCommand(liftC);
    _driveSub.setDefaultCommand(_DriveCommand);
 
    m_Chooser.setDefaultOption("Drive out", driveout);
    SmartDashboard.putData(m_Chooser);
    // Configure the trigger bindings
    configureBindings();
  
    
  }
  

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_Chooser.getSelected();  }
}
// robot container