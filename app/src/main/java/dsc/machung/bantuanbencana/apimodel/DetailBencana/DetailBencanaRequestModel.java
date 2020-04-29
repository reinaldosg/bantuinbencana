package dsc.machung.bantuanbencana.apimodel.DetailBencana;

import dsc.machung.bantuanbencana.http.RequestBase;

public class DetailBencanaRequestModel extends RequestBase {
    public DetailBencanaRequestModel(String id) {
        this.id = id;
    }

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
