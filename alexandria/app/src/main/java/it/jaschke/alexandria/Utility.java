package it.jaschke.alexandria;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by petr on 08-Nov-15.
 */
public class Utility {
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfoWiFi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo networkInfoMobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return (networkInfoWiFi != null && networkInfoWiFi.isConnectedOrConnecting()) || (networkInfoMobile != null && networkInfoMobile.isConnectedOrConnecting());
    }
    public static void showAlertMessage(Context context){
        new AlertDialog.Builder(context)
                .setTitle(context.getResources().getString(R.string.warning))
                .setMessage(context.getResources().getString(R.string.no_connection))
                .show().setCanceledOnTouchOutside(true);
    }
}
