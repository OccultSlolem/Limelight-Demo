# Limelight-Demo
Demo robot project that uses a 4-motor Victor SPX drivetrain that uses a Limelight for vision alignment

## Setup Instructions ##

**All IDEs**

1. Clone this repository by executing `git clone https://github.com/Uberlyuber/Limelight-Demo.git`
2. Run `gradlew deploy` to deploy this to your robot
3. Have fun!

**IntelliJ IDEA**

1. Select "Import Project from Existing Sources"
2. Point it towards the build.gradle file in the root of the project

**Visual Studio Code**

1. Strongly recommended - download the official WPIlib plugin from the VSCode marketplace.
2. Select Open Folder and point it towards the project folder.

## Code Highlights ##

* Uses proportional steering and driving to approach target
    * Proportional constants must be properly tuned for best performance

* Holds all the relevant information (methods, variables, results, etc) in a LimelightCamera class that can be easily manipulated or read from any other class.
* Can change the Limelight from vision processing mode to driver camera mode, and vice versa, at the press of a button.
* Follows target while button 2 on the joystick is pressed.