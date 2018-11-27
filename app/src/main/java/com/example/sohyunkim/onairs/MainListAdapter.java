package com.example.sohyunkim.onairs;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.sohyunkim.onairs.model.MessageDocumentData;
import com.example.sohyunkim.project_1.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.CLIPBOARD_SERVICE;

public class MainListAdapter extends BaseAdapter {

  private Context context;
  private final int MY_PERMISSIONS_RECORD_AUDIO = 1;
  private MediaPlayer mediaPlayer;
  private int playbackPosition = 0;
  private Map<String, String> audioUrlMap;
  private Map<String, String> NewsUrlMap;

   public class ListContents {

    String msg;
    ChatItemType type;

    ListContents(String msg, ChatItemType type) {
      this.msg = msg;
      this.type = type;
    }
  }

  private ArrayList<ListContents> m_List;

  public MainListAdapter(Context context){
    this.context = context;
  }

  public MainListAdapter() {

    m_List = new ArrayList<ListContents>();
    audioUrlMap = new HashMap<>();
    NewsUrlMap = new HashMap<>();
  }

  public void addAudioUrl(String message, String url) {
    audioUrlMap.put(message, url);
  }
  public String findAudioUrl(String message) {
    return audioUrlMap.get(message);
  }

  public void addNewsUrl(String message, String url){
    NewsUrlMap.put(message,url);
  }
  public String findNewsUrl(String message){ return NewsUrlMap.get(message); };


  // 외부에서 아이템 추가 요청 시 사용
  public void add(String msg, ChatItemType type) {

    m_List.add(new ListContents(msg, type));
  }

  public void remove(int _position) {
    m_List.remove(_position);
  }

  @Override
  public int getCount() {
    return m_List.size();
  }

  @Override
  public Object getItem(int position) {
    return m_List.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }


  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    final int pos = position;
    final Context context = parent.getContext();

    CustomHolder holder = null;
    LinearLayout wrappingLayout = null;
    RelativeLayout chatItemLayout = null;
    TextView textView = null;
    LinearLayout buttonWrapper = null;
    ImageButton playBtn = null;
    ImageButton stopBtn = null;
    ImageButton urlBtn = null;

    // 리스트가 길어지면서 현재 화면에 보이지 않는 아이템은 converView가 null인 상태로 들어 옴
    if (convertView == null) {
      // view가 null일 경우 커스텀 레이아웃을 얻어 옴
      LayoutInflater inflater = (LayoutInflater) context
              .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      convertView = inflater.inflate(R.layout.chat_item, parent, false);

      wrappingLayout = (LinearLayout) convertView.findViewById(R.id.layout);
      textView = (TextView) convertView.findViewById(R.id.text);
      chatItemLayout = (RelativeLayout) convertView.findViewById(R.id.chatItemLayout);
      buttonWrapper = (LinearLayout) convertView
              .findViewById(R.id.buttonWrapperLayout);
      playBtn = (ImageButton) convertView.findViewById(R.id.play_button);
      stopBtn = (ImageButton) convertView.findViewById(R.id.stop_button);
      urlBtn = (ImageButton) convertView.findViewById(R.id.url_button);

      holder = new CustomHolder();
      holder.wrappingLayout = wrappingLayout;
      holder.textView = textView;
      holder.chatItemLayout = chatItemLayout;
      holder.buttonWrapper = buttonWrapper;
      holder.playBtn = playBtn;
      holder.stopBtn = stopBtn;
      holder.urlBtn = urlBtn;
      convertView.setTag(holder);
    } else {
      holder = (CustomHolder) convertView.getTag();
      wrappingLayout = holder.wrappingLayout;
      textView = holder.textView;
      chatItemLayout = holder.chatItemLayout;
      buttonWrapper = holder.buttonWrapper;
      playBtn = holder.playBtn;
      stopBtn = holder.stopBtn;
      urlBtn = holder.urlBtn;
    }

    textView.setText(m_List.get(position).msg);
    final String news = textView.getText().toString();


    playBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View arg0) {
        try {
          if (mediaPlayer != null&& mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer = null;
          }else {
            playAudio(findAudioUrl(m_List.get(pos).msg));
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });

    urlBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse(findNewsUrl(m_List.get(pos).msg));
        intent.setData(uri);
        context.startActivity(intent);
       }
    });
    stopBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View arg0) {
        try {
          if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });

    if (m_List.get(position).type == ChatItemType.APP_TEXT_BUTTON) {
      textView.setVisibility(View.VISIBLE);
      chatItemLayout.setBackgroundResource(R.drawable.new_other_bubble);
      wrappingLayout.setGravity(Gravity.LEFT);
      buttonWrapper.setVisibility(View.VISIBLE);
      playBtn.setVisibility(View.VISIBLE);
      stopBtn.setVisibility(View.VISIBLE);
      urlBtn.setVisibility(View.GONE);
    } else if (m_List.get(position).type == ChatItemType.USER) {
      textView.setVisibility(View.VISIBLE);
      buttonWrapper.setVisibility(View.GONE);
      playBtn.setVisibility(View.GONE);
      stopBtn.setVisibility(View.GONE);
      urlBtn.setVisibility(View.GONE);
      chatItemLayout.setBackgroundResource(R.drawable.new_my_bubble);
      wrappingLayout.setGravity(Gravity.RIGHT);
    } else if(m_List.get(position).type == ChatItemType.TEXT){
      textView.setVisibility(View.VISIBLE);
      wrappingLayout.setGravity(Gravity.LEFT);
      buttonWrapper.setVisibility(View.GONE);
      playBtn.setVisibility(View.GONE);
      stopBtn.setVisibility(View.GONE);
      urlBtn.setVisibility(View.GONE);
      chatItemLayout.setBackgroundResource(R.drawable.new_other_bubble);
    } else if(m_List.get(position).type == ChatItemType.URL_TEXT){
      textView.setVisibility(View.VISIBLE);
      wrappingLayout.setGravity(Gravity.LEFT);
      buttonWrapper.setVisibility(View.VISIBLE);
      playBtn.setVisibility(View.GONE);
      stopBtn.setVisibility(View.GONE);
      urlBtn.setVisibility(View.VISIBLE);
      chatItemLayout.setBackgroundResource(R.drawable.new_other_bubble);
    }

      convertView.setOnLongClickListener(new OnLongClickListener() {
          @Override
          public boolean onLongClick(View v) {
              ClipboardManager clipboardManager = (ClipboardManager)context.getSystemService(CLIPBOARD_SERVICE);
              ClipData clipData = ClipData.newPlainText("news", news);
              clipboardManager.setPrimaryClip(clipData);
              Toast.makeText(context, "클립보드에 복사되었습니다.", Toast.LENGTH_SHORT).show();
              return true;
          }
      });

      return convertView;
  }

  private class CustomHolder {

    LinearLayout wrappingLayout;
    RelativeLayout chatItemLayout;
    TextView textView;
    LinearLayout buttonWrapper;
    ImageButton playBtn;
    ImageButton stopBtn;
    ImageButton urlBtn;
  }


  public void playAudio(String audioUrl) throws Exception {
    mediaPlayer = new MediaPlayer();
    try{
      mediaPlayer.setDataSource(audioUrl);
      mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
          mp.start();
        }
      });
      mediaPlayer.prepareAsync();
    }catch (IOException e){
      e.printStackTrace();
    }
  }
}
