package com.developer.annant.gopaltyres.Database;

import android.provider.BaseColumns;

/**
 * Created by Annant on 09-09-2017.
 */

public class DataContract {


    private DataContract() {
    }

    public static class DataEntry implements BaseColumns {
        public static final String TABLE_TYRE = "TyresTable";
        public static final String COLUMN_TYRE_SIZE = "size";
        public static final String COLUMN_TREAD_NAME = "tread";
        public static final String COLUMN_PRICE = "price";
        public static final String _ID  = BaseColumns._ID;


    }


}
