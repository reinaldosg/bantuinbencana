package dsc.machung.bantuanbencana.apimodel.ListBencana;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import dsc.machung.bantuanbencana.Model.DisasterModel;
import dsc.machung.bantuanbencana.http.ResponseBase;

public class ListBencanaResponseModel extends ResponseBase {
    @SerializedName("data")
    private List<DisasterModel> data;

    public List<DisasterModel> getData() {
        return data;
    }

    public void setData(List<DisasterModel> data) {
        this.data = data;
    }
}
