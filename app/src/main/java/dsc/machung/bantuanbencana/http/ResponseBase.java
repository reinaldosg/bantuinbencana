package dsc.machung.bantuanbencana.http;

import com.google.gson.annotations.SerializedName;

public class ResponseBase {
    @SerializedName("code")
    private String code;

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
