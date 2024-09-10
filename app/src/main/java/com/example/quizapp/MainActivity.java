package com.example.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private RadioGroup answerRadioGroup;
    private RadioButton answer1RadioButton, answer2RadioButton, answer3RadioButton, answer4RadioButton;
    private Button submitButton;

    private String[] questions = {
            "What is the capital of France?",
            "Which planet is known as the Red Planet?",
            "Who wrote 'Romeo and Juliet'?"
    };

    private String[][] options = {
            {"Paris", "London", "Berlin", "Madrid"},
            {"Earth", "Mars", "Jupiter", "Venus"},
            {"William Shakespeare", "Charles Dickens", "Leo Tolstoy", "Mark Twain"}
    };

    private int[] correctAnswers = {0, 1, 0}; // Indexes of correct answers in options array
    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questionTextView);
        answerRadioGroup = findViewById(R.id.answerRadioGroup);
        answer1RadioButton = findViewById(R.id.answer1RadioButton);
        answer2RadioButton = findViewById(R.id.answer2RadioButton);
        answer3RadioButton = findViewById(R.id.answer3RadioButton);
        answer4RadioButton = findViewById(R.id.answer4RadioButton);
        submitButton = findViewById(R.id.submitButton);

        loadQuestion();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void loadQuestion() {
        questionTextView.setText(questions[currentQuestionIndex]);
        answer1RadioButton.setText(options[currentQuestionIndex][0]);
        answer2RadioButton.setText(options[currentQuestionIndex][1]);
        answer3RadioButton.setText(options[currentQuestionIndex][2]);
        answer4RadioButton.setText(options[currentQuestionIndex][3]);
        answerRadioGroup.clearCheck();
    }

    private void checkAnswer() {
        int selectedRadioButtonId = answerRadioGroup.getCheckedRadioButtonId();

        if (selectedRadioButtonId == -1) {
            Toast.makeText(MainActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
            return;
        }

        int selectedAnswerIndex = -1;
        if (selectedRadioButtonId == R.id.answer1RadioButton) {
            selectedAnswerIndex = 0;
        } else if (selectedRadioButtonId == R.id.answer2RadioButton) {
            selectedAnswerIndex = 1;
        } else if (selectedRadioButtonId == R.id.answer3RadioButton) {
            selectedAnswerIndex = 2;
        } else if (selectedRadioButtonId == R.id.answer4RadioButton) {
            selectedAnswerIndex = 3;
        }

        if (selectedAnswerIndex == correctAnswers[currentQuestionIndex]) {
            score++;
            Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Wrong!", Toast.LENGTH_SHORT).show();
        }

        currentQuestionIndex++;

        if (currentQuestionIndex < questions.length) {
            loadQuestion();
        } else {
            Toast.makeText(MainActivity.this, "Quiz Finished! Your score: " + score, Toast.LENGTH_LONG).show();
            submitButton.setEnabled(false);
        }
    }
}
