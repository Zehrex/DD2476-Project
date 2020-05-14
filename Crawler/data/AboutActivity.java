9
https://raw.githubusercontent.com/swesust/covid-19-help-support-bd/master/Covid19Shahajjo/app/src/main/java/com/example/covid19shahajjo/activities/AboutActivity.java
package com.example.covid19shahajjo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.covid19shahajjo.R;
import com.example.covid19shahajjo.utils.Enums;
import com.example.covid19shahajjo.utils.SharedStorge;

public class AboutActivity extends AppCompatActivity {
    private TextView mesaage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        mesaage = findViewById(R.id.user_message);
        setText();
    }

    private void setUerPreferableTitle(){
        Enums.Language language = SharedStorge.getUserLanguage(this);
        if(language == Enums.Language.BD){
            setTitle("শর্তাবলী");
        }else{
            setTitle("About");
        }
    }

    public void setText(){
        Enums.Language language = SharedStorge.getUserLanguage(this);
        if(language == Enums.Language.EN){
            mesaage.setText(R.string.message_for_user_eng);
        }else{
            mesaage.setText(R.string.message_for_user_ban);
        }
    }
}
