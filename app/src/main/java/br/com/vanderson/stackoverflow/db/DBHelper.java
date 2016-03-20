package br.com.vanderson.stackoverflow.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vauruk on 19/03/16.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSAO_DB = 3;
    private static final String NAME_DB = "STACK_OVERFLOW";


    public DBHelper(Context context) {
        super(context, NAME_DB, null, VERSAO_DB);

    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;
        sql = "CREATE TABLE IF NOT EXISTS tbl_tags_stack_overflow (";
        sql += "id integer PRIMARY KEY AUTOINCREMENT, ";
        sql += "name TEXT NOT NULL, ";
        sql += "tag TEXT NOT NULL );";

        db.execSQL(sql);

        String insert = "insert into tbl_tags_stack_overflow (id, name, tag) values (1, 'Android Studio','Android Studio');";
        db.execSQL(insert);
        String insert2 = "insert into tbl_tags_stack_overflow (id, name, tag) values (2, 'Android','Android');";
        db.execSQL(insert2);

        String insert3 = "insert into tbl_tags_stack_overflow (id, name, tag) values (3, 'Java','Java');";
        db.execSQL(insert3);

        String insert4 = "insert into tbl_tags_stack_overflow (id, name, tag) values (4, 'Mashmallow','Mashmallow');";
        db.execSQL(insert4);

        String insert5 = "insert into tbl_tags_stack_overflow (id, name, tag) values (5, 'Nexus','Nexus');";
        db.execSQL(insert5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS tbl_tags_stack_overflow";

        db.execSQL(sql);

        onCreate(db);
    }
}
