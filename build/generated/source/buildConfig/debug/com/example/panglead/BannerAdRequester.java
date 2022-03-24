package com.example.panglead;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.DislikeInfo;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdLoadType;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTBannerAd;
import com.bytedance.sdk.openadsdk.TTNativeExpressAd;

import java.util.List;

public class BannerAdRequester {

    public static void RequestBannerAd(Activity activity, Context context){
        TTAdNative mTTAdNative= TTAdSdk.getAdManager().createAdNative(context);

        AdSlot adSlot = new AdSlot.Builder()
                .setCodeId("948248540") //广告位id
                .setSupportDeepLink(true)
                .setAdCount(1) //请求广告数量为1
                .setExpressViewAcceptedSize(600,90) //期望模板广告view的size,单位dp
                .setAdLoadType(TTAdLoadType.PRELOAD)//推荐使用，用于标注此次的广告请求用途为预加载（当做缓存）还是实时加载，方便后续为开发者优化相关策略
                .build();

        TTNativeExpressAd.ExpressAdInteractionListener expressAdInteractionListener = new TTNativeExpressAd.ExpressAdInteractionListener() {

            @Override
            public void onAdClicked(View view, int i) {

            }

            //广告展示回调
            @Override
            public void onAdShow(View view, int type) {

            }

            //广告渲染失败回调
            @Override
            public void onRenderFail(View view, String msg, int code) {

            }
            //广告渲染成功回调
            @Override
            public void onRenderSuccess(View view, float width, float height) {

            }
        };

        ViewGroup mBannerContainer = (ViewGroup) activity.findViewById(R.layout.activity_widget);

        mTTAdNative.loadBannerExpressAd(adSlot, new TTAdNative.NativeExpressAdListener() {
            //请求失败回调
            @Override
            public void onError(int code, String message) {
                System.out.println("xlt: Banner Request Failed : code = " + code + ", message = " + message);
            }

            //请求成功回调
            @Override
            public void onNativeExpressAdLoad(List<TTNativeExpressAd> ads) {
                if (ads.get(0) == null) {
                    return;
                }
                //bannerView接入方自行创建
                //mBannerContainer是给bannerView留的布局控件
                View bannerView = LayoutInflater.from(context).inflate(R.layout.activity_widget, mBannerContainer, false);
                if (bannerView == null) {
                    return;
                }
                mBannerContainer.removeAllViews();
                mBannerContainer.addView(bannerView);
                //绑定原生广告的数据 根据不同的广告类型类型 在adapter中接入方把获取到广告的元素添加到对应banner的控件上即可
                /*setAdData(bannerView, ads.get(0));*/
            }
        });

    }
}
