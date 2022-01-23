package com.myownassets.moa;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import org.apache.cordova.*;

public class MOA extends DroidGap {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.loadUrl("file:///android_asset/www/index.html");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_mo, menu);
        return true;
    }
}
