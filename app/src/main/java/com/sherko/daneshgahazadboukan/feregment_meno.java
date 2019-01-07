package com.sherko.daneshgahazadboukan;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class feregment_meno extends Fragment {
    private static String DB_NAME = "azad.db3";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_meno,container,false);
        final Context context = rootView.getContext();
        final TextView txtName = rootView.findViewById(R.id.txtName);
        final TextView txtMaqtae = rootView.findViewById(R.id.txtMaqtae);
        final TextView txtReshte = rootView.findViewById(R.id.txtReshte);
        txtName.setText("55");
            TestAdapter mDbHelper = new TestAdapter(context);
            mDbHelper.createDatabase();
            File db = new File(context.getDatabasePath(DB_NAME).getPath());
            if (db.exists()) {
                mDbHelper.open();
                Cursor C;
                C=mDbHelper.getTestData("select * from login");
                C.moveToFirst();
                String strName=C.getString(1);
                String strLastName=C.getString(2);
                strName=strName+" "+strLastName;
                txtName.setText(strName);
                switch (Integer.parseInt(C.getString(4))){
                    case 0:{
                        txtMaqtae.setText("دکترا");
                        break;
                    }
                    case 1:{
                        txtMaqtae.setText("کارشناسی ارشد");
                        break;
                    }
                    case 2:{
                        txtMaqtae.setText("کارشناسی پیوسته");
                        break;
                    }
                    case 3:{
                        txtMaqtae.setText("کارشناسی ناپیوسته");
                        break;
                    }
                    case 4:{
                        txtMaqtae.setText("کاردانی پیوسته");
                        break;
                    }
                    case 5:{
                        txtMaqtae.setText("کاردانی ناپیوسته");
                        break;
                    }
                }
                C=mDbHelper.getTestData("select * from reshte where id="+C.getString(5));
                txtReshte.setText(C.getString(1));
                mDbHelper.close();
            } else {
                File dir = new File(db.getParent());
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                Toast.makeText(context, "مشکل اتصال دیتابیس", Toast.LENGTH_LONG).show();
            }
        return rootView;
    }
}
