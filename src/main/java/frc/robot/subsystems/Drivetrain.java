package frc.robot.subsystems;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.controllers.PPLTVController;
import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase{
    double sensitivity = 0.5;
    CANSparkMax leftMotorFront = new CANSparkMax(37, CANSparkMax.MotorType.kBrushless);
    CANSparkMax leftMotorBack = new CANSparkMax(27, CANSparkMax.MotorType.kBrushless);
    CANSparkMax rightMotorFront = new CANSparkMax(44, CANSparkMax.MotorType.kBrushless);
    CANSparkMax rightMotorBack = new CANSparkMax(47, CANSparkMax.MotorType.kBrushless);

    private static Drivetrain instance = null;
    
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

        SmartDashboard.putNumber("autoSpeed", 0.2);
        SmartDashboard.putNumber("autoTime", 2);
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

    public void setAutoSpeed() {
        this.setLeftSpeed(SmartDashboard.getNumber("autoSpeed", 0)); 
        this.setRightSpeed(SmartDashboard.getNumber("autoSpeed", 0));
        System.out.println(SmartDashboard.getNumber("autoSpeed", 0));

        // this.setLeftSpeed(-0.2);
        // this.setRightSpeed(-0.2);
    }

    public Command setAutoSpeedCommand() {
        return new InstantCommand(() -> this.setAutoSpeed(), this);
    }

    public void stopAuto() {
        this.setLeftSpeed(0);
        this.setRightSpeed(0);
    }

    public Command stopAutoCommand() {
        return new InstantCommand(() -> this.stopAuto(), this);

    }

    public double getAutoTime() {
        return SmartDashboard.getNumber("autoTime", 0);
















        
    }

    @Override
    public void periodic(){
        sensitivity = SmartDashboard.getNumber("Drivetrain/sensitivity", sensitivity);
        SmartDashboard.putNumber("Drivetrain/sensitivity", sensitivity);
    }

    public static Drivetrain getInstance() {
        if (instance == null) instance = new Drivetrain();
        return instance;
    }
}
