package com.example.sohyunkim.onairs;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
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

import com.example.sohyunkim.project_1.R;
import java.util.ArrayList;

public class MainListAdapter extends BaseAdapter {

  private final int MY_PERMISSIONS_RECORD_AUDIO = 1;
  private MediaPlayer mediaPlayer;
  private int playbackPosition = 0;

  public class ListContents {

    String msg;
    int type;

    ListContents(String _msg, int _type) {
      this.msg = _msg;
      this.type = _type;
    }
  }

  private ArrayList<ListContents> m_List;


  public MainListAdapter() {

    m_List = new ArrayList<ListContents>();
  }

  // 외부에서 아이템 추가 요청 시 사용
  public void add(String _msg, int _type) {

    m_List.add(new ListContents(_msg, _type));
  }

  // 외부에서 아이템 삭제 요청 시 사용
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
    LinearLayout wrappinglayout = null;
    RelativeLayout chatItemLayout = null;
    TextView textView = null;
    LinearLayout buttonWrapper = null;
    ImageButton playBtn = null;
    ImageButton pauseBtn = null;

    // 리스트가 길어지면서 현재 화면에 보이지 않는 아이템은 converView가 null인 상태로 들어 옴
    if (convertView == null) {
      Log.d("MainListAdapter", "convert view null");
      Log.d("MainListAdapter", "position : " + position);
      Log.d("MainListAdapter", "msg : " + m_List.get(position).msg);
      Log.d("MainListAdapter", "type : " + m_List.get(position).type);

      // view가 null일 경우 커스텀 레이아웃을 얻어 옴
      LayoutInflater inflater = (LayoutInflater) context
          .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      convertView = inflater.inflate(R.layout.chat_item, parent, false);

      wrappinglayout = (LinearLayout) convertView.findViewById(R.id.layout);
      textView = (TextView) convertView.findViewById(R.id.text);
      chatItemLayout = (RelativeLayout) convertView.findViewById(R.id.chatItemLayout);
      buttonWrapper = (LinearLayout) convertView
          .findViewById(R.id.buttonWrapperLayout);
      playBtn = (ImageButton) convertView.findViewById(R.id.play_button);
      pauseBtn = (ImageButton) convertView.findViewById(R.id.pause_button);

      holder = new CustomHolder();
      holder.wrappinglayout = wrappinglayout;
      holder.textView = textView;
      holder.chatItemLayout = chatItemLayout;
      holder.buttonWrapper = buttonWrapper;
      holder.playBtn = playBtn;
      holder.pauseBtn = pauseBtn;
      convertView.setTag(holder);
    } else {
      Log.d("MainListAdapter", "convert view not null");
      Log.d("MainListAdapter", "position : " + position);
      Log.d("MainListAdapter", "msg : " + m_List.get(position).msg);
      Log.d("MainListAdapter", "type : " + m_List.get(position).type);
      holder = (CustomHolder) convertView.getTag();
      wrappinglayout = holder.wrappinglayout;
      textView = holder.textView;
      chatItemLayout = holder.chatItemLayout;
      buttonWrapper = holder.buttonWrapper;
      playBtn = holder.playBtn;
      pauseBtn = holder.pauseBtn;
    }

    textView.setText(m_List.get(position).msg);

    playBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View arg0) {
        try {
          if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer = null;
          }
          playAudio();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });

    pauseBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (mediaPlayer != null) {
          //현재 재생위치 저장
          playbackPosition = mediaPlayer.getCurrentPosition();
          mediaPlayer.pause();
        }
      }
    });

    if (m_List.get(position).type == 0) {
      Log.d("MainListAdapter", "hello my pos is : " + position);
      Log.d("MainListAdapter", "my text is : " + textView.getText());
      Log.d("MainListAdapter", "my text is : " + textView.getId());
      Log.d("MainListAdapter", "my layout is : " + wrappinglayout.getId());
      textView.setVisibility(View.VISIBLE);
      //textView.setBackgroundResource(R.drawable.other_balloon_shape);
      wrappinglayout.setGravity(Gravity.LEFT);
      chatItemLayout.setBackgroundResource(R.drawable.other_balloon_shape);
      buttonWrapper.setVisibility(View.VISIBLE);
      playBtn.setVisibility(View.VISIBLE);
      pauseBtn.setVisibility(View.VISIBLE);
    } else if (m_List.get(position).type == 1) {
      textView.setVisibility(View.VISIBLE);
      buttonWrapper.setVisibility(View.GONE);
      playBtn.setVisibility(View.GONE);
      pauseBtn.setVisibility(View.GONE);
      textView.setBackgroundResource(R.drawable.my_balloon_shape);
      wrappinglayout.setGravity(Gravity.RIGHT);
    } else if (m_List.get(position).type == 2) {
      textView.setVisibility(View.VISIBLE);
      textView.setBackgroundResource(R.drawable.datebg);
      buttonWrapper.setVisibility(View.GONE);
      playBtn.setVisibility(View.GONE);
      pauseBtn.setVisibility(View.GONE);
      wrappinglayout.setGravity(Gravity.CENTER);
    } else if (m_List.get(position).type == 3) {
      buttonWrapper.setBackgroundResource(R.drawable.other_balloon_shape);
      buttonWrapper.setVisibility(View.VISIBLE);
      playBtn.setVisibility(View.VISIBLE);
      pauseBtn.setVisibility(View.VISIBLE);
      Log.d("MainListAdapter", "hello my pos is : " + position);
      Log.d("MainListAdapter", "my text is : " + textView.getText());
      Log.d("MainListAdapter", "my text is : " + textView.getId());
      Log.d("MainListAdapter", "my layout is : " + wrappinglayout.getId());
      //text.setBackgroundResource(R.drawable.other_balloon_shape);
      wrappinglayout.setGravity(Gravity.LEFT);
      textView.setVisibility(View.GONE);
    }

    // 리스트 아이템을 터치 했을 때 이벤트 발생
    convertView.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        // 터치 시 해당 아이템 이름 출력
        Toast.makeText(context, "리스트 클릭 : " + m_List.get(pos), Toast.LENGTH_SHORT).show();
      }
    });

    // 리스트 아이템을 길게 터치 했을때 이벤트 발생
    convertView.setOnLongClickListener(new OnLongClickListener() {

      @Override
      public boolean onLongClick(View v) {
        // 터치 시 해당 아이템 이름 출력
        Toast.makeText(context, "리스트 롱 클릭 : " + m_List.get(pos), Toast.LENGTH_SHORT).show();
        return true;
      }
    });

    return convertView;
  }

  private class CustomHolder {

    LinearLayout wrappinglayout;
    RelativeLayout chatItemLayout;
    TextView textView;
    LinearLayout buttonWrapper;
    ImageButton playBtn;
    ImageButton pauseBtn;
  }

  //미디어를 재생하는 사용자 정의 메소드
  private void playAudio() throws Exception {

    //외부 서버나 외부 음악파일 다운로드 시
    mediaPlayer = new MediaPlayer();
    mediaPlayer.setDataSource(
        "https://test-audioposts.s3.ap-northeast-2.amazonaws.com/default_message.mp3");
    //다운로드가 다될 때 까지 준비하는 메소드이며 준비가 다되면 그다음 단계로 진행
    mediaPlayer.prepare();
    mediaPlayer.start();
  }
}
