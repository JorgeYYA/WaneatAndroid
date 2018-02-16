package florida.com.waneat.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.felipecsl.gifimageview.library.GifImageView;
import com.koushikdutta.ion.Ion;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

import florida.com.waneat.R;

public class SplashActivity extends AppCompatActivity {

    private ImageView gifImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        gifImageView = (ImageView)findViewById(R.id.carga);

        try{
            Ion.with(gifImageView)
                    .fitCenter()
                    .load("https://loading.io/spinners/ellipsis/lg.discuss-ellipsis-preloader.gif");
        }
        catch (Exception ex){

        }


        new Handler().postDelayed(new Runnable(){
           public void run(){
               SplashActivity.this.startActivity(new Intent(SplashActivity.this, LoginActivity.class));
               SplashActivity.this.finish();
           }
        }, 3000);

    }
}
