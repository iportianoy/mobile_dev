package com.example.lab3_1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "library.db";
    public static final int SCHEMA = 1;
    public static final String TABLE = "LIBRARY";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_AUTHOR = "AUTHOR";
    public static final String COLUMN_YEAR = "YEAR";



    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE + " (\n" +
                COLUMN_ID + "             INTEGER      PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                COLUMN_AUTHOR + "       STRING (255) NOT NULL,\n" +
                COLUMN_YEAR + "   STRING (15)  NOT NULL\n" + ");");

//        db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_AUTHOR
//                + ", " + COLUMN_YEAR  + ") VALUES ('Kostenko', 1981);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
