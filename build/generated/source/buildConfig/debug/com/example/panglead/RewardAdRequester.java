package com.example.panglead;

import static com.bytedance.sdk.openadsdk.TTAdLoadType.PRELOAD;

import android.app.Activity;
import android.content.Context;

import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTRewardVideoAd;

public class RewardAdRequester {

    public static void RequestRewardAd(Activity activity, Context context){
        TTAdNative mTTAdNative= TTAdSdk.getAdManager().createAdNative(context);

        AdSlot adSlot = new AdSlot.Builder()
                .setCodeId("948214330")
                .setExpressViewAcceptedSize(500,500)
                .setUserID("tag123")//tag_id
                .setMediaExtra("media_extra") //附加参数
                .setOrientation(TTAdConstant.VERTICAL) //必填参数，期望视频的播放方向：TTAdConstant.HORIZONTAL 或 TTAdConstant.VERTICAL
                .setAdLoadType(PRELOAD)//推荐使用，用于标注此次的广告请求用途为预加载（当做缓存）还是实时加载，方便后续为开发者优化相关策略
                .build();

        TTRewardVideoAd.RewardAdInteractionListener rewardAdInteractionListener = new TTRewardVideoAd.RewardAdInteractionListener() {
            @Override
            public void onAdShow() {
                System.out.println("xlt RewardAd onAdShow");
            }

            @Override
            public void onAdVideoBarClick() {
                System.out.println("xlt RewardAd onAdVideoBarClick");
            }

            @Override
            public void onAdClose() {
                System.out.println("xlt RewardAd onAdClose");
            }

            @Override
            public void onVideoComplete() {
                System.out.println("xlt RewardAd onVideoComplete");
            }

            @Override
            public void onVideoError() {
                System.out.println("xlt RewardAd onVideoError");
            }

            @Override
            public void onRewardVerify(boolean b, int i, String s, int i1, String s1) {
                System.out.println("xlt RewardAd onRewardVerify");
                System.out.println("xlt is Rewarded ? : " + b + "," + "count : " + i + "," + "string : " + s + ","
                        + "int i1 : " + i1 + "," + "string s1 : " + s1);
            }

            @Override
            public void onSkippedVideo() {
                System.out.println("xlt RewardAd onSkippedVideo");
            }
        };

        TTAdNative.RewardVideoAdListener rewardVideoAdListener = new TTAdNative.RewardVideoAdListener() {
            @Override
            public void onError(int code, String message) {
                System.out.println("xlt loadRewardVideoAd onError");

            }

            @Override
            public void onRewardVideoAdLoad(TTRewardVideoAd ttRewardVideoAd) {
                System.out.println("xlt loadRewardVideoAd onRewardVideoAdLoad");
            }

            //视频广告加载后，视频资源缓存到本地的回调，在此回调后，播放本地视频，流畅不阻塞。
            @Override
            public void onRewardVideoCached() {
                System.out.println("xlt loadRewardVideoAd onRewardVideoCached");
            }

            @Override
            public void onRewardVideoCached(TTRewardVideoAd ad) {
                ad.setRewardAdInteractionListener(rewardAdInteractionListener);
                ad.showRewardVideoAd(activity, TTAdConstant.RitScenes.CUSTOMIZE_SCENES, "scenes_test");
                System.out.println("xlt loadRewardVideoAd onRewardVideoCached");
            }
        };

        mTTAdNative.loadRewardVideoAd(adSlot, rewardVideoAdListener);
    }
}
