package net.bigmachini.challange.journalapp;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

public class App extends Application {
    private static App appInstance = new App();
    private static final int DAY_IN_MILLISECONDS = 1000 * 60 * 60 * 24;
    private static final int DB_VERSION = 1;
    private Gson gson;

    public static App getInstance() {
        return appInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .create();
    }



    @Override
    public void onTerminate() {
        super.onTerminate();
    }




    public Gson getGson() {
        return gson;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        //Glide.get(this).clearMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        //Glide.get(this).trimMemory(level);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}