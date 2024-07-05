// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import pabeles.concurrency.ConcurrencyOps.NewInstance;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {


  //4 motores chasis - 2 motores elevador - 1 motor intake - 1 motor garra - 2 motores brazo
  CANSparkMax chasisDerecho1 = new CANSparkMax(0, MotorType.kBrushless);
  CANSparkMax chasisDerecho2 = new CANSparkMax(1, MotorType.kBrushless);
  CANSparkMax chasisIzquierdo1 = new CANSparkMax(2, MotorType.kBrushless);
  CANSparkMax chasisIzquierdo2 = new CANSparkMax(3, MotorType.kBrushless);
  DifferentialDrive chasis = new DifferentialDrive(chasisIzquierdo1, chasisDerecho1);

  CANSparkMax elevador1 = new CANSparkMax(4, MotorType.kBrushless);
  CANSparkMax elevador2 = new CANSparkMax(5, MotorType.kBrushless);
  RelativeEncoder encoderElevador = elevador1.getEncoder();

  CANSparkMax intake = new CANSparkMax(6, MotorType.kBrushless);
  RelativeEncoder encoderIntake = intake.getEncoder();

  CANSparkMax garra = new CANSparkMax(7, MotorType.kBrushless);

  CANSparkMax brazo = new CANSparkMax(8, MotorType.kBrushless);
  //CANSparkMax brazo2 = new CANSparkMax(9, MotorType.kBrushless);
  RelativeEncoder encoderBrazo = brazo.getEncoder();

  Joystick control = new Joystick(0);

  CANSparkMax muñeca = new CANSparkMax(9, MotorType.kBrushless);
  RelativeEncoder encoderMuñeca = muñeca.getEncoder();




  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    chasisDerecho2.follow(chasisDerecho1);
    chasisIzquierdo2.follow(chasisIzquierdo1);
    elevador2.follow(elevador1);
  }

  @Override
  public void robotPeriodic() {
    chasis.tankDrive(control.getRawAxis(1)*0.7, control.getRawAxis(5) * 0.7);
  }

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {


    //brazo arriba
    while(control.getRawButton(5)){
      if(encoderBrazo.getPosition() >= 0 && encoderBrazo.getPosition() <= 40){
        brazo.set(0.4);
      }
      else{
        brazo.set(0);
      }
    }
    //brazo abajo
    while (control.getRawButton(6)) {
      if(encoderBrazo.getPosition() >= 0 && encoderBrazo.getPosition() <= 40){
        brazo.set(-0.4);
      }
      else{
        brazo.set(0);
      }
    }

    //intake
    while(control.getTriggerPressed() ) {
      if (encoderIntake.getPosition() >= 0 && encoderIntake.getPosition() <= 15) {
        intake.set(0.5);
      }
    }
  }


  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
