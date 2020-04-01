package dsc.machung.bantuanbencana;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);

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
    }
}
