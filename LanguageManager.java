package com.example.machine_learnnig.language;



import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LanguageManager {

    Context context;

    public LanguageManager(Context context) {
        this.context = context;
    }


       public void showLanguageDialog() {
        /*
         *   language_list a map of position and language
         * ex: 1= ab , 2= bn, etc
         * */
        Map<Integer, String> language_list = pos_val();
        /*
         *  languageList a list of language
         * ex: "arbic","english",etc
         * */
        String[] languageList = languageList();
        /*
         *   defultPosition give the defulat position of language  if not any sore any language
         *  and to sohow selected in the dialog option
         * */
        int defaultPosition = defultPosition(language().get("en"), languageList);
        SharedPreferences preferences = context.getSharedPreferences("languageSetting",
                Activity.MODE_PRIVATE);
        /*
         * whcich language selected by the user that position in the language_list
         * */
        int pos = preferences.getInt("position", defaultPosition);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Language");
        builder.setSingleChoiceItems(languageList, pos,
                (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        /*
                        * setLanguage is that fucntion to set language in the app
                        * */
                        setLanguage(language_list.get(position), position);
                        ((Activity) context).recreate();
//                        Log.d("rammm", "onClick: " + language_list.get(position) + " " + position);
                        dialog.dismiss();

                    }
                });
         /*
         * on click on the close button close the dialog
         * */
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        /*
         * create the dialog and show it
         * */
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    /*
     * setLanguage is that fucntion to set language in the app
     * */

   

    public void setLanguage(String language, int i) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
       context.getResources().updateConfiguration(configuration,
               context.getResources().getDisplayMetrics());

//Save language to SharedPreferences
        SharedPreferences.Editor editor =
                context.getSharedPreferences("languageSetting", ((Activity) context).MODE_PRIVATE).edit();
        editor.putString("myLang", language);
        editor.putInt("position", i);
        editor.apply();
    }

    public void lodeLanguage() {
        /*
         * data get from the SharedPreferences
         * */
        SharedPreferences preferences = context.getSharedPreferences("languageSetting",
                ((Activity) context).MODE_PRIVATE);

        String language = preferences.getString("myLang", "en");
        String[] languageList = languageList();
        int defaultPosition = defultPosition(language().get("en"), languageList);
        int position = preferences.getInt("position", defaultPosition);
        setLanguage(language, position);

    }

    public Map<String, String> language() {
        Map<String, String> languages = new HashMap<>();
        languages.put("en", "English");
        languages.put("ar", "عربي");
        languages.put("bn", "বাংলা");
        languages.put("de", "Deutsch");
        languages.put("es", "Español");
        languages.put("fr", "Français");
        languages.put("hi", "हिंदी");
        languages.put("it", "Italiano");
        languages.put("zh", "中國人");

        return languages;
    }

    public Map<Integer, String> pos_val() {
        Map<Integer, String> pos_key = new HashMap<>();
        Map<String, String> languages = new HashMap<>();
        languages = language();
        int i = 0;
        for (String languageCode : languages.keySet()) {
            pos_key.put(i, languageCode);
            i++;
        }
        return pos_key;
    }

    public String[] languageList() {
        Map<String, String> languages = new HashMap<>();
        languages = language();
        String[] languageList = new String[languages.size()];
        int i = 0;
        for (String languageCode : languages.keySet()) {
            languageList[i] = languages.get(languageCode);
            i++;
        }
        return languageList;
    }

    public int defultPosition(String language, String[] languageList) {
        int defaultPosition = 0;
        for (int i = 0; i < languageList.length; i++) {
            if (languageList[i].equals(language)) {
                defaultPosition = i;
                break;
            }
        }
        return defaultPosition;
    }

}
