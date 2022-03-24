package com.example.panglead;

import android.app.Application;

import com.bytedance.sdk.openadsdk.TTAdConfig;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdManager;
import com.bytedance.sdk.openadsdk.TTAdSdk;

public class DemoApplication extends Application {
    public static String PROCESS_NAME_XXXX = "process_name_xxxx";

    @Override
    public void onCreate() {
        super.onCreate();

        PangleAdIniter.InitPangleAd(getApplicationContext());
    }
}