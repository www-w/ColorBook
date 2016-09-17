package com.topv1.cyz.colorbook;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * HttpGet
 * Created by Administrator on 2016/9/2.
 */
public class HttpGet implements HttpMethod {
    private static final String TAG = "com.topv1.cyz.colorbook";
    private HttpRequestInfo mHttpRequestInfo;
    private Thread mThread;

    public HttpGet(HttpRequestInfo httpRequestInfo) {
        mHttpRequestInfo = httpRequestInfo;
    }

    public void Start() {
        if (mThread != null) {
            //throw already start
            Log.e("TAG", "Http Get Already Started!");
            return;
        }
        mThread = new Thread() {
            @Override
            public void run() {
                try {
                    Log.i(TAG, "HTTP GET...");

                    URL url = new URL(mHttpRequestInfo.getUrl());
                    URLConnection httpURLConnection = url.openConnection();
                    if (!mHttpRequestInfo.getReferer().isEmpty()) {
                        httpURLConnection.addRequestProperty("Referer", mHttpRequestInfo.getReferer());
                    }
                    InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String ret = "";
                    String readline;// = null;

                    while ((readline = bufferedReader.readLine()) != null) {
                        ret += readline;
                        Log.i(TAG, readline);
                    }
                    inputStreamReader.close();


                    Message msg = new Message();
                    Bundle data = new Bundle();
                    data.putString("body", ret);
                    msg.setData(data);
                    Handler onSuccess = mHttpRequestInfo.getOnSuccess();
                    if (onSuccess != null) {
                        onSuccess.sendMessage(msg);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    Handler onFailure = mHttpRequestInfo.getOnFailure();
                    if (onFailure != null) {
                        Message msg = new Message();
                        Bundle data = new Bundle();
                        data.putString("body", e.toString());
                        onFailure.sendMessage(msg);
                    }
                }
            }
        };
        mThread.start();
    }

    public void Action(HttpRequest mHttpRequest) {

    }
}
