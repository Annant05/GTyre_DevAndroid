package com.developer.annant.gopaltyres.databaseClasses;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.developer.annant.gopaltyres.databaseClasses.TyreContract.TyreEntry;

/**
 * Created by Annant on 09-09-2017.
 */

public class TyreProvider extends ContentProvider {

    public static final String LOG_TAG = TyreProvider.class.getSimpleName();

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int TYRES = 100;
    private static final int TYRES_ID = 101;

    static {
        sUriMatcher.addURI(TyreContract.CONTENT_AUTHORITY, TyreContract.PATH_TYRES, TYRES);
        sUriMatcher.addURI(TyreContract.CONTENT_AUTHORITY, TyreContract.PATH_TYRES + "/#", TYRES_ID);
    }

    private TyreDbHelper mDbHelper;


    @Override
    public boolean onCreate() {
        mDbHelper = new TyreDbHelper(getContext());
        return true;
    }


    // @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case TYRES:
                return insertTyre(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insetion is not supported for" + uri);
        }
    }


    private Uri insertTyre(Uri uri, ContentValues values) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        long newRowId = db.insert(TyreEntry.TABLE_NAME, null, values);
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            //         Toast.makeText(getContext(), "Error with saving pet", Toast.LENGTH_SHORT).show();
            Log.e(LOG_TAG, "Failed to insert new row " + uri);
            return null;
        }

        return ContentUris.withAppendedId(uri, newRowId);
    }


    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection,
                        @Nullable String selection, @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        Cursor cursor;

        int match = sUriMatcher.match(uri);
        switch (match) {
            case TYRES:
                cursor = database.query(TyreEntry.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
            case TYRES_ID:
                selection = TyreEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                cursor = database.query(TyreEntry.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        return cursor;
    }


    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case TYRES:
                return updateTyre(uri, contentValues, selection, selectionArgs);
            case TYRES_ID:
                selection = TyreEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateTyre(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for" + uri);
        }
        //return 0;
    }


    private int updateTyre(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        if (values.containsKey(TyreEntry.COLUMN_TREAD_NAME)) {
            String tread = values.getAsString(TyreEntry.COLUMN_TREAD_NAME);
            if (tread == null) {
                throw new IllegalArgumentException("Tyre requires a tread name");
            }
        }

        if (values.containsKey(TyreEntry.COLUMN_TYRE_SIZE)) {
            String size = values.getAsString(TyreEntry.COLUMN_TYRE_SIZE);
            if (size == null) {
                throw new IllegalArgumentException("Tyre requires a size");
            }
        }

        if (values.containsKey(TyreEntry.COLUMN_PRICE)) {
            String price = values.getAsString(TyreEntry.COLUMN_PRICE);
            if (price == null) {
                throw new IllegalArgumentException("Tyre requires a price");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        return database.update(TyreEntry.TABLE_NAME, values, selection, selectionArgs);
    }


    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }


    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

}




/*

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "tyresdb.db";


    private static final String CREATE_TABLE_TYRE =
            "CREATE TABLE " + DataEntry.TABLE_NAME + " (" +
                    DataEntry._ID + " INTEGER PRIMARY KEY," +
                    DataEntry.COLUMN_TYRE_SIZE + " TEXT," +
                    DataEntry.COLUMN_TREAD_NAME + " TEXT," +
                    DataEntry.COLUMN_PRICE + " TEXT)";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DataEntry.TABLE_NAME;
    Context context;


    public TyreProvider(Context context) {
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

        TyreProvider mdb = new TyreProvider(context);
        SQLiteDatabase db = mdb.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(DataEntry.COLUMN_TYRE_SIZE, dataVariable.getTyreSize());
        values.put(DataEntry.COLUMN_TREAD_NAME, dataVariable.getTreadName());
        values.put(DataEntry.COLUMN_PRICE, dataVariable.getPrice());

        long newRowId = db.insert(TABLE_TYRE, null, values);
    }

    public TyreDataVariable getTyre() {
        TyreProvider mdb = new TyreProvider(context);
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


    public ArrayList<TyreDataVariable> getAllTyresList() {
        ArrayList<TyreDataVariable> returnTyres = new ArrayList<>();


        TyreProvider mdb = new TyreProvider(context);
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
            returnTyres.add(new TyreDataVariable(size, tread, price));
        }


        //  return new TyreDataVariable(size, tread, price);


        return returnTyres;
    }
}

*/