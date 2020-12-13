package com.animsh.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.animsh.trivia.data.QuestionBank;
import com.animsh.trivia.model.Question;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new QuestionBank().getQuestions();
    }
}