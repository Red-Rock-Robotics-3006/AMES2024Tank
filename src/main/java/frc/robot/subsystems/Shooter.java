package frc.robot.subsystems;
import com.revrobotics.CANSparkFlex;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase{
    private final int SHOOTER_MOTOR_ID = 61; //TODO
    private final int INDEX_MOTOR_ID = 60; //TODO
    private final CANSparkFlex m_shooterMotor = new CANSparkFlex(SHOOTER_MOTOR_ID, CANSparkFlex.MotorType.kBrushless);
    private final CANSparkFlex m_indexMotor = new CANSparkFlex(INDEX_MOTOR_ID, CANSparkFlex.MotorType.kBrushless);
    private final CANSparkFlex m_intakeMotor = new CANSparkFlex(17, CANSparkFlex.MotorType.kBrushless);

    private double shooterSpeed = 0.37;
    private double indexSpeed = 0.4;
    private double intakeSpeed = 0.4;
    
    public Shooter(){
        super("Shooter");

        this.m_shooterMotor.restoreFactoryDefaults();
        this.m_shooterMotor.setInverted(false);
        this.m_shooterMotor.setIdleMode(CANSparkFlex.IdleMode.kBrake);

        this.m_indexMotor.restoreFactoryDefaults();
        this.m_indexMotor.setInverted(false);
        this.m_indexMotor.setIdleMode(CANSparkFlex.IdleMode.kBrake);
        
        this.m_intakeMotor.restoreFactoryDefaults();
        this.m_intakeMotor.setInverted(false);
        this.m_intakeMotor.setIdleMode(CANSparkFlex.IdleMode.kBrake);
        
        SmartDashboard.putNumber("Shooter/shooterSpeed", this.shooterSpeed);
        SmartDashboard.putNumber("Shooter/indexSpeed", this.indexSpeed);
        SmartDashboard.putNumber("Shooter/intakeSpeed", this.intakeSpeed);

    }
    // ahh who made al lthe parameters the same as the varialbes
    private void setShooter(double speed){
        this.m_shooterMotor.set(speed);
    }
    private void setIndex(double speed){
        this.m_indexMotor.set(speed);
    }
    public void startShooter(){
        this.setShooter(this.shooterSpeed);
        this.setIndex(this.indexSpeed);
    }
    public void stopShooter(){
        this.setShooter(0);
        this.setIndex(0);
    }
    public void setIntakeSpeed(double speed){
        this.m_intakeMotor.set(speed);
    }
    public void startIntake(){
        this.setIntakeSpeed(this.intakeSpeed);
    }
    public void stopIntake(){
        this.setIntakeSpeed(0);
    }
    
  public Command startCommand() {
    return runOnce(
        () -> {
            this.startShooter();
        });
  }
  
  public Command stopCommand() {
      return runOnce(
          () -> {
              this.stopShooter();
            });
        }
    public Command startIntakeCommand() {
      return runOnce(
          () -> {
                 this.startIntake();
          });
        }
    public Command stopIntakeCommand() {
      return runOnce(
          () -> {
                 this.stopIntake();
          });
        }
        
    @Override
    public void periodic()
    {
        this.shooterSpeed = SmartDashboard.getNumber("Shooter/shooterSpeed", this.shooterSpeed);
        this.indexSpeed = SmartDashboard.getNumber("Shooter/indexSpeed", this.indexSpeed);
        this.intakeSpeed = SmartDashboard.getNumber("Shooter/intakeSpeed", this.intakeSpeed);

        
        SmartDashboard.putNumber("Shooter/shooterSpeed", this.shooterSpeed);
        SmartDashboard.putNumber("Shooter/indexSpeed", this.indexSpeed);
        SmartDashboard.putNumber("Shooter/intakeSpeed", this.intakeSpeed);
   

    }
}
