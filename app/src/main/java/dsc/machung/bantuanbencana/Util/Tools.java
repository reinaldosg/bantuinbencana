package dsc.machung.bantuanbencana.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

public class Tools {

    public static boolean isConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isConnected = false;
        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            NetworkCapabilities capabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    isConnected = true;
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    isConnected =  true;
                }  else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)){
                    isConnected =  true;
                }
            }
        }else {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();
        }
        return isConnected;
    }
}
