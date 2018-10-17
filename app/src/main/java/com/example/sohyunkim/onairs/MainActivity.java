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


import com.example.sohyunkim.onairs.model.Input;
import com.example.sohyunkim.onairs.model.InputMessage;
import com.example.sohyunkim.onairs.model.Message;
import com.example.sohyunkim.onairs.model.Output;
import com.example.sohyunkim.onairs.model.State;
import com.example.sohyunkim.onairs.model.UserIdInput;
import com.example.sohyunkim.onairs.model.UserInput;
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
    private UserInput userInput;
    private UserIdInput userIdInput;
    private Input input;
    private State state;
    private Message message;
    // Messages
    private ArrayList<Message> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userInput = new UserInput();
        userIdInput = new UserIdInput();
        input = new Input();
        messages = new ArrayList<Message>();
        connectPostAsyncTask = new ConnectPostAsyncTask(getApplicationContext(), new OnEventListener<Output>() {
            @Override
            public void onSuccess(Output result) {
                // connect 요청 성공시 Callback
                if(userID == null){
                    userID = result.getUserId();
                    SaveSharedPreferences.saveUserIDState(MainActivity.this, userID);
                    SaveSharedPreferences.saveNameState(MainActivity.this, name);
                }
                message = result.getResponse().getMessage();
                messages.add(message);
                state = result.getResponse().getState();
                input.setState(state);
                input.setUserId(userID);

                // View에 메세지 추가
                // ~
                // audio 실행
            }
            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });
        chatbotPostAsyncTask = new ChatbotPostAsyncTask(getApplicationContext(), new OnEventListener<Output>() {
            @Override
            public void onSuccess(Output result) {
                // chatbot 요청 성공시 Callback
                message = result.getResponse().getMessage();
                messages.add(message);
                state = result.getResponse().getState();
                input.setState(state);

                // View에 메세지 추가
                // ~
                // audio 실행
            }
            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });

        intent = getIntent();
        userID = intent.getStringExtra("userID");
        name = intent.getStringExtra("name");
        age = intent.getStringExtra("age");
        concern = intent.getStringExtra("concern");
        gender = intent.getStringExtra("gender");

        if(userID == null) {
            userInput.setAge(age);
            userInput.setGender(gender);
            userInput.setConcern(concern);
        }
        userInput.setUserId(userID);

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
        connectPostAsyncTask.execute(userInput);
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

            // api 호출
            setInputUsingString(input,text);
            chatbotPostAsyncTask.execute(input);

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
    // input set method
    public void setInputUsingString(Input input, String text){
        InputMessage inputMessage = new InputMessage();
        inputMessage.setData(text);
        input.setMessage(inputMessage);
    }
}

