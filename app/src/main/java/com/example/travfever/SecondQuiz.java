package com.example.travfever;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class SecondQuiz extends AppCompatActivity {
    private RadioGroup radioGroup;
    private Button nxt;
    int pos;

    public void buttonOnClick4(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void buttonOnClick3(View v) {
        Intent i = new Intent(this, FirstQuiz.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz2);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if (checkedId == R.id.radio5) {
                    setContentView(R.layout.finish);
                }
                else {
                    setContentView(R.layout.final2);
                }
            }
        });
    }
}
