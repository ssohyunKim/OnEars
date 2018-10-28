package com.example.sohyunkim.onairs;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.sohyunkim.project_1.R;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity{
    EditText ageInput;
    RadioGroup radioGroup0;
    RadioGroup radioGroup1;
    EditText nameInput;
    Button btn;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //USERID
        userID = null;
        //이름
        nameInput = (EditText)findViewById(R.id.nameInput);
        //성별
        radioGroup0 = (RadioGroup)findViewById(R.id.radioGroup0);
        //나이
        ageInput = (EditText) findViewById(R.id.ageInput);
        //원하는 카테고리
        radioGroup1 = (RadioGroup)findViewById(R.id.radioGroup1);

        btn = (Button) findViewById(R.id.sendButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton rd = (RadioButton)findViewById(radioGroup0.getCheckedRadioButtonId());
                RadioButton rd1 = (RadioButton)findViewById(radioGroup1.getCheckedRadioButtonId());

                String name = nameInput.getText().toString();
                String gender = rd.getText().toString();
                String age = ageInput.getText().toString();
                String concern = rd1.getText().toString();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("age",age);
                intent.putExtra("concern",concern);
                intent.putExtra("gender",gender);

                startActivity(intent);
                finish();
            }
        });
    }
}

