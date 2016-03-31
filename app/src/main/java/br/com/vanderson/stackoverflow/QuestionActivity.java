package br.com.vanderson.stackoverflow;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.vanderson.stackoverflow.adapter.QuestionAdapterList;
import br.com.vanderson.stackoverflow.app.ActivityApp;
import br.com.vanderson.stackoverflow.db.model.StackOverflowQuestion;

public class QuestionActivity extends ActivityApp {


    private ListView listViewQuestions;
    private ProgressDialog loading;
    private List<StackOverflowQuestion> listQuestions = new ArrayList<StackOverflowQuestion>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        onLoadView();
    }

    private void onLoadView() {
        String nameTag = getIntent().getStringExtra(ListStackActivity.NAME_STACK);
        criarToolBarName(nameTag);

        listViewQuestions = (ListView) findViewById(R.id.listViewQuestions);

        loading = new ProgressDialog(this);
        loading.setCancelable(true);
        loading.setTitle("Processing ...");
        this.openLoading();

        loadListQuestion(listViewQuestions, nameTag);

    }

    private void criarToolBarName(String nameTag) {
        setTextToolBar("Perguntas " + nameTag);
    }

    private void closeLoading() {
        loading.dismiss();
    }

    private void openLoading() {
        loading.show();
    }

    /**
     * cria e popula a lista de de questões.
     * @param listViewQuestions
     * @param nameTag
     */
    private void loadListQuestion(final ListView listViewQuestions, final String nameTag) {
        final String url = makeUrlConexao(nameTag);
        final QuestionAdapterList adapter  = new QuestionAdapterList(QuestionActivity.this, R.layout.question_item, listQuestions, nameTag);
        listViewQuestions.setAdapter(adapter);
        listQuestions.clear();
        adapter.clear();

        JsonObjectRequest stringRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Type listType = new TypeToken<List<StackOverflowQuestion>>() {}.getType();
                            listQuestions = new Gson().fromJson(String.valueOf(response.get("items")), listType);

                            adapter.addAll(listQuestions);

                            criarToolBarName(nameTag + " " + listQuestions.size());
                            adapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        } finally {
                            loading.dismiss();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.getCause();
                    }
                });
        // Add the request to the RequestQueue.
        addToRequestQueue(stringRequest);

    }

    /**
     * cria a Url de conexao
     * @param nameTag
     * @return
     */
    private String makeUrlConexao(String nameTag) {
        Calendar start = Calendar.getInstance();
        start.add(Calendar.MONTH, -15);
        Calendar end = Calendar.getInstance();
        long st = start.getTimeInMillis()/1000;
        long ed = end.getTimeInMillis()/1000;
        String url = "https://api.stackexchange.com/2.2/questions?site=stackoverflow&key=" + stack_overflow_key;
        url += "&pagesize=20&sort=activity&tagged=" + nameTag;
        url += "&page=1&fromdate="+st+"&todate="+ed+"&order=desc";

        return url;

    }


}
