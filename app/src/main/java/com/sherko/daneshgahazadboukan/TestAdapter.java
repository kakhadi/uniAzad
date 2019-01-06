package com.sherko.daneshgahazadboukan;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;


public class TestAdapter
{
    protected static final String TAG = "DataAdapter";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;

    public TestAdapter(Context context)
    {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(mContext);
    }

    public TestAdapter createDatabase() throws SQLException
    {
        try
        {
            mDbHelper.createDataBase();
        }
        catch (IOException mIOException)
        {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public TestAdapter open() throws SQLException
    {
        try
        {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void close()
    {
        mDbHelper.close();
    }

    public Cursor getTestData(String codeSQL)
    {
        try
        {
            String sql =codeSQL;//"SELECT * FROM tbl1 where id=1";

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null)
            {
                mCur.moveToFirst();
            }
            return mCur;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    public void UpdateTest(String codeSQL)
    {
        try
        {
            String sql =codeSQL;//"SELECT * FROM tbl1 where id=1";

            //mDb.rawQuery(sql, null);
            mDb.execSQL(sql);
            mDb.close();
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }
}
