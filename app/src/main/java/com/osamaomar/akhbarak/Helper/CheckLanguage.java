package com.osamaomar.akhbarak.Helper;

import android.content.Context;
import android.content.res.Configuration;

import java.text.Bidi;
import java.util.Locale;

public class CheckLanguage {
    Context context;
    private String language = "ar";

    public CheckLanguage(Context context) {
        this.context = context;
        checkLanguage(language, context);
    }

    public void checkLanguage(String language, Context context) {

        switch (language) {
            case "ar":
                setRTL(context);
                break;
            case "en":
                setLTR(context);
                break;
            default:
                setLTR(context);
                break;
        }
    }

    //
    public void setRTL(Context context) {
        String languageToLoad = "ar"; // rtl language Arabic
//        setLanguage(languageToLoad);
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config,
                context.getResources().getDisplayMetrics());
        //layout direction
        Bidi b = new Bidi(languageToLoad, Bidi.DIRECTION_DEFAULT_RIGHT_TO_LEFT);
        b.isRightToLeft();
//        this.setContentView(R.layout.activity_home);

    }

    public void setLTR(Context context) {
        String languageToLoad = "en"; // ltr language English
//        setLanguage(languageToLoad);
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config,
                context.getResources().getDisplayMetrics());
        //layout direction
        Bidi b = new Bidi(languageToLoad, Bidi.DIRECTION_DEFAULT_LEFT_TO_RIGHT);
        b.isLeftToRight();
//        this.setContentView(R.layout.activity_home);
    }
}
