package com.sherko.daneshgahazadboukan;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;
public class ChartActivity extends AppCompatActivity {
    private static String DB_NAME = "azad.db3";
    DrawerLayout drawerLayout;
    String strPdfUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        drawerLayout = findViewById(R.id.DrawerView3);
        findViewById(R.id.DrawerBtn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                } else
                    drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });
        ImageView imageView = findViewById(R.id.imgProfile);

        WebView webView = findViewById(R.id.webpdf);
        TestAdapter mDbHelper = new TestAdapter(this);
        mDbHelper.createDatabase();
        File db = new File(this.getDatabasePath(DB_NAME).getPath());
        if (db.exists()) {
            mDbHelper.open();
            Cursor C;
            C=mDbHelper.getTestData("select * from login");
            int intCasePdfChart = Integer.parseInt(C.getString(4));
            C = mDbHelper.getTestData("select * from reshte where id=" + C.getString(5));
            switch (intCasePdfChart){
                case 0:{
                    strPdfUrl =C.getString(8);
                    break;
                }
                case 1:{
                    strPdfUrl = C.getString(9);
                    break;
                }
                case 2:{
                    strPdfUrl = C.getString(11);
                    break;
                }
                case 3:{
                    strPdfUrl = C.getString(10);
                    break;
                }
                case 4:{
                    strPdfUrl = C.getString(12);
                    break;
                }
                case 5:{
                    strPdfUrl = C.getString(13);
                    break;
                }
            }
            mDbHelper.close();
        } else {
            File dir = new File(db.getParent());
            if (!dir.exists()) {
                dir.mkdirs();
            }
            Toast.makeText(this, "مشکل اتصال دیتابیس", Toast.LENGTH_LONG).show();
        }
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://docs.google.com/viewer?url="+strPdfUrl);

    }
    public void clkBarnama(View view){
        Intent barname = new Intent(this , BarnameActivity.class);
        startActivity(barname);
    }
    public void clkChart(View view){
        Intent chart = new Intent(this , ChartActivity.class);
        startActivity(chart);
    }
    public void clkPhone(View view){
        Intent phone = new Intent(this , PhoneActivity.class);
        startActivity(phone);
    }
    public void clkAbout(View view){
        Intent about = new Intent(this , AboutUsActivity.class);
        startActivity(about);
    }
    public void clkHeyat(View view){
    }
}
