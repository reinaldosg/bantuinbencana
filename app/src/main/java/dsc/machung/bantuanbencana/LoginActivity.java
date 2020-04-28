package dsc.machung.bantuanbencana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.view.WindowManager;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import dsc.machung.bantuanbencana.Model.UserModel;
import dsc.machung.bantuanbencana.Task.LoginTask;
import dsc.machung.bantuanbencana.Util.db.UserDataCRUD;
import dsc.machung.bantuanbencana.apimodel.Login.LoginRequestModel;

import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout username;
    TextInputLayout password;
    CheckBox rememberme;
    Button login,register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        UserDataCRUD userHandler = new UserDataCRUD();
        List<UserModel> listUser = userHandler.getAllRecords(this);
        if(listUser.size() > 0){
            Intent intent = new Intent(this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|FLAG_ACTIVITY_SINGLE_TOP );
            startActivity(intent);
            finish();
        }else {
            setContentView(R.layout.activity_login);

            username = findViewById(R.id.username);
            password = findViewById(R.id.password);
            rememberme = findViewById(R.id.rememberme);
            login = findViewById(R.id.btnLogin);
            register = findViewById(R.id.btnRegister);

            login.setOnClickListener(this);
            register.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                LoginRequestModel userModel = new LoginRequestModel(username.getEditText().getText().toString().trim(),
                        password.getEditText().getText().toString().trim());
                LoginTask loginTask = new LoginTask(this, userModel);
                loginTask.execute();
                break;
            case R.id.btnRegister:
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
        }
    }
}
