package com.team1234.frc2020;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import static com.team1234.frc2020.RobotMap.m_leftAft;
import static com.team1234.frc2020.RobotMap.m_rightAft;
import static com.team1234.frc2020.RobotMap.m_leftFront;
import static com.team1234.frc2020.RobotMap.m_rightFront;
import static com.team1234.frc2020.RobotMap.limelightCamera;
import static com.team1234.frc2020.RobotMap.joystick;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    private static DifferentialDrive m_drive;

    @Override
    public void robotInit() {
        //Format Victors to factory defaults
        m_leftAft.configFactoryDefault();
        m_leftFront.configFactoryDefault();
        m_rightAft.configFactoryDefault();
        m_rightFront.configFactoryDefault();

        //Back motors follow the forward motors
        m_leftAft.follow(m_leftFront);
        m_rightAft.follow(m_rightFront);

        //Instantiate DifferentialDrive
        m_drive = new DifferentialDrive(m_leftFront,RobotMap.m_rightFront);

        //Set Limelight to use pipeline 0
        limelightCamera.setPipeline(0);
    }

    @Override
    public void robotPeriodic() {
    }

    @Override
    public void autonomousInit() {
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        limelightCamera.updateTracking(); //Update tracking on the Limelight
        boolean auto = joystick.getRawButton(2);

        if(auto) { //If alignment button is being pressed...
            m_drive.arcadeDrive(limelightCamera.steerCommand,limelightCamera.driveCommand);
            //Drive the robot according to the info collected from the Limelight
        } else { //If alignment button is NOT being pressed...
            m_drive.arcadeDrive(joystick.getX(),joystick.getY()); //Drive chassis according to joystick inputs
        }

        if(joystick.getRawButton(4)) { //If button 4 of the joystick is pressed...
            limelightCamera.setTrackingMode(true); //Set the limelight to vision processing mode
        }

        if(joystick.getRawButton(5)) { //If button 5 of the joystick is pressed...
            limelightCamera.setTrackingMode(false); //Set the limelight to driver camera mode
        }
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }
}
