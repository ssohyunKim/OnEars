package com.example.sohyunkim.onairs;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
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

public class LoginActivity extends AppCompatActivity{
    RadioGroup radioGroup;
    RadioGroup radioGroup0;
    RadioGroup radioGroup1;
    EditText nameInput;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //이름
        nameInput = (EditText)findViewById(R.id.nameInput);
        //성별
        radioGroup0 = (RadioGroup)findViewById(R.id.radioGroup0);
        //나이
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        //원하는 카테고리
        radioGroup1 = (RadioGroup)findViewById(R.id.radioGroup1);

        //보내기
        btn = (Button) findViewById(R.id.sendButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveSharedPreferences.saveNameState(LoginActivity.this, nameInput.getText().toString());

                RadioButton rd = (RadioButton)findViewById(radioGroup0.getCheckedRadioButtonId());
                RadioButton rd0 = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
                RadioButton rd1 = (RadioButton)findViewById(radioGroup1.getCheckedRadioButtonId());

                String name = nameInput.getText().toString();
                String gender = rd.getText().toString();
                String age = rd0.getText().toString();
                String concern = rd1.getText().toString();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("gender",gender);
                intent.putExtra("age",age);
                intent.putExtra("concern",concern);
                startActivity(intent);
                finish();
            }
        });
    }
}

