package florida.com.waneat.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import florida.com.waneat.R;
import florida.com.waneat.Services.UserService;

public class RegisterActivity extends AppCompatActivity {

    TextView alreadyRegistered;
    UserService service;
    EditText nombreRegister, emailRegister, pwdRegister;
    Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.alreadyRegistered = (TextView) findViewById(R.id.alreadyRegistered);
        this.service = new UserService(RegisterActivity.this);
        this.nombreRegister = (EditText) findViewById(R.id.nombreRegister);
        this.emailRegister = (EditText) findViewById(R.id.emailRegister);
        this.pwdRegister = (EditText) findViewById(R.id.passwordRegister);

        this.buttonRegister = (Button) findViewById(R.id.registrarButton);

        goToLogin();
        registerIn();
    }



    private void goToLogin(){
        this.alreadyRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    private void registerIn(){
        this.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(nombreRegister.getText().toString() == "" || emailRegister.getText().toString() == "" || pwdRegister.getText().toString() == ""){
                   Toast.makeText(RegisterActivity.this, "Introduce todos los campos, por favor!", Toast.LENGTH_SHORT).show();
               }else{
                   service.registerIn(nombreRegister.getText().toString(),emailRegister.getText().toString(),pwdRegister.getText().toString());
                   finish();
               }
            }
        });
    }
}
