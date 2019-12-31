package com.team7528.lib;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * This class holds variables and methods for the Limelight
 * vision camera. It holds two important public doubles; steerCommand,
 * which represents the amount of steering force desired to align,
 * and driveCommand, the amount of forward driving force to get closer
 * to the target.
 */
public class LimelightCamera {
    private NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
    @SuppressWarnings("FieldCanBeLocal")
    private boolean limelightHasValidTarget = false;
    public double steerCommand = 0.0;
    public double driveCommand = 0.0;

    /*  [CONSTANTS]   */
    //These must be tuned for each pipeline. Set them when you instantiate the LimelightCamera object
    private double STEER_K; //How hard to steer towards target
    private double DRIVE_K; //How hard to drive forward towards target
    private double DESIRED_TARGET_AREA; //Area of target when robot reaches wall
    private double MAX_SPEED; //Speed limit

    /**
     * Constructor for a LimelightCamera. You'll set your proportional constants for
     * steering and driving towards the target. These will need to be tuned for each use.
     *
     * @param steer Proportional constant for steering
     * @param drive Proportional constant for driving fwd towards target
     * @param desiredTargetArea Area of the target when the robot reaches the wall
     * @param maxSpeed Speed limit so we don't go too fast
     * @param startInDriverCam If true, this will start in driver camera mode, meaning
     *                         the vision processing will be OFF by default. Vice versa
     *                         for false.
     */
    public LimelightCamera(double steer, double drive, double desiredTargetArea, double maxSpeed, boolean startInDriverCam) {
        STEER_K = steer;
        DRIVE_K = drive;
        DESIRED_TARGET_AREA = desiredTargetArea;
        MAX_SPEED = maxSpeed;

        if(startInDriverCam) {
            limelightTable.getEntry("camMode").setNumber(1); //Set to driver cam mode
        } else {
            limelightTable.getEntry("camMode").setNumber(1); //Set to vision processing mode
        }
    }

    /**
     * This should be called iteratively (ie, in teleopPeriodic() or autonomousPeriodic()).
     * This updates the tracking values of the Limelight, with the relevant values for an
     * ArcadeDrive() call being output in driveCommand and steerCommand.
     */
    public void updateTracking() {
        limelightHasValidTarget = (limelightTable.getEntry("tv").getDouble(0)) == 1; //Determine whether limelight has a valid target

        if(limelightHasValidTarget) { //If target is acquired

            //Calculate proportional steering
            double horizontalOffset = limelightTable.getEntry("tx").getDouble(0);
            steerCommand = horizontalOffset * STEER_K;

            //Drive forward until target area is at our desired area
            double targetArea = limelightTable.getEntry("ta").getDouble(0);
            driveCommand = (DESIRED_TARGET_AREA - targetArea) * DRIVE_K;
            if(driveCommand > MAX_SPEED) {
                driveCommand = MAX_SPEED;
            }
        } else {
            driveCommand = 0.0;
            steerCommand = 0.0;
        }
    }

    /**
     * Turns on or off vision processing mode.
     *
     * @param tracking If true, vision processing will be enabled. If false, the Limelight will use
     *                 driver camera mode.
     */
    public void setTrackingMode(boolean tracking) {
        if(tracking) {
            System.out.println("VISION PROCESSING MODE ENABLED");
            limelightTable.getEntry("camMode").setNumber(0);
        } else {
            System.out.println("VISION PROCESSING MODE DISABLED");
            limelightTable.getEntry("camMode").setNumber(1);
        }
    }
}
