package dsc.machung.bantuanbencana.Util;

import dsc.machung.bantuanbencana.Model.UserModel;

public class Global {
    public static String APPLICATION_URL = "http://192.168.0.106:5000/";

    public static String SUCCESS_RESPONSE_CODE = "0";

    public static String REGISTER_FAILED_RESPONSE_CODE = "9998";
    public static String NODATA_FAILED_RESPONSE_CODE = "9997";

    public static String API_LOGIN = APPLICATION_URL + "apilogin";
    public static String API_REGISTER = APPLICATION_URL + "apiregister";
    public static String API_LIST_BENCANA = APPLICATION_URL + "apigetlistbencana";
    public static String API_DETAIL_LIST_BENCANA = APPLICATION_URL + "apidetail";

    public static String CLOTHES = "0";
    public static String FOOD= "1";
    public static String MONEY = "2";

    public static UserModel USER = new UserModel();
}
