package dsc.machung.bantuanbencana;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.view.WindowManager;

import com.google.android.material.textfield.TextInputLayout;

import dsc.machung.bantuanbencana.Task.LoginTask;
import dsc.machung.bantuanbencana.apimodel.LoginRequestModel;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView logo;
    TextInputLayout username;
    TextInputLayout password;
    CheckBox rememberme;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        logo = findViewById(R.id.logo);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        rememberme = findViewById(R.id.rememberme);
        login = findViewById(R.id.login);

        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                LoginRequestModel userModel = new LoginRequestModel(username.getEditText().getText().toString().trim(),
                        password.getEditText().getText().toString().trim());
                LoginTask loginTask = new LoginTask(this, userModel);
                loginTask.execute();
                break;
        }
    }
}
