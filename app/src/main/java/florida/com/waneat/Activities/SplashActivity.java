package florida.com.waneat.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

import florida.com.waneat.R;

public class SplashActivity extends AppCompatActivity {

    private GifImageView gifImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        gifImageView = (GifImageView)findViewById(R.id.carga);

        try{
            InputStream inputStream = getAssets().open("loader.gif");
            byte[] bytes = IOUtils.toByteArray(inputStream);
            gifImageView.setBytes(bytes);
            gifImageView.startAnimation();
        }
        catch (IOException ex){

        }


        new Handler().postDelayed(new Runnable(){
           public void run(){
               SplashActivity.this.startActivity(new Intent(SplashActivity.this, LoginActivity.class));
               SplashActivity.this.finish();
           }
        }, 3000);

    }
}
