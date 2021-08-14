package com.sumit.boundservices;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class MusicService extends Service {
    private MediaPlayer mediaPlayer;
    public MusicService() {
        //communication is very easy in bound services

    }
    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this , R.raw.sumit);
    }

    @Override//IBinder interface help of this i can stabilised the connection
    public IBinder onBind(Intent intent) {

       return new serviceBinder();

    }



    public  void play(){
      if(!mediaPlayer.isPlaying()){
          mediaPlayer.start();
      }

    }

    public  void pause(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();;
        }

    }

    public  void stop(){
if(mediaPlayer.isPlaying()){
    mediaPlayer.stop();

}

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }

    //parent is iBinder and Binder is child
    public class serviceBinder extends Binder{

        public MusicService getBoundService(){
            return  MusicService.this;
        }
    }
}