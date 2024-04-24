/*
 * Copyright (c) 2016 The CyanogenMod Project
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
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.SwitchPreference;
import android.hardware.fingerprint.FingerprintManager;
import androidx.preference.PreferenceFragment;
import android.view.MenuItem;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import static com.moto.actions.Constants.*;

public class FPGestureSettingsFragment extends PreferenceFragment {

    private SwitchPreference mFPScreenOffGesture;
    private PreferenceCategory mFPScreenOffCategory;
    private PreferenceCategory mFPScreenOnCategory;

    private TextView mSwitchBarText;
    private Switch mFPGestureSwitch;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = LayoutInflater.from(getContext()).inflate(R.layout.fp_gesture, container, false);
        ((ViewGroup) view).addView(super.onCreateView(inflater, container, savedInstanceState));
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View switchBar = view.findViewById(R.id.switch_bar);
        mFPGestureSwitch = (Switch) switchBar.findViewById(android.R.id.switch_widget);
        mFPGestureSwitch.setChecked(isFPGestureEnabled());
        mFPGestureSwitch.setOnCheckedChangeListener(mFPGesturePrefListener);

        switchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFPGestureSwitch.toggle();
            }
        });

        mSwitchBarText = switchBar.findViewById(R.id.switch_text);
        mSwitchBarText.setText(isFPGestureEnabled() ? R.string.switch_bar_on : R.string.switch_bar_off);
    }

    private void updatePrefs(boolean enabled) {
        Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
        prefEditor.putBoolean(FP_HOME_KEY, enabled);
        prefEditor.apply();
        mFPScreenOnCategory.setEnabled(enabled);
        mFPScreenOffGesture.setEnabled(enabled);
        mFPScreenOffCategory.setEnabled(enabled);
        if (enabled) {
            boolean hasEnrolledFingerprints = hasEnrolledFingerprints();
            mFPScreenOffGesture.setEnabled(!hasEnrolledFingerprints);
            mFPScreenOffCategory.setEnabled(!hasEnrolledFingerprints);
        }
    }

    private boolean isFPGestureEnabled() {
        return Constants.isPreferenceEnabled(getActivity(), FP_HOME_KEY);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.fp_gesture_panel);
        mFPScreenOffGesture = (SwitchPreference) findPreference(FP_HOME_KEY_OFF);
        mFPScreenOffCategory = (PreferenceCategory) findPreference("fp_keys_scr_off");
        mFPScreenOnCategory = (PreferenceCategory) findPreference("fp_keys_scr_on");
        updatePrefs(isFPGestureEnabled());

        if (!getResources().getBoolean(R.bool.config_device_support_swipe_updown_gesture)) {
            hideUnsupportedFeature(FP_KEY_UP, mFPScreenOnCategory);
            hideUnsupportedFeature(FP_KEY_UP_OFF, mFPScreenOffCategory);
            hideUnsupportedFeature(FP_KEY_DOWN, mFPScreenOnCategory);
            hideUnsupportedFeature(FP_KEY_DOWN_OFF, mFPScreenOffCategory);
        }

        if (!getResources().getBoolean(R.bool.config_device_support_swipe_leftright_gesture)) {
            hideUnsupportedFeature(FP_KEY_LEFT, mFPScreenOnCategory);
            hideUnsupportedFeature(FP_KEY_LEFT_OFF, mFPScreenOffCategory);
            hideUnsupportedFeature(FP_KEY_RIGHT, mFPScreenOnCategory);
            hideUnsupportedFeature(FP_KEY_RIGHT_OFF, mFPScreenOffCategory);
        }

        if (!getResources().getBoolean(R.bool.config_device_support_doubletap_gesture)) {
            hideUnsupportedFeature(FP_KEY_DBLTAP, mFPScreenOnCategory);
            hideUnsupportedFeature(FP_KEY_DBLTAP_OFF, mFPScreenOffCategory);
        }

        if (!getResources().getBoolean(R.bool.config_device_support_longpress_gesture)) {
            hideUnsupportedFeature(FP_KEY_HOLD, mFPScreenOnCategory);
            hideUnsupportedFeature(FP_KEY_HOLD_OFF, mFPScreenOffCategory);
        }
    }

    private void hideUnsupportedFeature(String key, PreferenceCategory category) {
        Preference pref = category.findPreference(key);
        if (pref != null) {
            category.removePreference(pref);
        }
    }

    private CompoundButton.OnCheckedChangeListener mFPGesturePrefListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean enable) {
            updatePrefs(enable);
            mSwitchBarText.setText(enable ? R.string.switch_bar_on : R.string.switch_bar_off);
        }
    };

    private boolean hasEnrolledFingerprints() {
        FingerprintManager fingerprintManager = (FingerprintManager) getActivity()
                .getSystemService(Context.FINGERPRINT_SERVICE);
        return fingerprintManager.hasEnrolledFingerprints();
    }

}
