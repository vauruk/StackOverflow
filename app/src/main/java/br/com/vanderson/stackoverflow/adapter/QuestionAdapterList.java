package br.com.vanderson.stackoverflow.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

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
            int sizeTitle = 100;
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
                Log.d(p.getScore() + "", p.getScore() + "");
                txtScore.setText(p.getScore().toString());
            } else {
                txtScore.setText("");
            }


            TextView txtVotesPlus = (TextView) rowView.findViewById(R.id.txtVotesPlus);
            if (p.getAnswer_count() != null && p.getAnswer_count() > 0) {
                txtVotesPlus.setText(p.getAnswer_count().toString());
            } else {
                txtVotesPlus.setText("");
            }
            ImageView imgView = (ImageView) rowView.findViewWithTag(R.id.imageView);

            URL urlConnection = null;
            try {
                urlConnection = new URL(p.getOwner().getProfile_image());

                HttpURLConnection connection = (HttpURLConnection) urlConnection.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                imgView.setImageBitmap(myBitmap);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        return rowView;
    }
}
