package com.topv1.cyz.colorbook;

import android.os.Handler;

import java.util.Map;

/**
 * HttpRequestInfo
 * Created by Administrator on 2016/9/1.
 */
public class HttpRequestInfo {
    public static final String GET = "GET";
    public static final String POST = "POST";

    private String mMethod = "GET";
    private String mUrl;
    private String mReferer = "";
    private Map<String, String> mParam;
    private String mPureParam;
    private int mRetry = 0;
    private Handler mOnSuccess;
    private Handler mOnFailure;
    private Handler mOnStateChanged;

    public HttpRequestInfo() {
    }

    public HttpRequestInfo(String url) {
        mUrl = url;
    }

    public HttpRequestInfo(String url, Map<String, String> param) {
        mUrl = url;
        mParam = param;
        mMethod = HttpRequestInfo.POST;
    }

    public HttpRequestInfo(String url, String method) {
        mUrl = url;
        mMethod = method;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getReferer() {
        return mReferer;
    }

    public void setReferer(String referer) {
        mReferer = referer;
    }

    public String getPureParam() {
        return mPureParam;
    }

    public void setPureParam(String pureParam) {
        mPureParam = pureParam;
    }

    public Handler getOnSuccess() {
        return mOnSuccess;
    }

    public void setOnSuccess(Handler onSuccess) {
        mOnSuccess = onSuccess;
    }

    public Handler getOnFailure() {
        return mOnFailure;
    }

    public void setOnFailure(Handler onFailure) {
        mOnFailure = onFailure;
    }

    public Handler getOnStateChanged() {
        return mOnStateChanged;
    }

    public void setOnStateChanged(Handler onStateChanged) {
        mOnStateChanged = onStateChanged;
    }

    public void Request() {
        //HttpRequest.Request(this);
    }
}
