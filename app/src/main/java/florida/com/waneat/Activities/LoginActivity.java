package florida.com.waneat.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import florida.com.waneat.R;
import florida.com.waneat.Services.UserService;

public class LoginActivity extends AppCompatActivity {

    private EditText editLogEmail, editLogPass;
    private Button login;
    private TextView registerLabel;
    private UserService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.service = new UserService(LoginActivity.this);

        editLogEmail = (EditText)findViewById(R.id.editLogEmail);
        editLogPass = (EditText)findViewById(R.id.editLogPass);

        login = (Button) findViewById(R.id.buttonLogin);
        registerLabel = (TextView) findViewById(R.id.registerLabel);

        service.isLoggedIn();

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //llamar metodo service
                service.signIn(editLogEmail.getText().toString(), editLogPass.getText().toString());
            }
        });

        registerLabel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Inicia activity
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}
