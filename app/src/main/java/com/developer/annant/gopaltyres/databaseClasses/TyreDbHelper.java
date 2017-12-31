package com.developer.annant.gopaltyres.databaseClasses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.developer.annant.gopaltyres.databaseClasses.TyreContract.TyreEntry;

/**
 * Created by Annant on 30-12-2017.
 */

public class TyreDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tyresdb.db";
    private static final int DATABASE_VERSION = 1;

    public TyreDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_TYRES_TABLE = "CREATE TABLE " + TyreEntry.TABLE_NAME + " ("
                + TyreEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TyreEntry.COLUMN_TYRE_SIZE + " TEXT NOT NULL, "
                + TyreEntry.COLUMN_TREAD_NAME + " TEXT NOT NULL, "
                + TyreEntry.COLUMN_PRICE + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_TYRES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

