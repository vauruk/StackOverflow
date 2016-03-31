package br.com.vanderson.stackoverflow.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.vanderson.stackoverflow.AnswerActivity;
import br.com.vanderson.stackoverflow.ListStackActivity;
import br.com.vanderson.stackoverflow.QuestionActivity;
import br.com.vanderson.stackoverflow.R;
import br.com.vanderson.stackoverflow.db.dao.TagStackOverflowDAO;
import br.com.vanderson.stackoverflow.db.model.TagStackOverflow;

/**
 * Created by vauruk on 18/03/16.
 * Lista as tags de bucas na StackOverflow
 */
public class TagAdapterList extends ArrayAdapter<TagStackOverflow> {

    private TagStackOverflowDAO dao;

    public TagAdapterList(Context context, int textViewResourceId) {

        super(context, textViewResourceId);
    }

    public TagAdapterList(Context context, int resource, List<TagStackOverflow> listItem, TagStackOverflowDAO dao) {
        super(context, resource, listItem);
        this.dao = dao;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater vi = (LayoutInflater) getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = vi.inflate(R.layout.tag_stack_overflow_item, null);
        }

        final TagStackOverflow p = getItem(position);

        if (p != null) {
            TextView txtItemTag = (TextView) rowView.findViewById(R.id.txtItemTag);
            txtItemTag.setText(p.getName());

        }

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getContext(), QuestionActivity.class);

                i.putExtra(ListStackActivity.TAG_STACK, p.getTag());
                i.putExtra(ListStackActivity.NAME_STACK, p.getName());

                getContext().startActivity(i);
            }
        });

        rowView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setMessage("Você tem certeza em excluir esse registro?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dao.excluir(p);
                                remove(p);

                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        })
                        .show();
                return true;
            }
        });

        return rowView;
    }
}
