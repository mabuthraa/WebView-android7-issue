package sample.com.apipas.webviewapp;

import android.app.Application;
import android.content.res.Configuration;

import java.util.Locale;


public class MyApp extends Application {
    private static MyApp sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        switchToCzLocale();
        sApplication = this;
    }

    public static MyApp getApplication() {
        return sApplication;
    }

    public void switchToCzLocale() {
        Locale mLocale = new Locale("cs","CZ");
        Configuration config = getBaseContext().getResources()
                .getConfiguration();
        Locale.setDefault(mLocale);
        config.setLocale(mLocale);
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }
}
