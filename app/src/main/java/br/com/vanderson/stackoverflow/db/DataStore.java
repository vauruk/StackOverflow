package br.com.vanderson.stackoverflow.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by vauruk on 19/03/16.
 */
public class DataStore {

    private static DataStore instance;

    private static DBHelper dbHelper;

    protected DataStore() {
    }

    public DBHelper getDbHelper() {
        return dbHelper;
    }

    public void setDbHelper(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public static DataStore sharedInstance(Context context) {
        if (instance == null) {
            instance = new DataStore();
            instance.setDbHelper(new DBHelper(context));

        }
        return instance;
    }

}
