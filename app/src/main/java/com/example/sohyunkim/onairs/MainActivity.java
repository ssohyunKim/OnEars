package com.example.sohyunkim.onairs;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    SpeechRecognizer mRecognizer;
    ListView m_ListView;
    MainListAdapter m_Adapter;
    public String text="";
    private final int MY_PERMISSIONS_RECORD_AUDIO = 1;
    Button button2;
    ImageButton setting;
    private Context appContext;
    String userID, name, age, concern, gender;

    private ConnectPostAsyncTask connectPostAsyncTask;
    private UserInput userInput;
    private UserIdInput userIdInput;
    private Input input;
    private InputMessage inputMessage;
    private State state;
    private Message message;
    private ArrayList<Message> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInput = new UserInput();
        userIdInput = new UserIdInput();
        input = new Input();
        inputMessage = new InputMessage();
        messages = new ArrayList<Message>();

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

        Toast toast = Toast.makeText(getApplicationContext(), "환영합니다. "+name+" 님!\n잠시만 기다려주세요.", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0,0);
        LinearLayout toastContentView = (LinearLayout)toast.getView();
        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.drawable.welcome);
        toastContentView.addView(imageView,0);
        toast.show();

        connectPostAsyncTask = new ConnectPostAsyncTask(getApplicationContext(), new OnEventListener<Output>() {
            @Override
            public void onSuccess(Output result) {
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

                m_Adapter.add(name+" 님의 관심 카테고리 뉴스를 보여드릴게요.",ChatItemType.TEXT);

                m_Adapter.add(message.getData(),ChatItemType.APP_TEXT_BUTTON);
                m_Adapter.add("자세한 내용을 들으시려면 '확인' 이라고 말씀해주세요.\n '종료'라고 말하시면 앱이 종료됩니다.",ChatItemType.TEXT);


                m_Adapter.addAudioUrl(message.getData() , message.getAudioUrl().toString());
                Log.d("###",message.getAudioUrl().toString());
                m_Adapter.notifyDataSetChanged();


            }
            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });

        connectPostAsyncTask.execute(userInput);


        appContext = getApplicationContext();

        setting = (ImageButton)findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        // 커스텀 어댑터 생성
        m_Adapter = new MainListAdapter();

        // Xml에서 추가한 ListView 연결
        m_ListView = (ListView) findViewById(R.id.listView1);

        // ListView에 어댑터 연결
        m_ListView.setAdapter(m_Adapter);


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
    }

    public RecognitionListener recognitionListener = new RecognitionListener() {
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
        public void onError(int i) {
            Toast.makeText(appContext, "다시 말씀해주시겠어요?", Toast.LENGTH_LONG).show();
        }
        @Override
        public void onResults(Bundle bundle) {
            String key = "";
            key = SpeechRecognizer.RESULTS_RECOGNITION;
            ArrayList<String> mResult = bundle.getStringArrayList(key);

            String[] result = new String[mResult.size()];
            mResult.toArray(result);
            text = result[0];
            Log.d("MainActivity","voice result :"+result[0]);
            m_Adapter.add(text,ChatItemType.USER);

            Toast.makeText(appContext, text, Toast.LENGTH_LONG).show();
            if(text.contains("확인")){
                setInputUsingString(input, inputMessage ,text);

                ChatbotPostAsyncTask chatbotPostAsyncTask = new ChatbotPostAsyncTask(getApplicationContext(), new OnEventListener<Output>() {
                    @Override
                    public void onSuccess(Output result) {
                        message = result.getResponse().getMessage();
                        messages.add(message);
                        state = result.getResponse().getState();
                        input.setState(state);

                        m_Adapter.add(message.getDocumentData().getEnSummary(),ChatItemType.APP_TEXT_BUTTON);
                        m_Adapter.add(message.getDocumentData().getKorSummary().toString(), ChatItemType.APP_TEXT_BUTTON);
                        m_Adapter.addAudioUrl(message.getDocumentData().getEnSummary(), message.getDocumentData().getEnAudioUrl().toString());
                        m_Adapter.addAudioUrl(message.getDocumentData().getKorSummary(), message.getDocumentData().getKorAudioUrl().toString());

                        m_Adapter.add(message.getDocumentUrl(),ChatItemType.URL_TEXT);
                        m_Adapter.addNewsUrl(message.getDocumentUrl(),message.getDocumentUrl().toString());
                        m_Adapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onFailure(Exception e) {
                        e.printStackTrace();
                    }
                });
                chatbotPostAsyncTask.execute(input);
            }else  if(text.contains("종료")){
                m_Adapter.add("이용해 주셔서 감사합니다.\n 오늘도 행복한 하루 되세요.",ChatItemType.TEXT);
                moveTaskToBack(true);
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
            m_Adapter.notifyDataSetChanged();
        }
        @Override
        public void onPartialResults(Bundle bundle) {
        }
        @Override
        public void onEvent(int i, Bundle bundle) {
        }
    };
    // input set method
    public void setInputUsingString(Input input,InputMessage inputMessage, String text){
        inputMessage.setData(text);
        input.setMessage(inputMessage);
    }
}

