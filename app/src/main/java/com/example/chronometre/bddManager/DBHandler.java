package com.example.chronometre.bddManager;

import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.chronometre.Modal.ChronoModal;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "DB_CHRONO";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "CHRONOMETRE";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String TIMER_COL = "timer";


    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + TIMER_COL + " TEXT)";


        db.execSQL(query);
    }

    public void addNewChrono(String chronoName, String chronoTimer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COL, chronoName);
        values.put(TIMER_COL, chronoTimer);
        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public ArrayList<ChronoModal> readChrono() {
        Log.i("BDD", "test");
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<ChronoModal> liste = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                liste.add(new ChronoModal(cursor.getString(1), cursor.getString(2)));
                Log.i("BDD", cursor.getString(1));
            } while (cursor.moveToNext());
        }

        cursor.close();

        return liste;
    }

    public void updateChrono(String originalChronoName, String chronoName, String timer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME_COL, chronoName);
        values.put(TIMER_COL, timer);

        db.update(TABLE_NAME, values, "name=?", new String[]{originalChronoName});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
