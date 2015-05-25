package com.soft.grecio.reader;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
/**
 * Created by grecio on 5/2/2015.
 */
public class DefineRuta extends PreferenceActivity {
    @Override protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias);


    }
}
