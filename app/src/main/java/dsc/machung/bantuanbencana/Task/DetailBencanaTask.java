package dsc.machung.bantuanbencana.Task;

import android.app.ProgressDialog;
import android.content.Context;
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
import dsc.machung.bantuanbencana.Fragment.DetailBencanaFragment;
import dsc.machung.bantuanbencana.Model.DetailDisasterModel;
import dsc.machung.bantuanbencana.Model.DisasterModel;
import dsc.machung.bantuanbencana.R;
import dsc.machung.bantuanbencana.Util.Global;
import dsc.machung.bantuanbencana.Util.Tools;
import dsc.machung.bantuanbencana.apimodel.DetailBencana.DetailBencanaRequestModel;
import dsc.machung.bantuanbencana.apimodel.DetailBencana.DetailBencanaResponseModel;
import dsc.machung.bantuanbencana.apimodel.ListBencana.ListBencanaResponseModel;

public class DetailBencanaTask {
    private Context activity;
    private DetailBencanaRequestModel model;
    private DetailLoadDone handler;
    public DetailBencanaTask(Context activity, DetailBencanaRequestModel model, DetailLoadDone handler){
        this.activity = activity;
        this.model = model;
        this.handler = handler;
    }

    public void execute(){
        final ProgressDialog progressDialog = ProgressDialog.show(activity,"",activity.getString(R.string.progressLoading),true,false);
        String jsonString = new Gson().toJson(model);
        JSONObject json = null;

        try {
            json = new JSONObject(jsonString);
        } catch (Exception e){}
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST, Global.API_DETAIL_LIST_BENCANA, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String responseString = Tools.fix(response).toString();
                DetailBencanaResponseModel model = new Gson().fromJson(responseString, DetailBencanaResponseModel.class);

                if(Global.SUCCESS_RESPONSE_CODE.equalsIgnoreCase(model.getCode())) {
                    DetailBencanaFragment.dataList = (ArrayList<DetailDisasterModel>) model.getData();
                    handler.load();
                }
                else if(Global.NODATA_FAILED_RESPONSE_CODE.equalsIgnoreCase(model.getCode())){
                    Toast.makeText(activity, "No data found", Toast.LENGTH_SHORT).show();
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
