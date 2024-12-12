package frc.robot.subsystems;

import com.revrobotics.CANSparkFlex;

import edu.wpi.first.wpilibj2.command.Command;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Index extends SubsystemBase {
    
    private final CANSparkFlex m_indexMotor = new CANSparkFlex(60, CANSparkFlex.MotorType.kBrushless);
    
    private Index(){
        super("Index");

        this.m_indexMotor.restoreFactoryDefaults();
        this.m_indexMotor.setInverted(false);
        this.m_indexMotor.setIdleMode(CANSparkFlex.IdleMode.kBrake);

        setSpeed(0.1);
        // wait(1000);
        // setSpeed(0);
        // wait(1000);
        // setSpeed(-0.1);
        // wait(1000);
        // setSpeed(0);

    }



    public void setSpeed(double speed) {
        this.m_indexMotor.set(speed);
    }

}
