2
https://raw.githubusercontent.com/LakshyaKhatri/Lifetime/master/app/src/main/java/com/compiletales/lifetime/SetTimerFragment.java
package com.compiletales.lifetime;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.shawnlin.numberpicker.NumberPicker;

import java.util.Date;

import static com.compiletales.lifetime.MainActivity.DESTINATION_TIME_KEY;
import static com.compiletales.lifetime.MainActivity.SHARED_PREFERENCE_NAME;


public class SetTimerFragment extends Fragment {

    Long countdownHrs;
    Integer countdownMins;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_set_timer, container, false);

        final Button setTimerButton = view.findViewById(R.id.start_timer_button);
        final NumberPicker ones = view.findViewById(R.id.ones_number_picker);
        final NumberPicker tens = view.findViewById(R.id.tens_number_picker);
        final NumberPicker hundred = view.findViewById(R.id.hundred_number_picker);
        final NumberPicker thousand = view.findViewById(R.id.thousand_number_picker);
        final NumberPicker tenThousand = view.findViewById(R.id.ten_thousand_number_picker);
        final NumberPicker lakh = view.findViewById(R.id.lakh_number_picker);
        final NumberPicker tenLakh = view.findViewById(R.id.ten_lakh_number_picker);
        final NumberPicker crore = view.findViewById(R.id.crore_number_picker);
        final NumberPicker minutesNumberPicker = view.findViewById(R.id.minutes_number_picker);

        setTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    countdownHrs = Long.parseLong( "" + crore.getValue()
                            + "" + tenLakh.getValue()
                            + "" + lakh.getValue()
                            + "" + tenThousand.getValue()
                            + "" + thousand.getValue()
                            + "" + hundred.getValue()
                            + "" + tens.getValue()
                            + "" + ones.getValue()
                    );

                    countdownMins = Integer.parseInt(minutesNumberPicker.getValue() + "");

                } catch(Exception e){
                    countdownHrs = Long.parseLong("0");
                    countdownMins = Integer.parseInt("0");
                }

                long currentTime = new Date().getTime();
                long countdownTime = (countdownHrs * 60 * 60 * 1000) + (countdownMins * 60 * 1000);
                long destinationTime = currentTime + countdownTime;

                if (destinationTime <= currentTime) {
                    Toast.makeText(getActivity(), "Enter valid time.", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor spEditor = getActivity().getSharedPreferences(SHARED_PREFERENCE_NAME, 0).edit();
                    spEditor.putLong(DESTINATION_TIME_KEY, destinationTime);
                    spEditor.apply();

                    Toast.makeText(getActivity(), "Countdown started", Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container_frame_layout, new ShowTimerFragment()).commit();
                }
            }
        });
        return view;
    }
}
