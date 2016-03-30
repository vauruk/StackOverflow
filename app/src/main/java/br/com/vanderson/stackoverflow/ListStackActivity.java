package br.com.vanderson.stackoverflow;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.vanderson.stackoverflow.adapter.TagAdapterList;
import br.com.vanderson.stackoverflow.app.ActivityApp;
import br.com.vanderson.stackoverflow.db.dao.TagStackOverflowDAO;
import br.com.vanderson.stackoverflow.db.model.TagStackOverflow;

public class ListStackActivity extends ActivityApp {

    public static final String TAG_STACK = "TagStackOverflow";
    public static final String NAME_STACK = "NameStackOverflow";

    private ListView listTagsStackView;

    private List<TagStackOverflow> listTagStackSelect = new ArrayList<TagStackOverflow>();
    private TagAdapterList adaptador = null;

    private TagStackOverflowDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_stack);

        populateListView();
    }

    private void populateListView() {
        dao = (TagStackOverflowDAO) getDaoFactory().createTagStackOverflow(this);

        listTagStackSelect = dao.listar(new TagStackOverflow(), null, null, "name");

        listTagsStackView = (ListView) findViewById(R.id.listTagsStackView);
        adaptador = new TagAdapterList(this, R.layout.tag_stack_overflow_item, listTagStackSelect, dao);

        listTagsStackView.setAdapter(adaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lista_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.new_question:
                newItemList();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void editItensList() {

    }

    private void newItemList() {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_tag);
        dialog.setTitle(R.string.dialog_save_title);

        // set the custom dialog components - text, image and button
        final EditText nameTxt = (EditText) dialog.findViewById(R.id.edtName);

        final EditText tagTxt = (EditText) dialog.findViewById(R.id.edtTag);

        Button saveNew = (Button) dialog.findViewById(R.id.btnNewTag);
        // if button is clicked, close the custom dialog
        saveNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialog.dismiss();
                TagStackOverflow tag = new TagStackOverflow();
                tag.setTag(tagTxt.getText().toString());
                tag.setName(nameTxt.getText().toString());
                dao.gravar(tag);
                populateListView();
                dialog.dismiss();
            }
        });

        dialog.show();

    }

}
