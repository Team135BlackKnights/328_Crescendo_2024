package frc.robot;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ForwardCommand;
import frc.robot.commands.RotateCommand;
import frc.robot.commands.StrafeCommand;
import frc.robot.subsystems.DriveSubsystem;


import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
public class RobotContainer {
  private final SendableChooser<SequentialCommandGroup> autoChooser = new SendableChooser<>();
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();

    public final static XboxController m_driverController =
      new XboxController(Constants.DRIVER_CONTROLLER);
      public RobotContainer() {
        // Configure the trigger bindings
        configureBindings();
        configureAutoChooser();
        driveSubsystem.setDefaultCommand(new DriveCommand(driveSubsystem));

       // intakeS.setDefaultCommand(intakeC);
        //_driveSub.setDefaultCommand(new DriveCommand(_driveSub));
    
        //NamedCommands.registerCommand("Pathy Boi", new Drive(_driveSub, 2, .5));
//        SmartDashboard.putData("Auto Chooser",autoChooser);
    
        // Configure the trigger bindings
        configureBindings();
      
        
      }
      private void configureBindings() {
        // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
      }
      private void configureAutoChooser() {
        autoChooser.setDefaultOption("Forward 1 Meter", new SequentialCommandGroup(
            new ForwardCommand(driveSubsystem, 1.0),
            new StrafeCommand(driveSubsystem, .4318), // 17 inches
            new RotateCommand(driveSubsystem, 90)
            ));
        autoChooser.addOption("Empty Auto", new SequentialCommandGroup()); // No-op auto
    
        SmartDashboard.putData("Auto Selector", autoChooser);
      }
      public SequentialCommandGroup getAutonomousCommand() {
        return autoChooser.getSelected();  }
}
