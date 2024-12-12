package frc.robot.subsystems;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase{
    double sensitivity = 0.5;
    CANSparkMax leftMotorFront = new CANSparkMax(37, CANSparkMax.MotorType.kBrushless);
    CANSparkMax leftMotorBack = new CANSparkMax(27, CANSparkMax.MotorType.kBrushless);
    CANSparkMax rightMotorFront = new CANSparkMax(44, CANSparkMax.MotorType.kBrushless);
    CANSparkMax rightMotorBack = new CANSparkMax(47, CANSparkMax.MotorType.kBrushless);
    
    public Drivetrain()
    {
        super("Drivetrain");
 
        this.rightMotorFront.restoreFactoryDefaults();
        this.rightMotorFront.setInverted(false);
        this.rightMotorFront.setIdleMode(CANSparkFlex.IdleMode.kBrake);
        
        this.leftMotorFront.restoreFactoryDefaults();
        this.leftMotorFront.setInverted(true);
        this.leftMotorFront.setIdleMode(CANSparkFlex.IdleMode.kBrake);
        
        this.rightMotorBack.restoreFactoryDefaults();
        this.rightMotorBack.setInverted(false);
        this.rightMotorBack.setIdleMode(CANSparkFlex.IdleMode.kBrake);
        
        this.leftMotorBack.restoreFactoryDefaults();
        this.leftMotorBack.setInverted(true);
        this.leftMotorBack.setIdleMode(CANSparkFlex.IdleMode.kBrake);
    }
    public void setLeftSpeed(double speed){
        leftMotorBack.set(speed);
        leftMotorFront.set(speed);
    }
    public void setRightSpeed(double speed){
        rightMotorBack.set(speed);
        rightMotorFront.set(speed);
    }
    public void drive(double speed, double turnAmount){
        //double speed = speed*sensitivity;
        //double speed = speed*sensitivity;
        // Turn amound will be based off of right X please, it will increase one side and simutaneuously decrease the other
        setLeftSpeed(sensitivity*(speed-turnAmount));
        setRightSpeed(sensitivity*(speed+turnAmount));
        SmartDashboard.putNumber("Drivetrain/lSpeed", sensitivity*(speed-turnAmount));
        
        SmartDashboard.putNumber("Drivetrain/rSpeed", sensitivity*(speed+turnAmount));
        
    }
    @Override
    public void periodic(){
        sensitivity = SmartDashboard.getNumber("Drivetrain/sensitivity", sensitivity);
        SmartDashboard.putNumber("Drivetrain/sensitivity", sensitivity);
    }
}
