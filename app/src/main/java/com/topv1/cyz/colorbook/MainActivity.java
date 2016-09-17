package com.topv1.cyz.colorbook;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteOrder;
import java.nio.CharBuffer;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "com.topv1.cyz.colorbook";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!UserInfo.HasLogin()) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                hideLoading();
                Bundle data = msg.getData();
                String val = data.getString("body");
                TextView textView = (TextView) findViewById(R.id.tb);
                textView.setVisibility(View.VISIBLE);
                textView.setText(val);
            }
        };
        showLoading();
        HttpRequestInfo httpRequestInfo = new HttpRequestInfo();
        String url = "http://image.baidu.com/search/acjson?tn=resultjson_com&ipn=rj&ct=201326592&is=&fp=result&queryWord=%E6%B6%82%E8%89%B2&cl=2&lm=-1&ie=utf-8&oe=utf-8&adpicid=&st=-1&z=&ic=0&word=%E6%B6%82%E8%89%B2&s=&se=&tab=&width=&height=&face=0&istype=2&qc=&nc=1&fr=&pn=0&rn=30&gsm=1e&1472373233154=";
        httpRequestInfo.setUrl(url);
        httpRequestInfo.setOnSuccess(handler);
        httpRequestInfo.setOnFailure(handler);
        HttpGet httpGet = new HttpGet(httpRequestInfo);
        httpGet.Start();
    }

    private void showLoading() {
        TextView tv = (TextView) findViewById(R.id.tvloading);
        tv.setVisibility(View.VISIBLE);
        tv.setText(R.string.text_loading);
    }

    private void hideLoading() {
        TextView tv = (TextView) findViewById(R.id.tvloading);
        tv.setVisibility(View.INVISIBLE);
    }

}
