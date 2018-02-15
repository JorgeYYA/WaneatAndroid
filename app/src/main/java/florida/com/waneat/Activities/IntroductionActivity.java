package florida.com.waneat.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import florida.com.waneat.Adapters.AdapterIntroduction;
import florida.com.waneat.R;
import florida.com.waneat.Services.UserService;

public class IntroductionActivity extends AppCompatActivity {

    private ViewPager viewPagerI;
    AdapterIntroduction adapter;
    private UserService service;

    private int[] images = { R.drawable.ima1, R.drawable.ima2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        viewPagerI = (ViewPager) findViewById(R.id.viewPagerI);

        adapter = new AdapterIntroduction(IntroductionActivity.this, images);
        viewPagerI.setAdapter(adapter);

        this.service = new UserService(IntroductionActivity.this);

        if(service.isLoggedIn()){
            startActivity(new Intent(IntroductionActivity.this, SplashActivity.class));
        }

    }

}
