package net.bigmachini.challange.journalapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.text.format.DateFormat;
import android.util.Patterns;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {
    /**
     * Create a dialog with an icon
     * @param context
     * @param title
     * @param errorMessage
     * @param drawable
     */
    public static void showDialog(Context context, String title, String errorMessage, Drawable drawable) {
        new MaterialDialog.Builder(context)
                .title(title)
                .icon(drawable)
                .content(errorMessage)
                .positiveText(R.string.dismiss)
                .show();
    }


    /**
     * Create a dialog without an icon
     * @param context
     * @param title
     * @param errorMessage
     */
    public static void showDialog(Context context, String title, String errorMessage) {
        new MaterialDialog.Builder(context)
                .title(title)
                .content(errorMessage)
                .positiveText(R.string.dismiss)
                .show();
    }

    /**
     * Return current date as a sting
     * @return
     */
    public static String getCurrentDate()
    {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        return df.format(c);
    }


    public static String getDateTimeFromTimeStamp(String time, String dateTimeFormatString) {
        //return null when time is null
        if (time == null || time.isEmpty())
            return null;
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(Long.parseLong(time) * 1000);
        String date = DateFormat.format(dateTimeFormatString, cal).toString();
        return date;
    }


    public static boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * Verifies phone number, takes in phone, lenght and what it should start with
     * @param phone
     * @param length
     * @param startWith
     * @return true or false
     */
    public static boolean isPhoneNumberValid(String phone, int length, String startWith) {
        return Patterns.PHONE.matcher(phone).matches() && phone.trim().length() == length && phone.startsWith(startWith);
    }


    public static Boolean CheckConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo activeWIFIInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (activeWIFIInfo.isConnected() || activeNetInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    public static void toastText(final Context context, final String text) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            public void run() {
                Toast.makeText(context, text, Toast.LENGTH_LONG).show();
            }
        });
    }


    public static int getIntSetting(Context mContext, String key, int defaultValue) {
        SharedPreferences pref = mContext.getSharedPreferences(mContext.getPackageName(), Activity.MODE_PRIVATE);
        return pref.getInt(key, defaultValue);
    }

    public static void setIntSetting(Context mContext, String key, int val) {
        SharedPreferences pref = mContext.getSharedPreferences(mContext.getPackageName(), Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, val);
        editor.commit();
    }

    public static void setLongSetting(Context mContext, String key, long val) {
        SharedPreferences pref = mContext.getSharedPreferences(mContext.getPackageName(), Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(key, val);
        editor.commit();
    }

    public static long getLongSetting(Context mContext, String key, long defaultValue) {
        SharedPreferences pref = mContext.getSharedPreferences(mContext.getPackageName(), Activity.MODE_PRIVATE);
        return pref.getLong(key, defaultValue);
    }

    public static String getStringSetting(Context mContext, String key, String defaultValue) {
        SharedPreferences pref = mContext.getSharedPreferences(mContext.getPackageName(), Activity.MODE_PRIVATE);
        return pref.getString(key, defaultValue);
    }

    public static void setStringSetting(Context mContext, String key, String val) {
        SharedPreferences pref = mContext.getSharedPreferences(mContext.getPackageName(), Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, val);
        editor.commit();
    }

    public static boolean getBooleanSetting(Context mContext, String key, Boolean defaultValue) {
        SharedPreferences pref = mContext.getSharedPreferences(mContext.getPackageName(), Activity.MODE_PRIVATE);
        return pref.getBoolean(key, defaultValue);
    }

    public static void setBooleanSetting(Context mContext, String key, boolean val) {
        SharedPreferences pref = mContext.getSharedPreferences(mContext.getPackageName(), Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, val);
        editor.commit();
    }

}

