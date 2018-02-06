package florida.com.waneat.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import florida.com.waneat.R;

public class LoginActivity extends AppCompatActivity {

    private EditText editLogEmail, editLogPass;
    private Button login, registeR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editLogEmail = (EditText)findViewById(R.id.editLogEmail);
        editLogPass = (EditText)findViewById(R.id.editLogPass);

        login = (Button) findViewById(R.id.buttonLogin);
        registeR = (Button) findViewById(R.id.buttonRegister);

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //llamar metodo service
            }
        });
    }
}
