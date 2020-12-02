package com.animsh.truecitizenquiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button falseBtn, trueBtn;
    private ImageButton nextBtn, previousBtn;
    private TextView questionsTv;
    private int currentIndex = 0;
    private Question[] questionsBank = new Question[]{
            new Question(R.string.question_amendments, false),
            new Question(R.string.question_constitution, true),
            new Question(R.string.question_declaration, true),
            new Question(R.string.question_independence_rights, true),
            new Question(R.string.question_religion, true),
            new Question(R.string.question_government, false),
            new Question(R.string.question_government_feds, false),
            new Question(R.string.question_government_senators, true),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        falseBtn = findViewById(R.id.false_btn);
        trueBtn = findViewById(R.id.true_btn);
        nextBtn = findViewById(R.id.next_btn);
        previousBtn = findViewById(R.id.previous_btn);
        questionsTv = findViewById(R.id.answerTV);

        falseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUserAnswer(false);
            }
        });

        trueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUserAnswer(true);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex + 1) % questionsBank.length;
                questionsTv.setText(questionsBank[currentIndex].getAnswerResID());
            }
        });

        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentIndex > 0) {
                    currentIndex = (currentIndex - 1);
                    questionsTv.setText(questionsBank[currentIndex].getAnswerResID());
                }
            }
        });
    }

    private void checkUserAnswer(boolean userChoice) {
        boolean isAnswerTrue = questionsBank[currentIndex].isAnswerTrue();
        int toastMessageId = 0;
        if (userChoice == isAnswerTrue) {
            toastMessageId = R.string.correct_answer;
        } else {
            toastMessageId = R.string.wrong_answer;
        }
        Toast.makeText(MainActivity.this, toastMessageId, Toast.LENGTH_LONG).show();
    }
}