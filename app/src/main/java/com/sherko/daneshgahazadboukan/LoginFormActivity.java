package com.sherko.daneshgahazadboukan;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LoginFormActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DataBaseHelper mydb;
    EditText fn, fln, fph;
    String mes = "";
    private static String DB_NAME = "azad.db3";
    private String strIdReshta;
    private String strMaqta;
    private Cursor C;
    private int intSelectSp=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        TestAdapter mDbHelper = new TestAdapter(this);
        mDbHelper.createDatabase();
        File db = new File(this.getDatabasePath(DB_NAME).getPath());
        if (db.exists()) {
            mDbHelper.open();
           // Cursor C;
            C = mDbHelper.getTestData("select * from login");

           // mDbHelper.UpdateTest("INSERT INTO login (id,name,lastname,phone,maqta,reshte) VALUES ("+String.valueOf(C.getCount()+1) +",'"+ fn.getText()+"' ,'" +fln.getText() + "' ,'"+fph.getText() +"','"+strMaqta+"','"+String.valueOf(strIdReshta)+"')");
            mDbHelper.close();
        } else {
            File dir = new File(db.getParent());
            if (!dir.exists()) {
                dir.mkdirs();
            }
            Toast.makeText(this, "مشکل اتصال دیتابیس", Toast.LENGTH_LONG).show();
        }
        if(C.getCount()>0){
            Intent MainA = new Intent(this , MainActivity.class);
            startActivity(MainA);
            this.finish();
        }else {
            List<String> maqtae = new ArrayList<String>();
            maqtae.add("دکترا");
            maqtae.add("کارشناسی ارشد");
            maqtae.add("کارشناسی پیوسته");
            maqtae.add("کارشناسی ناپیوسته");
            maqtae.add("کاردانی پیوسته");
            maqtae.add("کاردانی ناپیوسته");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, maqtae);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Spinner spinnerMaqta = findViewById(R.id.LoginSpinner);
            spinnerMaqta.setAdapter(dataAdapter);
            spinnerMaqta.setOnItemSelectedListener(this);
        }
    }
    public void onBtnSaveClick(View v) {
        fn = findViewById(R.id.EditName);
        fln = findViewById(R.id.EditLname);
        fph = findViewById(R.id.EditPhone);
        int cheching_data=0;
        String f = fn.getText().toString();
        String l = fln.getText().toString();
        String p = fph.getText().toString();

        if (f.isEmpty() || l.isEmpty()||p.isEmpty() || strMaqta.isEmpty() || strIdReshta.isEmpty()) {
            Toast.makeText(LoginFormActivity.this, "اطلاعات را کامل وارد نمایید", Toast.LENGTH_SHORT).show();
            cheching_data++;
        }

        if (p.length() < 11) {
            Toast.makeText(LoginFormActivity.this, "شماره موبایل را به صورت 09xxxxxxxxx وارد نمایید", Toast.LENGTH_LONG).show();
            cheching_data++;
        }

        if(cheching_data==0){
            TestAdapter mDbHelper = new TestAdapter(this);
            mDbHelper.createDatabase();
            File db = new File(this.getDatabasePath(DB_NAME).getPath());
            if (db.exists()) {
                mDbHelper.open();
                Cursor C;
                C = mDbHelper.getTestData("select * from login");
                mDbHelper.UpdateTest("INSERT INTO login (id,name,lastname,phone,maqta,reshte) VALUES ("+String.valueOf(C.getCount()+1) +",'"+ fn.getText()+"' ,'" +fln.getText() + "' ,'"+fph.getText() +"','"+strMaqta+"','"+String.valueOf(strIdReshta)+"')");
                mDbHelper.close();
                Intent MainA = new Intent(this , MainActivity.class);
                startActivity(MainA);
                this.finish();
            } else {
                File dir = new File(db.getParent());
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                Toast.makeText(this, "مشکل اتصال دیتابیس", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId() == R.id.LoginSpinner)
        {
            switch (position) {
                case 0: {
                    List<String> reshta = new ArrayList<String>();
                    TestAdapter mDbHelper = new TestAdapter(this);
                    mDbHelper.createDatabase();
                    File db = new File(this.getDatabasePath(DB_NAME).getPath());
                    if (db.exists()) {
                        mDbHelper.open();
                        C = mDbHelper.getTestData("select * from reshte WHERE dr=1");
                        C.moveToFirst();
                        int forend = C.getCount();
                        for (int i = 0; i < forend; i++) {
                            reshta.add(C.getString(1));
                            C.moveToNext();
                        }
                        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, reshta);
                        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        Spinner spinnerreshte = findViewById(R.id.LoginSpinner2);
                        spinnerreshte.setAdapter(dataAdapter2);
                        spinnerreshte.setOnItemSelectedListener(this);
                        mDbHelper.close();
                        strMaqta="0";
                    } else {
                        File dir = new File(db.getParent());
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        Toast.makeText(this, "مشکل اتصال دیتابیس", Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(this, "Afd", Toast.LENGTH_SHORT).show();
                    // Whatever you want to happen when the first item gets selected
                    break;
                }
                case 1: {
                    List<String> reshta = new ArrayList<String>();
                    TestAdapter mDbHelper = new TestAdapter(this);
                    mDbHelper.createDatabase();
                    File db = new File(this.getDatabasePath(DB_NAME).getPath());
                    if (db.exists()) {
                        mDbHelper.open();
//                    Cursor C;
                        C = mDbHelper.getTestData("select * from reshte WHERE karshenasiarshad=1");
                        C.moveToFirst();
                        int forend = C.getCount();
                        for (int i = 0; i < forend; i++) {
                            reshta.add(C.getString(1));
                            C.moveToNext();
                        }
                        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, reshta);
                        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        Spinner spinnerreshte = findViewById(R.id.LoginSpinner2);
                        spinnerreshte.setAdapter(dataAdapter2);
                        spinnerreshte.setOnItemSelectedListener(this);
                        mDbHelper.close();
                        strMaqta="1";
                    } else {
                        File dir = new File(db.getParent());
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        Toast.makeText(this, "مشکل اتصال دیتابیس", Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(this, "Afd", Toast.LENGTH_SHORT).show();
                    // Whatever you want to happen when the first item gets selected
                    break;
                }
                case 2: {
                    List<String> reshta = new ArrayList<String>();
                    TestAdapter mDbHelper = new TestAdapter(this);
                    mDbHelper.createDatabase();
                    File db = new File(this.getDatabasePath(DB_NAME).getPath());
                    if (db.exists()) {
                        mDbHelper.open();
//                    Cursor C;
                        C = mDbHelper.getTestData("select * from reshte WHERE karshenasipeyvaste=1 ");
                        C.moveToFirst();
                        int forend = C.getCount();
                        for (int i = 0; i < forend; i++) {
                            reshta.add(C.getString(1));
                            C.moveToNext();
                        }
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, reshta);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        Spinner spinnerreshte = findViewById(R.id.LoginSpinner2);
                        spinnerreshte.setAdapter(dataAdapter);
                        spinnerreshte.setOnItemSelectedListener(this);
                        mDbHelper.close();
                        strMaqta="2";
                    } else {
                        File dir = new File(db.getParent());
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        Toast.makeText(this, "مشکل اتصال دیتابیس", Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(this, "Afd", Toast.LENGTH_SHORT).show();
                    // Whatever you want to happen when the first item gets selected
                    break;
                }
                case 3: {
                    List<String> reshta = new ArrayList<String>();
                    TestAdapter mDbHelper = new TestAdapter(this);
                    mDbHelper.createDatabase();
                    File db = new File(this.getDatabasePath(DB_NAME).getPath());
                    if (db.exists()) {
                        mDbHelper.open();
//                    Cursor C;
                        C = mDbHelper.getTestData("select * from reshte WHERE  karshenasinapeyvaste=1");
                        C.moveToFirst();
                        int forend = C.getCount();
                        for (int i = 0; i < forend; i++) {
                            reshta.add(C.getString(1));
                            C.moveToNext();
                        }
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, reshta);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        Spinner spinnerreshte = findViewById(R.id.LoginSpinner2);
                        spinnerreshte.setAdapter(dataAdapter);
                        spinnerreshte.setOnItemSelectedListener(this);
                        mDbHelper.close();
                        strMaqta="3";
                    } else {
                        File dir = new File(db.getParent());
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        Toast.makeText(this, "مشکل اتصال دیتابیس", Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(this, "Afd", Toast.LENGTH_SHORT).show();
                    // Whatever you want to happen when the first item gets selected
                    break;
                }
                case 4: {
                    List<String> reshta = new ArrayList<String>();
                    TestAdapter mDbHelper = new TestAdapter(this);
                    mDbHelper.createDatabase();
                    File db = new File(this.getDatabasePath(DB_NAME).getPath());
                    if (db.exists()) {
                        mDbHelper.open();
//                    Cursor C;
                        C = mDbHelper.getTestData("select * from reshte WHERE  kardanipeyvaste=1");
                        C.moveToFirst();
                        int forend = C.getCount();
                        for (int i = 0; i < forend; i++) {
                            reshta.add(C.getString(1));
                            C.moveToNext();
                        }
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, reshta);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        Spinner spinnerreshte = findViewById(R.id.LoginSpinner2);
                        spinnerreshte.setAdapter(dataAdapter);
                        spinnerreshte.setOnItemSelectedListener(this);
                        mDbHelper.close();
                        strMaqta="4";
                    } else {
                        File dir = new File(db.getParent());
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        Toast.makeText(this, "مشکل اتصال دیتابیس", Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(this, "Afd", Toast.LENGTH_SHORT).show();
                    // Whatever you want to happen when the first item gets selected
                    break;
                }
                case 5: {
                    List<String> reshta = new ArrayList<String>();
                    TestAdapter mDbHelper = new TestAdapter(this);
                    mDbHelper.createDatabase();
                    File db = new File(this.getDatabasePath(DB_NAME).getPath());
                    if (db.exists()) {
                        mDbHelper.open();
//                    Cursor C;
                        C = mDbHelper.getTestData("select * from reshte WHERE  kardaninapeyvaste=1");
                        C.moveToFirst();
                        int forend = C.getCount();
                        for (int i = 0; i < forend; i++) {
                            reshta.add(C.getString(1));
                            C.moveToNext();
                        }
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, reshta);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        Spinner spinnerreshte = findViewById(R.id.LoginSpinner2);
                        spinnerreshte.setAdapter(dataAdapter);
                        spinnerreshte.setOnItemSelectedListener(this);
                        mDbHelper.close();
                        strMaqta="5";
                    } else {
                        File dir = new File(db.getParent());
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        Toast.makeText(this, "مشکل اتصال دیتابیس", Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(this, "Afd", Toast.LENGTH_SHORT).show();
                    // Whatever you want to happen when the first item gets selected
                    break;
                }
            }
            //strMaqta = parent.getItemAtPosition(position).toString();
        }else{
            TestAdapter mDbHelper = new TestAdapter(this);
            mDbHelper.createDatabase();
            File db = new File(this.getDatabasePath(DB_NAME).getPath());
            if (db.exists()) {
                mDbHelper.open();
                C = mDbHelper.getTestData("select * from reshte WHERE namereshte='"+parent.getItemAtPosition(position).toString()+"'");
                C.moveToFirst();
                strIdReshta=C.getString(0);
                mDbHelper.close();
            } else {
                File dir = new File(db.getParent());
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                Toast.makeText(this, "مشکل اتصال دیتابیس", Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
