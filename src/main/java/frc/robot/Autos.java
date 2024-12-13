package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;

public class Autos {
    private static Drivetrain tank = Drivetrain.getInstance();
    private static Shooter shooter = Shooter.getInstance();

    public static Command leaveAuto() {
        return new SequentialCommandGroup(
            new InstantCommand(() -> {tank.setAutoSpeed();}),
            // new StartEndCommand(() -> tank.setAutoSpeed(), () -> tank.stopAuto(), tank).withTimeout(4),
            new WaitCommand(3),
            new InstantCommand(() -> {tank.stopAuto();})
        );
    }

    public static Command leaveShootAuto() {
        return new SequentialCommandGroup(
            new RunCommand(() -> {tank.setAutoSpeed();}),
            new WaitCommand(3),
            new InstantCommand(() -> {tank.stopAuto();}),
            shooter.startCommand()
        );
    }
}
