package dsc.machung.bantuanbencana.Task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;

import dsc.machung.bantuanbencana.Fragment.Adapter.ListBencanaAdapter;
import dsc.machung.bantuanbencana.Fragment.ListBencanaFragment;
import dsc.machung.bantuanbencana.HomeActivity;
import dsc.machung.bantuanbencana.Model.DisasterModel;
import dsc.machung.bantuanbencana.R;
import dsc.machung.bantuanbencana.Util.Global;
import dsc.machung.bantuanbencana.Util.Tools;
import dsc.machung.bantuanbencana.Util.db.UserDataCRUD;
import dsc.machung.bantuanbencana.apimodel.ListBencana.ListBencanaRequestModel;
import dsc.machung.bantuanbencana.apimodel.ListBencana.ListBencanaResponseModel;
import dsc.machung.bantuanbencana.apimodel.Login.LoginRequestModel;
import dsc.machung.bantuanbencana.apimodel.Login.LoginResponseModel;

import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

public class ListBencanaTask {
    private Context activity;
    private ArrayList<DisasterModel> dataList;
    private ListBencanaAdapter adapter;
    public ListBencanaTask(Context activity, ArrayList<DisasterModel> dataList, ListBencanaAdapter adapter){
        this.activity = activity;
        this.dataList = dataList;
        this.adapter = adapter;
    }

    public void execute(){
        final ProgressDialog progressDialog = ProgressDialog.show(activity,"",activity.getString(R.string.progressLoading),true,false);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST, Global.API_LIST_BENCANA, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String responseString = Tools.fix(response).toString();
                ListBencanaResponseModel model = new Gson().fromJson(responseString, ListBencanaResponseModel.class);

                if(Global.SUCCESS_RESPONSE_CODE.equalsIgnoreCase(model.getCode())) {
                    dataList = (ArrayList<DisasterModel>) model.getData();
                    adapter.setDataList(dataList);
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(activity, "Get Data Failed", Toast.LENGTH_SHORT).show();
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
