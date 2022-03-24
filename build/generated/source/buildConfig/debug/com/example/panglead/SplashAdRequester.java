package com.example.panglead;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.MainThread;

import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdLoadType;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTSplashAd;

public class SplashAdRequester {

    public static void RequestSplashAd(Activity activity, Context context){
        TTAdNative mTTAdNative = TTAdSdk.getAdManager().createAdNative(context);

        AdSlot adSlot = new AdSlot.Builder()
                .setCodeId("887734905")
                //不区分渲染方式，要求开发者同时设置setImageAcceptedSize（单位：px）和setExpressViewAcceptedSize（单位：dp ）接口，不同时设置可能会导致展示异常。
                .setImageAcceptedSize(1080, 1920)
                .setExpressViewAcceptedSize(1080, 1920)
                .setAdLoadType(TTAdLoadType.PRELOAD)//推荐使用，用于标注此次的广告请求用途为预加载（当做缓存）还是实时加载，方便后续为开发者优化相关策略
                .build();

        ViewGroup mSplashContainer = null;

        mTTAdNative.loadSplashAd(adSlot, new TTAdNative.SplashAdListener() {
            //请求广告失败
            @Override
            @MainThread
            public void onError(int code, String message) {
                //开发者处理跳转到APP主页面逻辑
            }

            //请求广告超时
            @Override
            @MainThread
            public void onTimeout() {
                //开发者处理跳转到APP主页面逻辑
            }

            //请求广告成功
            @Override
            @MainThread
            public void onSplashAdLoad(TTSplashAd ad) {
                if (ad == null) {
                    return;
                }
                //获取SplashView
                View view = ad.getSplashView();
                if (view != null && mSplashContainer != null && !activity.isFinishing()) {
                    mSplashContainer.removeAllViews();
                    //把SplashView 添加到ViewGroup中,注意开屏广告view：width =屏幕宽；height >=75%屏幕高
                    mSplashContainer.addView(view);
                    //设置不开启开屏广告倒计时功能以及不显示跳过按钮,如果这么设置，您需要自定义倒计时逻辑
                    //ad.setNotAllowSdkCountdown();
                }else {
                    //开发者处理跳转到APP主页面逻辑
                }
            }
        }, 5);

    }
}
