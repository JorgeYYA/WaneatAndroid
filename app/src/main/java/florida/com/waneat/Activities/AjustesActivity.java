package florida.com.waneat.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Locale;

import florida.com.waneat.Preferences.Preferences;
import florida.com.waneat.R;

public class AjustesActivity extends AppCompatActivity {

    RadioGroup lang;
    Button salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        lang = findViewById(R.id.radioGroupLang);
        salir = findViewById(R.id.exitButton);

        checkSelectedLang();


        changeLanguage();
        exitApp();

    }



    public void changeLanguage(){
        lang.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.spanish){
                    changeLanguage("es", AjustesActivity.this);
                    refreshThis();
                }else if(i == R.id.english){
                    changeLanguage("en", AjustesActivity.this);
                    refreshThis();
                }
            }
        });

    }

    private void checkSelectedLang(){
        RadioGroup radioGroupLanguages2 = (RadioGroup) findViewById(R.id.radioGroupLang);
        String locale = Preferences.getString(this, Preferences.ACTUAL_LOCALE);
        if (locale != null) {
            switch (locale) {
                case "en":
                    (radioGroupLanguages2).check(R.id.english);
                    break;
                case "es":
                    (radioGroupLanguages2).check(R.id.spanish);
                    break;
            }
        }else{

        }

    }

    private void exitApp(){
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(AjustesActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }

    public void changeLanguage(String iso, Activity context) {
        //Change the current locale of the application
        Preferences.setString(AjustesActivity.this,Preferences.ACTUAL_LOCALE,iso);
        Locale myLocale = new Locale(iso);
        Resources res = context.getResources();
        Configuration conf = res.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            conf.setLocale(myLocale);
        } else {
            conf.locale = myLocale;
        }
        context.getResources().updateConfiguration(conf, context.getResources().getDisplayMetrics());
    }

    private void refreshThis() {
        //Recreate the activity to set the new Locale
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        overridePendingTransition(0, 0);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(AjustesActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
