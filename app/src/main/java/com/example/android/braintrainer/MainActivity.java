package com.example.android.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // to randomise where the real answer button will be.
    // here you randomise 4 number
    // button has not yet been set
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    TextView scoreTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timerTextView;

    Button goButton;
    TextView resultTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();

    public void chooseAnswer(View view) {

        //check locationOfCorrectAnswer = button tag
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            resultTextView.setText("Correct!");
            score++;
        } else {
            resultTextView.setText("Wrong :(");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+ "/" + Integer.toString(numberOfQuestions));
        newQuestion();
    }

    public void Start(View view) {
        goButton.setVisibility(View.INVISIBLE);
    }

    public void newQuestion() {
        Random rand = new Random();

        //bound = 0 -> 20 [gives range]
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        //must clear the ArrayList otherwise old number will retain
        answers.clear();

        //increments 0 to 3
        for (int i = 0; i<4; i++) {
            if ( i == locationOfCorrectAnswer) {
                answers.add(a+b);
            } else {
                int wrongAnswer = rand.nextInt(41);

                while (wrongAnswer == a+b) {
                    wrongAnswer = rand.nextInt(41);
                }

                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView = findViewById(R.id.sumTextView);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        goButton = findViewById(R.id.goButton);
        timerTextView = findViewById(R.id.timerTextView);

        newQuestion();

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l /1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done");
            }
        }.start();
    }
}
