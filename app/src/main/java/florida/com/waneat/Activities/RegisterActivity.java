package florida.com.waneat.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import florida.com.waneat.R;

public class RegisterActivity extends AppCompatActivity {

    TextView alreadyRegistered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.alreadyRegistered = (TextView) findViewById(R.id.alreadyRegistered);

        goToLogin();
    }



    private void goToLogin(){
        this.alreadyRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}
