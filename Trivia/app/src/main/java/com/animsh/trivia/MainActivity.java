package com.animsh.trivia;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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
                String counter = currentQuestionIndex + " / " + questionArrayList.size();
                counterTV.setText(counter);
                questionTV.setText(question);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.prev_btn:
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex = (currentQuestionIndex - 1) % questionList.size();
                    updateQuestion();
                }
                break;
            case R.id.next_btn:
                currentQuestionIndex = (currentQuestionIndex + 1) % questionList.size();
                updateQuestion();
                break;
            case R.id.true_btn:
                checkAnswer(true);
                updateQuestion();
                break;
            case R.id.false_btn:
                checkAnswer(false);
                updateQuestion();
                break;
        }
    }

    private void checkAnswer(boolean userChoice) {
        boolean answerIsTrue = questionList.get(currentQuestionIndex).isAnswerTrue();
        int toastMessageId = 0;
        if (userChoice == answerIsTrue) {
            fadeView();
            toastMessageId = R.string.correct_answer;
        } else {
            shakeAnimation();
            toastMessageId = R.string.wrong_answer;
        }
        Toast.makeText(this, toastMessageId, Toast.LENGTH_SHORT).show();
    }

    private void updateQuestion() {
        String question = "Q. " + questionList.get(currentQuestionIndex).getAnswer();
        String counter = currentQuestionIndex + " / " + questionList.size();
        counterTV.setText(counter);
        questionTV.setText(question);
    }

    private void shakeAnimation() {
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake_animation);
        final CardView cardView = findViewById(R.id.cardView);
        cardView.setAnimation(shake);
        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                cardView.setCardBackgroundColor(getResources().getColor(R.color.red));
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void fadeView(){
        final CardView cardView = findViewById(R.id.cardView);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.0f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setRepeatMode(Animation.REVERSE);

        cardView.setAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                cardView.setCardBackgroundColor(getResources().getColor(R.color.green));
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                cardView.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}