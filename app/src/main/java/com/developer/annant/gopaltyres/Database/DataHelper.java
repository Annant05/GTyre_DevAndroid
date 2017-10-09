package com.developer.annant.gopaltyres.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.developer.annant.gopaltyres.Database.DataContract.DataEntry;
import com.developer.annant.gopaltyres.Extras_imp.TyreDataVariable;

import static com.developer.annant.gopaltyres.Database.DataContract.DataEntry.TABLE_TYRE;

/**
 * Created by Annant on 09-09-2017.
 */

public class DataHelper extends SQLiteOpenHelper {

    Context context;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DataTyre.db";
    /*
        final String CREATE_TABLE_TYRE =
                "CREATE TABLE " +
                        TABLE_TYRE + " (" +
                        DataEntry._ID + "INT PRIMARY KEY ," +
                        DataEntry.COLUMN_TYRE_SIZE + " VARCHAR(20)," +
                        DataEntry.COLUMN_TREAD_NAME + " VARCHAR(20))," +
                        DataEntry.COLUMN_PRICE + "VARCHAR(20)" +
                        " );";
    */
    private static final String CREATE_TABLE_TYRE =
            "CREATE TABLE " + DataEntry.TABLE_TYRE + " (" +
                    DataEntry._ID + " INTEGER PRIMARY KEY," +
                    DataEntry.COLUMN_TYRE_SIZE + " TEXT," +
                    DataEntry.COLUMN_TREAD_NAME + " TEXT," +
                    DataEntry.COLUMN_PRICE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DataEntry.TABLE_TYRE;


    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TYRE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void addTyre(TyreDataVariable dataVariable) {

        DataHelper mdb = new DataHelper(context);
        SQLiteDatabase db = mdb.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(DataEntry.COLUMN_TYRE_SIZE,dataVariable.getTyreSize() );
        values.put(DataEntry.COLUMN_TREAD_NAME, dataVariable.getTreadName());
        values.put(DataEntry.COLUMN_PRICE, dataVariable.getPrice());

        long newRowId = db.insert(TABLE_TYRE, null, values);
    }

    public TyreDataVariable getTyre() {
        DataHelper mdb = new DataHelper(context);
        SQLiteDatabase db = mdb.getReadableDatabase();

        String[] columns = {
                DataEntry._ID,
                DataEntry.COLUMN_TYRE_SIZE,
                DataEntry.COLUMN_TREAD_NAME,
                DataEntry.COLUMN_PRICE
        };

        String selection = DataEntry.COLUMN_TYRE_SIZE + " = ?";
        String[] selectionArgs = {"6.00-16"};


        Cursor cursor = db.query(
                DataEntry.TABLE_TYRE,
                columns,
                null,
                null,
                null,
                null,
                null
        );

        int iRow = cursor.getColumnIndex(DataEntry._ID);
        int isize = cursor.getColumnIndex(DataEntry.COLUMN_TYRE_SIZE);
        int itread = cursor.getColumnIndex(DataEntry.COLUMN_TREAD_NAME);
        int iprice = cursor.getColumnIndex(DataEntry.COLUMN_PRICE);

        String size = "";
        String tread = "";
        String price = "";
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            size = cursor.getString(isize);
            tread = cursor.getString(itread);
            price = cursor.getString(iprice);
        }


        return new TyreDataVariable(size, tread, price);
    }
}

