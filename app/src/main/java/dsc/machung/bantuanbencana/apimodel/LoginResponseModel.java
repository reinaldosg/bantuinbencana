package dsc.machung.bantuanbencana.apimodel;

import com.google.gson.annotations.SerializedName;

import dsc.machung.bantuanbencana.UserModel;
import dsc.machung.bantuanbencana.http.ResponseBase;

public class LoginResponseModel extends ResponseBase {
    @SerializedName("data")
    private UserModel data;

    public UserModel getData() {
        return data;
    }

    public void setData(UserModel data) {
        this.data = data;
    }
}
