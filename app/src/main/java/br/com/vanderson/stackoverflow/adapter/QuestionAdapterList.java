package br.com.vanderson.stackoverflow.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.SimpleFormatter;

import br.com.vanderson.stackoverflow.R;
import br.com.vanderson.stackoverflow.app.OnSwipeTouchListener;
import br.com.vanderson.stackoverflow.app.Utils;
import br.com.vanderson.stackoverflow.db.model.StackOverflowQuestion;

/**
 * Created by vauruk on 24/03/16.
 */
public class QuestionAdapterList extends ArrayAdapter<StackOverflowQuestion> {

    List<StackOverflowQuestion> listItem = null;
    private String nameTag = "";

    public QuestionAdapterList(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public QuestionAdapterList(Context context, int resource, List<StackOverflowQuestion> listItem, String nameTag) {
        super(context, resource, listItem);
        this.listItem = listItem;
        this.nameTag = nameTag;
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
            int sizeTitle = 60;
            TextView txtViewTopico = (TextView) rowView.findViewById(R.id.txtViewTopico);
            String title = p.getTitle();
            if (title.length() > sizeTitle) {
                title = title.substring(0, sizeTitle) + "...";
            }
            txtViewTopico.setText(title);

            LinearLayout ly = (LinearLayout) rowView.findViewById(R.id.layoutTags);
            ly.removeAllViews();
            if (p.getTags().size() > 0) {
                TreeSet<String> newTags = new TreeSet<String>(p.getTags());
                for (String tag : newTags) {
                    tag = tag.replaceAll("\\p{Space}", "");

                    Resources res = getContext().getResources();
                    TextView txtTag = new TextView(getContext());
                    LinearLayout.LayoutParams parans = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    parans.rightMargin = 10;
                    txtTag.setLayoutParams(parans);
                  //  txtTag.setTextSize(View.DRAWING_CACHE_QUALITY_HIGH, View.MEASURED_STATE_TOO_SMALL);
                    int padding = 10;
                    txtTag.setPadding(padding, padding, padding, padding);
                    //TODO vanderson rever para melhorar
                    txtTag.setTextColor(res.getColor(R.color.white));
                    txtTag.setText(tag);
                    txtTag.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
                    if (tag.trim().equalsIgnoreCase(nameTag.trim())) {
                        txtTag.setBackgroundColor(res.getColor(R.color.colorPrimary));
                    } else {
                        txtTag.setBackgroundColor(res.getColor(R.color.colorBackground));
                    }
                    txtTag.setId(Utils.radomNumber());
                    ly.addView(txtTag);

                }
            }
            //    txtTagAssociate.setText("");
            //     txtTagAssociate.setVisibility(View.GONE);

            TextView txtViews = (TextView) rowView.findViewById(R.id.txtView);
            if (p.getView_count() != null && p.getView_count() > 0) {
                txtViews.setText(p.getView_count().toString());
            } else {
                txtViews.setText("0");
            }


            TextView txtDateEdit = (TextView) rowView.findViewById(R.id.txtDateEdit);
            if (p.getCreation_date() != null && p.getCreation_date() > 0) {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(p.getCreation_date()*1000);
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

            showFrame(rowView, p);


        }

        return rowView;
    }

    /**
     * gestos na row
     *
     * @param rowView
     * @return
     */
   /* private View.OnTouchListener createOnClick(final View rowView) {
        OnSwipeTouchListener listener = new OnSwipeTouchListener(getContext()) {
            @Override
            public void onSwipeLeft() {
                Toast.makeText(getContext(), "Arrasto o dedo", Toast.LENGTH_LONG).show();


            }
        };

        return listener;
    }*/
    private void showFrame(final View rowView, final StackOverflowQuestion p) {
        TextView txtDetail = (TextView) rowView.findViewById(R.id.txtDetail);
        txtDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TextView txtDetail = (TextView) rowView.findViewById(R.id.txtDetail);
                RelativeLayout rl = (RelativeLayout) rowView.findViewById(R.id.frDetail);
                if (rl.getVisibility() == View.VISIBLE) {
                    rl.setVisibility(View.GONE);
                    txtDetail.setText(R.string.txt_show_detail);
                } else {
                    rl.setVisibility(View.VISIBLE);
                    txtDetail.setText(R.string.txt_hide_detail);


                    TextView txtNameOwner = (TextView) rowView.findViewById(R.id.txtNameOwner);
                    txtNameOwner.setText(p.getOwner().getDisplay_name());

                    TextView txtReputation = (TextView) rowView.findViewById(R.id.txtReputation);
                    if (p.getOwner() != null && p.getOwner().getReputation() > 0) {
                        txtReputation.setText(p.getOwner().getReputation().toString());
                    } else {
                        txtReputation.setText("0");
                    }
                    TextView txtScore = (TextView) rowView.findViewById(R.id.txtScore);
                    if (p.getScore() != null && p.getScore() > 0) {
                        txtScore.setText(p.getScore().toString());
                    } else {
                        txtScore.setText("0");
                    }
                    ImageView imgView = (ImageView) rowView.findViewById(R.id.imgPerfil);
                    if (p.getOwner() != null && p.getOwner().getProfile_image() != null && p.getOwner().getProfile_image().length() > 0) {

                        Picasso.with(getContext()).load(p.getOwner().getProfile_image()).into(imgView);

                    }
                }
            }
        });

    }
}
