package br.com.vanderson.stackoverflow.app;


import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import br.com.vanderson.stackoverflow.db.dao.DAOFactory;
import br.com.vanderson.stackoverflow.db.dao.DAOFactorySQLite;

/**
 * Created by vauruk on 14/11/15.
 */
public class ActivityApp extends AppCompatActivity {

    protected void hideToolBar() {

        getSupportActionBar().hide();
    }

    private static DAOFactory daoFactory;

    protected static DAOFactory getDaoFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactorySQLite();
        }
        return daoFactory;
    }

    protected void setTextToolBar(String nameToolBar) {

        getSupportActionBar().setTitle(nameToolBar);
    }

    @Override
    public void onBackPressed() {
    /*    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //   getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

}
