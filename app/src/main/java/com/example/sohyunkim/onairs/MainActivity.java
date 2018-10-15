package com.example.sohyunkim.onairs;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.amazonaws.mobileconnectors.apigateway.ApiClientFactory;
import com.example.sohyunkim.onairs.model.OnEarsFirstMessageInputModel;
import com.example.sohyunkim.onairs.model.OnEarsLoadMessageOutputModel;
import com.example.sohyunkim.onairs.model.OnEarsLoadMessageOutputModelItem;
import com.example.sohyunkim.onairs.model.OnEarsMessageInputModel;
import com.example.sohyunkim.onairs.model.OnEarsMessageInputModelMessage;
import com.example.sohyunkim.onairs.model.OnEarsMessageInputModelState;
import com.example.sohyunkim.onairs.model.OnEarsMessageOutputModel;
import com.example.sohyunkim.onairs.model.OnEarsMessageOutputModelResponse;
import com.example.sohyunkim.onairs.model.OnEarsMessageOutputModelResponseState;
import com.example.sohyunkim.project_1.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    SpeechRecognizer mRecognizer;
    ListView m_ListView;
    MainListAdapter m_Adapter;
    public String text="";
    private final int MY_PERMISSIONS_RECORD_AUDIO = 1;
    private MediaPlayer mediaPlayer;
    private int playbackPosition = 0;
    Button button2;
    private Context appContext;
    // Splash or login 에서 받아오는 값
    String userID, name, age, concern, gender;

    // api을 호출을 위해서 추가한 내용
    private ConnectPostAsyncTask connectPostAsyncTask;
    private ChatbotPostAsyncTask chatbotPostAsyncTask;
    private OnEarsFirstMessageInputModel firstInputModel;
    private OnEarsMessageInputModel inputModel;
    private OnEarsMessageOutputModel outputModel;

//    private OnEarsMessageInputModelState inputState = new OnEarsMessageInputModelState();
//    private OnEarsMessageOutputModelResponseState outputState;
//    private OnEarsMessageInputModelMessage inputMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = getIntent();

        // api 호출하는 클래스 생성
        connectPostAsyncTask = new ConnectPostAsyncTask();
        chatbotPostAsyncTask = new ChatbotPostAsyncTask();
        firstInputModel = new OnEarsFirstMessageInputModel();
        inputModel = new OnEarsMessageInputModel();
        outputModel = new OnEarsMessageOutputModel();

     /*   String userID = null;
        SaveSharedPreferences.saveUserIDState(MainActivity.this, userID);*/
//        if(SaveSharedPreferences.getSharedPreferences(MainActivity.this).toString()==""){
//            Intent intent1 = new Intent(getApplicationContext(), LoginActivity.class);
//            startActivity(intent1);
//        }
        userID = intent.getStringExtra("userID");
        name = intent.getStringExtra("name");
        age = intent.getStringExtra("age");
        concern = intent.getStringExtra("concern");
        gender = intent.getStringExtra("gender");

        if(userID == "") {
            firstInputModel.setAge(age);
            firstInputModel.setGender(gender);
            firstInputModel.setConcern(concern);
        }
        firstInputModel.setUserId(userID);

//        msg =  intent.getStringExtra("msg");
//        audioURL = intent.getStringExtra("audioURL");
        // audioURL => outputModel.getResponse().getMessage().getAudioUrl();

        appContext = getApplicationContext();

        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(getApplicationContext(), "Webi_GetPreviousMessages",Toast.LENGTH_LONG).show();
        }
        });

        // 커스텀 어댑터 생성
        m_Adapter = new MainListAdapter();


        // Xml에서 추가한 ListView 연결
        m_ListView = (ListView) findViewById(R.id.listView1);

        // ListView에 어댑터 연결
        m_ListView.setAdapter(m_Adapter);

//        m_Adapter.add(outputModel.getResponse().getMessage().getData(), ChatItemType.APP_TEXT_BUTTON);
        //m_Adapter.add("",ChatItemType.USER);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECORD_AUDIO)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO}, MY_PERMISSIONS_RECORD_AUDIO
                );
            }
        }
        //구글 음성인식 intent 생성
        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR");

        mRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        mRecognizer.setRecognitionListener(recognitionListener);

        ImageButton button = (ImageButton)findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecognizer.startListening(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            outputModel = connectPostAsyncTask.execute(firstInputModel).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        OnEarsMessageInputModelState tmp = new OnEarsMessageInputModelState();
        tmp.setUrl("");
        tmp.setTitle("");
        tmp.setSubCategory("");
        tmp.setMainCategory("");
        tmp.setDepth(0);
        inputModel.setState(tmp);

        Log.d("output model",outputModel.getUserId());
        Log.d("output model",outputModel.getResponse().getMessage().getData());
//        Log.d("output model",outputModel.getResponse().getState().getTitle());
        Log.d("output model",outputModel.getResponse().getState().getMainCategory());
        Log.d("output model",outputModel.getResponse().getState().getSubCategory());
//        Log.d("output model",outputModel.getResponse().getState().getUrl());



//        Log.d("output user id",outputModel.getUserId());
//        convertStateModel(inputModel,outputModel);
//        Log.d("inputModel",inputModel.getState().getMainCategory());

        if(userID == ""){
            SaveSharedPreferences.saveUserIDState(MainActivity.this, outputModel.getUserId());
            SaveSharedPreferences.saveNameState(MainActivity.this, name);
        }
//        OnEarsMessageInputModel onEarsMessageInputModel = new OnEarsMessageInputModel();
//        onEarsMessageInputModel.setUserId(userID);
//
//        // (connect  POST 요청 다음) chatbot POST 보낼때
//        // inputMessage 작성
//        inputMessage.setData("유저가입력한내용");
//        onEarsMessageInputModel.setMessage(inputMessage);
//        // outputState -> inputState
//        convertStateModel(inputState,outputState);
//        onEarsMessageInputModel.setState(inputState);
//
//
//        OnEarsMessageOutputModel outputModel = null;
//
//        // chatbot POST 요청 보낼때
//        // inputMessage 작성
//        inputMessage.setData("유저가입력한내용");
//        onEarsMessageInputModel.setMessage(inputMessage);
//        outputState = outputModel.getResponse().getState();
//        convertStateModel(inputState,outputState);
//
//
//
//
//
//
//        try {
//            outputModel = chatbotPostAsyncTask.execute(onEarsMessageInputModel).get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        Log.d("LoginActivityHTTP", outputModel.getResponse().getMessage().getData());

    }

    /*    음성인식     */
    private RecognitionListener recognitionListener = new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle bundle) {
        }
        @Override
        public void onBeginningOfSpeech() {
        }
        @Override
        public void onRmsChanged(float v) {
        }
        @Override
        public void onBufferReceived(byte[] bytes) {
        }
        @Override
        public void onEndOfSpeech() {
        }
        @Override
        public void onError(int i) { //음성인식실패시
            Toast.makeText(appContext, "다시 말씀해주시겠어요?", Toast.LENGTH_LONG).show();
        }
        @Override
        public void onResults(Bundle bundle) { //음성인식결과값
            String key = "";
            key = SpeechRecognizer.RESULTS_RECOGNITION;
            ArrayList<String> mResult = bundle.getStringArrayList(key);

            String[] result = new String[mResult.size()];
            mResult.toArray(result);
            text = result[0];

            Log.d("MainActivity","voice result :"+result[0]);

            m_Adapter.add(text,ChatItemType.USER);
            m_Adapter.notifyDataSetChanged();

            Toast.makeText(appContext, text, Toast.LENGTH_LONG).show();

        }
        @Override
        public void onPartialResults(Bundle bundle) {
        }
        @Override
        public void onEvent(int i, Bundle bundle) {
        }
    };
    //미디어를 재생하는 사용자 정의 메소드
    public void playAudio(String audioURL) throws Exception{
        //외부 서버나 외부 음악파일 다운로드 시
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(audioURL);
            //다운로드가 다될 때 까지 준비하는 메소드이며 준비가 다되면 그다음 단계로 진행
            mediaPlayer.prepare();
            mediaPlayer.start();
    }

    /* AWS Model Method */
    public void initInputState(OnEarsMessageInputModelState inputState){
        inputState.setDepth(0);
        inputState.setMainCategory("news");
    }
    public void convertStateModel(OnEarsMessageInputModel im, OnEarsMessageOutputModel om) {
        im.getState().setDepth(om.getResponse().getState().getDepth());
        im.getState().setMainCategory(om.getResponse().getState().getMainCategory());
        im.getState().setSubCategory(om.getResponse().getState().getSubCategory());
//        im.getState().setTitle(om.getResponse().getState().getTitle());
//        im.getState().setUrl(om.getResponse().getState().getUrl());
    }
}

