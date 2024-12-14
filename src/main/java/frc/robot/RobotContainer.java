// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final Shooter m_shooter = Shooter.getInstance();
  private final Drivetrain m_drivetrain = Drivetrain.getInstance();

  private SendableChooser<Command> m_chooser = new SendableChooser<>();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(0);

  /** The container for the robot. Contains subsystems, OI devices, and commands. 
   * @throws InterruptedException */
  public RobotContainer() throws InterruptedException {
    // Configure the trigger bindings
    configureBindings();
    configureSelector();
    
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
    // new Trigger(m_exampleSubsystem::exampleCondition)
    //     .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    m_driverController.rightBumper()
      .onTrue(m_shooter.startCommand())
      .onFalse(m_shooter.stopCommand());

    m_driverController.leftBumper()
      .onTrue(m_shooter.startIntakeCommand())
      .onFalse(m_shooter.stopIntakeCommand());
    
    // m_driverController.leftStick().whileTrue(new RunCommand(() -> {m_drivetrain.drive(m_driverController.getLeftY(), m_driverController.getRightX());}, m_drivetrain));
  }

  public void configureDrive() {
    m_drivetrain.setDefaultCommand(new InstantCommand(
      () -> {
        m_drivetrain.drive(m_driverController.getLeftY(), m_driverController.getRightX());
      },
      m_drivetrain)
    );
  }

  // public void cancelDrive() {
  //   m_drivetrain.(null);
  // }

  public void configureSelector() {
    m_chooser.setDefaultOption("NO AUTO", Commands.print("womp womp lmao"));

    m_chooser.addOption("leaveauto", Autos.leaveAuto());
    m_chooser.addOption("leaveandshootauto", Autos.leaveShootAuto());

    SmartDashboard.putData("Auto Chooser", m_chooser);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    // return Autos.exampleAuto(m_exampleSubsystem);
    return m_chooser.getSelected();
  }
}
