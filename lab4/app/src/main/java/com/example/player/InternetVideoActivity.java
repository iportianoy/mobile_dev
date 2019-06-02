package com.example.player;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class InternetVideoActivity extends AppCompatActivity {

    VideoView videoPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoPlayer =  findViewById(R.id.videoPlayer);
        videoPlayer.setVideoPath("https://r4---sn-3c27sn7r.googlevideo.com/videoplayback?id=o-AJHs5DV4k_P_lXqt5fX7y1E5zEE7tbgnABBQy_rM0X_-&itag=18&source=youtube&requiressl=yes&pl=17&ei=jj7eXLX5AuGOz7sPwaGUyAw&mime=video%2Fmp4&gir=yes&clen=40074573&ratebypass=yes&dur=814.579&lmt=1558027981496150&fvip=4&beids=9466586&c=WEB&txp=4531432&ip=36.67.196.213&ipbits=0&expire=1558090478&sparams=clen,dur,ei,expire,gir,id,ip,ipbits,ipbypass,itag,lmt,mime,mip,mm,mn,ms,mv,nh,pl,ratebypass,requiressl,source&signature=42A4632641C1BE77DCC7EFB0E7B66398129C9434.381A225F558BA8DCE8D513B75099A85A9DF43B35&key=cms1&video_id=YErUWAEppX0&title=%D0%A0%D0%B0%D1%81%D0%BF%D0%B0%D0%BA%D0%BE%D0%B2%D0%BA%D0%B0+%D0%BA%D0%BE%D0%BB%D0%BE%D0%BD%D0%BA%D0%B8+B%26O+%D0%B7%D0%B0+150.000+%D1%80%D1%83%D0%B1.&redirect_counter=1&rm=sn-npoey7z&fexp=9466586&req_id=6a44c25a248fa3ee&cms_redirect=yes&ipbypass=yes&mip=77.47.194.243&mm=31&mn=sn-3c27sn7r&ms=au&mt=1558073384&mv=m&nh=IgpwcjAxLmticDAyKgkxMjcuMC4wLjE");

        MediaController mediaController = new MediaController(this);
        videoPlayer.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoPlayer);
    }

    public void play(View view){
        videoPlayer.start();
    }
    public void pause(View view){
        videoPlayer.pause();
    }
    public void stop(View view){
        videoPlayer.stopPlayback();
        videoPlayer.resume();
    }
}
