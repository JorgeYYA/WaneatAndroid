package florida.com.waneat.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import java.util.Timer;
import java.util.TimerTask;

import florida.com.waneat.R;

public class IntroductionActivity extends AppCompatActivity {

    private ImageSwitcher imageSwitcherI;
    private int[] galeria = { R.drawable.imagen1, R.drawable.imagen2};
    private int posicion;
    private static final int DURACION = 4000;
    private Timer timer = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);


        imageSwitcherI = (ImageSwitcher) findViewById(R.id.imageSwitcherI);
        imageSwitcherI.setFactory(new ViewSwitcher.ViewFactory()
        {
            public View makeView()
            {
                ImageView imageView = new ImageView(IntroductionActivity.this);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                return imageView;
            }
        });

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        imageSwitcherI.setInAnimation(fadeIn);
        imageSwitcherI.setOutAnimation(fadeOut);

        startSlider();
    }

    public void startSlider(){
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {
            public void run()
            {
                runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                        imageSwitcherI.setImageResource(galeria[posicion]);
                        posicion++;
                        if (posicion == galeria.length)
                            posicion = 0;
                    }
                });
            }
        }, 0, DURACION);
    }
}
