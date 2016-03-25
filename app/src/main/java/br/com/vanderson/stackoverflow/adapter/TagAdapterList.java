package br.com.vanderson.stackoverflow.adapter;

import android.content.Context;
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
import br.com.vanderson.stackoverflow.db.model.TagStackOverflow;

/**
 * Created by vauruk on 18/03/16.
 */
public class TagAdapterList extends ArrayAdapter<TagStackOverflow> {

    public TagAdapterList(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public TagAdapterList(Context context, int resource, List<TagStackOverflow> listItem) {
        super(context, resource, listItem);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater vi = ( LayoutInflater )getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = vi.inflate(R.layout.tag_stack_overflow_item, null);
        }

       final TagStackOverflow p = getItem(position);

        if(p != null)
        {
            TextView txtItemTag =(TextView) rowView.findViewById(R.id.txtItemTag);
            txtItemTag.setText(p.getName());

           // ImageView imgView =(ImageView) rowView.findViewWithTag(R.id.imageView);

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

      /* rowView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int y = (int) event.getRawY();

              //  RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) v.getLayoutParams();
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        Toast.makeText( getContext(), "MotionEvent.ACTION_DOWN", Toast.LENGTH_LONG).show();
                        break;

                    case MotionEvent.ACTION_UP:
                        Toast.makeText( getContext(), "MotionEvent.ACTION_UP", Toast.LENGTH_LONG).show();
                        break;

                    case MotionEvent.ACTION_POINTER_DOWN:
                    case MotionEvent.ACTION_POINTER_UP:
                        break;

                    case MotionEvent.ACTION_MOVE:
                        Toast.makeText( getContext(), "MotionEvent.ACTION_MOVE", Toast.LENGTH_LONG).show();

                        // Avoid out of screen
                      //  if (params.topMargin < 0) return true;

                        // Apply changes
                        break;
                }

                return true;
            }
        });*/

        return rowView;
    }
}
