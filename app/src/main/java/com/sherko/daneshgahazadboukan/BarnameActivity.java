package com.sherko.daneshgahazadboukan;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

public class BarnameActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barname);

        drawerLayout = findViewById(R.id.DrawerView2);
        findViewById(R.id.DrawerBtn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerOpen(Gravity.RIGHT)){
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                }
                else
                    drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });

    }
}
