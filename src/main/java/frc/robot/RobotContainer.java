// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.HangC;
import frc.robot.commands.IntakeC;
import frc.robot.commands.LiftIntakeC;
import frc.robot.commands.Autos.*;
import frc.robot.subsystems.MecanumSub;

import java.util.jar.Manifest;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.HangS;
import frc.robot.subsystems.IntakeS;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
import frc.robot.subsystems.LiftIntakeS;
public class RobotContainer {
    public static MecanumSub _driveSub = new MecanumSub();
    DriveCommand _DriveCommand = new DriveCommand(_driveSub);
    IntakeS intakeS = new IntakeS();
    LiftIntakeS liftIntakeS = new LiftIntakeS();
    LiftIntakeC liftC = new LiftIntakeC(liftIntakeS);
    IntakeC intakeC = new IntakeC(intakeS);
  //  HangS hangS = new HangS();
    //HangC hangC = new HangC(hangS);
// The robot's subsystems and commands are defined here...
private final SendableChooser<SequentialCommandGroup> autoChooser = new SendableChooser<>();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  public final static XboxController m_driverController =
      new XboxController(OperatorConstants.kDriverControllerPort);
public final static XboxController m_operatorController =
      new XboxController(OperatorConstants.kOperatorController);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    configureAutoChooser();

    intakeS.setDefaultCommand(intakeC);
    liftIntakeS.setDefaultCommand(liftC);
    _driveSub.setDefaultCommand(_DriveCommand);
    ///hangS.setDefaultCommand(hangC);
   // hangS.pneumaticOut();
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




  private void configureAutoChooser() {
        SmartDashboard.putNumber("PID", Constants.AutoConstants.kPForward);
        autoChooser.addOption("RED Alone Driver Station Auto (2 amp scores)", new SequentialCommandGroup(
        new ForwardCommand(_driveSub, 1.55, liftIntakeS,false,true,false), //2.4 is 1 meter
        new StrafeCommand(_driveSub, intakeS,.4,false,0), // 17 inches, may need to be flipped
          new Manipulator(liftIntakeS, 0),
            new ShootCommand(intakeS,false)
            //new ForwardCommand(_driveSub, -1.5, liftIntakeS,false,true) //2.4 is 1 meter
            /*new ForwardCommand(_driveSub, -1, liftIntakeS,true,false),
            new StrafeCommand(_driveSub, intakeS,.4318,true), // 17 inches, may need to be flipped
            new ForwardCommand(_driveSub, 1, liftIntakeS,false,false),
            new StrafeCommand(_driveSub, intakeS,-.4318,false), // 17 inches
            new ShootCommand(intakeS),
            new ForwardCommand(_driveSub,-2, liftIntakeS,true,false),
            new RotateCommand(_driveSub, 180)
            *///2 scores in amp, and ready to get next.
            ));
        autoChooser.addOption("BLUE Alone Driver Station Auto (2 amp scores)", new SequentialCommandGroup(
        new ForwardCommand(_driveSub, -1.55, liftIntakeS,false,true,true), //2.4 is 1 meter was 1.35
        new StrafeCommand(_driveSub, intakeS,.4,false,0), // 17 inches, may need to be flipped   
                  new Manipulator(liftIntakeS, 0),   
        new ShootCommand(intakeS,true)

                         //new ForwardCommand(_driveSub, 1.5, liftIntakeS,false,true) //2.4 is 1 meter
           /* new ForwardCommand(_driveSub, 1,liftIntakeS,true,false),
            new StrafeCommand(_driveSub, intakeS,.4318,true), // 17 inches, may need to be flipped
            new ForwardCommand(_driveSub, -1,liftIntakeS,false,false),
            new StrafeCommand(_driveSub, intakeS,-.4318,false), // 17 inches
            new ShootCommand(intakeS),
            new ForwardCommand(_driveSub,2,liftIntakeS,true,true)*/
            //2 scores in amp, and ready to get next.
            ));
          autoChooser.addOption("2 point Red", new SequentialCommandGroup(
        new ForwardCommand(_driveSub, 1.55, liftIntakeS,false,true,true), //was -1.625
        new StrafeCommand(_driveSub, intakeS,.3,false,0), // 17 inches, may need to be flipped   
        new Manipulator(liftIntakeS, 0),   
        new ShootCommand(intakeS,true),

        new ParallelCommandGroup(
          new Manipulator(liftIntakeS, 1),
           new SequentialCommandGroup(
            new wait(.5),
          new RotateCommand(_driveSub, 62)
            )
          ),
          new StrafeCommand(_driveSub, intakeS, -.35, true,0),
          new RotateCommand(_driveSub, 69),
          new StrafeCommand(_driveSub, intakeS, .35, false,0),
          new ParallelCommandGroup(
            new SequentialCommandGroup(
              new RotateCommand(_driveSub, 0),
              new StrafeCommand(_driveSub, intakeS, .2, false,0)
            ),
            new Manipulator(liftIntakeS, 0)
          ),
          new ShootCommand(intakeS, true),
          new Manipulator(liftIntakeS, 1)
            ));

          autoChooser.addOption("2 point Blue", new SequentialCommandGroup(
        new ForwardCommand(_driveSub, -1.55, liftIntakeS,false,true,true), //was -1.625
        new StrafeCommand(_driveSub, intakeS,.4,false,0), // 17 inches, may need to be flipped   
        new Manipulator(liftIntakeS, 0),   
        new ShootCommand(intakeS,true),

       new ParallelCommandGroup(
          new Manipulator(liftIntakeS, 1),
           new SequentialCommandGroup(
            new wait(.5),
          new RotateCommand(_driveSub, -73)
            )
          ),
          new StrafeCommand(_driveSub, intakeS, -.2, true,0),
          new StrafeCommand(_driveSub, intakeS, .2, false,0),
          new ParallelCommandGroup(
            new SequentialCommandGroup(
              new RotateCommand(_driveSub, 0),
              new StrafeCommand(_driveSub, intakeS, .2, false,0)
            ),
            new Manipulator(liftIntakeS, 0)
          ),
          new ShootCommand(intakeS, true),
          new Manipulator(liftIntakeS, 1)
            ));

            autoChooser.addOption("Alterantive Test (Not that good) ", new SequentialCommandGroup(
        new ForwardCommand(_driveSub, 1.7, liftIntakeS,false,true,false), //2.4 is 1 meter
        new StrafeCommand(_driveSub, intakeS,.4,false,0), // 17 inches, may need to be flipped
        new ShootCommand(intakeS,false),
        new StrafeCommand(_driveSub, intakeS,-.4,false,0), // 17 inches, may need to be flipped
        new ForwardCommand(_driveSub, 4.8, liftIntakeS,true,true,false), //2.4 is 1 meter
        new RotateCommand(_driveSub, 45),
        new Manipulator(liftIntakeS, 1),
        new RotateCommand(_driveSub, -45),
        new ForwardCommand(_driveSub, -4.8, liftIntakeS,true,true,false), //2.4 is 1 meter
        new StrafeCommand(_driveSub, intakeS,0.2,true,0), // 17 inches, may need to be flipped
        new ShootCommand(intakeS,false)
            ));
        autoChooser.addOption("SIMPLE drive out",new SequentialCommandGroup(
             new ForwardCommand(_driveSub, 3, liftIntakeS,false,false,false) //2.4 is 1 meter
        ));
        autoChooser.addOption("TEST ALL", new SequentialCommandGroup(
      //    new ForwardCommand(_driveSub, 1, liftIntakeS, false, true, false),
          
          //  new StrafeCommand(_driveSub, intakeS, 1, false)
        ));
        autoChooser.addOption("Empty Auto", new SequentialCommandGroup()); // No-op auto
    
        SmartDashboard.putData("Auto Selector", autoChooser);
      }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public SequentialCommandGroup getAutonomousCommand() {
    return autoChooser.getSelected();  }
}
// robot container