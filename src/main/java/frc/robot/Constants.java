package frc.robot;

public class Constants {
    public class DrivetrainConstants {
        public static final int frontLeftID = 2; // Front-left motor ID
        public static final int frontRightID = 0; // Front-right motor ID
        public static final int backLeftID = 3; // Back-left motor ID
        public static final int backRightID = 1; // back-right motor ID

        public static final double speed = 1; // Negate to invert robot (should be a decimal between -1 and 1)
    }

    public class DriverConstants {
        public static final int port = 0; // Controller port

        public static final double joystickDeadband = 0.08; // Deadzone for controller L/R joysticks
        public static final double triggerDeadband = 0; // Deadzone for controller L/R triggers

        public static final int leftJoystickAxis = 1; // Left joystick's axis (do not change)
        public static final int rightJoystickAxis = 5; // Right joystick's axis (do not change)

        public static final int leftTriggerAxis = 2;  // Left trigger's axis (do not change)
        public static final int rightTriggerAxis = 3; // Right trigger's axis (do not change)
    }
}
