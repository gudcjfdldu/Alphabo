package com.project.alphabo;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import android.webkit.CookieManager;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Cathode on 2016-07-20.
 */
public class AlphaboTimeRecord {

    private final static String TIME_URL="http://alphabo.tk:8000/alphabo/time/";
    static public void recordFirstInstallTime(SharedPreferences time,PackageManager packageManager){
        SharedPreferences.Editor editor;
        editor=time.edit();

        try {
            editor.putLong("installed_time",packageManager.getPackageInfo("com.project.alphabo",0).firstInstallTime);
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("Record Installed Time","Faild....");
            e.printStackTrace();
        }
        editor.commit();
        Log.d("Record Installed Time","Worked....");
    }

    static public void recordTimeServer(long InstalledTime){
        String str=CookieManager.getInstance().getCookie("http://alphabo.tk:8000/alphabo/");
        Log.d("Csrf Token",str.split(";")[0].split("=")[1]);
        String body="installtime="+String.valueOf(InstalledTime)+"&exittime="+String.valueOf(System.currentTimeMillis())+"&csrfmiddlewaretoken="+str.split(";")[0].split("=")[1];
        HttpURLConnection conn = null;
        URL url ;
        try {

            url= new URL(TIME_URL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Cookie",str);
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            conn.setDoInput(true);
            conn.setDoOutput(true);


            OutputStream os = conn.getOutputStream();
            os.write(body.getBytes("utf-8"));
            os.flush();
            os.close();
            Log.d("HTTP CODE", String.valueOf(conn.getResponseCode()));
            Log.d("ServerTime:","Worked");
        } catch (IOException e) {
            Log.d("HTTP_TIME","Error!!");
            e.printStackTrace();
        }

    }

    //로그인 했을때 True 아닐때 False
    static public boolean isLogined() {

        String str = CookieManager.getInstance().getCookie("http://alphabo.tk:8000/alphabo/");
        if (!str.isEmpty()){
            if (str.contains("sessionid")) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }


}
