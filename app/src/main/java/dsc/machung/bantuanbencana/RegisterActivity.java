package dsc.machung.bantuanbencana;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import dsc.machung.bantuanbencana.Task.RegisterTask;
import dsc.machung.bantuanbencana.apimodel.Register.RegisterRequestModel;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout edtUsername;
    TextInputLayout edtEmail;
    TextInputLayout edtPassword;
    TextInputLayout edtConfirmationPassword;
    TextInputLayout edtFullName;
    TextInputLayout edtPhoneNumber;
    TextInputLayout edtAddress;

    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUsername = findViewById(R.id.username);
        edtEmail = findViewById(R.id.email);
        edtPassword = findViewById(R.id.password);
        edtConfirmationPassword = findViewById(R.id.passwordValidation);
        edtFullName = findViewById(R.id.name);
        edtPhoneNumber = findViewById(R.id.phonenumber);
        edtAddress = findViewById(R.id.address);
        register = findViewById(R.id.btnRegister);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRegister:
                if(validateInput()){
                    if(confirmPassword()){
                        String username = edtUsername.getEditText().getText().toString();
                        String email = edtEmail.getEditText().getText().toString();
                        String password = edtPassword.getEditText().getText().toString();
                        String fullName = edtConfirmationPassword.getEditText().getText().toString();
                        String phoneNumber = edtFullName.getEditText().getText().toString();
                        String address = edtPhoneNumber.getEditText().getText().toString();

                        RegisterRequestModel request = new RegisterRequestModel(
                                username, password, email, fullName, phoneNumber, address);
                        RegisterTask registerTask = new RegisterTask(this, request);
                        registerTask.execute();
                    }
                }
        }
    }

    private boolean validateInput(){
        if(TextUtils.isEmpty(edtUsername.getEditText().getText())
                || TextUtils.isEmpty(edtEmail.getEditText().getText())
                || TextUtils.isEmpty(edtPassword.getEditText().getText())
                || TextUtils.isEmpty(edtConfirmationPassword.getEditText().getText())
                || TextUtils.isEmpty(edtFullName.getEditText().getText())
                || TextUtils.isEmpty(edtPhoneNumber.getEditText().getText())
                || TextUtils.isEmpty(edtAddress.getEditText().getText())){
            Toast.makeText(this, getString(R.string.emptyTextInput),Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(edtEmail.getEditText().getText()).matches()){
            Toast.makeText(this, getString(R.string.wrongEmail),Toast.LENGTH_SHORT).show();
        }
        return true;
    }
    private boolean confirmPassword(){
        String passwordInput = edtPassword.getEditText().getText().toString();
        String confirmationPasswordInput = edtConfirmationPassword.getEditText().getText().toString();
        if(!passwordInput.equals(confirmationPasswordInput)){
            edtPassword.setError(getString(R.string.passwordConfirmationNotMatch));
            edtConfirmationPassword.setError(getString(R.string.passwordConfirmationNotMatch));
            Toast.makeText(this, getString(R.string.passwordConfirmationNotMatch),Toast.LENGTH_SHORT).show();
            return false;
        } else{
            edtPassword.setErrorEnabled(false);
            edtConfirmationPassword.setErrorEnabled(false);
        }
        return true;
    }

    protected void usernameTaken(){
        edtUsername.setError(getString(R.string.usernameTaken));
    }
}
