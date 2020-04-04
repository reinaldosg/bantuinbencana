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

import dsc.machung.bantuanbencana.Task.LoginTask;
import dsc.machung.bantuanbencana.apimodel.LoginRequestModel;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Animation ttb;
    Animation btt;
    ImageView logo;
    EditText username;
    EditText password;
    CheckBox rememberme;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ttb = AnimationUtils.loadAnimation(this, R.anim.ttb);
        btt = AnimationUtils.loadAnimation(this, R.anim.btt);

        logo = findViewById(R.id.logo);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        rememberme = findViewById(R.id.rememberme);
        login = findViewById(R.id.login);

        //run animation
        logo.startAnimation(ttb);
        username.startAnimation(btt);
        password.startAnimation(btt);
        rememberme.startAnimation(btt);
        login.startAnimation(btt);

        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                LoginRequestModel userModel = new LoginRequestModel(username.getText().toString().trim(),
                        password.getText().toString().trim());
                LoginTask loginTask = new LoginTask(this, userModel);
                loginTask.execute();
                break;
        }
    }
}
