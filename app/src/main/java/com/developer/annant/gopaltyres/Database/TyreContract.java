package com.developer.annant.gopaltyres.Database;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Annant on 09-09-2017.
 */

public final class TyreContract {


    public static final String CONTENT_AUTHORITY = "com.developer.annant.gopaltyres";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_TYRES = "tyres";

    private TyreContract() {
    }

    public static class TyreEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_TYRES);

        public static final String TABLE_NAME = "tyres";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_TYRE_SIZE = "size";
        public static final String COLUMN_TREAD_NAME = "tread";
        public static final String COLUMN_PRICE = "price";
        //public static final String COLUMN_IMAGE_URL = "image";


    }


}
