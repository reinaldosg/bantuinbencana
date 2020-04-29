package dsc.machung.bantuanbencana.apimodel.DetailBencana;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import dsc.machung.bantuanbencana.Model.DetailDisasterModel;
import dsc.machung.bantuanbencana.Model.DisasterModel;
import dsc.machung.bantuanbencana.http.ResponseBase;

public class DetailBencanaResponseModel extends ResponseBase {
    @SerializedName("data")
    private List<DetailDisasterModel> data;

    public List<DetailDisasterModel> getData() {
        return data;
    }

    public void setData(List<DetailDisasterModel> data) {
        this.data = data;
    }
}
