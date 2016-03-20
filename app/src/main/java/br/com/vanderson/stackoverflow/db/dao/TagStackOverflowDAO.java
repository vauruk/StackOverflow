package br.com.vanderson.stackoverflow.db.dao;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.vanderson.stackoverflow.db.DataStore;
import br.com.vanderson.stackoverflow.db.model.EntidadeApp;
import br.com.vanderson.stackoverflow.db.model.TagStackOverflow;

/**
 * Created by vauruk on 19/03/16.
 */
public class TagStackOverflowDAO extends DAOGenerico {

    public TagStackOverflowDAO(DataStore dataStore){
        super(dataStore);
    }

    @Override
    public List<TagStackOverflow> listar( EntidadeApp entidade, String whereClause, String[] whereArgs , String orderBy) {
        db = dataStore.getDbHelper().getReadableDatabase();
        Cursor cursor = createQuerySqLite(db, entidade,whereClause, whereArgs, "name");
        List<TagStackOverflow> lista = new ArrayList<TagStackOverflow>();
        if(cursor.moveToFirst()){
            do{
                lista.add(new TagStackOverflow(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            }while (cursor.moveToNext());

        }
        db.close();
        return lista;
    }

    @Override
    public void gravar( EntidadeApp entidade) {
        db = dataStore.getDbHelper().getWritableDatabase();


        long result = super.createInsertSqlite(entidade);

    }

    @Override
    public void excluir(int id) {

    }

    @Override
    public void alterar(EntidadeApp obj) {

    }

}

