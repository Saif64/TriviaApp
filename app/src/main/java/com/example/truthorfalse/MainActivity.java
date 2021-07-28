package com.example.truthorfalse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.example.truthorfalse.databinding.ActivityMainBinding;
import com.example.truthorfalse.model.Question;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int currentQuestionIndex = 0;

    private Question[] questionBank = new Question[] {
            //create/instantiate question objects
            new Question(R.string.question_amendments, false), //correct: 27
            new Question(R.string.question_constitution, true),
            new Question(R.string.question_declaration, true),
            new Question(R.string.question_independence_rights, true),
            new Question(R.string.question_religion, true),
            new Question(R.string.question_government, false),
            new Question(R.string.question_government_feds, false),
            new Question(R.string.question_government_senators, true),
            //and add more!

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.questionTextView.setText(questionBank[currentQuestionIndex].getAnswerResId());
        binding.trueBtnID.setOnClickListener(view -> checkAnswer(true));
        binding.falseBtnID.setOnClickListener(view -> checkAnswer(false));
        binding.nextBtnID.setOnClickListener(view -> {
            currentQuestionIndex = (currentQuestionIndex + 1) % questionBank.length; //incrementing
            updateQuestion();

        });
        binding.previousBtnID.setOnClickListener(view -> {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex = (currentQuestionIndex - 1) % questionBank.length; //decrementing
                updateQuestion();
            }
            else {
                Snackbar.make(binding.imageView, "This is the first question", Snackbar.LENGTH_SHORT)
                        .show();
            }

        });

    }
    private void checkAnswer(boolean userChoseCorrect) {
        boolean answerIsCorrect = questionBank[currentQuestionIndex].isAnswerTrue();
        int messageId;

        if (answerIsCorrect == userChoseCorrect) {
            messageId = R.string.correct_answer;
        }else {
            messageId = R.string.wrong_answer;
        }

        Snackbar.make(binding.imageView, messageId, Snackbar.LENGTH_SHORT)
                .show();


    }

    private void updateQuestion() {
        binding.questionTextView.setText(questionBank[currentQuestionIndex].getAnswerResId());
    }
}
