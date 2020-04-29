package dsc.machung.bantuanbencana.Task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import org.json.JSONObject;

import dsc.machung.bantuanbencana.HomeActivity;
import dsc.machung.bantuanbencana.R;
import dsc.machung.bantuanbencana.Util.Global;
import dsc.machung.bantuanbencana.Util.Tools;
import dsc.machung.bantuanbencana.Util.db.UserDataCRUD;
import dsc.machung.bantuanbencana.apimodel.Login.LoginRequestModel;
import dsc.machung.bantuanbencana.apimodel.Login.LoginResponseModel;
import dsc.machung.bantuanbencana.apimodel.Register.RegisterRequestModel;
import dsc.machung.bantuanbencana.apimodel.Register.RegisterResponseModel;

public class RegisterTask {
    private Activity activity;
    private RegisterRequestModel model;
    public RegisterTask(Activity activity, RegisterRequestModel model){
        this.activity = activity;
        this.model = model;
    }
    public void execute(){
        final ProgressDialog progressDialog = ProgressDialog.show(activity,"",activity.getString(R.string.progressLoading),true,false);
        String jsonString = new Gson().toJson(model);
        JSONObject json = null;

        try {
            json = new JSONObject(jsonString);
        } catch (Exception e){}

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST, Global.API_REGISTER, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String responseString = Tools.fix(response).toString();
                RegisterResponseModel responseModal = new Gson().fromJson(responseString, RegisterResponseModel.class);

                if(Global.SUCCESS_RESPONSE_CODE.equalsIgnoreCase(responseModal.getCode())) {
                    LoginRequestModel userModel = new LoginRequestModel(model.getUsername(), model.getPassword());
                    LoginTask loginTask = new LoginTask(activity, userModel);
                    loginTask.execute();
                    activity.finishAffinity();
                }
                else if(Global.REGISTER_FAILED_RESPONSE_CODE.equalsIgnoreCase(responseModal.getCode())){
                    Toast.makeText(activity, activity.getString(R.string.usernameTaken), Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(activity, "Register Failed", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show();
                VolleyLog.e("Error: ", error.getMessage());
                progressDialog.dismiss();
            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(request);
    }
}
