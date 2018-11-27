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

    private SharedPreferences Data;
    private boolean saveLoginData;
    private String name;
    private String age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

      /*//설정값 불러오기
        Data = getSharedPreferences("Data", MODE_PRIVATE);
        load();*/

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

       if(saveLoginData){
            nameInput.setText(name);
            ageInput.setText(age);
        }

        btn = (Button) findViewById(R.id.sendButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //save();

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
    /*private void save(){
        SharedPreferences.Editor editor = Data.edit();
        editor.putString("NAME",nameInput.getText().toString().trim());
        editor.putString("AGE",ageInput.getText().toString().trim());

        editor.apply();
    }*/
    /*private  void  load(){
        saveLoginData = Data.getBoolean("SAVE_LOGIN_DATA",false);
        name = Data.getString("NAME","");
        age = Data.getString("AGE", "");
    }*/
}

