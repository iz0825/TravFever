package com.example.travfever;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class FirstQuiz extends AppCompatActivity {
    private RadioGroup radioGroup;
    private Button nxt;
    int pos;

    public void buttonOnClick2(View v) {
        Intent i = new Intent(this, SecondQuiz.class);
        startActivity(i);
    }

    public void buttonOnClick3(View v) {
        Intent i = new Intent(this, FirstQuiz.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz1);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if (checkedId == R.id.radio1) {
                    setContentView(R.layout.final2);
                }
                else if (checkedId == R.id.radio2){
                    setContentView(R.layout.final1);

                }
                else if (checkedId == R.id.radio3) {
                    setContentView(R.layout.final2);
                }
                else{
                    setContentView(R.layout.final2);
                }
            }
        });
    }
}
