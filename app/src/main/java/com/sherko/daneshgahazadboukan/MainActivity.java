package com.sherko.daneshgahazadboukan;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.DrawerView);
        findViewById(R.id.DrawerBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerOpen(Gravity.RIGHT)){
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                }
                else
                    drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });
        loadProfile loadProfile =new loadProfile();
        TextView textView = findViewById(R.id.nameMeno);
        TextView textView1 =findViewById(R.id.maqtaeMeno);
        TextView textView2 =findViewById(R.id.reshteMeno);
        loadProfile.all(this,textView,textView1,textView2);
    }
    public void clkBarnama(View view){
        Intent barname = new Intent(MainActivity.this , BarnameActivity.class);
        startActivity(barname);
    }
    public void clkChart(View view){
        Intent chart = new Intent(MainActivity.this , ChartActivity.class);
        startActivity(chart);
    }
    public void clkPhone(View view){
        Intent phone = new Intent(MainActivity.this , PhoneActivity.class);
        startActivity(phone);
    }
    public void clkAbout(View view){
        Intent about = new Intent(MainActivity.this , AboutUsActivity.class);
        startActivity(about);
    }
    public void clkHeyat(View view){
    }
    private int  time_interval = 2000;
    private long oldCurrentTimeMillis;
    @Override
    public void onBackPressed()
    {
        if (oldCurrentTimeMillis + time_interval > System.currentTimeMillis())
        {
            super.onBackPressed();
            return;
        }
        else {
            onFirstBackPressed();
        }
        oldCurrentTimeMillis = System.currentTimeMillis();
    }
    public void onFirstBackPressed() {
        Toast.makeText(getBaseContext(), "برای خروج دوباره کلیک کنید.", Toast.LENGTH_SHORT).show();

    }
}
