package com.example.sohyunkim.onairs;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sohyunkim.project_1.R;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    SpeechRecognizer mRecognizer;
    ListView m_ListView;
    MainListAdapter m_Adapter;
    public String text="";
    private final int MY_PERMISSIONS_RECORD_AUDIO = 1;
    private MediaPlayer mediaPlayer;
    private int playbackPosition = 0;

    private Context appContext;

    private Intent intent1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appContext = getApplicationContext();

        //intent1 이동
        intent1 = new Intent(appContext, CustomAdapter.class);

        //오디오 버튼
        ImageButton start = (ImageButton)findViewById(R.id.play_button);
        ImageButton pause = (ImageButton)findViewById(R.id.pause_button);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
               try{
                   if(mediaPlayer!=null){
                       mediaPlayer.stop();
                       mediaPlayer = null;
                   }
                   playAudio();
               }catch (Exception e){
                   e.printStackTrace();
               }
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(mediaPlayer != null){
                        //현재 재생위치 저장
                        playbackPosition = mediaPlayer.getCurrentPosition();
                        mediaPlayer.pause();
                    }
            }
        });


        // 커스텀 어댑터 생성
        m_Adapter = new MainListAdapter();

        // Xml에서 추가한 ListView 연결
        m_ListView = (ListView) findViewById(R.id.listView1);

        // ListView에 어댑터 연결
        m_ListView.setAdapter(m_Adapter);

        m_Adapter.add("정치, 경제, 건강, 문화, 세계, 기술 중에서 \n"+"원하시는 메뉴를 말씀해 주세요.",0);
        m_Adapter.add("hello", 3);
        //m_Adapter.add("건강",1);

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

            m_Adapter.add(text,1);
            m_Adapter.notifyDataSetChanged();

            Toast.makeText(appContext, text, Toast.LENGTH_LONG).show();
            //Intent intent1 = new Intent(getApplicationContext(),CustomAdapter.class);
            intent1.putExtra("result",text);
            //startActivity(intent1);



            //mMessageView.setText(result[0]);

            //result 값 서버에 보낸다.
        }
        @Override
        public void onPartialResults(Bundle bundle) {
        }
        @Override
        public void onEvent(int i, Bundle bundle) {
        }

    };
    //미디어를 재생하는 사용자 정의 메소드
    private void playAudio() throws Exception{

        //외부 서버나 외부 음악파일 다운로드 시
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource("https://test-audioposts.s3.ap-northeast-2.amazonaws.com/default_message.mp3");
            //다운로드가 다될 때 까지 준비하는 메소드이며 준비가 다되면 그다음 단계로 진행
            mediaPlayer.prepare();
            mediaPlayer.start();
    }
}


