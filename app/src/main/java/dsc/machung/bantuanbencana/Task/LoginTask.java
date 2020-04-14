package dsc.machung.bantuanbencana.Task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import dsc.machung.bantuanbencana.R;
import dsc.machung.bantuanbencana.Util.Global;
import dsc.machung.bantuanbencana.Util.Tools;
import dsc.machung.bantuanbencana.Util.db.PostsDatabaseHelper;
import dsc.machung.bantuanbencana.Util.db.UserDataCRUD;
import dsc.machung.bantuanbencana.apimodel.LoginRequestModel;
import dsc.machung.bantuanbencana.apimodel.LoginResponseModel;


public class LoginTask{
    private Activity activity;
    private LoginRequestModel model;
    public LoginTask(Activity activity, LoginRequestModel model){
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
                Request.Method.POST, Global.API_LOGIN, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String responseString = Tools.fix(response).toString();
                LoginResponseModel userModel = new Gson().fromJson(responseString, LoginResponseModel.class);

                if(Global.SUCCESS_RESPONSE_CODE.equalsIgnoreCase(userModel.getCode())) {
                    Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show();
                    UserDataCRUD userHandler = new UserDataCRUD();
                    userHandler.addRecord(activity.getApplicationContext(), userModel.getData());
                }
                else{
                    Toast.makeText(activity, "Login Failed", Toast.LENGTH_SHORT).show();
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
