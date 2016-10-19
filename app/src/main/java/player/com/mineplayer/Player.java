package player.com.mineplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class Player extends AppCompatActivity {

    Button pause_button;

    MediaPlayer mMediaPlayer=new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_player);
        pause_button=(Button)findViewById(R.id.pauseButton);
        Intent i=getIntent();
        String uriString=i.getStringExtra("SONG_URI");
        if(uriString!=null){
                        try {
                            if (mMediaPlayer.isPlaying())
                            {
                                mMediaPlayer.stop();
                                mMediaPlayer.release();
                            }
                mMediaPlayer.setDataSource(this, Uri.parse(uriString));
                mMediaPlayer.prepare();
                mMediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        @Override
//                pause_button.setOnClickListener(new View.OnClickListener() {
//             public void onClick(View view) {
//
//                if (mMediaPlayer.isPlaying())
//                {
//                    mMediaPlayer.stop();
//                }
//            }
//        });
    }


    @Override
    protected void onDestroy() {

        if (mMediaPlayer.isPlaying())
        {
            mMediaPlayer.stop();
            mMediaPlayer.release();
        }
        super.onDestroy();
    }
}
