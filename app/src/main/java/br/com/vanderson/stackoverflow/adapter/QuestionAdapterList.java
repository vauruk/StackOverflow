package br.com.vanderson.stackoverflow.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.SimpleFormatter;

import br.com.vanderson.stackoverflow.R;
import br.com.vanderson.stackoverflow.db.model.StackOverflowQuestion;

/**
 * Created by vauruk on 24/03/16.
 */
public class QuestionAdapterList extends ArrayAdapter<StackOverflowQuestion> {


    public QuestionAdapterList(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public QuestionAdapterList(Context context, int resource, List<StackOverflowQuestion> listItem) {
        super(context, resource, listItem);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater vi = (LayoutInflater) getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = vi.inflate(R.layout.question_item, null);
        }

        final StackOverflowQuestion p = getItem(position);
        if (p != null) {
            int sizeTitle = 80;
            TextView txtViewTopico = (TextView) rowView.findViewById(R.id.txtViewTopico);
            String title = p.getTitle();
            if (title.length() > sizeTitle) {
                title = title.substring(0, sizeTitle) + "...";
            }
            txtViewTopico.setText(title);

            TextView txtNameOwner = (TextView) rowView.findViewById(R.id.txtNameOwner);
            txtNameOwner.setText(p.getOwner().getDisplay_name());

            TextView txtTagAssociate = (TextView) rowView.findViewById(R.id.txtTagAssociate);
            if (p.getTags().size() > 0) {
                txtTagAssociate.setText(p.getTags().toString());
            } else {
                txtTagAssociate.setText("");
            }

            TextView txtScore = (TextView) rowView.findViewById(R.id.txtScore);
            if (p.getScore() != null && p.getScore() > 0) {
                txtScore.setText(p.getScore().toString());
            } else {
                txtScore.setText("0");
            }

            TextView txtReputation = (TextView) rowView.findViewById(R.id.txtReputation);
            if (p.getOwner() != null && p.getOwner().getReputation() > 0) {
                txtReputation.setText(p.getOwner().getReputation().toString());
            } else {
                txtReputation.setText("0");
            }

            TextView txtViews = (TextView) rowView.findViewById(R.id.txtView);
            if (p.getView_count() != null && p.getView_count() > 0) {
                txtViews.setText(p.getView_count().toString());
            } else {
                txtViews.setText("0");
            }


            TextView txtDateEdit = (TextView) rowView.findViewById(R.id.txtDateEdit);
            if (p.getCreation_date() != null && p.getCreation_date() > 0) {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(p.getCreation_date());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                txtDateEdit.setText(sdf.format(cal.getTime()));
            } else {
                txtDateEdit.setText("");
            }


            TextView txtVotesPlus = (TextView) rowView.findViewById(R.id.txtVotesPlus);
            if (p.getAnswer_count() != null && p.getAnswer_count() > 0) {
                txtVotesPlus.setText(p.getAnswer_count().toString());
            } else {
                txtVotesPlus.setText("");
            }

            ImageView imgView = (ImageView) rowView.findViewById(R.id.imgPerfil);
            if (p.getOwner() != null && p.getOwner().getProfile_image() != null && p.getOwner().getProfile_image().length() > 0) {

                Picasso.with(getContext()).load(p.getOwner().getProfile_image()).into(imgView);

            }

            showFrame(rowView);


        }

        return rowView;
    }

    private void showFrame(final View rowView) {
        TextView txtDetail = (TextView) rowView.findViewById(R.id.txtDetail);
        txtDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtDetail = (TextView) rowView.findViewById(R.id.txtDetail);
                RelativeLayout rl = (RelativeLayout) rowView.findViewById(R.id.frDetail);
                if (rl.getVisibility() == View.VISIBLE) {
                    rl.setVisibility( View.GONE );
                    txtDetail.setText(R.string.txt_show_detail);
                } else {
                    rl.setVisibility( View.VISIBLE );
                    txtDetail.setText(R.string.txt_hide_detail);
                }
            }
        });

    }
}
