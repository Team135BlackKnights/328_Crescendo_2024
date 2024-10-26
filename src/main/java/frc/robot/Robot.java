// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot { //double distance up here somewhere
  private Command m_autonomousCommand;
  private Command DriveDistance;


  private RobotContainer m_robotContainer;
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  Thread m_visionThread;
  private String m_autoSelected;
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
@Override
public void robotInit() {
  // Instantiate our RobotContainer. This will perform all our button bindings and put our
  // autonomous chooser on \ the dashboard.
      m_visionThread =
  new Thread(
      () -> {
        // Get the UsbCamera from CameraServer
         UsbCamera camera = CameraServer.startAutomaticCapture();

        // Set the resolution
        camera.setResolution(240,144);
        camera.setFPS(24);
      });
      m_visionThread.setDaemon(true);
      //m_visionThread.start();
  m_robotContainer = new RobotContainer();
    }
  
/*                  
        // Get a CvSink. This will capture Mats from the camera
        CvSink cvSink = CameraServer.getVideo();
        // Setup a CvSource. This will send images back to the Dashboard
        CvSource outputStream = CameraServer.putVideo("Rectangle", 240, 144);

        // Mats are very memory expensive. Lets reuse this Mat.
        Mat mat = new Mat();

        // This cannot be 'true'. The program will never exit if it is. This
        // lets the robot stop this thread when restarting robot code or
        // deploying.
        while (!Thread.interrupted()) {
          // Tell the CvSink to grab a frame from the camera and put it
          // in the source mat.  If there is an error notify the output.
          if (cvSink.grabFrame(mat) == 0) {
            // Send the output the error.
            outputStream.notifyError(cvSink.getError());
            // skip the rest of the current iteration
            continue;
          }
          // Put a rectangle on the image
          Imgproc.rectangle(
              mat, new Point(500, 500), new Point(400, 400), new Scalar(255, 255, 255), 5);
          // Give the output stream a new image to display
          outputStream.putFrame(mat);
        }
      });
m_visionThread.setDaemon(true);
m_visionThread.start();
  // Add autonomous mode options to the chooser

  // Put the chooser on the SmartDashboard
  // You can access this dropdown menu on the SmartDashboard during runtime
  // to select the autonomous mode
}

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
  
    // Get the autonomous command based on the selected mode
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
  
    // Schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }
  
  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    // Switch based on the selected autonomous mode
    }
  

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    RobotContainer._driveSub.resetRotation();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
