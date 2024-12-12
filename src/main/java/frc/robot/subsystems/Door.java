package frc.robot.subsystems;
import com.revrobotics.CANSparkFlex;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Door extends SubsystemBase{
    private final int DOOR_MOTOR_ID = 0; //TODO
    private final CANSparkFlex m_doorMotor = new CANSparkFlex(DOOR_MOTOR_ID, CANSparkFlex.MotorType.kBrushless);
    
    public double kP = 0.0; //TODO
    private double kI = 0.0; 
    private double kD = 0.0; //TODO
    public double kF = 0.0; //TODO
    private PIDController controller = new PIDController(kP, kI, kD);
    
    private double openPosition = 0.0; //TODO
    private double targetPosition;

    private Door(){
        super("Door");
        this.m_doorMotor.restoreFactoryDefaults();
        this.m_doorMotor.setInverted(false);
        this.m_doorMotor.setIdleMode(CANSparkFlex.IdleMode.kBrake);
        SmartDashboard.putNumber("Door/kF", kF);
        SmartDashboard.putNumber("Door/kP", kP);
        SmartDashboard.putNumber("Door/kD", kD);

    }
    
    public void set(double motorSpeed){
        this.m_doorMotor.set(motorSpeed);
    }
    public void openDoor(){
        this.targetPosition = this.openPosition;
    }
    public void closeDoor(){
        this.targetPosition = 0.0; 
    }
    @Override
    public void periodic(){
        double feedfoward = Math.sin(this.m_doorMotor.getEncoder().getPosition()/ this.openPosition * Math.PI/2) * kF;
        this.set(feedfoward + this.controller.calculate(this.m_doorMotor.getEncoder().getPosition(), this.targetPosition));

        this.kF = SmartDashboard.getNumber("Door/kF", kF);
        this.kD = SmartDashboard.getNumber("Door/kD", kD);
        this.kP = SmartDashboard.getNumber("Door/kP", kP);
        
        SmartDashboard.getNumber("Door/kF", kF);
        SmartDashboard.getNumber("Door/kD", kD);
        SmartDashboard.getNumber("Door/kP", kP);

        this.controller.setP(kP);
        this.controller.setD(kD);
    }

}
