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
        autoChooser.setDefaultOption("RED Alone Driver Station Auto (2 amp scores)", new SequentialCommandGroup(
            new ForwardCommand(driveSubsystem, 1.5),
            new RotateCommand(driveSubsystem, 180),
            new StrafeCommand(driveSubsystem, -.4318), // 17 inches, may need to be flipped
            //new shoot command,
            //new arm down command,
            new ForwardCommand(driveSubsystem, -1),
            //new intake and move .4318 meters command,
            new ForwardCommand(driveSubsystem, 1),
            //new arm up command,
            new StrafeCommand(driveSubsystem, -.4318), // 17 inches
            //new shoot command,
            new ForwardCommand(driveSubsystem,-2),
            new RotateCommand(driveSubsystem, 180)
            //2 scores in amp, and ready to get next.
            ));
        autoChooser.setDefaultOption("BLUE Alone Driver Station Auto (2 amp scores)", new SequentialCommandGroup(
            new ForwardCommand(driveSubsystem, 1.5),
            new StrafeCommand(driveSubsystem, -.4318), // 17 inches
            //new shoot command,
            //new arm down command,
            new ForwardCommand(driveSubsystem, 1),
            //new intake and move .4318 meters command,
            new ForwardCommand(driveSubsystem, -1),
            //new arm up command,
            new StrafeCommand(driveSubsystem, -.4318), // 17 inches
            //new shoot command,
            new ForwardCommand(driveSubsystem,2)
            //2 scores in amp, and ready to get next.
            ));
        autoChooser.addOption("Empty Auto", new SequentialCommandGroup()); // No-op auto
    
        SmartDashboard.putData("Auto Selector", autoChooser);
      }
      public SequentialCommandGroup getAutonomousCommand() {
        return autoChooser.getSelected();  }
}
