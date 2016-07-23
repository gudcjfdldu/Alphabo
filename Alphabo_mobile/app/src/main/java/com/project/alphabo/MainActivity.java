package com.project.alphabo;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String URL_MAIN = "http://alphabo.tk:8000/alphabo/login";
    public WebView mWebView;
    public WebSettings mWebSettings;
    private BackPressCloseHandler backPressCloseHandler;
    SharedPreferences preferences;
    PackageManager packageManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if(android.os.Build.VERSION.SDK_INT > 9) {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);

        }

        mWebView=(WebView)findViewById(R.id.webView_main);
        mWebSettings=mWebView.getSettings();
        mWebSettings.setSaveFormData(false);
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setSupportZoom(false);
        mWebSettings.setBuiltInZoomControls(false);
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebSettings.setDomStorageEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClientNew());
        mWebView.setLayerType(View.LAYER_TYPE_HARDWARE,null);
        mWebView.loadUrl(URL_MAIN);
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                String value = getIntent().getExtras().getString(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }


        // For FCM
        FirebaseMessaging.getInstance().subscribeToTopic("noti");
        Log.d(TAG, "InstanceID token: " + FirebaseInstanceId.getInstance().getToken());
        preferences=getSharedPreferences("timerecord",MODE_PRIVATE);
        packageManager=this.getPackageManager();
        AlphaboTimeRecord.recordFirstInstallTime(preferences,packageManager);
        Log.d(TAG,"Time Recorded");
        backPressCloseHandler = new BackPressCloseHandler(this);

    }
    @Override
    public void onBackPressed(){
        backPressCloseHandler.onBackPressed();
    }

    class WebViewClientNew extends WebViewClient{

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            view.loadUrl(url);

            return true;
        }

        // 웹뷰 로딩이 끝나면 시작.
        @Override
        public void onPageFinished(WebView view, String url) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                CookieManager.getInstance().flush();
                String str=CookieManager.getInstance().getCookie("http://alphabo.tk:8000/alphabo/");
                if (str!=null)
                    if (!str.isEmpty())
                        Log.d("Cookie-value-PageFinshe", str);


            }
            Log.d("onpagefinished", "onpagefinished");
            //Log.d("Cookie",CookieManager.getInstance().getCookie(url));
        }
    }
}
