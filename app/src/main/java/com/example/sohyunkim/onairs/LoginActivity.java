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

import com.example.sohyunkim.onairs.model.OnEarsFirstMessageInputModel;
import com.example.sohyunkim.onairs.model.OnEarsMessageOutputModel;
import com.example.sohyunkim.onairs.model.OnEarsMessageOutputModelResponse;
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
                //SaveSharedPreferences.saveNameState(LoginActivity.this, nameInput.getText().toString());

                RadioButton rd = (RadioButton)findViewById(radioGroup0.getCheckedRadioButtonId());
                RadioButton rd1 = (RadioButton)findViewById(radioGroup1.getCheckedRadioButtonId());

                String name = nameInput.getText().toString();
                String gender = rd.getText().toString();
                String age = ageInput.getText().toString();
                String concern = rd1.getText().toString();

                OnEarsFirstMessageInputModel onEarsFirstMessageInputModel = new OnEarsFirstMessageInputModel();
                onEarsFirstMessageInputModel.setUserId(null);
                onEarsFirstMessageInputModel.setAge(age);
                onEarsFirstMessageInputModel.setGender(gender);
                onEarsFirstMessageInputModel.setConcern(concern);

                ConnectPostAsyncTask connectPostAsyncTask = new ConnectPostAsyncTask();
                OnEarsMessageOutputModel outputModel = null;

                try {
                    outputModel = connectPostAsyncTask.execute(onEarsFirstMessageInputModel).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                Log.d("LoginActivityHTTP", outputModel.getUserId());
                Log.d("LoginActivityHTTP1", outputModel.getResponse().getMessage().getData());
                Log.d("LoginActivityHTTP2", outputModel.getResponse().getMessage().getDate());
                Log.d("LoginActivityHTTP3", outputModel.getResponse().getMessage().getAudioUrl());
                Log.d("LoginActivityHTTP4",outputModel.getResponse().getState().getDepth().toString());
                Log.d("LoginActivityHTTP5",outputModel.getResponse().getState().getMainCategory().toString());
                Log.d("LoginActivityHTTP6",outputModel.getResponse().getState().getSubCategory().toString());
                Log.d("LoginActivityHTTP7",outputModel.getResponse().getState().getTitle().toString());
                Log.d("LoginActivityHTTP8",outputModel.getResponse().getState().getUrl().toString());




                String msg = outputModel.getResponse().getMessage().getData();
                String audioURL = outputModel.getResponse().getMessage().getAudioUrl();
                userID = outputModel.getUserId().toString();
                //state도 필요함
                SaveSharedPreferences.saveUserIDState(LoginActivity.this, userID);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("userID",userID);
                intent.putExtra("name",name);
                intent.putExtra("msg",msg);
                intent.putExtra("audioURL",audioURL);

                startActivity(intent);
                finish();
            }
        });
    }
}

