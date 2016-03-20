package br.com.vanderson.stackoverflow.db.dao;

import android.content.Context;

import br.com.vanderson.stackoverflow.db.DBHelper;
import br.com.vanderson.stackoverflow.db.DataStore;

/**
 * Created by vauruk on 19/03/16.
 */
public class DAOFactorySQLite implements DAOFactory {
    @Override
    public DAO createTagStackOverflow(Context context) {
        return new TagStackOverflowDAO(DataStore.sharedInstance(context));
    }
}
