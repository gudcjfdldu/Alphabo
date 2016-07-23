package com.project.alphabo;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;
/**
 * Created by Cathode on 2016-07-22.
 * 출처:http://dsnight.tistory.com/14
 */


public class BackPressCloseHandler {

    private long backKeyPressedTime = 0;
    private Toast toast;

    private Activity activity;
    SharedPreferences sharedPreferences;
    public BackPressCloseHandler(Activity context) {
        this.activity = context;

        sharedPreferences= context.getSharedPreferences("timerecord", Context.MODE_PRIVATE);

    }

    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            if (AlphaboTimeRecord.isLogined())
                AlphaboTimeRecord.recordTimeServer(sharedPreferences.getLong("installed_time",0));
            else
                Log.d("Logined:","False");
            activity.finish();
            toast.cancel();
        }
    }

    public void showGuide() {
        toast = Toast.makeText(activity,
                "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
        toast.show();
    }
}