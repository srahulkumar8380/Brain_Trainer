package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btn;
    ArrayList<Integer>answer=new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score=0;
    int numberOfQuestions=0;
    TextView resultTextView;
    TextView pointsTextView;
    TextView sumTextView;
    Button btn1,btn2,btn3,btn4;
    TextView timerTextView;
    Button playAgain;
    RelativeLayout newRelativeLayout;

    public void PlayAgain(final View view) {
        score=0;
        numberOfQuestions=0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgain.setVisibility(View.INVISIBLE);

        generateQuestion();


        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                playAgain.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your Score: "+Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
            }
        }.start();

    }

    public void generateQuestion(){
        Random random=new Random();
        int a=random.nextInt(101);
        int b=random.nextInt(101);
        sumTextView.setText(a + "+" + b+"="+"?");
        locationOfCorrectAnswer=random.nextInt(4);
        answer.clear();
        int incorrectAnswer;
        for (int i=0;i<4;i++){
            if(i==locationOfCorrectAnswer){
                answer.add(a + b);
            }else{
                incorrectAnswer=random.nextInt(202);
                while(incorrectAnswer==(a+b)){
                    incorrectAnswer=random.nextInt(202);
                }
                answer.add(incorrectAnswer);
            }
        }


        btn1.setText(Integer.toString(answer.get(0)));
        btn2.setText(Integer.toString(answer.get(1)));
        btn3.setText(Integer.toString(answer.get(2)));
        btn4.setText(Integer.toString(answer.get(3)));

    }
    public void startMethod(View view) {
        btn.setVisibility(View.INVISIBLE);
        newRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        PlayAgain(findViewById(R.id.playAgain));
    }

    public void chooseAnswer(View view) {
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setText("Correct");
            resultTextView.setTextColor(Color.GREEN);
        }else{
            resultTextView.setText("Wrong");
            resultTextView.setTextColor(Color.RED);
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        generateQuestion();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=findViewById(R.id.startButton);
        sumTextView=findViewById(R.id.sumTextView);
        resultTextView=findViewById(R.id.resultTextView);
        pointsTextView=findViewById(R.id.scoreTextView);
        timerTextView=findViewById(R.id.timeTextView);
        playAgain=findViewById(R.id.playAgain);
        newRelativeLayout=findViewById(R.id.newRelativeLayout);
        btn1=findViewById(R.id.button0);
        btn2=findViewById(R.id.button1);
        btn3=findViewById(R.id.button2);
        btn4=findViewById(R.id.button3);

    }


}
