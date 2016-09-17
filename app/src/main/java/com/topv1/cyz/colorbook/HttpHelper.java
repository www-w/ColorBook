package com.topv1.cyz.colorbook;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by Administrator on 2016/8/29.
 * HttpRequestInfo{
 * String Url;
 * String[] param;
 * Handler Success;
 * Handler Failure;
 * int RetryTime;
 * Handler StateChanged;
 * }
 * HttpHelper.Get(HttpRequestInfo);
 * HttpHelper.Post(HttpRequestInfo);
 */
public class HttpHelper {
    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String TAG = "com.topv1.cyz.colorbook";
    private String mUrl;
    private String mMethod;
    private String mReferer;
    private String mPostbody;
    private Handler mHandler;
    private Thread mThread;

    public HttpHelper(String url) {
        this(url, GET, null);
    }

    public HttpHelper(String url, String method, String postbody) {
        this(url, method, postbody, null);
    }

    public HttpHelper(String url, String method, String postbody, String referer) {
        mUrl = url;
        mMethod = method;
        mReferer = referer;
        mPostbody = postbody;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getMethod() {
        return mMethod;
    }

    public void setMethod(String method) {
        mMethod = method;
    }

    public String getReferer() {
        return mReferer;
    }

    public void setReferer(String referer) {
        mReferer = referer;
    }

    public String getPostbody() {
        return mPostbody;
    }

    public void setPostbody(String postbody) {
        mPostbody = postbody;
    }

    public Handler getHandler() {
        return mHandler;
    }

    public void setHandler(Handler handler) {
        mHandler = handler;
    }

    public void Request() {
        mThread = new Thread() {
            @Override
            public void run() {
                try {
                    Log.i(TAG, "START...");
                    //URL url = new URL("http://image.baidu.com/search/acjson?tn=resultjson_com&ipn=rj&ct=201326592&is=&fp=result&queryWord=%E6%B6%82%E8%89%B2&cl=2&lm=-1&ie=utf-8&oe=utf-8&adpicid=&st=-1&z=&ic=0&word=%E6%B6%82%E8%89%B2&s=&se=&tab=&width=&height=&face=0&istype=2&qc=&nc=1&fr=&pn=0&rn=30&gsm=1e&1472373233154=");
                    URL url = new URL(mUrl);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String ret = "";
                    String readline = null;
                    Log.i(TAG, "before Read");
                    while ((readline = bufferedReader.readLine()) != null) {
                        ret += readline;
                        Log.i(TAG, readline);
                    }
                    inputStreamReader.close();
                    httpURLConnection.disconnect();
                    Log.i(TAG, "Read End");
                    Message msg = new Message();
                    Bundle data = new Bundle();
                    data.putString("value", ret);
                    msg.setData(data);
                    mHandler.sendMessage(msg);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
