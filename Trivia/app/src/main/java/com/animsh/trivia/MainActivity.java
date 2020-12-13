package com.animsh.trivia;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.animsh.trivia.data.AnswerListAsyncResponse;
import com.animsh.trivia.data.QuestionBank;
import com.animsh.trivia.model.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView questionTV, counterTV;
    private Button trueBtn, falseBtn;
    private ImageButton prevBtn, nextBtn;
    private int currentQuestionIndex = 0;
    public List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialization
        questionTV = findViewById(R.id.question_tv);
        counterTV = findViewById(R.id.counter_tv);
        trueBtn = findViewById(R.id.true_btn);
        falseBtn = findViewById(R.id.false_btn);
        prevBtn = findViewById(R.id.prev_btn);
        nextBtn = findViewById(R.id.next_btn);

        nextBtn.setOnClickListener(this);
        prevBtn.setOnClickListener(this);
        trueBtn.setOnClickListener(this);
        falseBtn.setOnClickListener(this);

        new QuestionBank().getQuestions(new AnswerListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Question> questionArrayList) {
                questionList = questionArrayList;
                Log.d("ASYNC TASK: ", "processFinished: " + questionArrayList);
                String question = "Q. " + questionArrayList.get(currentQuestionIndex).getAnswer();
                questionTV.setText(question);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.prev_btn:
                break;
            case R.id.next_btn:
                currentQuestionIndex = (currentQuestionIndex + 1) % questionList.size();
                updateQuestion();
                break;
            case R.id.true_btn:
                break;
            case R.id.false_btn:
                break;
        }
    }

    private void updateQuestion() {
        String question = "Q. " + questionList.get(currentQuestionIndex).getAnswer();
        questionTV.setText(question);
    }
}