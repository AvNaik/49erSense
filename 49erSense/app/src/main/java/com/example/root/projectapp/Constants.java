package com.example.root.projectapp;

import android.provider.DocumentsContract;

public class Constants {

        private static final String ROOT_URL = "http://172.20.10.4/android/v1/";
        public static final String URL_REGISTER = ROOT_URL + "registerUser.php";
        public static final String URL_LOGIN = ROOT_URL + "userLogin.php";

        private static final String Root_Generators_URL = "http://172.20.10.4/Generators/v2/";
        public static final String URL_Toggle_Generators= Root_Generators_URL + "Toggle_Gensets.php";
        public static final String URL_VIEW = Root_Generators_URL + "view_Gensets.php";

        public static final String URL_WEATHERFORECAST = "http://172.20.10.4/android/v1/Weather.php";
        public static String username = SharedPrefManager.user_name;

        public static final String THERMOSTAT_URL = ROOT_URL + "Thermostat.php";

        public static final String TEMPERATURE_URL = ROOT_URL + "Temperature.php";

        public static final String SECURITYSYSTEM_URL = ROOT_URL + "Security_System.php";

        public static final String Lighting_URL = ROOT_URL + "Lighting.php";

        public static final String DoorLocks_URL = ROOT_URL + "Door_Locks.php";

        public static final String MotionDetector_URL = ROOT_URL + "Motion_Detector.php";

        public static String getSecurity_Status_URL = ROOT_URL + "getSecurityStatus.php";

        public static String getThermostat_Status_URL = ROOT_URL + "getThermostatStatus.php";

        public static String getLighting_Status_URL = ROOT_URL + "getLightingStatus.php";

        public static String getLockStatus_URL = ROOT_URL + "getLockStatus.php";

        public static String getMotionDetectorStatus_URL = ROOT_URL + "getMotionDetectorStatus.php";

}
