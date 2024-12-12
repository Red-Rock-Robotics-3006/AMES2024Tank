// package frc.robot.subsystems;

// import com.revrobotics.CANSparkFlex;
// import com.revrobotics.CANSparkMax;

// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;

// public class Drivetrain extends SubsystemBase {


//     private final CANSparkMax frontRightMotor = new CANSparkMax(44, CANSparkMax.MotorType.kBrushless);
//     private final CANSparkMax frontLeftMotor = new CANSparkMax(37, CANSparkMax.MotorType.kBrushless);
//     private final CANSparkMax backRightMotor = new CANSparkMax(47, CANSparkMax.MotorType.kBrushless);
//     private final CANSparkMax backLeftMotor = new CANSparkMax(27, CANSparkMax.MotorType.kBrushless);

//     public Drivetrain()
//     {
//         super("Drivetrain");

//         this.frontRightMotor.restoreFactoryDefaults();
//         this.frontRightMotor.setInverted(false);
//         this.frontRightMotor.setIdleMode(CANSparkFlex.IdleMode.kBrake);
        
//         this.frontLeftMotor.restoreFactoryDefaults();
//         this.frontLeftMotor.setInverted(false);
//         this.frontLeftMotor.setIdleMode(CANSparkFlex.IdleMode.kBrake);
        
//         this.backRightMotor.restoreFactoryDefaults();
//         this.backRightMotor.setInverted(false);
//         this.backRightMotor.setIdleMode(CANSparkFlex.IdleMode.kBrake);
        
//         this.backLeftMotor.restoreFactoryDefaults();
//         this.backLeftMotor.setInverted(false);
//         this.backLeftMotor.setIdleMode(CANSparkFlex.IdleMode.kBrake);
//     }

//     private void setLeftSpeed(double speed)
//     {
//         this.frontLeftMotor.set(speed);
//         this.backLeftMotor.set(speed);
//     }

//     private void setRightSpeed(double speed)
//     {
//         this.frontRightMotor.set(speed);
//         this.backRightMotor.set(speed);
//     }

//     public void drive(double speed, double dir)
//     {
//         double lSpeed = 0, rSpeed = 0;

//         if(dir == 0)
//         {
//             lSpeed = speed;
//             rSpeed = -speed;
//         }
//         else if(speed == 0)
//         {
//             lSpeed = speed * dir;
//             rSpeed = speed * dir;
//         }
//         else if(dir < 0)
//         {
//             lSpeed = speed * (dir+1);
//             rSpeed = speed;
//         }
//         else
//         {
//             lSpeed = -speed;
//             rSpeed = speed * (dir-1);
//         }

//         this.setLeftSpeed(lSpeed);
//         this.setRightSpeed(rSpeed);
//         // System.out.println(lSpeed);
//         SmartDashboard.putNumber("Drivetrain/lSpeed", lSpeed);
//         SmartDashboard.putNumber("Drivetrain/rSpeed", rSpeed);
//         SmartDashboard.putNumber("Drivetrain/speed", speed);
//         SmartDashboard.putNumber("Drivetrain/dir", dir);
//     }
// }
