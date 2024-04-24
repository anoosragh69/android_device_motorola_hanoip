/*
 * Copyright (C) 2016 The CyanogenMod Project
 * Copyright (C) 2017 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.moto.actions;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;

import com.moto.actions.util.FileUtils;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static final boolean DEBUG = true;

    private static final String TAG = "MotoActions";

    // FP gestures
    public static final int FP_TAP_SCANCODE = 616;
    public static final int FP_HOLD_SCANCODE = 617;
    public static final int FP_DTAP_SCANCODE = 622;
    public static final int FP_UP_SCANCODE = 618;
    public static final int FP_DOWN_SCANCODE = 619;
    public static final int FP_LEFT_SCANCODE = 621;
    public static final int FP_RIGHT_SCANCODE = 620;
    public static final int ASSISTANT_SCANCODE = 217;
    public static final int[] sSupportedFPGestures = new int[]{
            FP_TAP_SCANCODE,
            FP_HOLD_SCANCODE,
            FP_DTAP_SCANCODE,
            FP_UP_SCANCODE,
            FP_DOWN_SCANCODE,
            FP_LEFT_SCANCODE,
            FP_RIGHT_SCANCODE
    };

    // FP actions
    public static final int ACTION_HOME = 100;
    public static final int ACTION_POWER = 101;
    public static final int ACTION_BACK = 102;
    public static final int ACTION_RECENTS = 103;
    public static final int ACTION_VOLUME_UP = 104;
    public static final int ACTION_VOLUME_DOWN = 105;
    public static final int ACTION_VOICE_ASSISTANT = 106;
    public static final int ACTION_PLAY_PAUSE = 107;
    public static final int ACTION_PREVIOUS_TRACK = 108;
    public static final int ACTION_NEXT_TRACK = 109;
    public static final int ACTION_FLASHLIGHT = 110;
    public static final int ACTION_CAMERA = 111;
    public static final int ACTION_SCREENSHOT = 112;
    public static final int ACTION_BROWSER = 116;
    public static final int ACTION_DIALER = 117;
    public static final int ACTION_EMAIL = 118;
    public static final int ACTION_MESSAGES = 119;
    public static final int ACTION_PIP = 120;
    public static final int ACTION_LAST_APP = 121;
    public static final int[] sFPSupportedActions = new int[]{
            ACTION_HOME,
            ACTION_POWER,
            ACTION_BACK,
            ACTION_RECENTS,
            ACTION_VOLUME_UP,
            ACTION_VOLUME_DOWN,
            ACTION_VOICE_ASSISTANT,
            ACTION_PLAY_PAUSE,
            ACTION_PREVIOUS_TRACK,
            ACTION_NEXT_TRACK,
            ACTION_FLASHLIGHT,
            ACTION_CAMERA,
            ACTION_SCREENSHOT,
            ACTION_PIP,
            ACTION_LAST_APP
    };
    public static final int[] sFPSupportedActionsScreenOff = new int[]{
            ACTION_POWER,
            ACTION_VOLUME_UP,
            ACTION_VOLUME_DOWN,
            ACTION_PLAY_PAUSE,
            ACTION_PREVIOUS_TRACK,
            ACTION_NEXT_TRACK,
            ACTION_FLASHLIGHT,
            ACTION_CAMERA
    };

    // Swap keys
    public static final String FP_HOME_KEY = "fp_home";
    public static final String FP_HOME_KEY_OFF = "fp_home_scr_off";

    // Haptic node
    public static final String FP_HAPTIC_KEY = "fp_haptic";
    public static final String FP_HAPTIC_SCREENOFF_KEY = "fp_haptic_scr_off";

    // Proximity check node
    public static final String FP_PROXIMITY_CHECK_SCREENOFF_KEY = "fp_proximity_check_scr_off";

    // List of keys
    public static final String FP_KEYS = "fp_keys";
    public static final String FP_KEY_DBLTAP = "fp_key_dbltap";
    public static final String FP_KEY_HOLD = "fp_key_hold";
    public static final String FP_KEY_UP = "fp_key_up";
    public static final String FP_KEY_DOWN = "fp_key_down";
    public static final String FP_KEY_LEFT = "fp_key_left";
    public static final String FP_KEY_RIGHT = "fp_key_right";

    public static final String FP_KEYS_OFF = "fp_keys_off";
    public static final String FP_KEY_DBLTAP_OFF = "fp_key_dbltap_off";
    public static final String FP_KEY_HOLD_OFF = "fp_key_hold_off";
    public static final String FP_KEY_UP_OFF = "fp_key_up_off";
    public static final String FP_KEY_DOWN_OFF = "fp_key_down_off";
    public static final String FP_KEY_LEFT_OFF = "fp_key_left_off";
    public static final String FP_KEY_RIGHT_OFF = "fp_key_right_off";

    public static final int[] sScreenOffSupportedActions = new int[]{
            ACTION_POWER,
            ACTION_PLAY_PAUSE,
            ACTION_PREVIOUS_TRACK,
            ACTION_NEXT_TRACK,
            ACTION_FLASHLIGHT,
            ACTION_CAMERA,
            ACTION_BROWSER,
            ACTION_DIALER,
            ACTION_EMAIL,
            ACTION_MESSAGES
    };

    // Assistant key
    public static final String ASSISTANT_KEY = "assistant_button";

    // Gestures key
    public static final String FP_GESTURES_KEY = "fp_gestures";

    // Holds <preference_key> -> <proc_node> mapping
    public static final Map<String, String> sBooleanNodePreferenceMap = new HashMap<>();

    // Holds <preference_key> -> <default_values> mapping
    public static final Map<String, Object> sNodeDefaultMap = new HashMap<>();

    public static final String[] sPrefKeys = {
        FP_HOME_KEY,
        FP_HOME_KEY_OFF,
        FP_HAPTIC_KEY,
        FP_HAPTIC_SCREENOFF_KEY,
        FP_PROXIMITY_CHECK_SCREENOFF_KEY,
        FP_KEYS,
        FP_KEY_DBLTAP,
        FP_KEY_HOLD,
        FP_KEY_UP,
        FP_KEY_DOWN,
        FP_KEY_LEFT,
        FP_KEY_RIGHT,
        FP_KEYS_OFF,
        FP_KEY_DBLTAP_OFF,
        FP_KEY_HOLD_OFF,
        FP_KEY_UP_OFF,
        FP_KEY_DOWN_OFF,
        FP_HOME_KEY_OFF,
        FP_KEY_LEFT_OFF,
        FP_KEY_RIGHT_OFF
    };

    static {
        sNodeDefaultMap.put(FP_HOME_KEY, false);
        sNodeDefaultMap.put(FP_HOME_KEY_OFF, false);
        sNodeDefaultMap.put(FP_HAPTIC_KEY, false);
        sNodeDefaultMap.put(FP_HAPTIC_SCREENOFF_KEY, false);
        sNodeDefaultMap.put(FP_PROXIMITY_CHECK_SCREENOFF_KEY, true);
        sNodeDefaultMap.put(FP_KEYS, "0");
        sNodeDefaultMap.put(FP_KEY_DBLTAP, "0");
        sNodeDefaultMap.put(FP_KEY_HOLD, "0");
        sNodeDefaultMap.put(FP_KEY_UP, "0");
        sNodeDefaultMap.put(FP_KEY_DOWN, "0");
        sNodeDefaultMap.put(FP_KEY_LEFT, "0");
        sNodeDefaultMap.put(FP_KEY_RIGHT, "0");
        sNodeDefaultMap.put(FP_KEYS_OFF, "0");
        sNodeDefaultMap.put(FP_KEY_DBLTAP_OFF, "0");
        sNodeDefaultMap.put(FP_KEY_HOLD_OFF, "0");
        sNodeDefaultMap.put(FP_KEY_UP_OFF, "0");
        sNodeDefaultMap.put(FP_KEY_DOWN_OFF, "0");
        sNodeDefaultMap.put(FP_KEY_LEFT_OFF, "0");
        sNodeDefaultMap.put(FP_KEY_RIGHT_OFF, "0");
    }

    public static boolean isPreferenceEnabled(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(key, (Boolean) sNodeDefaultMap.get(key));
    }

    public static String GetPreference(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, (String) sNodeDefaultMap.get(key));
    }

    public static boolean GetBooleanPreference(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(key, (boolean) sNodeDefaultMap.get(key));
    }

    public static void setSystemSetting(Context context, String key) {
        boolean isBooleanPref = false;
        Log.w("SHIT", "key = " + key);
        if (FP_HOME_KEY.equals(key) || FP_HOME_KEY_OFF.equals(key) || FP_HAPTIC_KEY.equals(key) || FP_HAPTIC_SCREENOFF_KEY.equals(key)
                || FP_PROXIMITY_CHECK_SCREENOFF_KEY.equals(key)) {

                Settings.System.putInt(context.getContentResolver(),
                        key, GetBooleanPreference(context, key) ? 1 : 0);
        } else {
                Settings.System.putInt(context.getContentResolver(),
                        key, Integer.parseInt((String) GetPreference(context, key)));
        }
    }

    public static int getSystemSetting(Context context, String key) {
        return Settings.System.getInt(context.getContentResolver(),
                key, 0);
    }
}
