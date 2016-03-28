package br.com.vanderson.stackoverflow;

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
import br.com.vanderson.stackoverflow.adapter.TagAdapterList;
import br.com.vanderson.stackoverflow.app.ActivityApp;
import br.com.vanderson.stackoverflow.db.model.StackOverflowQuestion;

public class QuestionActivity extends ActivityApp {

    public static String stack_overflow_key = "U2qOjw7i9b3CsrPcJjUAtA((";
    private ListView listViewQuestions;


    private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        onLoadView();
    }

    private void onLoadView() {
        String nameTag = getIntent().getStringExtra(ListStackActivity.NAME_STACK);
        criarToolBarName(nameTag);

        listViewQuestions = (ListView)findViewById(R.id.listViewQuestions);

        queue = Volley.newRequestQueue(this);

        doGet(nameTag);
    }

    private void criarToolBarName(String nameTag) {
        setTextToolBar("Perguntas " + nameTag);
    }

    public void doGet( final String nameTag) {

        final String url = makeUrlConexao(nameTag);

        // Request a string response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.
                        response.length();
                        String questions="";
                        try {
                            List<StackOverflowQuestion> listQuestions  = new ArrayList<StackOverflowQuestion>();

                            Type listType = new TypeToken<List<StackOverflowQuestion>>() {}.getType();
                            listQuestions = new Gson().fromJson(String.valueOf(response.get("items")), listType);


                            QuestionAdapterList adaptador = new QuestionAdapterList(QuestionActivity.this, R.layout.question_item, listQuestions);

                            listViewQuestions.setAdapter(adaptador);

                            criarToolBarName(nameTag+" "+listQuestions.size());

                        } catch (JSONException e) {
                            e.printStackTrace();
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
        queue.add(stringRequest);
    }

    private String makeUrlConexao(String nameTag) {
        Calendar start = Calendar.getInstance();
        start.set(Calendar.YEAR, 2015);
        start.getTimeInMillis();
        Calendar end = Calendar.getInstance();
        String url = "https://api.stackexchange.com/2.2/questions?site=stackoverflow&key="+stack_overflow_key;
               url+= "&pagesize=20&max=1383264000&sort=activity&tagged="+nameTag;
                url+= "&page=1";
        return url;

    }
}
