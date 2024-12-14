package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;

public class Autos {
    private static Drivetrain tank = Drivetrain.getInstance();
    private static Shooter shooter = Shooter.getInstance();

    public static Command leaveAuto() {
        return new SequentialCommandGroup(
            tank.setAutoSpeedCommand(),
            // new StartEndCommand(() -> tank.setAutoSpeed(), () -> tank.stopAuto(), tank).withTimeout(4),
            new WaitCommand(tank.getAutoTime()),
            tank.stopAutoCommand()
        );
    }

    public static Command leaveShootAuto() {
        return new SequentialCommandGroup(
            tank.setAutoSpeedCommand(),
            new WaitCommand(tank.getAutoTime()),
            tank.stopAutoCommand(),
            shooter.startCommand()
        );
    }
}
