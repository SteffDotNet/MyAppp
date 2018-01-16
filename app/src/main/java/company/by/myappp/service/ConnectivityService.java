package company.by.myappp.service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Egor on 14.01.2018.
 */

public class ConnectivityService {

    public static boolean isConnectInternet(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(cm == null) return false;

        NetworkInfo[] nInfo = cm.getAllNetworkInfo();
        if(nInfo != null){
            for(int i = 0; i < nInfo.length; i++){
                if(nInfo[i].isConnected()) return true;
            }
        }

        return false;
    }
}
