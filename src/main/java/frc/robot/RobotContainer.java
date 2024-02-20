package frc.robot;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.IntakeC;
import frc.robot.commands.LiftIntakeC;
import frc.robot.commands.Autos.ForwardCommand;
import frc.robot.commands.Autos.RotateCommand;
import frc.robot.commands.Autos.ShootCommand;
import frc.robot.commands.Autos.StrafeCommand;
import frc.robot.subsystems.IntakeS;
import frc.robot.subsystems.LiftIntakeS;
import frc.robot.subsystems.MecanumSub;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
public class RobotContainer {
  private final SendableChooser<SequentialCommandGroup> autoChooser = new SendableChooser<>();
  private final MecanumSub driveSubsystem = new MecanumSub();
  private final IntakeS intakeS = new IntakeS();
  private final LiftIntakeS liftIntakeS = new LiftIntakeS();

    public final static XboxController m_driverController =
      new XboxController(Constants.DRIVER_CONTROLLER);
    public final static XboxController m_operatorController =
      new XboxController(Constants.OPERATOR_CONTROLLER);
      public RobotContainer() {
        // Configure the trigger bindings
        configureBindings();
        configureAutoChooser();
        driveSubsystem.setDefaultCommand(new DriveCommand(driveSubsystem));
        intakeS.setDefaultCommand(new IntakeC(intakeS));
        liftIntakeS.setDefaultCommand(new LiftIntakeC(liftIntakeS));
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
            new ForwardCommand(driveSubsystem, 1.5, liftIntakeS,false,true),
            new RotateCommand(driveSubsystem, 180),
            new StrafeCommand(driveSubsystem, intakeS,-.4318,false), // 17 inches, may need to be flipped
            new ShootCommand(intakeS),
            new ForwardCommand(driveSubsystem, -1, liftIntakeS,true,false),
            new StrafeCommand(driveSubsystem, intakeS,.4318,true), // 17 inches, may need to be flipped
            new ForwardCommand(driveSubsystem, 1, liftIntakeS,false,false),
            new StrafeCommand(driveSubsystem, intakeS,-.4318,false), // 17 inches
            new ShootCommand(intakeS),
            new ForwardCommand(driveSubsystem,-2, liftIntakeS,true,false),
            new RotateCommand(driveSubsystem, 180)
            //2 scores in amp, and ready to get next.
            ));
        autoChooser.setDefaultOption("BLUE Alone Driver Station Auto (2 amp scores)", new SequentialCommandGroup(
            new ForwardCommand(driveSubsystem, 1.5,liftIntakeS,false,true),
            new StrafeCommand(driveSubsystem, intakeS,-.4318,false), // 17 inches
            new ShootCommand(intakeS),
            new ForwardCommand(driveSubsystem, 1,liftIntakeS,true,false),
            new StrafeCommand(driveSubsystem, intakeS,.4318,true), // 17 inches, may need to be flipped
            new ForwardCommand(driveSubsystem, -1,liftIntakeS,false,false),
            new StrafeCommand(driveSubsystem, intakeS,-.4318,false), // 17 inches
            new ShootCommand(intakeS),
            new ForwardCommand(driveSubsystem,2,liftIntakeS,true,true)
            //2 scores in amp, and ready to get next.
            ));
        autoChooser.addOption("Empty Auto", new SequentialCommandGroup()); // No-op auto
    
        SmartDashboard.putData("Auto Selector", autoChooser);
      }
      public SequentialCommandGroup getAutonomousCommand() {
        return autoChooser.getSelected();  }
}
