package com.example.root.projectapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SharedPrefManager {
        public static String user_role;
        public static String user_name;
        public static String user_email;
        public static String GEN_Name;
        public static String GEN_Status;
        public static String GEN_Voltage;
        public static String GEN_Current;
        public static String Security_System_Status;
        public static String Floor ;
        public static String Thermostat_Mode;
         public static String Fan_Mode;
         public static String LightIntensity;
         public static String LockStatus;
         public static String MotionDetectorStatus;
        private static SharedPrefManager mInstance;
        private static Context mCtx;
        private static final String SHARED_PREF_NAME = "mysharedpref12";
        private static final String KEY_USER_ID = "key_Id";
        private static final String KEY_NAME = "key_Name";
        private static final String KEY_USER_Email = "key_Email";
        private static final String KEY_ROLE = "key_Role";
        private static final String KEY_GeneratorNAME = "key_NAME";
        private static final String KEY_GeneratorSTATUS = "key_Status";
        private static final String KEY_GeneratorVOLTAGE = "key_Voltage";
        private static final String KEY_GeneratorCURRENT = "key_Current";
        private static final String KEY_SecuritySystem = "key_Security";
        private static final String KEY_Floor = "key_floor";
        private static final String KEY_ThermostatMode = "key_thermostatmode";
        private static final String KEY_FanMode = "key_fanmode";
        private static final String KEY_LightLocation = "key_lightLocation";
        private static final String KEY_Intensity = "key_intensity";
        private static final String KEY_LockStatus   = "key_lockstatus";

        public SharedPrefManager(Context context) {
            mCtx = context;
        }

        public static synchronized SharedPrefManager getInstance(Context context) {
            if (mInstance == null) {
                mInstance = new SharedPrefManager(context);
            }
            return mInstance;
        }
        public boolean userLogin(int Id,  String Name, String Email, String Role)
        {
            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(KEY_USER_ID, Id);
            editor.putString(KEY_NAME, Name);
            editor.putString(KEY_USER_Email, Email);
            editor.putString(KEY_ROLE, Role);
            editor.apply ();
            user_role = sharedPreferences.getString(KEY_ROLE, "");
            user_name = sharedPreferences.getString(KEY_NAME, "");
            user_email = sharedPreferences.getString(KEY_USER_Email, "");
            return true;
        }

        public boolean SecuritySystemStatus (int Id, String Email, String Status)
        {
            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(KEY_USER_ID, Id);
            editor.putString(KEY_USER_Email, Email);
            editor.putString(KEY_SecuritySystem, Status);
            editor.apply();
            Security_System_Status = sharedPreferences.getString(KEY_SecuritySystem, "");
            return true;
        }

    public boolean ThermostatStatus (int Id, String Email, String Floor, String Mode, String FanMode)
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_USER_ID, Id);
        editor.putString(KEY_USER_Email, Email);
        editor.putString(KEY_Floor, Floor);
        editor.putString(KEY_ThermostatMode, Mode);
        editor.putString(KEY_FanMode, FanMode);
        editor.apply();
        Floor = sharedPreferences.getString(KEY_Floor, "");
        Thermostat_Mode = sharedPreferences.getString(KEY_ThermostatMode, "");
        Fan_Mode = sharedPreferences.getString(KEY_FanMode, "");
        return true;
    }

    public boolean LightingStatus (int Id, String Email, String Floor, String Location, String Intensity)
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_USER_ID, Id);
        editor.putString(KEY_USER_Email, Email);
        editor.putString(KEY_Floor, Floor);
        editor.putString(KEY_LightLocation, Location);
        editor.putString(KEY_Intensity, Intensity);
        editor.apply();
        LightIntensity = sharedPreferences.getString(KEY_Intensity, "");
        return true;
    }

    public boolean LockStatus (int Id, String Email, String Location, String Status)
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_USER_ID, Id);
        editor.putString(KEY_USER_Email, Email);
        editor.putString(KEY_LightLocation, Location);
        editor.putString(KEY_LockStatus, Status);
        editor.apply();
        LockStatus = sharedPreferences.getString(KEY_LockStatus, "");
        return true;
    }

    public boolean MotionDetectorStatus (int Id, String Email, String Floor, String Status)
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_USER_ID, Id);
        editor.putString(KEY_USER_Email, Email);
        editor.putString(KEY_Floor, Floor);
        editor.putString(KEY_LockStatus, Status);
        editor.apply();
        MotionDetectorStatus = sharedPreferences.getString(KEY_LockStatus, "");
        return true;
    }

        public boolean viewGenerators (int Id, String GenName, String Status, String Voltage, String Current)
        {
            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(KEY_USER_ID, Id);
            editor.putString(KEY_GeneratorNAME, GenName);
            editor.putString(KEY_GeneratorSTATUS, Status);
            editor.putString(KEY_GeneratorVOLTAGE, Voltage);
            editor.putString(KEY_GeneratorCURRENT, Current);
            editor.apply ();
            GEN_Name = sharedPreferences.getString(KEY_GeneratorNAME, "");
            GEN_Status = sharedPreferences.getString(KEY_GeneratorSTATUS, "");
            GEN_Voltage = sharedPreferences.getString(KEY_GeneratorVOLTAGE, "");
            GEN_Current = sharedPreferences.getString(KEY_GeneratorCURRENT, "");
            return true;
        }

        public boolean isLoggedIn()
        {
            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            if  (sharedPreferences.getString(KEY_USER_Email, null) != null)
            {
                return true;
            }
            return false;
        }

        public boolean logout ()
        {
            SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            return true;
        }

            public String getUserEmail()
            {
                SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                return sharedPreferences.getString(KEY_USER_Email, null);

            }

            public String getUserName()
            {
                SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                return sharedPreferences.getString(KEY_NAME, null);
            }
        }
