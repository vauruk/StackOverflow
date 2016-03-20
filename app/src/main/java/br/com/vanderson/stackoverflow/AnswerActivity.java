package br.com.vanderson.stackoverflow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.vanderson.stackoverflow.app.ActivityApp;

public class AnswerActivity extends ActivityApp {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        String nameTag = getIntent().getStringExtra(ListStackActivity.NAME_STACK);
        setTextToolBar("Respostas "+nameTag);
    }
}
