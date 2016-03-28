package br.com.vanderson.stackoverflow;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import br.com.vanderson.stackoverflow.app.ActivityApp;

public class SplashScreenActivity extends ActivityApp {

    private static final int IO_BUFFER_SIZE = 4 * 1024;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        hideToolBar();
        //ImageView img = (ImageView) findViewById(R.id.imgSplash);
       /* Uri uri = Uri.parse("https://www.gravatar.com/avatar/d2cc9f3e4af21a9e193ae137a649e60b");
        img.setImageURI(uri);*/



      /*  String url = "https://www.gravatar.com/avatar/d2cc9f3e4af21a9e193ae137a649e60b?s=128&d=identicon&r=PG";
        String url1 = "https://www.gravatar.com/avatar/d2cc9f3e4af21a9e193ae137a649e60b";
        String url2 ="http://i.imgur.com/DvpvklR.png";

        Picasso.with(this).load(url).into(img);
       // img.setImageBitmap(BitmapUtil.loadBitmap(url));

*/
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                finish();
                Intent intent = new Intent();
                intent.setClass(SplashScreenActivity.this, ListStackActivity.class);
                startActivity(intent);
            }
        }, 3000);


    }
}
