package com.android.profile2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqliteHelper extends SQLiteOpenHelper {

    String TABLE_NAME = "MovieFinder";


    public SqliteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + "("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "movie TEXT"
                + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertMovies(String movie) {

        String INSERT_MOVIE_QUERY = "INSERT INTO " + TABLE_NAME + "(movie) VALUES("
                + "'" + movie + "'"
                + ")";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(INSERT_MOVIE_QUERY);
        db.close();
    }

    public String getAllMoviesName() {
        String result = "";
        String GET_ALL_MOVIES_NAME_QUERY = "SELECT movie FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery(GET_ALL_MOVIES_NAME_QUERY, null);

        while (data.moveToNext()) {
            result = result + data.getString(0) + "\n";
        }

        db.close();

        return result;
    }

}
