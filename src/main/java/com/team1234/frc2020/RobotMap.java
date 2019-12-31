package com.team7528.frc2020;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.team7528.lib.LimelightCamera;
import edu.wpi.first.wpilibj.Joystick;

@SuppressWarnings("WeakerAccess")
public class RobotMap {
    /*   [MOTOR CONTROLLERS]   */
    public static WPI_VictorSPX m_leftAft = new WPI_VictorSPX(0);
    public static WPI_VictorSPX m_leftFront = new WPI_VictorSPX(1);
    public static WPI_VictorSPX m_rightAft = new WPI_VictorSPX(2);
    public static WPI_VictorSPX m_rightFront = new WPI_VictorSPX(3);

    /*   [INPUT DEVICES]   */
    public static Joystick joystick = new Joystick(0);

    /*   [CAMERAS]   */
    public static LimelightCamera limelightCamera = new LimelightCamera(0.2,0.03,13.0,0.7, true);

}
