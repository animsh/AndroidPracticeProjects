package com.animsh.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.animsh.trivia.data.AnswerListAsyncResponse;
import com.animsh.trivia.data.QuestionBank;
import com.animsh.trivia.model.Question;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new QuestionBank().getQuestions(new AnswerListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Question> questionArrayList) {
                Log.d("ASYNC TASK: ", "processFinished: " + questionArrayList);
            }
        });
    }
}