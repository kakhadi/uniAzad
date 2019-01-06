package com.sherko.daneshgahazadboukan;

import android.database.Cursor;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;
import java.io.File;

public class ChartActivity extends AppCompatActivity {
    private static String DB_NAME = "azad.db3";
    DrawerLayout drawerLayout;
    Integer pageNumber = 0;
    String pdfFileName;

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
        WebView webView = findViewById(R.id.webpdf);
        TestAdapter mDbHelper = new TestAdapter(this);
        mDbHelper.createDatabase();
        File db = new File(this.getDatabasePath(DB_NAME).getPath());
        if (db.exists()) {
            mDbHelper.open();
            //Cursor C;
            mDbHelper.getTestData("select * from login");
            //C = mDbHelper.getTestData("select * from reshte where ");
            // mDbHelper.UpdateTest("INSERT INTO login (id,name,lastname,phone,maqta,reshte) VALUES ("+String.valueOf(C.getCount()+1) +",'"+ fn.getText()+"' ,'" +fln.getText() + "' ,'"+fph.getText() +"','"+strMaqta+"','"+String.valueOf(strIdReshta)+"')");
            mDbHelper.close();
        } else {
            File dir = new File(db.getParent());
            if (!dir.exists()) {
                dir.mkdirs();
            }
            Toast.makeText(this, "مشکل اتصال دیتابیس", Toast.LENGTH_LONG).show();
        }


        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://docs.google.com/viewer?url=");

    }
}
