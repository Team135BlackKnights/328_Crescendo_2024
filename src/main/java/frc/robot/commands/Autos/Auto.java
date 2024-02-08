package frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autos.AutoCommands.Drive;
import frc.robot.subsystems.MecanumSub;

public class Auto extends SequentialCommandGroup {
   public Auto(MecanumSub drive) {
        super(
            Commands.sequence(
            
                new Drive(drive, 1, 0.5)
  
            )
        );

    }
}
