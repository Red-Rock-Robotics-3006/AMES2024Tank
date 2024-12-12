package frc.robot.subsystems;

import com.revrobotics.CANSparkFlex;

import edu.wpi.first.wpilibj2.command.Command;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class index2 extends SubsystemBase {
    
    private final CANSparkFlex m_index2Motor = new CANSparkFlex(60, CANSparkFlex.MotorType.kBrushless);
    
    public index2(){
        super("index2");

        this.m_index2Motor.restoreFactoryDefaults();
        this.m_index2Motor.setInverted(false);
        this.m_index2Motor.setIdleMode(CANSparkFlex.IdleMode.kBrake);

        setSpeed(0.06);
        //wait(1000);
        // setSpeed(-0.75);
        //wait(1000);
        // setSpeed(0);

    }



    public void setSpeed(double speed) {
        this.m_index2Motor.set(speed);
    }

}