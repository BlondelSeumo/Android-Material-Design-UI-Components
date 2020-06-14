package com.material.components.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.material.components.BuildConfig;

public class SharedPref {

    private Context context;
    private SharedPreferences sharedPreferences;

    private static final String FIRST_LAUNCH = "_.FIRST_LAUNCH";
    private static final String CLICK_OFFER = "_.MAX_CLICK_OFFER";
    private static final String CLICK_INTERS = "_.MAX_CLICK_INTERS ";
    private static final String CLICK_SWITCH = "_.MAX_CLICK_SWITCH";
    private static final String NEED_REGISTER = "_.NEED_REGISTER";
    private static final String FCM_PREF_KEY = "_.FCM_PREF_KEY";

    private static final int MAX_CLICK_OFFER = BuildConfig.DEBUG ? Integer.MAX_VALUE : 10;
    private static final int MAX_CLICK_INTERS = BuildConfig.DEBUG ? Integer.MAX_VALUE : 10;

    public SharedPref(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("MAIN_PREF", Context.MODE_PRIVATE);
    }

    public void setFirstLaunch(boolean flag) {
        sharedPreferences.edit().putBoolean(FIRST_LAUNCH, flag).apply();
    }

    public boolean isFirstLaunch() {
        return sharedPreferences.getBoolean(FIRST_LAUNCH, true);
    }

    public boolean actionClickOffer() {
        int current = sharedPreferences.getInt(CLICK_OFFER, 1);
        boolean is_reset = false;
        if (current < MAX_CLICK_OFFER) {
            current++;
        } else {
            current = 1;
            is_reset = true;
        }
        sharedPreferences.edit().putInt(CLICK_OFFER, current).apply();
        return is_reset;
    }

    public boolean actionClickInters() {
        int current = sharedPreferences.getInt(CLICK_INTERS, 1);
        boolean is_reset = false;
        if (current < MAX_CLICK_INTERS) {
            current++;
        } else {
            current = 1;
            is_reset = true;
        }
        sharedPreferences.edit().putInt(CLICK_INTERS, current).apply();
        return is_reset;
    }

    public boolean getClickSwitch() {
        return sharedPreferences.getBoolean(CLICK_SWITCH, true);
    }

    public void setClickSwitch(Boolean val) {
        sharedPreferences.edit().putBoolean(CLICK_SWITCH, val).apply();
    }

    public void setFcmRegId(String fcmRegId) {
        sharedPreferences.edit().putString(FCM_PREF_KEY, fcmRegId).apply();
    }

    public String getFcmRegId() {
        return sharedPreferences.getString(FCM_PREF_KEY, null);
    }

    public void setNeedRegister(boolean value) {
        sharedPreferences.edit().putBoolean(NEED_REGISTER, value).apply();
    }

    public boolean isNeedRegister() {
        return sharedPreferences.getBoolean(NEED_REGISTER, true);
    }

    public void setSubscibeNotif(boolean value) {
        sharedPreferences.edit().putBoolean("SUBSCRIBE_NOTIF", value).apply();
    }

    public boolean isSubscibeNotif() {
        return sharedPreferences.getBoolean("SUBSCRIBE_NOTIF", false);
    }

}
