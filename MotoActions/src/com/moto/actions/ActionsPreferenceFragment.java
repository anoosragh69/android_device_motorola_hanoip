/*
 * Copyright (C) 2015-2016 The CyanogenMod Project
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

import android.os.Bundle;
import android.os.SystemProperties;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragment;

import static com.moto.actions.Constants.*;

public class ActionsPreferenceFragment extends PreferenceFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.main_panel);

        if (!getResources().getBoolean(R.bool.config_device_support_assistant_key)) {
            Preference pref = getPreferenceScreen().findPreference(ASSISTANT_KEY);
            if (pref != null) {
                getPreferenceScreen().removePreference(pref);
            }
        }

        if (!getResources().getBoolean(R.bool.config_device_support_fingerprint_gestures)) {
            Preference pref = getPreferenceScreen().findPreference(FP_GESTURES_KEY);
            if (pref != null) {
                getPreferenceScreen().removePreference(pref);
            }
        }

         try {
            String deviceProp = SystemProperties.get("ro.product.product.device", "sofia");
            if (!deviceProp.contains("sofiap")) {
                Preference pref = getPreferenceScreen().findPreference("stylus");
                if (pref != null) {
                    getPreferenceScreen().removePreference(pref);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
